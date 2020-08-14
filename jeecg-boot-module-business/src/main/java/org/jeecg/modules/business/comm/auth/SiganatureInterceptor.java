package org.jeecg.modules.business.comm.auth;


import org.apache.commons.lang.StringUtils;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.modules.business.comm.auth.utils.AppSiganatureUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class SiganatureInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(SiganatureInterceptor.class);
	
    private final String appidHeader ="ACCESS_APPID";
	private final String timestampHeader ="ACCESS_TIMESTAMP";
	private final String siganatureHeader ="ACCESS_SIGANATURE";

	@Value("${siganature.appSecret}")
	private String appSecret;

	public AppSiganatureCredentials validateCredentials(HttpServletRequest request) throws Exception {
		Credentials credentials =retrieveFromRequest(request);
		if(credentials==null){
			throw new JeecgBootException("凭据为空");
		}
		AppSiganatureCredentials appSiganatureCredentials = (AppSiganatureCredentials) credentials;
		//TODO 读取伙伴ID
      //  String appSecret= authConfig.getDataKey();//partnerService.queryPrivateKey(appSiganatureCredentials.getAppid());

        if(appSecret==null){
        	throw new JeecgBootException("appid不存在或appsecret为空");
        }
		boolean canAccess= AppSiganatureUtils.validateSiganature(appSiganatureCredentials.getData(), appSiganatureCredentials.getAppid(), appSecret, appSiganatureCredentials.getTimestamp(), appSiganatureCredentials.getAppSiganature());
		if (canAccess) {
			return appSiganatureCredentials;
		} else {
			throw new JeecgBootException("认证失败");
		}
	}

	private Credentials retrieveFromRequest(HttpServletRequest request) {
		String appid = request.getHeader(this.appidHeader);
		String siganature = request.getHeader(this.siganatureHeader);
		String timestamp = request.getHeader(this.timestampHeader);

		if(StringUtils.isBlank(appid)|| StringUtils.isBlank(siganature)|| StringUtils.isBlank(timestamp)){
			return null;
		}
		appid=appid.replace("\r", "");
		appid=appid.replace("\n", "");
        logger.info("get appid header:"+appid+ "  headerName:"+this.appidHeader);
		siganature=siganature.replace("\r", "");
		siganature=siganature.replace("\n", "");
        logger.info("get siganature header:"+siganature+ "  headerName:"+this.siganatureHeader);
		timestamp=timestamp.replace("\r", "");
		timestamp=timestamp.replace("\n", "");
        logger.info("get timestamp header:"+timestamp+ "  headerName:"+this.timestampHeader);
		String body;
		try {
			body = readBody(request);
            logger.info("get request body:"+body);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (!StringUtils.isBlank(appid)) {
			return new AppSiganatureCredentials(appid, siganature,
					Long.parseLong(timestamp), body);
		} else {
			return null;
		}
	}

	public String readBody(HttpServletRequest request) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
                (ServletInputStream) request.getInputStream()));) {
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		}



	}
}