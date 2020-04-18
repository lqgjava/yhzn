<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@page import="com.yhzn.model.security.User"%>
<%
	User user = (User) request.getSession().getAttribute("user");
	String username = user.getTrueName();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
<meta charset="utf-8">
<title>项目前期 页面</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx}/resource/js/earlyProject_hide.js"></script>


</head>
<body>
	<div class="panel">
		<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0" style="height: 20px; padding: 10px; overflow: hidden; width: 99%">
			<form id="earlyProjectSearchForm">
				公司名称：<input id="unitName" type="text" class="easyui-textbox" data-options="prompt:'请输入公司名称...'" /> 
				业务经理：<input id="contacts" type="text" class="easyui-textbox" data-options="prompt:'请输入业务经理...'" /> 
				项目名称：<input id="projName" type="text" class="easyui-textbox" data-options="prompt:'请输入项目名称...'" /> 
				
				 <a id="earlyProjectSearch" iconCls="icon-search" class="button_blue">检索</a> 
				 <a id="showlist" iconCls="icon-search" class="button_blue">显示列表</a> 
				 <a id="earlyhide" iconCls="icon-reload" class="button_green">重置</a>
			</form>

		</div>
		<hr>
		<div>
			<table id="earlyProjectGrid" id="query-data" style="padding: 1px; width: 100%"></table>
		</div>
	</div>

	<div id="bottonBar" style="display:none">
	<a class="actions show easyui-linkbutton " iconCls="icon-edit" plain="true">改为显示</a>
	</div>

	
</body>
</html>