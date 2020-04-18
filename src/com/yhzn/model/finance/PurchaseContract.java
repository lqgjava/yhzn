package com.yhzn.model.finance;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * @author liany
 *财务采购合同实体类
 */
public class PurchaseContract implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		// 主键
		private String id;
		// 供应商ID 外键
		private String supplierId;
		// 供应商名称
		private String supplierName;
		// 应付金额
		private String money;
		// 已付金额
		private String payMoney;
		// 合同名称
		private String contractName;
		// 合同编号
		private String contractNo;
		// 备注
		private String remark;
		// 创建人
		private String createName;
		// 创建时间
		private Date createDate;
		// 修改人
		private String modifyName;
		// 修改时间
		private Date modifyDate;
		// 删除标识
		private String deleteFlag;
		// 备用字段1
		private String rev1;
		// 备用字段2
		private String rev2;
		// 备用字段3
		private String rev3;
		// 签订时间
		@DateTimeFormat(pattern="yyyy-MM-dd")
		private Date sign;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getSupplierId() {
			return supplierId;
		}
		public void setSupplierId(String supplierId) {
			this.supplierId = supplierId;
		}
		public String getSupplierName() {
			return supplierName;
		}
		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
		public String getPayMoney() {
			return payMoney;
		}
		public void setPayMoney(String payMoney) {
			this.payMoney = payMoney;
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
		public Date getSign() {
			return sign;
		}
		public void setSign(Date sign) {
			this.sign = sign;
		}
		
}
