package com.yhzn.web.controller.finance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.yhzn.common.page.PageUtil;
import com.yhzn.model.finance.SalesDetails;
import com.yhzn.model.security.User;
import com.yhzn.service.finance.SalesDetailsService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;

/**
 * @author Liany
 *销售合同详情控制层
 */
@Controller
@RequestMapping("/salesDetails")
public class SalesDetailsController {
	//财务销售合同服务接口
			@Autowired
			private SalesDetailsService SalesDetailsService;
			
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
//			@RequestMapping(value = "/SalesDetails", method = RequestMethod.GET)
//			public String finance() {
//				return "/financemanage/payable/SalesDetails";
//			}
			
			/**
			 *销售合同详情页
			 */
			@RequestMapping(value = "/salesDetailsPage", method = RequestMethod.GET)
			public String SalesDetails(String customerId, Model model) {
				model.addAttribute("customerId", customerId);
				return "/financemanage/accounts/salesContract";
			}
			
			/**
			 * 加载新添加的采购合同详情页
			 * 可能有问题
			 */
			/*@RequestMapping(value = "/purchasedetailspage", method = RequestMethod.GET)
			public String purchaseDetails(String contractNo, Model model) {
				model.addAttribute("contractNo", contractNo);
				return "/financemanage/payable/purchaseContract";
			}*/
			
			/**
			 * 新增销售合同详情信息
			 * @param person
			 * @param request
			 * @return
			 * @throws IOException 
			 */
			@RequestMapping(value="/addSalesDetails",method= RequestMethod.POST)
			@ResponseBody
			public Map<String,Object> addSalesDetails(SalesDetails SalesDetails,HttpServletRequest request) throws IOException{
			  //获取登录人信息
			  User user= (User) request.getSession().getAttribute("user");
		      //日志类型，操作人，操作内容，操作人IP,操作方法
			  //financeSalesDetailsService.insertSysLog("新增",user.getTrueName(),"新增人员信息： "+financeSalesDetailsService.getName(),user.getLoginIp(),"/person/addPerson");
			  Map<String,Object> map = new HashMap<String,Object>();  
			  SalesDetailsService.addSalesDetails(SalesDetails, user);
		      //新增人员信息
		      return map;  
			}
			
			/**
			 * 查询销售合同详情列表
			 * @param request
			 * @return
			 */
			@RequestMapping(value="/querySalesDetailsList", method = RequestMethod.POST)
			@ResponseBody
			public PageUtil querySalesDetailsList(HttpServletRequest request){
				//获取登录人信息 
				User user= (User) request.getSession().getAttribute("user");
		        //日志类型，操作人，操作内容，操作人IP,操作方法
				sysLogService.insertSysLog("查询",user.getTrueName(),"查询人员信息列表 ",user.getLoginIp(),"/SalesDetails/querySalesDetailsList");

				
				int page = Integer.parseInt(request.getParameter("page"));
				int rows = Integer.parseInt(request.getParameter("rows"));
	            //可能有问题			
				String contractNo = request.getParameter("contractNo");//合同编号
				String contractName = request.getParameter("contractName");//部门
				
				Map<String,Object> parameter = new HashMap<String,Object>();
					parameter.put("contractNo", contractNo);
					parameter.put("contractName",contractName);

				PageBounds bounds = new PageBounds(page , rows );
				List<SalesDetails> list = SalesDetailsService.querySalesDetailsList(bounds, parameter);
				// 获得结果集条总数
				int total = ((PageList<SalesDetails>) list).getPaginator().getTotalCount();
				// 页面列表展示
				PageUtil result = new PageUtil();
				result.setRows(list);
				result.setTotal(total);
				return result;
			}
			
			/**
			 * 修改销售合同详情信息
			 * @param person
			 * @param request
			 * @return
			 */
			@RequestMapping(value="/editSalesDetails",method= RequestMethod.POST)
			@ResponseBody
			public Map<String,Object> editSalesDetails(SalesDetails SalesDetails,HttpServletRequest request){
				
			   Map<String,Object> map = new HashMap<String,Object>();  
			   //获取登录人信息
			   User user= (User)request.getSession().getAttribute("user");
		       //日志类型，操作人，操作内容，操作人IP,操作方法
			   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+SalesDetails.getModifyName(),user.getLoginIp(),"/financeSalesDetails/editFinanceSalesDetails");
			   //changePerson(person,request);
			   SalesDetailsService.updateSalesDetails(SalesDetails, user);
		       map.put("success", 1);  
		       return map;  
			}
			
			/**
			 * 根据id查询销售合同详情
			 * @param id
			 * @return
			 */
			@RequestMapping(value="selSalesDetailsById")
			@ResponseBody
			public SalesDetails selSalesDetailsById(String id){
				return SalesDetailsService.selSalesDetailsById(id);
			}
			
			/**
			 * 根据id删除销售合同详情
			 * @param id
			 * @param request
			 * @return
			 */
			@RequestMapping(value="delSalesDetailsById")
			@ResponseBody
			public Map<String,Object> delSalesDetailsById(String id,HttpServletRequest request){
				
				   Map<String,Object> map = new HashMap<String,Object>();  
				   //获取登录人信息
				   User user= (User)request.getSession().getAttribute("user");
//			       //日志类型，操作人，操作内容，操作人IP,操作方法
//				   sysLogService.insertSysLog("修改",user.getTrueName(),"修改人员信息： "+financeSalesDetails.getModifyName(),user.getLoginIp(),"/financeSalesDetails/editFinanceSalesDetails");
//				   //changePerson(person,request);
				   SalesDetailsService.delSalesDetailsById(id, user);
			       map.put("success", 1);  
			       return map;  
				}
}
