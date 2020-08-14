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
import org.jeecg.modules.business.trade.entity.Postransaction;
import org.jeecg.modules.business.trade.service.IPostransactionService;
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
 * @Description: pos机交易信息
 * @Author: jeecg-boot
 * @Date:   2019-08-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="pos机交易信息")
@RestController
@RequestMapping("/trade/postransaction")
public class PostransactionController {
	@Autowired
	private IPostransactionService postransactionService;
	
	/**
	  * 分页列表查询
	 * @param postransaction
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "pos机交易信息-分页列表查询")
	@ApiOperation(value="pos机交易信息-分页列表查询", notes="pos机交易信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Postransaction>> queryPageList(Postransaction postransaction,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<Postransaction>> result = new Result<IPage<Postransaction>>();
		QueryWrapper<Postransaction> queryWrapper = QueryGenerator.initQueryWrapper(postransaction, req.getParameterMap());
		Page<Postransaction> page = new Page<Postransaction>(pageNo, pageSize);
		IPage<Postransaction> pageList = postransactionService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param postransaction
	 * @return
	 */
	@AutoLog(value = "pos机交易信息-添加")
	@ApiOperation(value="pos机交易信息-添加", notes="pos机交易信息-添加")
	@PostMapping(value = "/add")
	public Result<Postransaction> add(@RequestBody Postransaction postransaction) {
		Result<Postransaction> result = new Result<Postransaction>();
		try {
			postransactionService.save(postransaction);
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param postransaction
	 * @return
	 */
	@AutoLog(value = "pos机交易信息-编辑")
	@ApiOperation(value="pos机交易信息-编辑", notes="pos机交易信息-编辑")
	@PutMapping(value = "/edit")
	public Result<Postransaction> edit(@RequestBody Postransaction postransaction) {
		Result<Postransaction> result = new Result<Postransaction>();
		Postransaction postransactionEntity = postransactionService.getById(postransaction.getId());
		if(postransactionEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = postransactionService.updateById(postransaction);
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
	@AutoLog(value = "pos机交易信息-通过id删除")
	@ApiOperation(value="pos机交易信息-通过id删除", notes="pos机交易信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<Postransaction> delete(@RequestParam(name="id",required=true) String id) {
		Result<Postransaction> result = new Result<Postransaction>();
		Postransaction postransaction = postransactionService.getById(id);
		if(postransaction==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = postransactionService.removeById(id);
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
	@AutoLog(value = "pos机交易信息-批量删除")
	@ApiOperation(value="pos机交易信息-批量删除", notes="pos机交易信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<Postransaction> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<Postransaction> result = new Result<Postransaction>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.postransactionService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "pos机交易信息-通过id查询")
	@ApiOperation(value="pos机交易信息-通过id查询", notes="pos机交易信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Postransaction> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Postransaction> result = new Result<Postransaction>();
		Postransaction postransaction = postransactionService.getById(id);
		if(postransaction==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(postransaction);
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
      QueryWrapper<Postransaction> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Postransaction postransaction = JSON.parseObject(deString, Postransaction.class);
              queryWrapper = QueryGenerator.initQueryWrapper(postransaction, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Postransaction> pageList = postransactionService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "pos机交易信息列表");
      mv.addObject(NormalExcelConstants.CLASS, Postransaction.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("pos机交易信息列表数据", "导出人:Jeecg", "导出信息"));
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
              List<Postransaction> listPostransactions = ExcelImportUtil.importExcel(file.getInputStream(), Postransaction.class, params);
              for (Postransaction postransactionExcel : listPostransactions) {
                  postransactionService.save(postransactionExcel);
              }
              return Result.ok("文件导入成功！数据行数:" + listPostransactions.size());
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
