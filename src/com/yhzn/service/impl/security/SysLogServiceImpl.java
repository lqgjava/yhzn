package com.yhzn.service.impl.security;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.yhzn.dao.security.SysLogDao;
import com.yhzn.model.security.SysLog;
import com.yhzn.service.security.SysLogService;

/**
 * 日志管理实现类
 * @author liany
 *
 */
@Service
public class SysLogServiceImpl implements SysLogService{

	//日志管理Dao
	@Resource
	private SysLogDao sysLogDao;
	
	/**
	 * 查询日志信息列表
	 * @param bounds
	 * @param parameter
	 * @return
	 */
	@Override
	public List<SysLog> querySysLogList(PageBounds bounds,Map<String, Object> parameter) {
		return sysLogDao.querySysLogList(bounds,parameter);
	}


	/**
	 * 新增操作日志信息
	 * @param logType
	 * @param logUser
	 * @param logContent
	 * @param logIp
	 * @param logMethod
	 */
	@Override
	public void insertSysLog(String logType, String logUser,String logContent, String logIp,String logMethod) {
		// TODO Auto-generated method stub
		SysLog sysLog = new SysLog();
		sysLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));//id
		sysLog.setLogType(logType);//日志类型
		sysLog.setLogUser(logUser);//操作人
		sysLog.setLogContent(logContent);//操作内容
		sysLog.setLogIp(logIp);//操作人IP
		sysLog.setLogMethod(logMethod);//操作方法名称
		sysLogDao.insertSysLog(sysLog);
	}

}
