package org.jeecg.modules.business.mdm.controller;

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
import org.jeecg.modules.business.mdm.entity.Posapply;
import org.jeecg.modules.business.mdm.service.IPosapplyService;
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
 * @Description: pos机领用记录
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="pos机领用记录")
@RestController
@RequestMapping("/mdm/posapply")
public class PosapplyController {
	@Autowired
	private IPosapplyService posapplyService;
	
	/**
	  * 分页列表查询
	 * @param posapply
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "pos机领用记录-分页列表查询")
	@ApiOperation(value="pos机领用记录-分页列表查询", notes="pos机领用记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Posapply>> queryPageList(Posapply posapply,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Posapply>> result = new Result<IPage<Posapply>>();
		QueryWrapper<Posapply> queryWrapper = QueryGenerator.initQueryWrapper(posapply, req.getParameterMap());
		Page<Posapply> page = new Page<Posapply>(pageNo, pageSize);
		IPage<Posapply> pageList = posapplyService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param posapply
	 * @return
	 */
	@AutoLog(value = "pos机领用记录-添加")
	@ApiOperation(value="pos机领用记录-添加", notes="pos机领用记录-添加")
	@PostMapping(value = "/add")
	public Result<Posapply> add(@RequestBody Posapply posapply) {
		Result<Posapply> result = new Result<Posapply>();
		try {
			posapplyService.save(posapply);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param posapply
	 * @return
	 */
	@AutoLog(value = "pos机领用记录-编辑")
	@ApiOperation(value="pos机领用记录-编辑", notes="pos机领用记录-编辑")
	@PutMapping(value = "/edit")
	public Result<Posapply> edit(@RequestBody Posapply posapply) {
		Result<Posapply> result = new Result<Posapply>();
		Posapply posapplyEntity = posapplyService.getById(posapply.getId());
		if(posapplyEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = posapplyService.updateById(posapply);
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
	@AutoLog(value = "pos机领用记录-通过id删除")
	@ApiOperation(value="pos机领用记录-通过id删除", notes="pos机领用记录-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<Posapply> delete(@RequestParam(name="id",required=true) String id) {
		Result<Posapply> result = new Result<Posapply>();
		Posapply posapply = posapplyService.getById(id);
		if(posapply==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = posapplyService.removeById(id);
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
	@AutoLog(value = "pos机领用记录-批量删除")
	@ApiOperation(value="pos机领用记录-批量删除", notes="pos机领用记录-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Posapply> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Posapply> result = new Result<Posapply>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.posapplyService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pos机领用记录-通过id查询")
	@ApiOperation(value="pos机领用记录-通过id查询", notes="pos机领用记录-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Posapply> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Posapply> result = new Result<Posapply>();
		Posapply posapply = posapplyService.getById(id);
		if(posapply==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(posapply);
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
      QueryWrapper<Posapply> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Posapply posapply = JSON.parseObject(deString, Posapply.class);
              queryWrapper = QueryGenerator.initQueryWrapper(posapply, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Posapply> pageList = posapplyService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "pos机领用记录列表");
      mv.addObject(NormalExcelConstants.CLASS, Posapply.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("pos机领用记录列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Posapply> listPosapplys = ExcelImportUtil.importExcel(file.getInputStream(), Posapply.class, params);
              for (Posapply posapplyExcel : listPosapplys) {
                  posapplyService.save(posapplyExcel);
              }
              return Result.ok("文件导入成功！数据行数:" + listPosapplys.size());
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
