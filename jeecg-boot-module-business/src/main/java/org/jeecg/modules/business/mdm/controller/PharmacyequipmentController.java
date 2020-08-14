package org.jeecg.modules.business.mdm.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.DictModel;
import org.jeecg.modules.business.mdm.service.IEquipmentapplyService;
import org.jeecg.modules.business.mdm.service.IPosapplyService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment4project;
import org.jeecg.modules.business.mdm.entity.Pharmacyequipment;
import org.jeecg.modules.business.mdm.vo.PharmacyequipmentPage;
import org.jeecg.modules.business.mdm.service.IPharmacyequipmentService;
import org.jeecg.modules.business.mdm.service.IPharmacyequipment4projectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 药房pos机pad协议管理
 * @Author: jeecg-boot
 * @Date:   2019-08-22
 * @Version: V1.0
 */
@RestController
@RequestMapping("/mdm/pharmacyequipment")
@Slf4j
public class PharmacyequipmentController {
	@Autowired
	private IPharmacyequipmentService pharmacyequipmentService;
	@Autowired
	private IPharmacyequipment4projectService pharmacyequipment4projectService;

	 @Autowired
	 private IPosapplyService posapplyService;

	 @Autowired
	 private IEquipmentapplyService equipmentapplyService;

	 @Autowired
	 private ISysBaseAPI sysBaseAPI;
	/**
	  * 分页列表查询
	 * @param pharmacyequipment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<Pharmacyequipment>> queryPageList(Pharmacyequipment pharmacyequipment,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Pharmacyequipment>> result = new Result<IPage<Pharmacyequipment>>();
		QueryWrapper<Pharmacyequipment> queryWrapper = QueryGenerator.initQueryWrapper(pharmacyequipment, req.getParameterMap());
		Page<Pharmacyequipment> page = new Page<Pharmacyequipment>(pageNo, pageSize);
		IPage<Pharmacyequipment> pageList = pharmacyequipmentService.page(page, queryWrapper);
		String drugstoreid = "";
		QueryWrapper queryWrapper1 = new QueryWrapper();
		QueryWrapper queryWrapper2 = new QueryWrapper();
		int countPosapply = 0;
		int countPadapply = 0;
		int countProject = 0;
		for(Pharmacyequipment item : pageList.getRecords() ) {
			drugstoreid = item.getPharmacyid();
			queryWrapper1 = new QueryWrapper();
			queryWrapper1.eq("drugstoreid",drugstoreid);
			countPosapply = posapplyService.count(queryWrapper1);
			countPadapply = equipmentapplyService.count(queryWrapper1);
			LambdaQueryWrapper<Pharmacyequipment4project> query = new LambdaQueryWrapper<Pharmacyequipment4project>();
			query.eq(Pharmacyequipment4project::getPharmacyequipmentid,item.getId() );
			countProject = pharmacyequipment4projectService.count(query);
			item.setCountPadapply(countPadapply);
			item.setCountPosapply(countPosapply);
			item.setCountProject(countProject);
		}
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param pharmacyequipmentPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<Pharmacyequipment> add(@RequestBody PharmacyequipmentPage pharmacyequipmentPage) {
		Result<Pharmacyequipment> result = new Result<Pharmacyequipment>();
		try {
			Pharmacyequipment pharmacyequipment = new Pharmacyequipment();
			BeanUtils.copyProperties(pharmacyequipmentPage, pharmacyequipment);
			
			pharmacyequipmentService.saveMain(pharmacyequipment, pharmacyequipmentPage.getPharmacyequipment4projectList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param pharmacyequipmentPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<Pharmacyequipment> edit(@RequestBody PharmacyequipmentPage pharmacyequipmentPage) {
		Result<Pharmacyequipment> result = new Result<Pharmacyequipment>();
		Pharmacyequipment pharmacyequipment = new Pharmacyequipment();
		BeanUtils.copyProperties(pharmacyequipmentPage, pharmacyequipment);
		Pharmacyequipment pharmacyequipmentEntity = pharmacyequipmentService.getById(pharmacyequipment.getId());
		if(pharmacyequipmentEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = pharmacyequipmentService.updateById(pharmacyequipment);
			pharmacyequipmentService.updateMain(pharmacyequipment, pharmacyequipmentPage.getPharmacyequipment4projectList());
			result.success("修改成功!");
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<Pharmacyequipment> delete(@RequestParam(name="id",required=true) String id) {
		Result<Pharmacyequipment> result = new Result<Pharmacyequipment>();
		Pharmacyequipment pharmacyequipment = pharmacyequipmentService.getById(id);
		if(pharmacyequipment==null) {
			result.error500("未找到对应实体");
		}else {
			pharmacyequipmentService.delMain(id);
			result.success("删除成功!");
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<Pharmacyequipment> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Pharmacyequipment> result = new Result<Pharmacyequipment>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.pharmacyequipmentService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<Pharmacyequipment> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Pharmacyequipment> result = new Result<Pharmacyequipment>();
		Pharmacyequipment pharmacyequipment = pharmacyequipmentService.getById(id);
		if(pharmacyequipment==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(pharmacyequipment);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryPharmacyequipment4projectByMainId")
	public Result<List<Pharmacyequipment4project>> queryPharmacyequipment4projectListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<Pharmacyequipment4project>> result = new Result<List<Pharmacyequipment4project>>();
		List<Pharmacyequipment4project> pharmacyequipment4projectList = pharmacyequipment4projectService.selectByMainId(id);
		result.setResult(pharmacyequipment4projectList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Pharmacyequipment> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Pharmacyequipment pharmacyequipment = JSON.parseObject(deString, Pharmacyequipment.class);
              queryWrapper = QueryGenerator.initQueryWrapper(pharmacyequipment, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<PharmacyequipmentPage> pageList = new ArrayList<PharmacyequipmentPage>();
      List<Pharmacyequipment> pharmacyequipmentList = pharmacyequipmentService.list(queryWrapper);
      for (Pharmacyequipment pharmacyequipment : pharmacyequipmentList) {
          PharmacyequipmentPage vo = new PharmacyequipmentPage();
          BeanUtils.copyProperties(pharmacyequipment, vo);
          List<Pharmacyequipment4project> pharmacyequipment4projectList = pharmacyequipment4projectService.selectByMainId(pharmacyequipment.getId());
          vo.setPharmacyequipment4projectList(pharmacyequipment4projectList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "药房pos机pad协议管理列表");
      mv.addObject(NormalExcelConstants.CLASS, PharmacyequipmentPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("药房pos机pad协议管理列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<PharmacyequipmentPage> list = ExcelImportUtil.importExcel(file.getInputStream(), PharmacyequipmentPage.class, params);
              for (PharmacyequipmentPage page : list) {
                  Pharmacyequipment po = new Pharmacyequipment();
                  BeanUtils.copyProperties(page, po);
                  pharmacyequipmentService.saveMain(po, page.getPharmacyequipment4projectList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }


	 /**
	  * 分页列表查询
	  * @param pharmacyequipment
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @GetMapping(value = "/listDicItem/{dictCode}")
	 public Result<IPage<DictModel>> queryDicItemPageList(@PathVariable String dictCode,
														   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
														   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
														   HttpServletRequest req) {
		 Result<IPage<DictModel>> result = new Result<IPage<DictModel>>();
		 Page<DictModel> page = new Page<DictModel>(pageNo, pageSize);
		 IPage<DictModel> pageList = sysBaseAPI.selectDictModelPage(page,dictCode);
		 result.setSuccess(true);
		 result.setResult(pageList);
		 return result;
	 }
}
