package com.yhzn.web.controller.security;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import com.yhzn.common.controller.BaseController;
import com.yhzn.common.util.ConfigUtil;
import com.yhzn.common.util.DESUtil;
import com.yhzn.model.security.Permission;
import com.yhzn.model.security.User;
import com.yhzn.service.impl.security.ShiroService;
import com.yhzn.service.security.SysLogService;
import com.yhzn.service.security.SysRoleService;
import com.yhzn.service.security.UserService;
//import com.yhzn.service.source.UserLogsService;

/**
 * 登录管理
 * @author liany
 *
 */
@Controller
public class LoginActionController extends BaseController{

	
	//用户管理接口
	@Autowired
	private UserService userService;
	//日志管理接口
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private ShiroService shiroService;
	
	@RequestMapping("/")
	public String toHomePage(HttpServletRequest request) throws Exception{
		/*String ip=this.getRequest().getRemoteAddr();
		System.out.println(ip);
		if(ip==null||ip.indexOf("10.", 0)<0)
		{
			request.setAttribute("ifSuccess", "no");
			request.setAttribute("errDesc", "IP不在允许范围内!");
			return  "/security/pkiLogin";
		}*/
		  DESUtil des = new DESUtil("cq*&@$#2"); 
		//获取PKI随机验证数
	    /*randomStr = this.generateRandomNum();
	    request.setAttribute("randomStr", randomStr);
	    request.getSession().setAttribute("original_data", randomStr);*/
	    //判断是否单点登录进入系统
	   /* idCard = request.getParameter("sfzh");
	    if(idCard==null)
	    {
	    	  String para=  request.getQueryString();
	    	  if(para!=null)
	    	  {
	    	   String[] jimi=des.decryptStr(para).split("-");
		       idCard=jimi[jimi.length-1];
	    	  }
	    }*/
		
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String toLoginPage(){
		return "/security/login";
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/shiroLogin")
	@ResponseBody
	public String login(@RequestParam String username, @RequestParam String password,HttpServletRequest request) {
		String msg = "";
		/*String ip=this.getRequest().getRemoteAddr();
		if(ip==null||ip.indexOf("10.", 0)<0)
		{
			return "{\"info\":\"IP不在允许范围内!\",\"status\":\"n\"}";
		}*/
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 将用户名和密码封装为UsernamePasswordToken对象
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			token.setRememberMe(true);
			currentUser.login(token);
			String hashAlgorithName = "MD5";
			Object credentials = password;
			Object salt = ByteSource.Util.bytes(username);
			int hashIterations = 1024;
			Object result = new SimpleHash(hashAlgorithName,credentials,salt,hashIterations);
		//	user.setPassword(String.valueOf(result));
			HashMap<String,String> para=new HashMap<String,String>();
			para.put("userName", username);
			para.put("password", String.valueOf(result));
			User user = userService.checkUser(para);
			
			if(user==null){
				msg = "{\"info\":\"用户名或密码错误！\",\"status\":\"n\"}";
			}else{
				currentUser.isPermitted(username);	
				 String roleId= sysRoleService.findRolesByUserName(user.getUserName());
				List<Permission> permission = sysRoleService.permissionById(roleId);
				Set<String> biaoshi = userService.findPermissionsByUserName(user.getUserName());
				request.getSession().setAttribute("biaoshi", biaoshi);
				request.getSession().setAttribute("permission", permission);
				//获取访问者IP
			//String ip = this.getRequest().getRemoteAddr();
			//user.setLoginIp(ip);
			user.setLoginIp(getIp(request));
			//日志类型，操作人，操作内容，操作人IP,操作方法
			sysLogService.insertSysLog("登录",user.getTrueName(),"登录系统",user.getLoginIp(),"/shiroLogin");
			this.getRequest().getSession().setAttribute("user", user);
				
			msg = "{\"info\":\"登录成功！\",\"status\":\"y\"}";
			}
		} catch (UnknownAccountException e) {
			msg = "{\"info\":\"登录失败【用户不存在】！\",\"status\":\"n\"}";
		} catch (IncorrectCredentialsException e) {
			msg = "{\"info\":\"登录失败【密码错误】！\",\"status\":\"n\"}";
		} catch (LockedAccountException e) {
			msg = "{\"info\":\"登录失败【用户已锁】！\",\"status\":\"n\"}";
		} catch (AuthenticationException e) {
			msg = "{\"info\":\"登录失败【认证异常】！\",\"status\":\"n\"}";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "{\"info\":\"用户名或密码错误！\",\"status\":\"n\"}";
		}
		return msg;
	}
	
	/**
	 * 获取访问者ip
	 * @param request
	 * @return
	 */
	public  String getIp(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

	
	@RequestMapping("/home")
	public String toHomePage(){
		return "/security/homeCenter";
	}
	
	@RequestMapping("/error")
	public String toErrorPage(){
		return "/security/error";
	}

	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		//清除session
		session.invalidate();
		return "redirect:/login";
	}
}
