package com.yhzn.web.controller.finance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.finance.PurchaseContract;
import com.yhzn.model.finance.ReceivableModel;
import com.yhzn.model.person.Person;
import com.yhzn.model.security.User;
import com.yhzn.service.common.CommonPhotoService;
import com.yhzn.service.finance.PurchaseContractService;
import com.yhzn.service.person.PersonService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 财务采购合同控制层
 * @author liany
 */
@Controller
@RequestMapping("/financePurchaseContract")
public class PurchaseContractController{
	
	//财务采购合同服务接口
	@Autowired
	private PurchaseContractService financePurchaseContractService;
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;

	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/**
	 * 加载采购合同管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/purchaseContract", method = RequestMethod.GET)
	public String finance() {
		return "/financemanage/payable/purchaseContract";
	}
	
	
	/**
	 * 新增采购合同信息
	 * @param person
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/addfinancePurchaseContract",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addfinancePurchaseContract(PurchaseContract financePurchaseContract,HttpServletRequest request) throws IOException{
	  //获取登录人信息
	  User user= (User) request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  //financePurchaseContractService.insertSysLog("新增",user.getTrueName(),"新增人员信息： "+financePurchaseContractService.getName(),user.getLoginIp(),"/person/addPerson");
	  Map<String,Object> map = new HashMap<String,Object>();  
	  financePurchaseContractService.addFinancePurchaseContract(financePurchaseContract, user);
      //新增人员信息
      return map;  
	}
	
	/**
	 * 查询采购合同列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryFinancePurchaseContractList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryFinancePurchaseContractList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询人员信息列表 ",user.getLoginIp(),"/financePurchaseContract/queryFinancePurchaseContractList");

		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String contractNo = request.getParameter("contractNo");//合同编号
		String contractName = request.getParameter("contractName");//部门
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("contractNo", contractNo);
			parameter.put("contractName",contractName);

		PageBounds bounds = new PageBounds(page , rows );
		List<PurchaseContract> list = financePurchaseContractService.queryFinancePurchaseContractList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<PurchaseContract>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	/**
	 * 修改员工信息
	 * @param person
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editFinancePurchaseContract",method= RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> financePurchaseContract(PurchaseContract financePurchaseContract,HttpServletRequest request){
		
	   Map<String,Object> map = new HashMap<String,Object>();  
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+financePurchaseContract.getModifyName(),user.getLoginIp(),"/financePurchaseContract/editFinancePurchaseContract");
	   //changePerson(person,request);
	   financePurchaseContractService.updateFinancePurchaseContract(financePurchaseContract, user);
       map.put("success", 1);  
       return map;  
	}
	
	@RequestMapping(value="selFinancePurchaseContractById")
	@ResponseBody
	public PurchaseContract selFinancePurchaseContractById(String id){
		return financePurchaseContractService.selFinancePurchaseContractById(id);
	}
	
	
	@RequestMapping(value="delFinancePurchaseContractById")
	@ResponseBody
	public Map<String,Object> delFinancePurchaseContractById(String id,HttpServletRequest request){
		
		   Map<String,Object> map = new HashMap<String,Object>();  
		   //获取登录人信息
		   User user= (User)request.getSession().getAttribute("user");
//	       //日志类型，操作人，操作内容，操作人IP,操作方法
//		   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+financePurchaseContract.getModifyName(),user.getLoginIp(),"/financePurchaseContract/editFinancePurchaseContract");
//		   //changePerson(person,request);
		   financePurchaseContractService.delFinancePurchaseContractById(id, user);
	       map.put("success", 1);  
	       return map;  
		}
}
