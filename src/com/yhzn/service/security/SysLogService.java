package com.yhzn.service.security;

import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.model.security.SysLog;

/**
 * 系统日志管理接口
 * @author liany
 *
 */
public interface SysLogService {

	/**
	 * 查询日志信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	public List<SysLog> querySysLogList(PageBounds bounds, Map<String, Object> parameter);

	/**
	 * 新增操作日志信息
	 * @param logType
	 * @param logUser
	 * @param logContent
	 * @param logIp
	 * @param logMethod
	 */
	public void insertSysLog(String logType,String logUser,String logContent,String logIp,String logMethod);
}
