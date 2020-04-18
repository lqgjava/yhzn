package com.yhzn.model.security;

import java.util.Date;

/**
 * 字典管理类Module
 * @author liany
 *
 */
public class SysDictModule {

	//主键id
	private String id;
	//字典等级
	private String dictLevel;
	//字典值
	private String dictKey;
	//父级字典值
	private String parentKey;
	//根级字典值
	private String rootKey;
	//字典中文
	private String dictValue;
	//字典排序
	private int dictSort;
	//字典拼音
	private String dictPy;
	//备注
	private String remark;
	//创建时间
	private Date createDate;
	//创建人姓名
    private String createName;
    //修改时间
	private Date modifyDate;
	//修改人姓名
	private String modifyName;
	//删除标示
	private String deleteFlag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDictLevel() {
		return dictLevel;
	}
	public void setDictLevel(String dictLevel) {
		this.dictLevel = dictLevel;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public String getParentKey() {
		return parentKey;
	}
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}
	public String getRootKey() {
		return rootKey;
	}
	public void setRootKey(String rootKey) {
		this.rootKey = rootKey;
	}
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getDictPy() {
		return dictPy;
	}
	public void setDictPy(String dictPy) {
		this.dictPy = dictPy;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public int getDictSort() {
		return dictSort;
	}
	public void setDictSort(int dictSort) {
		this.dictSort = dictSort;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
