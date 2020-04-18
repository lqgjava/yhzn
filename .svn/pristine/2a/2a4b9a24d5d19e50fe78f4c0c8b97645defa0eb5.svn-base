package com.yhzn.common.shiro;

import java.util.Set;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import com.yhzn.model.security.User;
import com.yhzn.service.security.UserService;

/**
 * 
 * shiro认证类
 *
 */
public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		Set<String> findPermissionsByUserName = userService.findPermissionsByUserName(userName);
		authorizationInfo.setRoles(findPermissionsByUserName);
		authorizationInfo.setStringPermissions(findPermissionsByUserName);
		
		
		return authorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		
		 //1.把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2.从UsernamePasswordToken中获取username
        String username = upToken.getUsername();
        
        //从数据库获取用户记录
        User user = userService.findUserByUserName(username);
        
        if (null == user) {
			throw new UnknownAccountException("用户不存在");
		} 
        
        Object principal = username;
        
        Object credentials = user.getPassword().trim();
        
        //调用当前的realm的名字
        String realmName = getName();
        
        //获取盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        //3.用户名和密码去验证结果
        SimpleAuthenticationInfo info = null;
        info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
        return info;
	}

	
	

}