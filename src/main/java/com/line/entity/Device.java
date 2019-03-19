package com.line.entity;

public class Device {

	
	private String deviceName;
	
	private String deviceNo;
	
	private String failType;
	
	private String time;
	
	private String result;
	
	private String nomarlUrl;
	
	private String errorUrl;
	
	private String baseurl = "http://127.0.0.1:7001/img/";
	
	public Device(){
		deviceName = "";
		deviceNo = "";
		failType = "";
		time = "";
		result = "";
		nomarlUrl = baseurl + "BKLADJ_C.jpg";
		errorUrl = baseurl + "BKLADJ_C.jpg";
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getFailType() {
		return failType;
	}

	public void setFailType(String failType) {
		this.failType = failType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getNomarlUrl() {
		return nomarlUrl;
	}

	public void setNomarlUrl(String nomarlUrl) {
		this.nomarlUrl = nomarlUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}

	@Override
	public String toString() {
		return "Device [deviceName=" + deviceName + ", deviceNo=" + deviceNo
				+ ", failType=" + failType + ", time=" + time + ", result="
				+ result + "]";
	}
	
	
}
