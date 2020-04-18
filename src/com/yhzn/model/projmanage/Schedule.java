package com.yhzn.model.projmanage;

/**
 * 进度管理对象
 * @author liany
 */
public class Schedule  extends ScheduleModel{

	//创建时间str
	private String createDateStr;
	//开始时间str
	private String startDateStr;
	//结束时间str
	private String endDateStr;
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
}
