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
<script src="${ctx}/resource/js/earlyProject.js"></script>


</head>
<body>
	<div class="panel">
		<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0" style="height: 20px; padding: 10px; overflow: hidden; width: 99%">
			<form id="earlyProjectSearchForm">
				公司名称：<input id="unitName" type="text" class="easyui-textbox" data-options="prompt:'请输入公司名称...'" /> 
				项目编号：<input id="projNo" type="text" class="easyui-textbox" data-options="prompt:'请输入项目编号...'" /> 
				项目名称：<input id="projName" type="text" class="easyui-textbox" data-options="prompt:'请输入项目名称...'" /> 
				
				 <a id="earlyProjectSearch" iconCls="icon-search" class="button_blue">检索</a> 
				 <a id="earlyReset" iconCls="icon-reload" class="button_green">重置</a>
			</form>

		</div>
		<hr>
		<div>
			<table id="earlyProjectGrid" id="query-data" style="padding: 1px; width: 100%"></table>
		</div>
	</div>
	<div id="earlyGridToolbar" style="display: none;">
		<shiro:hasPermission name="projectq:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add" plain="true">新建</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectq:edit">
			<a class="actions edit easyui-linkbutton " iconCls="icon-edit" plain="true">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectq:delete">
			<a class="actions delete easyui-linkbutton " iconCls="icon-remove" plain="true">删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectq:hide">
		<a class="actions hide easyui-linkbutton " iconCls="icon-hide" plain="true">隐藏</a>
		</shiro:hasPermission>
		<a class="actions show easyui-linkbutton " iconCls="icon-list" plain="true">显示隐藏列表</a>
	</div>
	<div id="bottonBar" style="display:none">
	<a class="actions update easyui-linkbutton " iconCls="icon-edit" plain="true">更改状态</a>
	</div>
</body>
</html>