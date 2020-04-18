<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@page import="com.yhzn.model.security.User"%>
<%@page import="com.yhzn.common.util.ConfigUtil"%>
<%
	User user = (User)request.getSession().getAttribute("user");
	String username = user.getTrueName();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title><%=ConfigUtil.getConfig("companyName") %></title>

		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome & ace styles-->
		<link rel="stylesheet" href="${ctx }/resource/plugins/assets/css/bootstrap.css" />
		<link rel="stylesheet" href="${ctx }/resource/plugins/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="${ctx }/resource/plugins/assets/css/ace.css"  />
		<link rel="stylesheet" href="${ctx }/resource/plugins/contabs/contabs.css" />

	</head>

	<body class="no-skin">
		
		<!-- 首页头部开始 -->
		<div id="navbar" class="navbar navbar-default ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<!-- #section:basics/sidebar.mobile.toggle -->
				<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
					<span class="sr-only">Toggle sidebar</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<div class="navbar-header pull-left">
					<!-- #section:basics/navbar.layout.brand -->
					<a href="#" class="navbar-brand">
						<small>
							<i class="fa fa-key"></i>
							<%=ConfigUtil.getConfig("companyName") %>
						</small>
					</a>	
				</div>
			
				<!-- 头部功能菜单开始 -->
				<div class="navbar-buttons navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue dropdown-modal">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<!-- <img class="nav-user-photo" src="http://127.0.0.1:8080/xjpt/resource/plugins/assets/avatars/user.jpg"> -->
								<span class="user-info">
									<small>欢迎您，<%=username %></small>
								</span>
								<i class="ace-icon fa fa-caret-down"></i>
							</a>
							<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="ace-icon fa fa-cog"></i>
										设置
									</a>
								</li>
								<li>
									<a href="profile.html">
										<i class="ace-icon fa fa-user"></i>
										个人中心
									</a>
								</li>
								<li class="divider"></li>

								<li>
									<a href="${ctx}/logout">
										<i class="ace-icon fa fa-power-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- 头部功能菜单结束-->
			</div>
		</div>
		<!-- 首页头部结束 -->
		
		<!-- 首页下半部分开始 -->
		<div class="main-container ace-save-state" id="main-container">

			<div id="sidebar" class="sidebar responsive ace-save-state">

				<!-- 菜单导航栏工具栏开始 -->
				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<button class="btn btn-success">
							<i class="ace-icon fa fa-list-alt"></i>
						</button>

						<button class="btn btn-info">
							<i class="ace-icon fa fa-desktop"></i>
						</button>

						<!-- #section:basics/sidebar.layout.shortcuts -->
						<button class="btn btn-warning">
							<i class="ace-icon fa fa-globe"></i>
						</button>

						<button class="btn btn-danger">
							<i class="ace-icon fa fa-trophy"></i>
						</button>
					</div>
				</div>
				<!-- 菜单导航栏工具栏结束 -->
				
				<!-- 菜单导航栏开始 -->
				<ul class="nav nav-list" id="menuItem">
					<li class="active">
						<a href="#">
							<i class="menu-icon fa fa-home"></i>
							<span class="menu-text">首页</span>
						</a>

						<b class="arrow"></b>
					</li> 
							<c:forEach var="per"  items="${permission}">
					<c:if test="${per.url==null }">
					<li class="">
						<a href="#" class="dropdown-toggle">
							<i class="${per.icon}"></i>
							<span class="menu-text">
								${per.name}
							</span>
							<b class="arrow fa fa-angle-down"></b>
						</a>
						<b class="arrow"></b>
						<ul class="submenu">
						<c:forEach var="children" items="${per.children}"> 
							<li classs="">
								<a class="J_menuItem" href="${ctx}/${children.url}">
									<i class="${children.icon}"></i>
									${children.name}
								</a>
								<b class="arrow"></b>
							</li>
							
							</c:forEach>
						</ul>
					</li>
					</c:if>
					<c:if test="${per.url!=null }">
					<li class="">
						<a href="${ctx }/${per.url}" class="J_menuItem">
							<i class="${per.icon }"></i>
							<span class="menu-text">
								${per.name }
							</span>
							<b class="arrow"></b>
						</a>
						<b class="arrow"></b>
					</li>
					
					</c:if>
					</c:forEach>
				</ul>
				<!-- 菜单导航栏结束 -->
				
				<!-- 菜单工具开始 -->
				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
					<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
				<!-- 菜单工具结束 -->
			</div>
			

			<!-- 主要内容显示区开始 -->
			<div class="main-content">
				<div class="main-content-inner" id="main-content-inner">
					<!-- 标签页开始 -->
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<!-- <ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li class="active">用户管理</li>
						</ul> -->
						<div class="contabs content-tabs">
                			<button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
                			<nav class="page-tabs J_menuTabs">
                    			<div class="page-tabs-content">
                        			<a href="javascript:;" class="active J_menuTab" data_id="index_page">首页</a>
                    			</div>
                			</nav>
               				<button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i></button>
                			<div class="btn-group roll-nav roll-right">
                    			<button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
                    				<ul role="menu" class="dropdown-menu dropdown-menu-right">
                       					<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                        				<li class="divider"></li>
                        				<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                        				<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                    				</ul>
               				</div>
            			</div>
					</div>
					<!-- 标签页结束 -->
					
					<!-- 网站内容主要显示区开始 -->
					<div class="J_mainContent">
                		<iframe class="J_iframe" id="J_iframe" scrolling="no" width="100%" height="100%" name="iframe0" frameborder="0" src="${ctx }/main/toHomePage" data_id="index_page" seamless></iframe>
					</div>
					<!-- 网站内容主要显示区结束 -->
					
				</div>
			</div>				
			<!-- 主要内容显示区结束 -->		
										
			<!-- 首页底部开始 -->
			<div class="footer">
				<div class="footer-inner">
					<div class="footer-content">
						<span class="bigger-60">
						     <center style="margin-top:10px;color:red"></center>
						</span>
					</div>
				</div>
			</div>

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
			<!-- 首页底部结束 -->
			
		</div>
		<!-- 首页下半部分结束 -->
		
		<!-- basic scripts -->
		<script src="${ctx }/resource/plugins/jquery/jquery.min.js"></script>
		<script src="${ctx }/resource/plugins/bootstrap/dist/js/bootstrap.js"></script>
		<script type="text/javascript" src="${ctx }/resource/plugins/contabs/contabs.min.js"></script>
		<!-- ace scripts -->
		<script src="${ctx }/resource/plugins/assets/js/src/ace.js"></script>
		<script src="${ctx }/resource/plugins/assets/js/src/ace.basics.js"></script>
		<script src="${ctx }/resource/plugins/assets/js/src/ace.sidebar.js"></script>
	

		<script type="text/javascript">
		//调整页面高度
		$(function(){
			init();
		});
		
		function init(){
			var _height_container=$("body").height() - 100;
			var _height_content=$("body").height() - 150;
			$('#main-container').css('height',_height_container+'px');
			$('#main-content-inner').css('height',_height_content+'px');
			
			/*$.ajax({  
            	 type : "post", 
                url : "/xjpt/sysRole/getRolePermisList",  
                data : {},  
                dataType : "json",  
                success : function(data) {
                	 $(data).each(function(index,d) { 
                	 		$("#"+d.moduleNo).css("display","block");
                     });  
                }  
            }); */
		} 
		
		
		function switchModule(moduleName){
			if(moduleName == "sjjk"){
				addMenuItem("用户管理", "${ctx}/user/toUserList", "menu-icon fa fa-database");
			}
		}
	
		</script>
	
	</body>
</html>
