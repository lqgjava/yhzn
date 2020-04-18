package com.yhzn.web.controller.finance;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

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
import com.yhzn.model.finance.PayableDetailsModel;
import com.yhzn.model.finance.PayableModel;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.PayableService;
import com.yhzn.service.security.SysLogService;


/**
 * 
 * 
 * @ClassName: PayableController.java
 * @Description: 应付账单管理控制器
 *
 * @version: v1.0.0
 * @author: mayn
 * @date: 2019年3月22日 上午11:52:23
 * 
 */
@Controller
@RequestMapping("/pay")
public class PayableController extends BaseController {
	@Autowired
	private PayableService payableService;
	@Autowired
	private SysLogService sysLogService;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	/**
	 * 加载应付账款管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/payable", method = RequestMethod.GET)
	public String finance() {
		return "/financemanage/payable/payable";
	}
	@RequestMapping("/a")
	public String a(){
		return "/financemanage/payable/a";
	}

	/**
	 * 账款列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param receivable
	 * @return
	 */
	@RequiresPermissions("pay:list")
	@RequestMapping("/queryOweMoney")
	@ResponseBody
	public PageUtil QueryOweMoney(@RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "1") int pageSize, PayableModel payable) {
		PageBounds bounds = new PageBounds(pageNum, pageSize);
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		// sysLogService.insertSysLog("查询", user.getTrueName(), "查询单位欠账款列表 ",
		// user.getLoginIp(), "/finance/queryOweMoney");
		List<PayableModel> list = payableService.QueryOweMoney(bounds, payable);
		int total = ((PageList<PayableModel>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);

		return result;

	}

	/**
	 * 公司欠款详情页
	 */
	@RequestMapping(value = "/payabledetails", method = RequestMethod.GET)
	public String payabledetails(String company, Model model) {
		model.addAttribute("company", company);
		return "/financemanage/payable/payabledetails";
	}

	/**
	 * 公司欠款详情数据
	 */
	@RequiresPermissions("payd:list")
	@RequestMapping("/QueryById")
	@ResponseBody
	public PageUtil findByCompany(@RequestParam(required = true) String id,
			@RequestParam(required = false) String unPaydMoney,
			@RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "50") int pageSize) {
		PageBounds bounds = new PageBounds(pageNum, pageSize);
		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");
		// 日志类型，操作人，操作内容，操作人IP,操作方法
		// sysLogService.insertSysLog("查询", user.getTrueName(), "公司欠款详情列表 ",
		// user.getLoginIp(), "/finance/QueryById");
		List<PayableDetailsModel> list = payableService.QueryById(bounds, id, unPaydMoney);
		// 获得结果集条总数
		int total = ((PageList<PayableDetailsModel>) list).getPaginator().getTotalCount();
		// 获取实际收入金额
		int receMoney = payableService.queryrecepitMoney(id);
		// 获取结存金额
		int unpaiMoney = payableService.queryunpaidMoney(id);
		// 页面列表展示
		PageUtil result = new PageUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("receMoney", receMoney);
		map.put("unpaiMoney", unpaiMoney);
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
	@RequestMapping({ "/addPaiable", "/addPaiable/{id}" })
	public String addPaiable(@PathVariable(value = "id", required = false) String id, Model model) {
		if (id != null) {
			PayableModel rece = payableService.queryContractById(id);
			model.addAttribute("rece", rece);
		}
		return "/financemanage/payable/addpayable";
	}

	/**
	 * 删除账款
	 * 
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	@RequiresPermissions("pay:delete")
	@RequestMapping("/deleteReceivable/{id}")
	@ResponseBody
	public JSONObject deleteReceivable(@PathVariable("id") String id) throws FileNotFoundException {
		JSONObject object = new JSONObject();

		payableService.deleteReceivable(id);

		object.put("success", true);
		return object;
	}

	/**
	 * 新增账单信息
	 * 
	 * @param recreModel
	 * @return
	 */
	@RequiresPermissions("pay:add")
	@RequestMapping(value = "/addReceivableModel", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addReceivableModel(PayableModel recreModel) {
		User user = (User) request.getSession().getAttribute("user");
		JSONObject json = new JSONObject();
		try {
			if (recreModel.getId() == null || recreModel.getId() == "") {
				String id = UUID.randomUUID().toString().replaceAll("-", "");
				recreModel.setId(id);
				recreModel.setInsertName(user.getId());
				payableService.insertFinance(recreModel);

			} else {
				recreModel.setModifyName(user.getId());
				recreModel.setModifyDate(sdf.format(new Date()));
				payableService.updateFinance(recreModel);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			json.put("success", false);
			return json;
		}
		json.put("success", true);
		return json;
	}

	/**
	 * 新增收款详情
	 * 
	 * @param recreModel
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions("payd:add")
	@PostMapping("/addReceivableDetails")
	public void addReceivableDetails(PayableDetailsModel details, @RequestParam("file") MultipartFile[] files,
			HttpServletResponse response) throws IOException {

		// 获取登录人信息
		User user = (User) request.getSession().getAttribute("user");

		String uploadDir = ConfigUtil.getConfig("upload.dir");
		List<Attachment> list = new ArrayList<Attachment>();
		for (MultipartFile file : files) {
			if (StringUtils.isNotBlank(file.getOriginalFilename())) {
				Attachment att = FileUpload.upload(file, uploadDir, "ReceivableDetails");
				list.add(att);
				
			}else {
				continue;
			}
		}
		details.setInsertName(user.getId());
		details.setJoinDate(sdf.format(new Date()));
		try {
			payableService.insertReceivableDetails(details, list);
		} catch (Exception e) {
			response.getWriter().write(JSON.toJSONString("文件上传失败"));
		}
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
	@RequiresPermissions("payd:edit")
	@RequestMapping(value = "/editReceivableDetails", method = RequestMethod.POST)
	public void editReceivableDetails(PayableDetailsModel receivable, @RequestParam("file") MultipartFile[] files,
			HttpServletResponse response) throws IOException {
		List<Attachment> list = new ArrayList<Attachment>();
			String uploadDir = ConfigUtil.getConfig("upload.dir");
			for (MultipartFile file : files) {
				Attachment att=new Attachment();
				if(StringUtils.isNotBlank(file.getOriginalFilename())) {
					att = FileUpload.upload(file, uploadDir, "ReceivableDetails");
					list.add(att);
				}
			}
				try {
					// 获取登录人信息
					User user = (User) request.getSession().getAttribute("user");
					receivable.setModeifyName(user.getId());
					receivable.setMonifyDate(sdf.format(new Date()));
					payableService.editReceivableDetails(receivable, list);
					response.getWriter().write(JSON.toJSONString(1));
				} catch (IOException e) {
					System.out.println(e.getMessage());
					response.getWriter().write(JSON.toJSONString("编辑失败"));
				}

			}
		

	

	/**
	 * 删除收款详情
	 * 
	 * @param ids
	 * @return
	 * @throws FileNotFoundException
	 */
	@RequiresPermissions("payd:delete")
	@GetMapping("/delete/{ids}")
	@ResponseBody
	public Map<String, Object> deteleReceivableDetails(@PathVariable(name = "ids", required = true) String[] ids)
			throws FileNotFoundException {
		Map<String, Object> map = new HashMap<String, Object>();

		payableService.deteleReceivableDetails(ids);
		map.put("success", "1");
		return map;

	}
}