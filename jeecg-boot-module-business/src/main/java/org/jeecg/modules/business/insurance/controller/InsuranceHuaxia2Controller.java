package org.jeecg.modules.business.insurance.controller;

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
import org.jeecg.modules.business.insurance.entity.InsuranceHuaxia2;
import org.jeecg.modules.business.insurance.service.IInsuranceHuaxia2Service;
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
 * @Description: 华夏保险升级版
 * @Author: jeecg-boot
 * @Date:   2019-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="华夏保险升级版")
@RestController
@RequestMapping("/insurance/insuranceHuaxia2")
public class InsuranceHuaxia2Controller {
	@Autowired
	private IInsuranceHuaxia2Service insuranceHuaxia2Service;
	
	/**
	  * 分页列表查询
	 * @param insuranceHuaxia2
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "华夏保险升级版-分页列表查询")
	@ApiOperation(value="华夏保险升级版-分页列表查询", notes="华夏保险升级版-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<InsuranceHuaxia2>> queryPageList(InsuranceHuaxia2 insuranceHuaxia2,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<InsuranceHuaxia2>> result = new Result<IPage<InsuranceHuaxia2>>();
		QueryWrapper<InsuranceHuaxia2> queryWrapper = QueryGenerator.initQueryWrapper(insuranceHuaxia2, req.getParameterMap());
		Page<InsuranceHuaxia2> page = new Page<InsuranceHuaxia2>(pageNo, pageSize);
		IPage<InsuranceHuaxia2> pageList = insuranceHuaxia2Service.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param insuranceHuaxia2
	 * @return
	 */
	@AutoLog(value = "华夏保险升级版-添加")
	@ApiOperation(value="华夏保险升级版-添加", notes="华夏保险升级版-添加")
	@PostMapping(value = "/add")
	public Result<InsuranceHuaxia2> add(@RequestBody InsuranceHuaxia2 insuranceHuaxia2) {
		Result<InsuranceHuaxia2> result = new Result<InsuranceHuaxia2>();
		try {
			insuranceHuaxia2Service.save(insuranceHuaxia2);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param insuranceHuaxia2
	 * @return
	 */
	@AutoLog(value = "华夏保险升级版-编辑")
	@ApiOperation(value="华夏保险升级版-编辑", notes="华夏保险升级版-编辑")
	@PutMapping(value = "/edit")
	public Result<InsuranceHuaxia2> edit(@RequestBody InsuranceHuaxia2 insuranceHuaxia2) {
		Result<InsuranceHuaxia2> result = new Result<InsuranceHuaxia2>();
		InsuranceHuaxia2 insuranceHuaxia2Entity = insuranceHuaxia2Service.getById(insuranceHuaxia2.getId());
		if(insuranceHuaxia2Entity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = insuranceHuaxia2Service.updateById(insuranceHuaxia2);
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
	@AutoLog(value = "华夏保险升级版-通过id删除")
	@ApiOperation(value="华夏保险升级版-通过id删除", notes="华夏保险升级版-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<InsuranceHuaxia2> delete(@RequestParam(name="id",required=true) String id) {
		Result<InsuranceHuaxia2> result = new Result<InsuranceHuaxia2>();
		InsuranceHuaxia2 insuranceHuaxia2 = insuranceHuaxia2Service.getById(id);
		if(insuranceHuaxia2==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = insuranceHuaxia2Service.removeById(id);
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
	@AutoLog(value = "华夏保险升级版-批量删除")
	@ApiOperation(value="华夏保险升级版-批量删除", notes="华夏保险升级版-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<InsuranceHuaxia2> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<InsuranceHuaxia2> result = new Result<InsuranceHuaxia2>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.insuranceHuaxia2Service.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "华夏保险升级版-通过id查询")
	@ApiOperation(value="华夏保险升级版-通过id查询", notes="华夏保险升级版-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<InsuranceHuaxia2> queryById(@RequestParam(name="id",required=true) String id) {
		Result<InsuranceHuaxia2> result = new Result<InsuranceHuaxia2>();
		InsuranceHuaxia2 insuranceHuaxia2 = insuranceHuaxia2Service.getById(id);
		if(insuranceHuaxia2==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(insuranceHuaxia2);
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
      QueryWrapper<InsuranceHuaxia2> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              InsuranceHuaxia2 insuranceHuaxia2 = JSON.parseObject(deString, InsuranceHuaxia2.class);
              queryWrapper = QueryGenerator.initQueryWrapper(insuranceHuaxia2, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<InsuranceHuaxia2> pageList = insuranceHuaxia2Service.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "华夏保险升级版列表");
      mv.addObject(NormalExcelConstants.CLASS, InsuranceHuaxia2.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("华夏保险升级版列表数据", "导出人:Jeecg", "导出信息"));
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
              List<InsuranceHuaxia2> listInsuranceHuaxia2s = ExcelImportUtil.importExcel(file.getInputStream(), InsuranceHuaxia2.class, params);
              for (InsuranceHuaxia2 insuranceHuaxia2Excel : listInsuranceHuaxia2s) {
                  insuranceHuaxia2Service.save(insuranceHuaxia2Excel);
              }
              return Result.ok("文件导入成功！数据行数:" + listInsuranceHuaxia2s.size());
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
