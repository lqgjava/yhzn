package com.yhzn.dao.common;

import java.util.Map;

import com.yhzn.model.common.SysSequence;

/**
 * 通用编号接口Dao
 * @author liany
 *
 */
public interface SysSequenceDao {

	/**
	 * 根据id查询序号信息
	 * @param map
	 * @return
	 */
	public SysSequence findMaxSeqById(Map map);
	
	/**
	 * 插入序号
	 * @param sysSequence
	 */
	public void insertSysSequence(SysSequence sysSequence);
	
	/**
	 * 更新序号
	 * @param sysSequence
	 */
	public void updateMaxSeqById(SysSequence sysSequence);
}
