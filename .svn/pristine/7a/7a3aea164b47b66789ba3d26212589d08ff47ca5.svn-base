package com.yhzn.dao.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.SysDict;

public interface SysDictDao {

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
	public String getDictValueByDictKey(HashMap map);

	/**
	 * 查询字典信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	List<SysDict> querySysDictList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 新增字典
	 * @param sysDict
	 */
	public int insertSysDict(SysDict sysDict);
	
	/**
	 * 修改字典
	 * @param sysDict
	 */
	public int editSysDict(SysDict sysDict);
	
	/**
	 * 查询字典信息列表
	 * @param map
	 * @return
	 */
    public List<SysDict> findDictListByMap(HashMap<String, Object> map);
    
    /**
     * 删除字典信息
     * @param map
     */
    public void deleteSysDictByMap(HashMap<String, Object> map);
    
    /**
     * 删除字典信息
     * @param id
     */
    public void deleteSysDictById(String id);
}
