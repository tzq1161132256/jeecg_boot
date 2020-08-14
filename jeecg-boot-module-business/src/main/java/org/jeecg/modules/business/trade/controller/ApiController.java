package org.jeecg.modules.business.trade.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.business.comm.auth.AppSiganatureCredentials;
import org.jeecg.modules.business.comm.auth.SecureValid;
import org.jeecg.modules.business.comm.auth.SiganatureInterceptor;
import org.jeecg.modules.business.comm.auth.utils.AesUtil;
import org.jeecg.modules.business.mdm.entity.*;
import org.jeecg.modules.business.mdm.entity.Dictionary;
import org.jeecg.modules.business.mdm.service.IDictionaryService;
import org.jeecg.modules.business.mdm.service.IDrugstore4projectService;
import org.jeecg.modules.business.trade.entity.ApiResult;
import org.jeecg.modules.business.trade.entity.Usercumulate;
import org.jeecg.modules.business.trade.entity.Userregistration;
import org.jeecg.modules.business.trade.service.IOrderAllService;
import org.jeecg.modules.business.trade.service.IOrderService;
import org.jeecg.modules.business.trade.service.IUsercumulateService;
import org.jeecg.modules.business.trade.service.IUserregistrationService;
import org.jeecg.modules.business.trade.vo.OrderPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * <p>
 * 菜单 前端控制器
 * </p>
 *
 * @author visa123
 * @since 2018-08-08
 */
@Slf4j
@Controller
@RequestMapping("/api")
public class ApiController {


    @Autowired
    private SiganatureInterceptor siganatureInterceptor;

    @Autowired
    IUserregistrationService userregistrationService;

    @Autowired
    private IUsercumulateService usercumulateService;

    @Autowired
    IOrderAllService orderAllService;

    @Autowired
    IDictionaryService dictionaryService;

    @Autowired
    IOrderService orderService;

@Autowired
    IDrugstore4projectService drugstore4projectService;

