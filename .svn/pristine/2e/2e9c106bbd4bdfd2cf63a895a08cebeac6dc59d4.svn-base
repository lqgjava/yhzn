package com.yhzn.web.controller.workbench;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.yhzn.model.customer.Customer;
import com.yhzn.model.security.User;
import com.yhzn.model.storehouse.PurBillModel;
import com.yhzn.model.workbench.Task;
import com.yhzn.model.workbench.TaskReveice;
import com.yhzn.model.workbench.TaskReveiceModel;
import com.yhzn.service.common.SysSequenceService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.workbench.WorkbenchService;

/**
 * 工作台管理
 * @author liany
 *
 */
@Controller
@RequestMapping("/workbench")
public class WorkbenchController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(WorkbenchController.class);

	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//工作台管理接口
	@Autowired
	private WorkbenchService workbenchService;
	//自动生成编号接口
	@Autowired
	private SysSequenceService sysSequenceService;
	
	
	/**
	 * 任务列表查询
	 * @param request
	 * @return
	 */
	@RequiresPermissions("workbench:listTask")
	@RequestMapping(value="/queryTaskList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryTaskList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询任务信息列表 ",user.getLoginIp(),"/workbench/queryTaskList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String taskNo = request.getParameter("taskNo");//任务编号
		String taskName = request.getParameter("taskName");//任务名称
		String beginDate = request.getParameter("beginDate");//操作开始时间
		String endDate = request.getParameter("endDate");//操作结束时间
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("taskNo", taskNo);
			parameter.put("taskName", taskName);
			parameter.put("beginDate", beginDate);
			parameter.put("endDate", endDate);
			parameter.put("userId", user.getPersonId());

		PageBounds bounds = new PageBounds(page , rows );
		List<Task> list = workbenchService.queryTaskList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Task>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	/**
	 * 删除任务信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("workbench:deleteTask")
	@RequestMapping("/deleteTask")  
    @ResponseBody  
    public Map<String,Object> deleteTaskById(HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        //修改删除标记del为 1
        String ids = request.getParameter("ids");
        
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除任务信息: "+ids,user.getLoginIp(),"/workbench/deleteTask");
		
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		workbenchService.deleteTaskById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	
	/**
	 * 新增任务信息
	 * @param task
	 * @param request
	 * @return
	 */
	@RequiresPermissions("workbench:addTask")
	@RequestMapping(value="/addTask",method= RequestMethod.POST)
	@ResponseBody
	public String addTask(Task task,HttpServletRequest request){
	  Map<String,Object> map = new HashMap<String,Object>();  
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法

	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增任务信息： "+task.getTaskName(),user.getLoginIp(),"/workbench/addTask");
	  try {
		  task.setTaskNo("RW"+sysSequenceService.getNextNo("TASK", user));//设置任务编号
		  workbenchService.insertTaskInfo(task,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 修改任务信息
	 * @param task
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editTask",method= RequestMethod.POST)
	@ResponseBody
	public String editTask(Task task,HttpServletRequest request){
		
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改任务信息： "+task.getTaskName(),user.getLoginIp(),"/workbench/editTask");
	   try {
		   workbenchService.editTaskInfo(task,user);
		} catch (Exception e) {
			return "false";
		}
       return "1";  
	}
	
	/**
	 * 下发任务
	 * @param task
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/sentTask",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> sentTask(String id,String taskNo,HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("下发任务",user.getTrueName(),"下发任务信息: "+taskNo,user.getLoginIp(),"/workbench/sentTask");
  
        workbenchService.sentTask(id,user); 
        map.put("success", 1);  
        return map;  
	}
	
	
	/**
	 * 我的任务列表查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryRecTaskList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryRecTaskList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询我的任务信息列表 ",user.getLoginIp(),"/workbench/queryRecTaskList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String taskNo = request.getParameter("taskNo");//任务编号
		String taskName = request.getParameter("taskName");//任务名称
		String beginDate = request.getParameter("beginDate");//操作开始时间
		String endDate = request.getParameter("endDate");//操作结束时间
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("taskNo", taskNo);
			parameter.put("taskName", taskName);
			parameter.put("beginDate", beginDate);
			parameter.put("endDate", endDate);
			parameter.put("userId", user.getPersonId());

		PageBounds bounds = new PageBounds(page , rows );
		List<Task> list = workbenchService.queryRecTaskList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Task>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	

	/**
	 * 签收任务
	 * @param task
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/signTask",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> signTask(String id,String taskNo,HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("签收任务",user.getTrueName(),"签收任务信息: "+taskNo,user.getLoginIp(),"/workbench/signTask");
		map.put("id", id);
		map.put("singedr", user.getTrueName());
		map.put("modifyName", user.getTrueName());
		map.put("status", "1");
        workbenchService.updateTaskReveice(map); 
        map.put("success", 1);  
        return map;  
	}
	
	 /**
	 * 查询入库清单信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findRecTaskList",method= RequestMethod.POST)
	@ResponseBody
	public void findRecTaskList(String taskId,HttpServletRequest request){
	 try {
        List<TaskReveice> list = workbenchService.findRecTaskList(taskId);
        //list转换为json 
        JSONArray json = JSONArray.fromObject(list);
        String str = "{\"total\":" + list.size() + ",\"rows\":" + json + "}";
 	 	response.setCharacterEncoding("utf-8");
 		PrintWriter out = response.getWriter();
 		out.print(str);
	     } catch (Exception e) {
	    	 System.out.println(e.toString());
	   }	
	}
	
	/**
	 * 反馈任务
	 * @param taskReveice
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/feedbackTask",method= RequestMethod.POST)
	@ResponseBody
	public String feedbackTaskTask(TaskReveice taskReveice,HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("反馈任务",user.getTrueName(),"反馈任务信息: "+taskReveice.getTaskNo(),user.getLoginIp(),"/workbench/signTask");
		map.put("id", taskReveice.getId());
		map.put("modifyName", user.getTrueName());
		map.put("reportResult", taskReveice.getReportResult());
		map.put("status", "2");
		int flag = workbenchService.updateTaskReveice(map); 
        if (flag > 0) {
			return "1";
		} else {
			return "false";
		}
	}
	
	
}
