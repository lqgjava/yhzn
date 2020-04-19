package com.yhzn.model.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 财务销售合同实体类
 * @author Liany
 */
public class SalesContract implements Serializable{
	private static final long serialVersionUID = 1L;
	//主键
	private String id;
	//客户ID 外键
	private String customerId;
	//客户名称
	private String customerName;
	//应收金额
	private Long money;
	//已收金额
	private BigDecimal acceptMoney;
	//备注
	private String remark;
	//创建人
	private String createName;
	//创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createDate;
	//修改人
	private String modifyName;
	//修改时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifyDate;
	//删除标识
	private String deleteFlag;
	//备用字段1
	private String rev1;
	//备用字段2
	private String rev2;
	//备用字段3
	private String rev3;
	//合同名称
	private String contractName;
	//合同编号
	private String contractNo;
	//签订时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date signDate;
	
	
	private List<SalesContract> salesContracts;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public BigDecimal getAcceptMoney() {
		return acceptMoney;
	}
	public void setAcceptMoney(BigDecimal acceptMoney) {
		this.acceptMoney = acceptMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<SalesContract> getSalesContracts() {
		return salesContracts;
	}
	public void setSalesContracts(List<SalesContract> salesContracts) {
		this.salesContracts = salesContracts;
	}
	
	
}