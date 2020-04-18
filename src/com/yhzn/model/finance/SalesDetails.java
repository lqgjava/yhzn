package com.yhzn.model.finance;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售合同详情表实体类
 * @author Liany
 */
public class SalesDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//主键
	private String id;
	//应收金额
	private String money;
	//已收金额
	private String acceptMoney;
	//创建人
	private String createName;
	//创建时间
	private Date createDate;
	//修改人
	private String modifyName;
	//修改时间
	private Date modifyDate;
	//删除标识
	private String deleteFlag;
	//收款时间
	private Date acceptDate;
	//应收金额ID  外键
	private String acceptId;
	//备用字段1
	private String rev1;
	//备用字段2
	private String rev2;
	//备用字段3
	private String rev3;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getAcceptMoney() {
		return acceptMoney;
	}
	public void setAcceptMoney(String acceptMoney) {
		this.acceptMoney = acceptMoney;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getModifyName() {
		return modifyName;
	}
	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}