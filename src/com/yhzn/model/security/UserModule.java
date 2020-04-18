package com.yhzn.model.security;

import java.util.Date;

/**
 * 用户管理Module
 * @author liany
 *
 */
public class UserModule {
	
	//id                                                                                  
	private String id;                                                                    
	//人员id                                                                                
	private String personId;                                                              
	//密码                                                                                  
	private String password;                                                              
	//用户账号                                                                                
	private String userName;                                                              
	//真实姓名                                                                                
	private String trueName;                                                              
	//部门                                                                                  
	private String dept;                                                                  
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
                                                                 
	//备用字段2                                                                               
	private String rev2;                                                                  
	//备用字段3                                                                               
	private String rev3;                                                                  
	//角色id                                                                                
	private String roleId;                                                                
	//角色名称                                                                                
	private String roleName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
