package com.yhzn.web.controller.gxc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest=(HttpServletRequest) arg0;
		HttpServletResponse httpResponse=(HttpServletResponse) arg1;
		String remoteIp=httpRequest.getRemoteAddr();
		System.out.println(remoteIp);
        if("10.154.133.145".equals(remoteIp)||"10.154.133.96".equals(remoteIp)
        		||"10.154.133.143".equals(remoteIp))
        {
        	arg2.doFilter(httpRequest,httpResponse);
        }
        else
        {
        	return;
        	//httpResponse.sendRedirect(httpRequest.getContextPath()+"/index.jsp");
        }
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
