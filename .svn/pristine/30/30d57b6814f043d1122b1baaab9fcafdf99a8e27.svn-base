<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript"
	src="${ctx }/resource/js/dateFormatter.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui-me/common.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<div class="easyui-layout" fit="true">
	<div data-options="region:'center'" border="false">
		<table id="resoucesGrid"></table>
	</div>
</div>
<div id="resoucesGridAction" style="display: none;">
	<shiro:hasPermission name="permission:edit">
		<a class="actions easyui-linkbutton ctr edit" iconCls="icon-edit"
			plain="true">编辑</a>
	</shiro:hasPermission>
	<shiro:hasPermission name="permission:delete">
		<a class="actions easyui-linkbutton ctr delete" iconCls="icon-remove"
			plain="true">删除</a>
	</shiro:hasPermission>
</div>

<div id="resoucesGridToolbar" style="display: none;">
	<shiro:hasPermission name="permission:add">
		<a class="actions create easyui-linkbutton " iconCls="icon-add"
			plain="true">创建权限</a>
	</shiro:hasPermission>
</div>

<script src="${ctx}/resource/js/sysModule.js"></script>