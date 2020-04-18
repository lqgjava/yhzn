package com.yhzn.web.controller.finance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.finance.SalesContract;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.SalesContractService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;

/**
 * 销售合同控制层
 * @author liany
 */
@Controller
@RequestMapping("/financialSalesContract")
public class SalesContractController {
	//  销售合同服务接口
		@Autowired
		private SalesContractService salesContractService;
		
		//日志管理接口
		@Autowired
		private SysLogService sysLogService;

		//字典管理接口
		@Autowired
		private SysDictService sysDictService;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		/**
		 * 加载销售合同管理页面
		 * 
		 * @return
		 */
		@RequestMapping(value = "/salesContract", method = RequestMethod.GET)
		public String finance() {
			return "/financemanage/accounts/salesContract";
		}
		/**
		 * 新增销售合同信息
		 * @param person
		 * @param request
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping(value="/addFinancialSalesContract",method= RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> addFinancialSalesContract(SalesContract salesContract,HttpServletRequest request) throws IOException{
		  //获取登录人信息
		  User user= (User) request.getSession().getAttribute("user");
	      //日志类型，操作人，操作内容，操作人IP,操作方法
		  //FinancialSalesContractService.insertSysLog("新增",user.getTrueName(),"新增人员信息： "+FinancialSalesContractService.getName(),user.getLoginIp(),"/person/addPerson");
		  Map<String,Object> map = new HashMap<String,Object>();  
		  salesContractService.addFinancialSalesContract(salesContract, user);
	      //新增人员信息
	      return map;  
		}
		
		/**
		 * 查询销售合同列表
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/queryFinancialSalesContractList", method = RequestMethod.POST)
		@ResponseBody
		public PageUtil queryFinancialSalesContractList(HttpServletRequest request){
			//获取登录人信息 
			User user= (User) request.getSession().getAttribute("user");
	        //日志类型，操作人，操作内容，操作人IP,操作方法
			sysLogService.insertSysLog("查询",user.getTrueName(),"查询人员信息列表 ",user.getLoginIp(),"/financialSalesContract/queryFinancialSalesContractList");

			
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			
			String contractNo = request.getParameter("contractNo");//合同编号    模糊查询的时候需要用到
			String contractName = request.getParameter("contractName");//部门
			
			Map<String,Object> parameter = new HashMap<String,Object>();
				parameter.put("contractNo", contractNo);
				parameter.put("contractName",contractName);

			PageBounds bounds = new PageBounds(page , rows );
			List<SalesContract> list = salesContractService.queryFinancialSalesContractList(bounds, parameter);
			// 获得结果集条总数
			int total = ((PageList<SalesContract>) list).getPaginator().getTotalCount();
			// 页面列表展示
			PageUtil result = new PageUtil();
			result.setRows(list);
			result.setTotal(total);
			return result;
		}
		
		/**
		 * 修改销售合同信息
		 * @param person
		 * @param request
		 * @return
		 */
		@RequestMapping(value="/editFinancialSalesContract",method= RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> editFinancialSalesContract(SalesContract financialSalesContract,HttpServletRequest request){
			
		   Map<String,Object> map = new HashMap<String,Object>();  
		   //获取登录人信息
		   User user= (User)request.getSession().getAttribute("user");
	       //日志类型，操作人，操作内容，操作人IP,操作方法
		   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+financialSalesContract.getModifyName(),user.getLoginIp(),"/financialSalesContract/editFinancialSalesContract");
		   //changePerson(person,request);
		   salesContractService.updateFinancialSalesContract(financialSalesContract, user);
	       map.put("success", 1);  
	       return map;  
		}
		
		/**
		 * 根据id查询销售合同信息
		 * @param id
		 * @return
		 */
		@RequestMapping(value="selFinancialSalesContractById")
		@ResponseBody
		public SalesContract selFinancialSalesContractById(String id){
			return salesContractService.selFinancialSalesContractById(id);
		}
		
		/**
		 * 根据id删除销售合同信息
		 * @param id
		 * @param request
		 * @param financialSalesContract
		 * @return
		 */
		@RequestMapping(value="delFinancialSalesContractById")
		@ResponseBody
		public Map<String,Object> delFinancialSalesContractById(String id,HttpServletRequest request,SalesContract financialSalesContract){
			
			   Map<String,Object> map = new HashMap<String,Object>();  
			   //获取登录人信息
			   User user= (User)request.getSession().getAttribute("user");
//		       //日志类型，操作人，操作内容，操作人IP,操作方法
			   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+financialSalesContract.getModifyName(),user.getLoginIp(),"/FinancialSalesContract/editFinancialSalesContract");
//			   //changePerson(person,request);
			   salesContractService.delFinancialSalesContractById(id, user);
		       map.put("success", 1);  
		       return map;  
			}
}
