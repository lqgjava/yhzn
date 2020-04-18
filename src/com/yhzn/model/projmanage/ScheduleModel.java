package com.yhzn.model.projmanage;

import java.util.Date;

/**
 * 进度管理对象Model
 * @author liany
 */
public class ScheduleModel {
	
	//主键id
	private String id;
	//项目编号
	private String projNo;
	//项目名称
	private String projName;
	//项目类别
	private String projType;
	//联系人
	private String contacts;
	//电话
	private String phone;
	//单位名称
	private String unitName;
	//项目地址
	private String projAddr;
	//项目单位名称
	private String projUnitName;
	//项目状态
	private String projStatus;
	//项目情况
	private String projCondition;
	//项目责任人
	private String projDuty;
	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate;
	//施工人员
	private String builder;
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
	//甲方联系电话
	private String projTel;
	//甲方地址
	private String projAddress;
	//是否隐藏
	private String isHide;
	//合同编号
	private String contractNo;
	//合同名称
	private String contractName;
	//单位联系人职位
	private String projPosition;
	//项目单位联系人
	private String projContacts;
	private String projZhuangtai;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	public String getProjType() {
		return projType;
	}
	public void setProjType(String projType) {
		this.projType = projType;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProjAddr() {
		return projAddr;
	}
	public void setProjAddr(String projAddr) {
		this.projAddr = projAddr;
	}
	public String getProjUnitName() {
		return projUnitName;
	}
	public void setProjUnitName(String projUnitName) {
		this.projUnitName = projUnitName;
	}
	public String getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(String projStatus) {
		this.projStatus = projStatus;
	}
	public String getProjCondition() {
		return projCondition;
	}
	public void setProjCondition(String projCondition) {
		this.projCondition = projCondition;
	}
	public String getProjDuty() {
		return projDuty;
	}
	public void setProjDuty(String projDuty) {
		this.projDuty = projDuty;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getBuilder() {
		return builder;
	}
	public void setBuilder(String builder) {
		this.builder = builder;
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
	
	public String getProjTel() {
		return projTel;
	}
	public void setProjTel(String projTel) {
		this.projTel = projTel;
	}
	public String getProjAddress() {
		return projAddress;
	}
	public void setProjAddress(String projAddress) {
		this.projAddress = projAddress;
	}

	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getProjPosition() {
		return projPosition;
	}
	public void setProjPosition(String projPosition) {
		this.projPosition = projPosition;
	}
	public String getContractNo() {
		return contractNo;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getProjContacts() {
		return projContacts;
	}
	public void setProjContacts(String projContacts) {
		this.projContacts = projContacts;
	}
	public String getIsHide() {
		return isHide;
	}
	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}
	public String getProjZhuangtai() {
		return projZhuangtai;
	}
	public void setProjZhuangtai(String projZhuangtai) {
		this.projZhuangtai = projZhuangtai;
	}
	
}
