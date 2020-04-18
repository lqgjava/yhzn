package com.yhzn.model.finance;

public class ReceivableDetailsModel {
	private String id;// id
	private String financeId;// 公司账款id
	private String contractNo;// 合同编号
	private String detailMoney;// 应付款
	private String detailRemark;// 备注
	private String unpaidMoney;// 结存
	private String recepitMoney;// 实收
	private String state;//状况
	private String insertDate;// 收入时间
	private String monifyDate;// 修改时间
	private String insertName;// 增加收款人
	private String modeifyName;// 修改时间
	private String deleteFlag;// 删除标识
	private String joinDate;// 入库时间
	private String nextDate; //下次付款时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFinanceId() {
		return financeId;
	}

	public void setFinanceId(String financeId) {
		this.financeId = financeId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getDetailMoney() {
		return detailMoney;
	}

	public void setDetailMoney(String detailMoney) {
		this.detailMoney = detailMoney;
	}

	public String getDetailRemark() {
		return detailRemark;
	}

	public void setDetailRemark(String detailRemark) {
		this.detailRemark = detailRemark;
	}

	public String getUnpaidMoney() {
		return unpaidMoney;
	}

	public void setUnpaidMoney(String unpaidMoney) {
		this.unpaidMoney = unpaidMoney;
	}

	public String getRecepitMoney() {
		return recepitMoney;
	}

	public void setRecepitMoney(String recepitMoney) {
		this.recepitMoney = recepitMoney;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getMonifyDate() {
		return monifyDate;
	}

	public void setMonifyDate(String monifyDate) {
		this.monifyDate = monifyDate;
	}

	public String getInsertName() {
		return insertName;
	}

	public void setInsertName(String insertName) {
		this.insertName = insertName;
	}

	public String getModeifyName() {
		return modeifyName;
	}

	public void setModeifyName(String modeifyName) {
		this.modeifyName = modeifyName;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public ReceivableDetailsModel(String id, String financeId, String contractNo, String detailMoney,
			String detailRemark, String unpaidMoney, String recepitMoney, String insertDate, String monifyDate,
			String insertName, String modeifyName, String deleteFlag, String joinDate,String state) {
		super();
		this.state=state;
		this.id = id;
		this.joinDate = joinDate;
		this.financeId = financeId;
		this.contractNo = contractNo;
		this.detailMoney = detailMoney;
		this.detailRemark = detailRemark;
		this.unpaidMoney = unpaidMoney;
		this.recepitMoney = recepitMoney;
		this.insertDate = insertDate;
		this.monifyDate = monifyDate;
		this.insertName = insertName;
		this.modeifyName = modeifyName;
		this.deleteFlag = deleteFlag;
	}

	public ReceivableDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
