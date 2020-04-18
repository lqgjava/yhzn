package com.yhzn.web.controller.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.security.SysLog;
import com.yhzn.model.security.SysRole;
import com.yhzn.service.security.SysLogService;

/**
 * 日志处理Controller
 * @author liany
 *
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(SysLogController.class);

	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	
	@RequestMapping(value="/querySysLogList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil querySysLogList(HttpServletRequest request){
		
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String logUser = request.getParameter("logUser");//操作人
		String logContent = request.getParameter("logContent");//操作内容
		String beginDate = request.getParameter("beginDate");//操作开始时间
		String endDate = request.getParameter("endDate");//操作结束时间
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("logUser", logUser);
			parameter.put("logContent", logContent);
			parameter.put("beginDate", beginDate);
			parameter.put("endDate", endDate);

			//userLogsService.insertLogByUser((User) request.getSession().getAttribute("user"),
			//		"/querySysRoleList", "jsgl", roleName);
		PageBounds bounds = new PageBounds(page , rows );
		List<SysLog> list = sysLogService.querySysLogList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<SysLog>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}
}
