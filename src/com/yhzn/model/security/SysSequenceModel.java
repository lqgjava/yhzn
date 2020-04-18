package com.yhzn.model.security;

import java.util.Date;

/**
 * 系统流水model表
 */
public class SysSequenceModel {
	//id
	private String id;
	//序号最大值
    private String maxSeq;
    //表名
    private String tableName;
    //所属年月
    private String belongTime;
    //备注
  	private String remark;
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
	public String getBelongTime() {
		return belongTime;
	}
	public void setBelongTime(String belongTime) {
		this.belongTime = belongTime;
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
}
