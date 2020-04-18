package com.yhzn.web.controller.projmanage;

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
import com.yhzn.model.projmanage.Analysis;
import com.yhzn.model.security.User;
import com.yhzn.model.workbench.Task;
import com.yhzn.service.projmanage.AnalysisService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.workbench.WorkbenchService;

/**
 * 成本分析管理
 * @author liany
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController  extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(AnalysisController.class);

	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//成本分析管理接口
	@Autowired
	private AnalysisService analysisService;

	
	/**
	 * 成本分析列表查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryAnalysisList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryAnalysisList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询成本分析信息列表 ",user.getLoginIp(),"/analysis/queryAnalysisList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String projNo = request.getParameter("projNo");//项目编号
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("projNo", projNo);

		PageBounds bounds = new PageBounds(page , rows );
		List<Analysis> list = analysisService.queryAnalysisList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Analysis>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
}
