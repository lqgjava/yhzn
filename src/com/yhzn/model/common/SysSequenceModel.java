package com.yhzn.model.common;

import java.util.Date;

/**
 * 通用编号类model
 * @author liany
 *
 */
public class SysSequenceModel {

	//主键id
	private String id;
	//最大流水
	private String maxSeq;
	//表名
	private String tableName;
	//备注
	private String remark;
	//所属年月
	private String curDate;
	//创建时间
	private Date createDate;
	//创建人姓名
    private String createName;
    //修改时间
	private Date modifyDate;
	//修改人姓名
	private String modifyName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMaxSeq() {
		return maxSeq;
	}
	public void setMaxSeq(String maxSeq) {
		this.maxSeq = maxSeq;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public String getCurDate() {
		return curDate;
	}
	public void setCurDate(String curDate) {
		this.curDate = curDate;
	}
}
