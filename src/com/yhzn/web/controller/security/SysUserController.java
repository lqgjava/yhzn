package com.yhzn.web.controller.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
import com.yhzn.model.person.Person;
import com.yhzn.model.security.SysDict;
import com.yhzn.model.security.User;
import com.yhzn.service.person.PersonService;
import com.yhzn.service.security.SysDictService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.security.UserService;

/**
 * 系统管理模块
 * @author liany
 *
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	//用户管理接口
	@Autowired
	private UserService userService;
	//字典管理接口
	@Autowired
	private SysDictService sysDictService;
	//人员管理接口
	@Autowired
	private PersonService personService;

	@RequiresPermissions("user:list")
	@RequestMapping(value="/querySysUserList", method = RequestMethod.POST)
	@ResponseBody
	public PageUtil querySysUserList(HttpServletRequest request){
		
		User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
	//	sysLogService.insertSysLog("查询",user.getTrueName(),"查询用户信息列表 ",user.getLoginIp(),"/sysUser/querySysUserList");
		
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		String userName = request.getParameter("userName");//用户名
		String trueName = request.getParameter("trueName");//姓名
		String dept = request.getParameter("dept");//单位名称
		String beginDate = request.getParameter("beginDate");//开始时间
		String endDate = request.getParameter("endDate");//结束时间
		
		Map<String,Object> parameter = new HashMap<String,Object>();
			parameter.put("userName", userName);
			parameter.put("trueName", trueName);
			parameter.put("dept", dept);
			parameter.put("beginDate", beginDate);
			parameter.put("endDate", endDate);

		PageBounds bounds = new PageBounds(page , rows );
		List<User> list = userService.querySysUserList(bounds, parameter);
		// 获得结果集条总数
		int total = ((PageList<User>) list).getPaginator().getTotalCount();
		// 页面列表展示
		PageUtil result = new PageUtil();
		result.setRows(list);
		result.setTotal(total);
		
		return result;

	}
	
	
	/**
	 * 删除用户信息
	 * @param id
	 * @return
	 */
	@RequiresPermissions("user:delete")
	@RequestMapping("/deleteSysUser")  
    @ResponseBody  
    public Map<String,Object> deleteUserById(HttpServletRequest request) {  
		
        Map<String,Object> map = new HashMap<String,Object>();  
        //修改删除标记del为 1
        String ids = request.getParameter("ids");
        User user= (User) request.getSession().getAttribute("user");
        //日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("删除",user.getTrueName(),"删除用户id为 "+ids,user.getLoginIp(),"/sysUser/deleteSysUser");

        String[] idArray = ids.split(",");
        if(null!=ids && idArray.length>0){
        	for(int i=0;i<idArray.length;i++){
        		userService.deleteUserById(idArray[i]); 
        	}
        }
        map.put("success", 1);  
        return map;  
    }  
	
	/**
	 * 新增用户信息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequiresPermissions("user:add")
	@RequestMapping(value="/addSysUser",method= RequestMethod.POST)
	@ResponseBody
	public String addSysUser(User user,HttpServletRequest request){
		User user1= (User) request.getSession().getAttribute("user");
		//日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("新增",user1.getTrueName(),"新增用户 "+user.getUserName(),user.getLoginIp(),"/sysUser/addSysUser");

		user.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		//shiro盐值加密
		String hashAlgorithName = "MD5";
		Object credentials = user.getPasswordNew();
		Object salt = ByteSource.Util.bytes(user.getUserName());
		int hashIterations = 1024;
		Object result = new SimpleHash(hashAlgorithName,credentials,salt,hashIterations);
		user.setPassword(String.valueOf(result));
		user.setDeleteFlag("0");
		user.setCreateName(user1.getTrueName());
		int flag = userService.saveUser(user);
		if (flag > 0) {
			return "1";
		} else {
			return "false";
		}
	}
	
	/**
	 * 修改用户信息
	 * @param user
	 * @param request
	 * @return
	 */
	@RequiresPermissions("user:edit")
	@RequestMapping(value="/editSysUser",method= RequestMethod.POST)
	@ResponseBody
	public String editSysUser(User user,HttpServletRequest request){
		
		User user1= (User) request.getSession().getAttribute("user");
		//日志类型，操作人，操作内容，操作人IP,操作方法
		sysLogService.insertSysLog("修改",user.getTrueName(),"修改用户 "+user.getUserName(),user.getLoginIp(),"/sysUser/editSysUser");

		//用户修改密码需要重新加密
		if(null != user.getPasswordNew() && ""!=user.getPasswordNew()){
			//shiro盐值加密
			String hashAlgorithName = "MD5";
			Object credentials = user.getPasswordNew();
			Object salt = ByteSource.Util.bytes(user.getUserName());
			int hashIterations = 1024;
			Object result = new SimpleHash(hashAlgorithName,credentials,salt,hashIterations);
			user.setPassword(String.valueOf(result));
		}
		//更新用户信息
		int flag = userService.updateUser(user);
		if (flag > 0) {
			return "1";
		} else {
			return "false";
		}
	}
	
    /**
	 * 获取人员列表
	 * @param id
	 * @return
     * @throws IOException 
	 */
    @RequestMapping(value="/getPersonList",method= RequestMethod.POST)
    @ResponseBody  
    public String getPersonList(String personId,HttpServletRequest request) throws IOException {
    	String str ="";  
        StringBuilder json = new StringBuilder();  
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("id", personId);
        map.put("rootKey", "BMLBDM");
        map.put("parentKey", "BMLBDM");
        // 获取部门字典代码
        List<SysDict> sysDictList = sysDictService.findDictListByMap(map);
        // 遍历子节点下的子节点  
        if(sysDictList!=null && sysDictList.size()!=0){  
            json.append("[");  
            for (SysDict s : sysDictList) {  
                  
                json.append("{\"id\":\"" +s.getId()+ "\"");   
                json.append(",\"text\":\"" +s.getDictValue() + "\"");   
                json.append(",\"state\":\"open\"");  
                json.append(",\"name\":\"parent\""); 
                map.put("dept", s.getDictValue());
                // 该节点有子节点  
                // 设置为关闭状态,而从构造异步加载tree  
                List<Person> sList = personService.queryPersonListByMap(map);  
                if(sList!=null && sList.size()!=0){// 存在子节点  
                     json.append(",\"children\":[");  
                     json.append(dealJsonFormat(sList,personId));// 存在子节点的都放在一个工具类里面处理了  
                     json.append("]");  
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
     * 处理数据集合，将数据集合转为符合格式的json 
     * @param sList 参数 
     * @return json字符串 
     */  
    public String dealJsonFormat(List<Person> sList,String id){  
        StringBuilder json = new StringBuilder();  
        for (Person person : sList) {  
            json.append("{\"id\":\"" +person.getId()+ "\"");   
            json.append(",\"text\":\"" +person.getName() + "\"");   
            json.append(",\"state\":\"open\""); 
            if(StringUtils.isNotBlank(id) && id.indexOf(person.getId())>-1){
            	json.append(",\"checked\":"+true);  
            }
            json.append("},");  
        }  
        String str = json.toString();  
        str = str.substring(0, str.length()-1); 
        return str;  
    }  
	
}
