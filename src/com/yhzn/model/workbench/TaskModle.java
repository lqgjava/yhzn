package com.yhzn.model.workbench;

import java.util.Date;

/**
 * 任务对象model
 * @author liany
 *
 */
public class TaskModle {
	
	//ID
	private String id;
	//任务编号
	private String taskNo;
	//任务名称
	private String taskName;
	//任务内容
	private String taskContent;
	//任务接收人姓名
	private String receiveName;
	//任务接收人id
	private String receiveId;
	//任务奖金
	private String reward;
	//任务完成时间
	private Date finishDate;
	//任务状态代码
	private String status;
	//任务人id
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskNo() {
		return taskNo;
	}
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskContent() {
		return taskContent;
	}
	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
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
}
