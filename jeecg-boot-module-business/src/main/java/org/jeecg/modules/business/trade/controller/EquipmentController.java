package org.jeecg.modules.business.trade.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.business.trade.entity.Equipment;
import org.jeecg.modules.business.trade.service.IEquipmentService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: ipad登录信息
 * @Author: jeecg-boot
 * @Date:   2019-08-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="ipad登录信息")
@RestController
@RequestMapping("/trade/equipment")
public class EquipmentController {
	@Autowired
	private IEquipmentService equipmentService;
	
	/**
	  * 分页列表查询
	 * @param equipment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "ipad登录信息-分页列表查询")
	@ApiOperation(value="ipad登录信息-分页列表查询", notes="ipad登录信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Equipment>> queryPageList(Equipment equipment,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Equipment>> result = new Result<IPage<Equipment>>();
		QueryWrapper<Equipment> queryWrapper = QueryGenerator.initQueryWrapper(equipment, req.getParameterMap());
		Page<Equipment> page = new Page<Equipment>(pageNo, pageSize);
		IPage<Equipment> pageList = equipmentService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param equipment
	 * @return
	 */
	@AutoLog(value = "ipad登录信息-添加")
	@ApiOperation(value="ipad登录信息-添加", notes="ipad登录信息-添加")
	@PostMapping(value = "/add")
	public Result<Equipment> add(@RequestBody Equipment equipment) {
		Result<Equipment> result = new Result<Equipment>();
		try {
			equipmentService.save(equipment);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param equipment
	 * @return
	 */
	@AutoLog(value = "ipad登录信息-编辑")
	@ApiOperation(value="ipad登录信息-编辑", notes="ipad登录信息-编辑")
	@PutMapping(value = "/edit")
	public Result<Equipment> edit(@RequestBody Equipment equipment) {
		Result<Equipment> result = new Result<Equipment>();
		Equipment equipmentEntity = equipmentService.getById(equipment.getId());
		if(equipmentEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = equipmentService.updateById(equipment);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ipad登录信息-通过id删除")
	@ApiOperation(value="ipad登录信息-通过id删除", notes="ipad登录信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<Equipment> delete(@RequestParam(name="id",required=true) String id) {
		Result<Equipment> result = new Result<Equipment>();
		Equipment equipment = equipmentService.getById(id);
		if(equipment==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = equipmentService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "ipad登录信息-批量删除")
	@ApiOperation(value="ipad登录信息-批量删除", notes="ipad登录信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Equipment> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Equipment> result = new Result<Equipment>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.equipmentService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "ipad登录信息-通过id查询")
	@ApiOperation(value="ipad登录信息-通过id查询", notes="ipad登录信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Equipment> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Equipment> result = new Result<Equipment>();
		Equipment equipment = equipmentService.getById(id);
		if(equipment==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(equipment);
			result.setSuccess(true);
		}
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
      QueryWrapper<Equipment> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Equipment equipment = JSON.parseObject(deString, Equipment.class);
              queryWrapper = QueryGenerator.initQueryWrapper(equipment, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Equipment> pageList = equipmentService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "ipad登录信息列表");
      mv.addObject(NormalExcelConstants.CLASS, Equipment.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("ipad登录信息列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Equipment> listEquipments = ExcelImportUtil.importExcel(file.getInputStream(), Equipment.class, params);
              for (Equipment equipmentExcel : listEquipments) {
                  equipmentService.save(equipmentExcel);
              }
              return Result.ok("文件导入成功！数据行数:" + listEquipments.size());
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

}
