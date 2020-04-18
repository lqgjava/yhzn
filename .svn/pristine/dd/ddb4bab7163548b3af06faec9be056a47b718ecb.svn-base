<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@page import="com.yhzn.model.security.User"%>
<%
	User user = (User)request.getSession().getAttribute("user");
	String username = user.getTrueName();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
<meta charset="utf-8">
<title>供应商管理</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx}/resource/js/supplier.js"></script>


</head>
<body>
<div class="panel">
	<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0"  style="height: 20px;padding: 10px;overflow: hidden;width:99%">
		<form id="SupplierSearchForm">
			供应商名称：<input id="name" type="text" class="easyui-textbox" data-options="prompt:'请输入名称...'"/>
			联系人：<input id="userName" type="text" class="easyui-textbox" data-options="prompt:'请输入联系人...'"/>  
			所属位置：<input id="area" type="text" class="easyui-textbox" data-options="prompt:'请输入位置...'"/>
			<a id="SupplierSearch" iconCls="icon-search" class="button_blue">检索</a>
			<a id="SupplierReset" iconCls="icon-reload" class="button_green">重置</a>
		</form>
		
	</div>
	<hr>
	<div>
		<table id="supplierGrid" id="query-data" style="padding:1px;"></table>
	</div>
	 <div id="supplierGridToolbar" style="display: none;">	
		<shiro:hasPermission name="supplier:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add"
				plain="true">添加</a>	
		</shiro:hasPermission>
	   <shiro:hasPermission name="supplier:delete">
				<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
		          plain="true">删除</a>	
	  </shiro:hasPermission>
	  <shiro:hasPermission name="supplier:edit">
				<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
				plain="true">编辑</a>	
	</shiro:hasPermission>
</div>
</div>
</body>
</html>