package com.yhzn.dao.security;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.SysLog;

/**
 * 日志管理Dao
 * @author liany
 *
 */
public interface SysLogDao {

	/**
	 * 查询日志信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<SysLog> querySysLogList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 新增操作日志
	 * @param sysLog
	 */
	public void insertSysLog(SysLog sysLog);
}
