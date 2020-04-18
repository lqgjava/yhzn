package com.yhzn.model.security;

/**
 * 日志管理类
 * @author liany
 *
 */
public class SysLog extends SysLogModule{

	//操作时间str
	private String logTimeStr;

	public String getLogTimeStr() {
		return logTimeStr;
	}

	public void setLogTimeStr(String logTimeStr) {
		this.logTimeStr = logTimeStr;
	}
}
