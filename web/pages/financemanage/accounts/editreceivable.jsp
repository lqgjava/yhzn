<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />

<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/js/dateFormatter.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>

<body>
	<div class="panel">
	<%-- 新增修改弹出框 begin --%>
	       <form id="linkForm" method="post"  enctype="multipart/form-data"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center" style="margin-top: 10px;">
	      		 <tr height="35px;">
					<td width="25%" height="35px;" align="right">公司名称：</td>
					<td width="75%" align="left">
						<input class="easyui-textbox" value="${rece.company}" name="company" style="width:90%;" />					
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">负责人：</td>
					<td width="75%" align="left">
						<input  name="chargeMan"  value="${rece.chargeMan}"  id="man" class="easyui-textbox" style="width:90%;" missingMessage="负责人不能空" required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">负责人电话：</td>
					<td width="75%" align="left">
						<input  name="chargeTel"  value="${rece.chargeTel}" id="chargeTel" class="easyui-textbox" style="width:90%;" required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">财务人员：</td>
					<td width="75%" align="left">
						<input  name="financeMan" value="${rece.financeMan}"  id="financeMan" class="easyui-textbox" style="width:90%;"  required="true"/>
					</td>
				</tr>
				
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">财务电话：</td>
					<td width="75%" align="left">
						<input  name="financeTel" value="${rece.financeTel}"  id="financeTel" class="easyui-textbox" style="width:90%;" required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">剩余金额：</td>
					<td width="75%" align="left">
						<input  name="money" id="money" readonly="readonly"  value="${rece.money}" class="easyui-numberbox" style="width:90%;" />
					</td>
				</tr>
				
				
				
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">新增时间：</td>
					<td width="70%" align="left">
					<input class="easyui-datetimebox" value="${rece.insertDate}"  editable="fasle"  required="true" style="width:90%;" id="insertDate" name="insertDate">
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
					<input class="easyui-textbox" value="${rece.remark}"  data-options="multiline:true"  style="width:90%;height:120px"  id="remark" name="remark">
				</td>
			</table>
	    
	         <input type="hidden" name="id" id="id" value="${rece.id}"  />
	       </form> 
	   <%-- 新增修改弹出框 end --%>		

</body>
</html>
