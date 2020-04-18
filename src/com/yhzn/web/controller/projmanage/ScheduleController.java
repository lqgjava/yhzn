package com.yhzn.web.controller.projmanage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.common.util.ConfigUtil;
import com.yhzn.common.util.FileUpload;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.common.CommonPhoto;
import com.yhzn.model.projmanage.ProjectLinkModel;
import com.yhzn.model.projmanage.Schedule;
import com.yhzn.model.security.User;
import com.yhzn.service.common.CommonPhotoService;
import com.yhzn.service.projmanage.ScheduleService;
//import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;

/**
 * 项目管理
 * 
 * @author liany
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
	// 日志管理接口
	@Autowired
	private SysLogService sysLogService;
	// 字典管理接口
	// @Autowired
	// private SysDictService sysDictService;
	// 项目进度管理接口
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private CommonPhotoService commonPhotoService;

	/**
	 * 项目前期新增和编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping({ "/addProject/{id}", "/addProject" })
	public String projectPage(@PathVariable(name = "id", required = false) String id, Model model) {
		if (id != null && id != "") {
			Schedule schedule = scheduleService.queryScheduleById(id);
			model.addAttribute("schedule", schedule);
		}
		return "/projmanage/schedule/addProject";
	}

	/**
	 * 项目前期页面
	 * 
	 * @return
	 */
	@GetMapping("/projectLink")
	public String projectLink(String id, Model model,int projStatus) {
		Schedule schedule = scheduleService.queryScheduleById(id);
		model.addAttribute("schedule", schedule);
		model.addAttribute("projStatus",projStatus);
		return "/projmanage/schedule/projectLink";
	}

	/**
	 * 与客户沟通记录
	 * 
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequiresPermissions("projectlink:list")
	@RequestMapping("/linkdetails/{id}")
	@ResponseBody
	public PageUtil linkdetails(@PathVariable("id") String id,
			@RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "20") int pageSize) {
		PageBounds bounds = new PageBounds(pageNum, pageSize);
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询", user.getTrueName(), "查询客户沟通记录列表 ", user.getLoginIp(), "/schedule/linkdetails");
		List<ProjectLinkModel> list = scheduleService.linkdetails(bounds, id);
		// 获得结果集条总数
		int total = ((PageList<ProjectLinkModel>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		return result;

	}

	/**
	 * 新增项目沟通记录
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("projectlink:add")
	@PostMapping("/addLink")
	@ResponseBody
	public void addLink(ProjectLinkModel link, @RequestParam("file") MultipartFile[] files) throws IOException {
		if (files.length < 0) {
			response.getWriter().write(JSON.toJSONString("请上传文件"));
		} else {
			// 获取登录人信息
			User user = (User) request.getSession().getAttribute("user");
			// 日志类型，操作人，操作内容，操作人IP,操作方法
			// sysLogService.insertSysLog("新增", user.getTrueName(), "新增收款记录 ",
			// user.getLoginIp(),"/finance/addReceivableDetails");

			String uploadDir = ConfigUtil.getConfig("upload.dir");
			List<Attachment> list = new ArrayList<Attachment>();
			for (MultipartFile file : files) {
				Attachment att = FileUpload.upload(file, uploadDir, "ProjectLink");
				list.add(att);
			}
			link.setInsertName(user.getId());
			scheduleService.insertLinkInfo(link, list);

		}
		response.getWriter().write(JSON.toJSONString(1));
	}

	/**
	 * 项目前期列表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequiresPermissions("projectq:list")
	@RequestMapping(value = "/queryEarlyList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryEarlyList(Schedule schedule, @RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "1") int pageSize) {
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询", user.getTrueName(), "查询项目前期信息列表 ", user.getLoginIp(),
				"/schedule/queryEarlyList");
		PageBounds bounds = new PageBounds(pageNum, pageSize);
		List<Schedule> list = scheduleService.queryEarlyList(bounds, schedule);
		// 获得结果集条总数
		int total = ((PageList<Schedule>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;
	}

	@RequestMapping("/updateProjectStatus")
	@ResponseBody
	public Map<String, Object> updateProjectStatus(String id, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		int r = scheduleService.updateProjectStatusById(id, status);
		if (r > 0) {
			map.put("success", true);
			return map;
		} else {
			map.put("success", false);
			return map;
		}

	}

	/**
	 * 修改沟通信息
	 * 
	 * @param person
	 * @param request
	 * @return
	 */
	@RequiresPermissions("projectlink:edit")
	@RequestMapping(value = "/editLink", method = RequestMethod.POST)
	@ResponseBody
	public void editLink(ProjectLinkModel link, @RequestParam("file") MultipartFile[] files) throws IOException {

		if (files.length < 0) {
		} else {
			// 获取登录人信息
			User user = (User) request.getSession().getAttribute("user");
			// 日志类型，操作人，操作内容，操作人IP,操作方法
			// sysLogService.insertSysLog("修改", user.getTrueName(), "修改人员信息" +
			// user.getTrueName(), user.getLoginIp(),
			// "/person/editPerson");

			String uploadDir = ConfigUtil.getConfig("upload.dir");
			List<Attachment> list = new ArrayList<Attachment>();
			for (MultipartFile file : files) {
				Attachment att = FileUpload.upload(file, uploadDir, "ProjectLink");
				list.add(att);
			}
			link.setModifyName(user.getId());
			scheduleService.editLinkInfo(link, list);
			response.getWriter().write(JSON.toJSONString(1));
		}

	}

	/**
	 * 删除项目信息
	 * 
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	@RequiresPermissions("projectlink:delete")
	@RequestMapping("/deleteLink/{ids}")
	@ResponseBody
	public Map<String, Object> deleteLink(@PathVariable("ids") String[] ids) throws FileNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除", user.getTrueName(), "删除项目前期沟通记录信息: " + ids, user.getLoginIp(),
				"/schedule/deleteLink");
		for (int i = 0; i < ids.length; i++) {
			scheduleService.deleteLinkById(ids[i]);
		}
		map.put("success", 1);
		return map;
	}

	/**
	 * 获取数据库图片
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPhoto", method = RequestMethod.GET)
	@ResponseBody
	public void getPhoto(String id, HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		CommonPhoto commonPhoto = commonPhotoService.findCommonPhotoByparentId(id);
		InputStream ips = null;
		ServletOutputStream out = null;
		try {
			Blob blob = (Blob) commonPhoto.getImage();
			ips = blob.getBinaryStream();
			out = response.getOutputStream();
			response.setContentType("multipart/form-data");
			response.setCharacterEncoding("utf-8");
			int len = 0;
			byte[] buffer = new byte[1024 * 5];
			while ((len = ips.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "sorry,您请求的图片不存在！");
		} finally {
			out.close();
			ips.close();
		}
	}

	/**
	 * 图片预览界面
	 */
	@GetMapping("/img")
	public String img(String id, Model model) {
		model.addAttribute("id", id);
		return "/common/img";
	}

	/**
	 * 项目进度列表查询
	 * 
	 * @param request
	 * @return
	 */
	@RequiresPermissions("projectz:list")
	@RequestMapping(value = "/queryScheduleList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryScheduleList(HttpServletRequest request) {
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询", user.getTrueName(), "查询项目进度信息列表 ", user.getLoginIp(),
				"/schedule/queryScheduleList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));

		String projNo = request.getParameter("projNo");// 项目编号
		String projName = request.getParameter("projName");// 项目名称
		String projStatus = request.getParameter("projStatus");// 项目状态
		String beginDate = request.getParameter("beginDate");// 项目开始时间
		String endDate = request.getParameter("endDate");// 项目结束时间
		String fromDate = request.getParameter("fromDate");// 项目起始时间
		String toDate = request.getParameter("toDate");// 项目终止时间
		String unitName = request.getParameter("unitName");// 单位名称	

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("projNo", projNo);
		parameter.put("projName", projName);
		parameter.put("projStatus", projStatus);
		parameter.put("beginDate", beginDate);
		parameter.put("endDate", endDate);
		parameter.put("fromDate", fromDate);
		parameter.put("toDate", toDate);
		parameter.put("unitName", unitName);

		PageBounds bounds = new PageBounds(page, rows);
		List<Schedule> list = scheduleService.queryScheduleList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Schedule>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;
	}

	/**
	 * 删除项目信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("projectz:delete")
	@RequestMapping("/deleteProject")
	@ResponseBody
	public Map<String, Object> deleteProjectById(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 修改删除标记del为 1
		String ids = request.getParameter("ids");
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除", user.getTrueName(), "删除项目信息: " + ids, user.getLoginIp(),
				"/schedule/deleteProject");

		String[] idArray = ids.split(",");
		if (null != ids && idArray.length > 0) {
			for (int i = 0; i < idArray.length; i++) {
				scheduleService.deleteProjectById(idArray[i]);
			}
		}
		map.put("success", 1);
		return map;
	}

	/**
	 * 删除项目信息
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("projectq:delete")
	@RequestMapping("/deleteProject/{ids}")
	@ResponseBody
	public Map<String, Object> deleteProjectByIds(@PathVariable("ids") String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 修改删除标记del为 1
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除", user.getTrueName(), "删除项目信息: " + ids, user.getLoginIp(),
				"/schedule/deleteProject");
		scheduleService.deleteProjectByIds(ids);
		map.put("success", 1);
		return map;
	}

	/**
	 * 新增或者项目信息（前期）
	 * 
	 * @param schedule
	 * @param request
	 * @return
	 */
	@RequiresPermissions("project:add")
	@RequestMapping(value = "/addProject", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addProject(Schedule schedule, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		if (schedule.getId() == null || schedule.getId() == "") {
			// 日志类型，操作人，操作内容，操作人IP,操作方法
			sysLogService.insertSysLog("新增", user.getTrueName(), "新增项目信息： " + schedule.getProjName(), user.getLoginIp(),
					"/schedule/addProject");
			try {
				scheduleService.insertProjectInfo(schedule, user);
			} catch (Exception e) {
				logger.info("系统错误：" + e.getStackTrace());
				map.put("success", 0);
				return map;
			}
			map.put("success", "1");
			return map;
		} else {
			sysLogService.insertSysLog("修改", user.getTrueName(), "修改项目信息： " + schedule.getProjName(), user.getLoginIp(),
					"/schedule/editProject");
			try {
				scheduleService.updateProjectInfo(schedule, user);
			} catch (Exception e) {
				logger.info("系统错误：" + e.getStackTrace());
				map.put("success", "0");
			}

		}
		map.put("success", "1");
		return map;
	}

	/**
	 * 修改项目信息
	 * 
	 * @param schedule
	 * @param request
	 * @return
	 */
	@RequiresPermissions("projectq:edit")
	@RequestMapping(value = "/editProject", method = RequestMethod.POST)
	@ResponseBody
	public String editProject(Schedule schedule, HttpServletRequest request) {
		System.out.println("tel:" + schedule.getProjTel());
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("修改", user.getTrueName(), "修改项目信息： " + schedule.getProjName(), user.getLoginIp(),
				"/schedule/editProject");
		try {
			scheduleService.updateProjectInfo(schedule, user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "false";
		}
		return "1";
	}
	//项目售后页面
	@RequestMapping("projectService")
	public String projectService() {
		return "/projmanage/schedule/projectServiceList";		
	}
	//隐藏项目
	@RequestMapping("/hideProject/{ids}")
	@ResponseBody
	public Map<String, Object> hideProjectByIds(@PathVariable("ids") String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 修改删除标记del为 1
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("隐藏", user.getTrueName(), "隐藏项目信息: " + ids, user.getLoginIp(),
				"/schedule/hideProject");
		scheduleService.hideProjectByIds(ids);
		map.put("success", 1);
		return map;
	}
	//项目进度管理
	//更改状态
	@RequestMapping("/updateStatus")
	@ResponseBody
	public Map<String,Object> updateStatus(String id,String zt){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean stutas=scheduleService.updateStatus(id,zt);
		if(stutas) {
			map.put("success", true);
			return map;
		}else {
			map.put("success", false);
			return map;
		}
		
		}
}
