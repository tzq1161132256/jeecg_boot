package org.jeecg.modules.business.comm.auth;

public class AppSiganatureCredentials implements Credentials {


	/**
	 *
	 */
	private static final long serialVersionUID = -8803627614582645095L;

	private String appid;

	private String appSiganature;

	private long timestamp;

	private String data;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getAppSiganature() {
		return appSiganature;
	}

	public void setAppSiganature(String appSiganature) {
		this.appSiganature = appSiganature;
	}

	public AppSiganatureCredentials(String appid, String appSiganature, long timestamp, String data){
		this.appid=appid;
		this.appSiganature=appSiganature;
		this.timestamp=timestamp;
		this.data=data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


    public AppSiganatureCredentials(){

    }



}
