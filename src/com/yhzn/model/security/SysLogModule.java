package com.yhzn.model.security;

import java.util.Date;

/**
 * 日志类Module
 * @author liany
 *
 */
public class SysLogModule {

	//主键id
	private String id;
	//日志类型
	private String logType;
	//操作时间
	private Date logTime;
	//操作人
	private String logUser;
	//操作内容
	private String logContent;
	//操作人IP
	private String logIp;
	//操作方法
	private String logMethod;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getLogUser() {
		return logUser;
	}
	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public String getLogMethod() {
		return logMethod;
	}
	public void setLogMethod(String logMethod) {
		this.logMethod = logMethod;
	}
}
