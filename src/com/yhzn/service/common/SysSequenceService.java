package com.yhzn.service.common;

import com.yhzn.model.security.User;

/**
 * 通用编号接口实现类
 * @author liany
 *
 */
public interface SysSequenceService {

	/**
	 * 获取序号
	 * @param tableName
	 * @param user
	 * @return
	 */
	public String getNextNo(String tableName,User user);
}
