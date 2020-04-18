<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>新增供应商</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>	 
	       <form id="supplierForm" method="post"> 
	       <table  border="0" width="100%" cellpadding="0">
	      		 <tr height="35px;">
					<td width="30%" height="35px;" align="right">供应商名称：</td>
					<td width="70%" align="left">
						<input  name="name" id="name" class="easyui-textbox" value="${supplier.name}"  size="35px"   required="true"/>						
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">产品名称：</td>
					<td width="70%" align="left">
						<input  name="product" id="product" class="easyui-textbox" size="35px"  value="${supplier.product}"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">税号：</td>
					<td width="70%" align="left">
						<input  name="dutyPargraph" id="dutyPargraph" class="easyui-textbox" value="${supplier.dutyPargraph}" size="35px"   />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">银行账号：</td>
					<td width="70%" align="left">
						<input  name="bankAccount" id="bankAccount" class="easyui-textbox" value="${supplier.bankAccount}" size="35px"   />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">开户行：</td>
					<td width="70%" align="left">
						<input  name="openBank" id="openBank" class="easyui-textbox" value="${supplier.openBank}"  size="35px"  />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">单位地址：</td>
					<td width="70%" align="left">
						<input  name="supplierAddress" id="supplierAddress" class="easyui-textbox" value="${supplier.supplierAddress}" size="35px"  />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">联系人：</td>
					<td width="70%" align="left">
						<input  name="userName" id="userName" class="easyui-textbox"  size="35px" value="${supplier.userName}"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">电话：</td>
					<td width="70%" align="left">
						<input  name="userTel" id="userTel" class="easyui-textbox"   size="35px" value="${supplier.userTel}" required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">所属区域：</td>
					<td width="70%" align="left">
						<input  name="area" id="area" class="easyui-textbox"  size="35px" value="${supplier.area}"  required="true"/>
					</td>
				</tr>
				
				</tr>
				<tr height="80px;">
					<td width="30%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
						<input class="easyui-textbox" data-options="multiline:true" size="35px" style="height:80px" value="${supplier.remark}"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" value="${supplier.id}"/> 
	       </form> 
</body>
</html>