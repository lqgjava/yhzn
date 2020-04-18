<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui-me/common.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>

<body>
	<div class="title">
		<h2 style="margin-left: 30%">采购单编号：${purNo}</h2>
	</div>
	<c:forEach items="${supp}" var="supp">
	<hr style="height: 10px; border: none; border-top: 10px groove skyblue;" />
	<table class="table">
		<tr>
			<th colspan="3"><span style="text-align: left;">供应商：${supp.name}</span></th>
			<th colspan="3"><span style="text-align: right;">总计：${supp.totalPrice}</span></th>
		</tr>
		<tr>
			<th>产品</th>
			<th>品牌</th>
			<th>规格</th>
			<th>数量</th>
			<th>单价（元）</th>
			<th>合计（元）</th>
		</tr>
		<c:forEach items="${supp.plist}" var="p">
		<tr>
			<td>${p.name}</td>
			<td>${p.brand}</td>
			<td>${p.standard}</td>
			<td>${p.amount}</td>
			<td>${p.costPrice}</td>
			<td>${p.totalPrice}</td>
		</tr>
		</c:forEach>
	</table>
</c:forEach>
<script type="text/javascript">
console.log('${supp}')
</script>

</body>

</html>