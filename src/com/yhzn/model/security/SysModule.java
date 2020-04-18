package com.yhzn.model.security;

import java.util.Date;

/**
 * 系统模块类
 * 
 * @author liany
 * 
 */
public class SysModule {

	// 主键id
	private String id;
	// 父节点id
	private String parentId;
	// 标题
	private String title;
	// 描述
	private String description;
	// 模块编号
	private String moduleNo;
	//创建时间
	private Date createDate;
	//创建人姓名
	private String createName;
	//修改时间
	private Date modifyDate;
    //修改人姓名
    private String modifyName;
	// sys_role_permis表id
	private String permisId;

	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getPermisId() {
		return permisId;
	}

	public void setPermisId(String permisId) {
		this.permisId = permisId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
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