    @RequestMapping(value="/addUserregistration", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @SecureValid
    public Result addUserregistration(HttpServletRequest request, HttpServletResponse response, AppSiganatureCredentials credentials )  {
        Result result = new Result();
        String body = null;
        try{
            //创建一个  Gson对象

;
            Userregistration userregistration = JSON.parseObject(credentials.getData(), Userregistration.class);

            //调用方法存入数据库
            userregistrationService.processUserregistration(userregistration);

            Map map = new HashedMap();
            map.put("msg","success");

            result.setResult(map);
        }catch (Exception e){
            result.error500(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value="/addSubscriptionUser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @SecureValid
    public Result addSubscriptionUser(HttpServletRequest request, HttpServletResponse response, AppSiganatureCredentials credentials )  {
        Result result = new Result();

        try{
            Usercumulate model = JSON.parseObject(credentials.getData(), Usercumulate.class);
            usercumulateService.processUsercumulate(model);
            Map map = new HashedMap();
            map.put("msg","success");
            result.setResult(map);
        }catch (Exception e){
            log.error(e.getMessage());

            result.error500(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value="/addOrder", produces = "application/json;charset=UTF-8")
    @ResponseBody
    @SecureValid
    public ApiResult addOrder(HttpServletRequest request, HttpServletResponse response, AppSiganatureCredentials credentials )  {
        ApiResult result = new ApiResult();

        try{
          //  AppSiganatureCredentials credentials = siganatureInterceptor.validateCredentials(request);
           // Order order = JsonUtils.json2Object(credentials.getData(), Order.class);

            OrderPage order = JSON.parseObject(credentials.getData(), OrderPage.class);
            log.info("order="+order);

            order.setStatus("已完成");
            if("45".equals(order.getOrdertype()) || "30".equals(order.getOrdertype()) || "43".equals(order.getOrdertype())) {
                order.setOrdertype("送药到家");
            }else  {
                order.setOrdertype("到店购药");
            }
            order.setStatus("完成");
// 01-现金;02-刷卡;11-银联;12-网上银行;13-微信支付;14-支付宝支付;21-金融支付
            if("01".equals(order.getPaychannel())) {
                order.setPaychannel("现金");
            } else if("02".equals(order.getPaychannel())) {
                order.setPaychannel("刷卡");
            }else if("11".equals(order.getPaychannel())) {
                order.setPaychannel("银联");
            }else if("12".equals(order.getPaychannel())) {
                order.setPaychannel("网上银行");
            }else if("13".equals(order.getPaychannel())) {
                order.setPaychannel("微信支付");
            }else if("14".equals(order.getPaychannel())) {
                order.setPaychannel("支付宝支付");
            }else if("21".equals(order.getPaychannel())) {
                order.setPaychannel("金融支付");
            }



           // order.setOrderDetails(orderDTO.getOrderDetails());

            //敏感信息加密
            order.setPatientname(AesUtil.Encrypt(order.getPatientname(), AesUtil.SKEY));
            order.setPatientidcard(AesUtil.Encrypt(order.getPatientidcard(),AesUtil.SKEY));
            order.setPatientmobile(AesUtil.Encrypt(order.getPatientmobile(),AesUtil.SKEY));
            order.setBuyeridcard(AesUtil.Encrypt(order.getBuyeridcard(),AesUtil.SKEY));
            order.setBuyermobile(AesUtil.Encrypt(order.getBuyermobile(),AesUtil.SKEY));
            order.setBuyername(AesUtil.Encrypt(order.getBuyername(),AesUtil.SKEY));
            order.setConsigneename(AesUtil.Encrypt(order.getConsigneename(),AesUtil.SKEY));
            order.setConsigneemobile(AesUtil.Encrypt(order.getConsigneemobile(),AesUtil.SKEY));


            orderAllService.processOrderAll(order);
            Map map = new HashedMap();
            map.put("msg","success");
            map.put("returnid",order.getOrdercode());
            result.setResult(map);
        }catch (Exception e){
            log.error(e.getMessage());
            result.setErrorNo(1);
            result.setErrorInfo(e.getMessage());
        }
        return result;
    }

    @RequestMapping(value="/getOrder", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getOrder(HttpServletRequest request, HttpServletResponse response )  {
        String projectvalue  = request.getParameter("projectvalue");
        Result result = new Result();

        try{
            //  AppSiganatureCredentials credentials = siganatureInterceptor.validateCredentials(request);
            // Order order = JsonUtils.json2Object(credentials.getData(), Order.class);
            log.info("projectvalue="+projectvalue);
            String projectname = "";
            // 根据projectvalue获取projectname

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("type","projectName");
            queryWrapper.eq("value",projectvalue);

            Dictionary dictionary =  dictionaryService.getOne(queryWrapper);


            log.info("projectname="+dictionary.getName());
            HashMap map_1 = new HashMap();
            map_1.put("projectname",dictionary.getName());
            map_1.put("limit",5);
            // map_1.put("ordercode","1901180139383038");
            // order.setOrderDetails(orderDTO.getOrderDetails());
            List<Map> orders = orderService.getLimitOrder(map_1);


            result.setResult(orders);
        }catch (Exception e){
            log.error(e.getMessage());

            result.error500(e.getMessage());
        }
        return result;

    }

    @RequestMapping(value="/getNoSalesDrugstore", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getNoSalesDrugstore(HttpServletRequest request, HttpServletResponse response )  {
        String projectvalue  = request.getParameter("projectvalue");
        Result result = new Result();

        try{
            //  AppSiganatureCredentials credentials = siganatureInterceptor.validateCredentials(request);
            // Order order = JsonUtils.json2Object(credentials.getData(), Order.class);
            log.info("projectvalue="+projectvalue);
            String projectname = "";
            // 根据projectvalue获取projectname
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("type","projectName");
            queryWrapper.eq("value",projectvalue);

            Dictionary dictionary =  dictionaryService.getOne(queryWrapper);

            log.info("projectname="+dictionary.getName());
            HashMap map_1 = new HashMap();
            map_1.put("projectname",dictionary.getName());

            // order.setOrderDetails(orderDTO.getOrderDetails());
            List<Map> drugstores = drugstore4projectService.getNoSalesDrugstore(map_1);

            result.setResult(drugstores);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error500(e.getMessage());
        }
        return result;

    }

    @RequestMapping(value="/getOrder4marketing", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result getOrder4marketing(HttpServletRequest request, HttpServletResponse response )  {

        Result result = new Result();

        try{

            HashMap map_1 = new HashMap();

            List<Map> orders = orderService.getLimitOrder4marketing(map_1);

            result.setResult(orders);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error500(e.getMessage());
        }
        return result;

    }


}
