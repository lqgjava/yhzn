package com.yhzn.web.controller.customer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.yhzn.model.person.Person;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.service.customer.CustomerService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;

/**
 * 
 * @author lin
 * 客户管理处理类
 */
 
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//客户管理接口
	@Autowired
	private CustomerService customerService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	
	/**
	 * 客户信息列表查询
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/queryCustomerList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil queryCustomerList(HttpServletRequest request){
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("查询",user.getTrueName(),"查询客户信息列表 ",user.getLoginIp(),"/customer/queryCustomerList");

		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String name = request.getParameter("name");//姓名
		String type = request.getParameter("type");//客户类别
		String unitName = request.getParameter("unitName");//单位名称
		System.out.println(unitName);
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("name", name);
			parameter.put("type", type);
			parameter.put("unitName", unitName);

		PageBounds bounds = new PageBounds(page , rows );
		List<Customer> list = customerService.queryCustomerList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<Customer>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;
	}
	
	/**
	 * 删除客户信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("customer:delete")
	@RequestMapping("/deleteCustomer")  
    @ResponseBody  
    public Map<String,Object> deleteCustomerById(HttpServletRequest request) {  
        Map<String,Object> map = new HashMap<String,Object>();  
        //修改删除标记del为 1
        String ids = request.getParameter("ids");
        
		//获取登录人信息 
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除客户信息: "+ids,user.getLoginIp(),"/customer/deleteCustomer");
		
        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		customerService.deleteCustomerById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }
	
	
	/**
	 * 新增客户信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequiresPermissions("customer:add")
	@RequestMapping(value="/addCustomer",method= RequestMethod.POST)
	@ResponseBody
	public String addCustomer(Customer customer,HttpServletRequest request){
	  Map<String,Object> map = new HashMap<String,Object>();  
      //获取登录人信息
	  User user= (User)request.getSession().getAttribute("user");
      //日志类型，操作人，操作内容，操作人IP,操作方法
	  sysLogService.insertSysLog("新增",user.getTrueName(),"新增客户信息： "+customer.getName(),user.getLoginIp(),"/customer/addCustomer");
	  try {
		  	customerService.insertCustomerInfo(customer,user);
	    } catch (Exception e) {
			return "false";
	  }
      return "1";   
	}
	
	/**
	 * 修改客户信息
	 * @param customer
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/editCustomer",method= RequestMethod.POST)
	@ResponseBody
	public String editCustomer(Customer customer,HttpServletRequest request){
		
	   //获取登录人信息
	   User user= (User)request.getSession().getAttribute("user");
       //日志类型，操作人，操作内容，操作人IP,操作方法
	   sysLogService.insertSysLog("修改",user.getTrueName(),"修改客户信息： "+customer.getName(),user.getLoginIp(),"/customer/editCustomer");
	   try {
		   customerService.editCustomerInfo(customer,user);
		} catch (Exception e) {
			return "false";
		}
       return "1";  
	}
	
	 /**
	 * 获取客户行业类别
	 * @param type
	 * @return
     * @throws IOException 
	 */
    @RequestMapping(value="/getCustomerTypeList",method= RequestMethod.GET)
    @ResponseBody  
    public String getCustomerTypeList(String type,HttpServletRequest request) throws IOException {
    	String str ="";  
        StringBuilder json = new StringBuilder();  
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("rootKey", "HYLBDM");
        map.put("parentKey", "HYLBDM");
        // 获取客户行业类别字典代码
        List<SysDict> sysDictList = sysDictService.findDictListByMap(map);
        // 遍历子节点下的子节点  
        if(sysDictList!=null && sysDictList.size()!=0){  
            json.append("[");  
            for (SysDict s : sysDictList) {  
                json.append("{\"id\":\"" +s.getDictValue()+ "\"");   
                json.append(",\"text\":\"" +s.getDictValue() + "\"");   
                if(s.getDictValue().equals(type)){
                	json.append(",\"selected\":"+true);  
                }
                json.append("},");  
            }  
            str = json.toString();  
            str = str.substring(0, str.length()-1);  
            str+="]";  
              
        }  
       return str;
    } 
    
    
    /**
   	 * 获取客户信息
   	 * @param name
   	 * @return
        * @throws IOException 
   	 */
       @RequestMapping(value="/getCustomerList",method= RequestMethod.GET)
       @ResponseBody  
       public String getCustomerList(String name,HttpServletRequest request) throws IOException {
       	   String str ="";  
           StringBuilder json = new StringBuilder();  
           HashMap<String,Object> map = new HashMap<String,Object>();
           List<Customer>  list= customerService.queryCustomerInfoList(map);
           // 遍历子节点下的子节点  
           if(list!=null && list.size()!=0){  
               json.append("[");  
               for (Customer ct : list) {  
                   json.append("{\"id\":\"" +ct.getName()+ "\"");   
                   json.append(",\"text\":\"" +ct.getName() + "\"");   
                   json.append(",\"phone\":\"" +ct.getPhoneNo() + "\"");   
                   json.append(",\"unitName\":\"" +ct.getUnitName() + "\"");   
                   if(ct.getName().equals(name)){
                   	json.append(",\"selected\":"+true);  
                   }
                   json.append("},");  
               }  
               str = json.toString();  
               str = str.substring(0, str.length()-1);  
               str+="]";  
                 
           } 
          return str;
       } 
       
    // 客戶列表combobox
   	@RequestMapping("/combobox")
   	@ResponseBody
   	public List<SysDict> supplierCombobox() {
   		return customerService.customerCombobox();

   	}
}