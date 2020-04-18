package com.yhzn.model.storehouse;

import java.util.Date;

/**
 * 采购管理对象Model
 * @author liany
 *
 */
public class PurchaseModel {

	//ID
	private String id;
	//采购清单编号
	private String purNo;
	//采购单名称
	private String purName;
	//采购原因
	private String reason;
	//清单状态代码
	private String status;
	//采购人id
	private String userId;
	//备注
	private String remark;
	//删除标示
	private String deleteFlag;
	//创建时间
	private Date createDate;
	//创建人姓名
    private String createName;
    //修改时间
	private Date modifyDate;
	//修改人姓名
	private String modifyName;
	//备用字段1
	private String rev1;
	//备用字段2
	private String rev2;
	//备用字段3
	private String rev3;
	 //期望交付时间
	private Date reachDate;
	//不通过原因
	private String notPass;
	//审核人
	private String checkUser;
	//审核人ID
	private String checkUserId;
	//入库状态
	private String entryStatus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPurNo() {
		return purNo;
	}
	public void setPurNo(String purNo) {
		this.purNo = purNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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
	public String getRev1() {
		return rev1;
	}
	public void setRev1(String rev1) {
		this.rev1 = rev1;
	}
	public String getRev2() {
		return rev2;
	}
	public void setRev2(String rev2) {
		this.rev2 = rev2;
	}
	public String getRev3() {
		return rev3;
	}
	public void setRev3(String rev3) {
		this.rev3 = rev3;
	}
	public Date getReachDate() {
		return reachDate;
	}
	public void setReachDate(Date reachDate) {
		this.reachDate = reachDate;
	}
	public String getPurName() {
		return purName;
	}
	public void setPurName(String purName) {
		this.purName = purName;
	}
	public String getNotPass() {
		return notPass;
	}
	public void setNotPass(String notPass) {
		this.notPass = notPass;
	}
	public String getCheckUser() {
		return checkUser;
	}
	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}
	public String getCheckUserId() {
		return checkUserId;
	}
	public void setCheckUserId(String checkUserId) {
		this.checkUserId = checkUserId;
	}
	public String getEntryStatus() {
		return entryStatus;
	}
	public void setEntryStatus(String entryStatus) {
		this.entryStatus = entryStatus;
	}
	
}
