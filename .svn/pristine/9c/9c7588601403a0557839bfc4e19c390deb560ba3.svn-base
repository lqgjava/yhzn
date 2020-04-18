package com.yhzn.service.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;



public interface SysDictService  {

	/**
	 * 获取字典列表
	 * @param paraMap
	 * @return
	 */
	public List getDictValue(HashMap paraMap);

	/**
	 * 获取字典中文值
	 * @param map
	 * @return
	 */
	public String getDictValueByDictKey(String organId);

	/**
	 * 查询字典信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<SysDict> querySysDictList(PageBounds bounds, Map<String, Object> parameter);
	
	/**
	 * 查询字典信息列表
	 * @param map
	 * @return
	 */
    public List<SysDict> findDictListByMap(HashMap<String, Object> map);
    
    /**
	 * 新增字典
	 * @param sysDict
	 */
    public int insertSysDict(SysDict sysDict,User user);
    
    /**
	 * 修改字典
	 * @param sysDict
	 */
	public int editSysDict(SysDict sysDict,User user);
	
	/**
	 * 删除字典
	 * @param sysDict
	 */
	public void deleteSysDictById(String id);
}
