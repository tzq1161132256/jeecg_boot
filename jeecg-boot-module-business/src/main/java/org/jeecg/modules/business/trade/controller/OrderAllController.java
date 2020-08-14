package org.jeecg.modules.business.trade.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.business.trade.entity.OrderdetailAll;
import org.jeecg.modules.business.trade.entity.OrderAll;
import org.jeecg.modules.business.trade.vo.OrderAllPage;
import org.jeecg.modules.business.trade.service.IOrderAllService;
import org.jeecg.modules.business.trade.service.IOrderdetailAllService;
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
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-31
 * @Version: V1.0
 */
@RestController
@RequestMapping("/trade/orderAll")
@Slf4j
public class OrderAllController {
	@Autowired
	private IOrderAllService orderAllService;
	@Autowired
	private IOrderdetailAllService orderdetailAllService;
	
	/**
	  * 分页列表查询
	 * @param orderAll
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<OrderAll>> queryPageList(OrderAll orderAll,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<OrderAll>> result = new Result<IPage<OrderAll>>();
		QueryWrapper<OrderAll> queryWrapper = QueryGenerator.initQueryWrapper(orderAll, req.getParameterMap());
		Page<OrderAll> page = new Page<OrderAll>(pageNo, pageSize);
		IPage<OrderAll> pageList = orderAllService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param orderAllPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<OrderAll> add(@RequestBody OrderAllPage orderAllPage) {
		Result<OrderAll> result = new Result<OrderAll>();
		try {
			OrderAll orderAll = new OrderAll();
			BeanUtils.copyProperties(orderAllPage, orderAll);
			
			orderAllService.saveMain(orderAll, orderAllPage.getOrderdetailAllList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param orderAllPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<OrderAll> edit(@RequestBody OrderAllPage orderAllPage) {
		Result<OrderAll> result = new Result<OrderAll>();
		OrderAll orderAll = new OrderAll();
		BeanUtils.copyProperties(orderAllPage, orderAll);
		OrderAll orderAllEntity = orderAllService.getById(orderAll.getId());
		if(orderAllEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = orderAllService.updateById(orderAll);
			orderAllService.updateMain(orderAll, orderAllPage.getOrderdetailAllList());
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
	public Result<OrderAll> delete(@RequestParam(name="id",required=true) String id) {
		Result<OrderAll> result = new Result<OrderAll>();
		OrderAll orderAll = orderAllService.getById(id);
		if(orderAll==null) {
			result.error500("未找到对应实体");
		}else {
			orderAllService.delMain(id);
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
	public Result<OrderAll> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<OrderAll> result = new Result<OrderAll>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.orderAllService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<OrderAll> queryById(@RequestParam(name="id",required=true) String id) {
		Result<OrderAll> result = new Result<OrderAll>();
		OrderAll orderAll = orderAllService.getById(id);
		if(orderAll==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(orderAll);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryOrderdetailAllByMainId")
	public Result<List<OrderdetailAll>> queryOrderdetailAllListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<OrderdetailAll>> result = new Result<List<OrderdetailAll>>();
		List<OrderdetailAll> orderdetailAllList = orderdetailAllService.selectByMainId(id);
		result.setResult(orderdetailAllList);
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
      QueryWrapper<OrderAll> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              OrderAll orderAll = JSON.parseObject(deString, OrderAll.class);
              queryWrapper = QueryGenerator.initQueryWrapper(orderAll, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<OrderAllPage> pageList = new ArrayList<OrderAllPage>();
      List<OrderAll> orderAllList = orderAllService.list(queryWrapper);
      for (OrderAll orderAll : orderAllList) {
          OrderAllPage vo = new OrderAllPage();
          BeanUtils.copyProperties(orderAll, vo);
          List<OrderdetailAll> orderdetailAllList = orderdetailAllService.selectByMainId(orderAll.getId());
          vo.setOrderdetailAllList(orderdetailAllList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "订单列表");
      mv.addObject(NormalExcelConstants.CLASS, OrderAllPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单列表数据", "导出人:Jeecg", "导出信息"));
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
              List<OrderAllPage> list = ExcelImportUtil.importExcel(file.getInputStream(), OrderAllPage.class, params);
              for (OrderAllPage page : list) {
                  OrderAll po = new OrderAll();
                  BeanUtils.copyProperties(page, po);
                  orderAllService.saveMain(po, page.getOrderdetailAllList());
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

}
