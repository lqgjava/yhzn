<%@page import="java.net.InetAddress"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.yhzn.common.util.ConfigUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
	String hostAddress = "";
	try{
		InetAddress address = InetAddress.getLocalHost();
		hostAddress = address.getHostAddress();
	}catch(Exception e){
		e.printStackTrace();    
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">

    <head>
  	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta charset="utf-8" />
    <title><%=ConfigUtil.getConfig("companyName") %></title>
	<meta name="description" content="User login page" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <!-- CSS -->
        <link rel="stylesheet" href="${ctx}/resource/plugins1/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx}/resource/plugins1/assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${ctx}/resource/plugins1/assets/css/form-elements.css">
        <link rel="stylesheet" href="${ctx}/resource/plugins1/assets/css/style.css">
    </head>
    <body>
        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong><%=ConfigUtil.getConfig("companyName") %></strong></h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>欢迎回来</h3>
                            		<p>输入用户名密码</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form id="loginForm" action="${ctx }/shiroLogin" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" name="username" placeholder="用户名..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="password" placeholder="密码..." class="form-password form-control" id="form-password">
			                        </div>
			                        <button id="login" type="submit" class="btn">登  陆</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Javascript -->
        <script src="${ctx }/resource/plugins1/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${ctx }/resource/plugins1/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${ctx }/resource/plugins1/assets/js/jquery.backstretch.min.js"></script>
<!--         <script src="${ctx }/resource/plugins1/assets/js/scripts.js"></script> -->
 		<script type="text/javascript" src="${ctx }/resource/plugins/validform/Validform_v5.3.2_min.js"></script>
 		<link rel="stylesheet" href="${ctx }/resource/plugins/validform/Validform.css"/>
        <script>
        	jQuery(document).ready(function() {
	
		    /*
		     *   全屏背景图
		    */
		    $.backstretch("resource/plugins1/assets/img/backgrounds/33.jpg");
		    
		    /*
		        Form validation
		    */
		    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
		    	$(this).removeClass('input-error');
		    });
		    
		    /*$('.login-form').on('submit', function(e) {
		    	
		    	//验证用户名密码
		    	var username= $('.login-form input[type="text"]').val();
		    	var password = $('.login-form input[type="password"]').val();
		    	if(username == ""){
		    		e.preventDefault();
		    		$('#form-username').addClass('input-error');
		    		alert("请输入用户名！");
					return false;
		    	}
		    	if(password == ""){
		    		e.preventDefault();
		    		$('#form-password').addClass('input-error');
		    		alert("请输入密码！");
					return false;
		    	}
		    });*/
		    
		    
		});
		
		
	
		
		$(function(){
    		$("#loginForm").Validform({
    			tiptype : 3,
        		postonce: true,
        		ajaxPost : true,
        		callback : function(data){
        			if(data.status == "y"){
						window.location.href = "/yhzn/home";
					}else{
						setTimeout(function(){
							$.Hidemsg();
						},2000);
					}
        		}
    		});
		});
		
        </script>

    </body>

</html>
