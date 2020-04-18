package com.yhzn.model.storehouse;

/**
 * 入库管理对象
 * @author liany
 *
 */
public class EntryStock extends EntryStockModel{
	
	//创建时间str
	private String createDateStr;
	//期望入库时间str
	private String reachDateStr;
	//入库完成时间str
	private String finishDateStr;
	//审核时间str
	private String checkDateStr;
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getReachDateStr() {
		return reachDateStr;
	}
	public void setReachDateStr(String reachDateStr) {
		this.reachDateStr = reachDateStr;
	}
	public String getFinishDateStr() {
		return finishDateStr;
	}
	public void setFinishDateStr(String finishDateStr) {
		this.finishDateStr = finishDateStr;
	}
	public String getCheckDateStr() {
		return checkDateStr;
	}
	public void setCheckDateStr(String checkDateStr) {
		this.checkDateStr = checkDateStr;
	}
	
}
