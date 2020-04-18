package com.yhzn.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.controller.BaseController;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.model.supplier.SupplierModel;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.supplier.SupplierService;

/**
 * 
 * @ClassName: SupplierController.java
 * @Description: 供应商controller类
 *
 * @version: v1.0.0
 * @author: zhouml
 * @date: 2019年3月15日 上午9:46:08
 *
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 查询所有供应商或者模糊查询
	 * 
	 * @param name
	 * @param userName
	 * @param area
	 * @return
	 */
	@RequiresPermissions("supplier:list")
	@RequestMapping("/findAll")
	@ResponseBody
	public PageUtil findAll(@RequestParam(required = false, defaultValue = "") String name,
			@RequestParam(required = false, defaultValue = "") String userName,
			@RequestParam(required = false, defaultValue = "") String area,
			@RequestParam(name = "page", defaultValue = "1") int pageNum,
			@RequestParam(name = "rows", defaultValue = "20") int pageSize) {
		PageBounds bounds = new PageBounds(pageNum , pageSize );
		//获取登录人信息 
				User user= (User) request.getSession().getAttribute("user");
		        //日志类型，操作人，操作内容，操作人IP,操作方法
				sysLogService.insertSysLog("查询",user.getTrueName(),"查询供应商列表 ",user.getLoginIp(),"/supplier/findAll");
		List<SupplierModel> list = supplierService.findAll(bounds,name, userName,area);
		// 获得结果集条总数
		int total = ((PageList<SupplierModel>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}

	/**
	 * 添加或者编辑供应商
	 * 
	 * @param supplier
	 * @return
	 */
	@RequestMapping("/addsupplier")
	@ResponseBody
	public Map<String, Object> addSupplier(SupplierModel supplier) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user= (User) request.getSession().getAttribute("user");
		if (supplier.getId() == null || supplier.getId() == "") {
			supplier.setInsertDate(sdf.format(new Date()));
			boolean addsupplier = supplierService.addsupplier(supplier);
			if (addsupplier == true) {
				sysLogService.insertSysLog("新增",user.getTrueName(),"添加供应商 ",user.getLoginIp(),"/supplier/addsupplier");
			
				map.put("success", 1);
				return map;
			} else {
				map.put("success", 0);
				return map;
			}
		} else {
			// 更新时间
			supplier.setUpdateDate((sdf.format(new Date())));
			boolean updatesupplier = supplierService.updatesupplier(supplier);
			if (updatesupplier == true) {
				map.put("success", 1);
				sysLogService.insertSysLog("更改",user.getTrueName(),"更改供应商 信息",user.getLoginIp(),"/supplier/addsupplier");
				return map;
			} else {
				map.put("success", 0);
				return map;
			}
		}

	}

	/**
	 * 新增或者编辑页面
	 * 
	 * @return
	 */
	@GetMapping({ "/addSupplier/{id}", "/addSupplier" })
	public String addSupplier(@PathVariable(name = "id", required = false) String id, Model model) {
		if (id != null && id != "") {
			SupplierModel supplier = supplierService.findById(id);
			model.addAttribute("supplier", supplier);
		}
		return "/supplier/addsupplier";

	}

	/**
	 * 删除供应商
	 * 
	 * @param ids
	 */
	@GetMapping("/delete/{ids}")
	@ResponseBody
	public Map<String, Object> deleteSupplier(@PathVariable(name = "ids", required = true) String[] ids) {
		User user= (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		boolean zheng = supplierService.deletesupplier(ids);
		if (zheng == true) {
			map.put("success", "1");
			sysLogService.insertSysLog("删除",user.getTrueName(),"删除供应商 信息",user.getLoginIp(),"/supplier/delete");
			return map;
		} else {
			map.put("success", "0");
			return map;
		}
	}
	
	//供应商列表combobox
	@RequestMapping("/combobox")
	@ResponseBody
public List<SysDict> supplierCombobox(){
	return supplierService.supplierCombobox();
	
}
	
}
