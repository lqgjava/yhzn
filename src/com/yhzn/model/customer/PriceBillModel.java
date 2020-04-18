package com.yhzn.model.customer;

import java.util.Date;

/**
 * 报价清单Model
 * @author liany
 *
 */
public class PriceBillModel {

	//ID
	private String id;
	//项目id
	private String projId;
	//项目编号
	private String projNo;
	//产品名称
	private String name;
	//规格
	private String standard;
	//型号
	private String model;
	//单位
	private String unit;
	//品牌
	private String brand;
	//单价
	private String unitPrice;
	//成本价
	private String costPrice;
	//总价
	private String totalPrice;
	//数量
	private String amount;
	//折扣
	private String discount;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProjId() {
		return projId;
	}
	public void setProjId(String projId) {
		this.projId = projId;
	}
	public String getProjNo() {
		return projNo;
	}
	public void setProjNo(String projNo) {
		this.projNo = projNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(String costPrice) {
		this.costPrice = costPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
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
}
