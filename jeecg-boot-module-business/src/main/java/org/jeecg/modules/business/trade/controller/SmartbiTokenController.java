package org.jeecg.modules.business.trade.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: jeecg-boot-parent
 * @Package: org.jeecg.modules.message.controller
 * @ClassName: SmartbiTokenController
 * @Author: TZQ
 * @Description: 获取smartbi的token和ip给前端
 * @Date: 2019/9/12 10:10
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/smarbi/token")
public class SmartbiTokenController {
    @Value("${smartbi.url}")
    private String urlSmartbi;

    @RequestMapping(value = "/getSmarbiCookie", method = RequestMethod.GET)
    public Result<?> getSmarbiCookie(HttpServletRequest request) {
        Result<JSONObject> result = new Result<JSONObject>();
        try {
            String  smartbi_cookie = (String) request.getSession().getAttribute("smartbi_cookie");
            JSONObject json = new JSONObject();
            json.put("smartbi_cookie", smartbi_cookie);
            json.put("smartbi_url", urlSmartbi);
            result.setResult(json);
            result.success("获取成功");
        } catch (Exception e) {
            result.error500("获取失败:" + e.getMessage());
            log.error(e.getMessage(), e);
        }
        return result;
    }

}
