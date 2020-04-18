package com.yhzn.model.storehouse;

import java.util.Date;

/**
 * 采购清单对象MODEL
 * @author liany
 *
 */
public class PurBillModel {
	//ID
	private String id;
	//采购清单编号
	private String purNo;
	//采购清单id
	private String purId;
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
	//数量
	private String amount;
	//总价
	private String totalPrice;
	//入库数量
	private String entryAmount;
	//产品到货状态
	private String status;
	//产品类别（材料、配件、成品）
	private String type;
	//序号
	private String serialNumber;
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
	//供应商
	private String supplier;
	
	private String oper;
	private String createDateStr;
	private String modifyDateStr;
	//产品ID
	private String productId;
	
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
	public String getPurId() {
		return purId;
	}
	public void setPurId(String purId) {
		this.purId = purId;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getEntryAmount() {
		return entryAmount;
	}
	public void setEntryAmount(String entryAmount) {
		this.entryAmount = entryAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getModifyDateStr() {
		return modifyDateStr;
	}
	public void setModifyDateStr(String modifyDateStr) {
		this.modifyDateStr = modifyDateStr;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
