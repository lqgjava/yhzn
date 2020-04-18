package com.yhzn.web.controller.finance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.common.util.ConfigUtil;
import com.yhzn.common.util.FileUpload;
import com.yhzn.model.common.Attachment;
import com.yhzn.model.finance.ReceivableDetailsModel;
import com.yhzn.model.finance.ReceivableModel;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.ReceivableService;
import com.yhzn.service.security.SysLogService;

/**
 * 
 * 
 * @ClassName: ReceivableController.java
 * @Description: 应收账单管理控制器
 *
 * @version: v1.0.0
 * @author: zhoulm
 * @date: 2019年3月22日 上午11:52:23
 * 
 */
@Controller
@RequestMapping("/finance")
public class ReceivableController extends BaseController {
	@Autowired
	private ReceivableService receivableService;
	@Autowired
	private SysLogService sysLogService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * 加载应收账款管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/receivable", method = RequestMethod.GET)
	public String finance() {
		return "/financemanage/accounts/receivable";
	}

	/**
	 * 账款列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param receivable
	 * @return
	 */
	@RequestMapping("/queryOweMoney")
	@ResponseBody
	public PageUtil QueryOweMoney(@RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "1") int pageSize, ReceivableModel receivable) {
		PageBounds bounds = new PageBounds(pageNum, pageSize);
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		// sysLogService.insertSysLog("查询", user.getTrueName(), "查询单位欠账款列表 ",
		// user.getLoginIp(), "/finance/queryOweMoney");
		List<ReceivableModel> list = receivableService.QueryOweMoney(bounds, receivable);
		int total = ((PageList<ReceivableModel>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;

	}

	/**
	 * 公司欠款详情页
	 */
	@RequestMapping(value = "/addreceivable", method = RequestMethod.GET)
	public String addreceivableCompany(String company, Model model) {
		model.addAttribute("company", company);
		return "/financemanage/accounts/receivabledetails";
	}

	/**
	 * 公司欠款详情数据
	 */
	@RequestMapping("/QueryById")
	@ResponseBody
	public PageUtil findByCompany(@RequestParam(required = true) String id,
			@RequestParam(required = false) String unpaidMoney,
			@RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "100") int pageSize) {
		PageBounds bounds = new PageBounds(pageNum, pageSize);
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询", user.getTrueName(), "公司欠款详情列表 ", user.getLoginIp(), "/finance/QueryById");
		List<ReceivableDetailsModel> list = receivableService.QueryById(bounds, id, unpaidMoney);
		// 获得结果集条总数
		int total = ((PageList<ReceivableDetailsModel>) list).getPaginator().getTotalCount();
		// 获取实际收入金额
		int receMoney = receivableService.queryrecepitMoney(id);
		// 获取结存金额
		int unpaiMoney = receivableService.queryunpaidMoney(id);
		// 应收金额=实际+结存
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receMoney", receMoney);
		map.put("unpaiMoney", unpaiMoney);
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		result.setMap(map);
		return result;
	}

	/**
	 * 新增和编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	
	@RequestMapping({ "/addcontract", "/addcontract/{id}" })
	public String addreceivable(@PathVariable(value = "id", required = false) String id, Model model) {
		if (id != null) {
			ReceivableModel rece = receivableService.queryContractById(id);
			model.addAttribute("rece", rece);
		}
		return "/financemanage/accounts/addcontract";
	}

	/**
	 * 删除账款
	 * 
	 * @param id
	 * @return
	 */
	@RequiresPermissions("finance:delete")
	@RequestMapping("/deleteReceivable/{id}")
	@ResponseBody
	public JSONObject deleteReceivable(@PathVariable("id") String id) {
		JSONObject object = new JSONObject();
		try {
			receivableService.deleteReceivable(id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			object.put("success", false);
			return object;
		}
		object.put("success", true);
		return object;
	}

	/**
	 * 新增账单信息
	 * 
	 * @param recreModel
	 * @return
	 */
	@RequiresPermissions("finance:add")
	@RequestMapping(value = "/addReceivableModel", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addReceivableModel(ReceivableModel recreModel) {
		User user = (User) request.getSession().getAttribute("user");
		JSONObject json = new JSONObject();
		try {
			if (recreModel.getId() == null || recreModel.getId() == "") {
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				recreModel.setId(id);
				recreModel.setInsertName(user.getId());
				receivableService.insertFinance(recreModel);

			} else {
				recreModel.setModifyName(user.getId());
				recreModel.setModifyDate(sdf.format(new Date()));
				receivableService.updateFinance(recreModel);
			}
			json.put("success", true);
			return json;

		} catch (Exception e) {
			json.put("success", false);
			return json;
		}
	}

	/**
	 * 新增收款详情
	 * 
	 * @param recreModel
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("financed:add")
	@PostMapping("/addReceivableDetails")
	@ResponseBody
	public void addReceivableDetails(ReceivableDetailsModel details, @RequestParam("file") MultipartFile[] files)
			throws IOException {
		System.out.println(details.toString());
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");

		String uploadDir = ConfigUtil.getConfig("upload.dir");
		List<Attachment> list = new ArrayList<Attachment>();
		for (MultipartFile file : files) {
			if (StringUtils.isNotBlank(file.getOriginalFilename())) {
				Attachment att = FileUpload.upload(file, uploadDir, "ReceivableDetails");
				list.add(att);
			}

		}
		details.setInsertName(user.getId());
		details.setJoinDate(sdf.format(new Date()));
		receivableService.insertReceivableDetails(details, list);
		response.getWriter().write(JSON.toJSONString(1));
	}

	/**
	 * 编辑收款详情
	 * 
	 * @param receivable
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("financed:edit")
	@RequestMapping(value = "/editReceivableDetails", method = RequestMethod.POST)
	@ResponseBody
	public void editReceivableDetails(ReceivableDetailsModel receivable, @RequestParam("file") MultipartFile[] files)
			throws IOException {
		List<Attachment> list = null;

		String uploadDir = ConfigUtil.getConfig("upload.dir");
		list = new ArrayList<Attachment>();
		for (MultipartFile file : files) {
			if (StringUtils.isNotBlank(file.getOriginalFilename())) {
				Attachment att = FileUpload.upload(file, uploadDir, "ReceivableDetails");
				list.add(att);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");

		receivable.setModeifyName(user.getId());
		receivable.setMonifyDate(sdf.format(new Date()));
		receivableService.editReceivableDetails(receivable, list);
		response.getWriter().write(JSON.toJSONString(1));
	}

	/**
	 * 删除收款详情
	 * 
	 * @param ids
	 * @return
	 */
	@RequiresPermissions("finance:delete")
	@GetMapping("/delete/{ids}")
	@ResponseBody
	public Map<String, Object> deteleReceivableDetails(@PathVariable(name = "ids", required = true) String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			receivableService.deteleReceivableDetails(ids);
			map.put("success", "1");
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			map.put("success", "0");
			return map;
		}
	}

	@GetMapping("receivablein")
	public String receivablein() {
		return "/financemanage/accounts/receivablein";
	}
}