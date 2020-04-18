<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>新增项目</title>
<style>
.combo {
	width: 273px
}
</style>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<form id="ProjectForm" method="post">
		<table border="0" width="100%" cellpadding="0">
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">项目名称：</td>
				<td width="70%" align="left"><input name="projName" id="projName" class="easyui-textbox" value="${schedule.projName}" data-options="required:true" missingMessage="项目名称不能空" size="35px" /></td>
			</tr>
			<tr height="35px;">
				<td width="18%" height="35px;" align="right">项目类别：</td>
				<td width="50%" align="left"><input class="easyui-combobox" id="projType" name="projType" style="width: 180px" value="${schedule.projType}" data-options="url: '${ctx }/customer/getCustomerTypeList', method: 'get',valueField:'id',textField:'text', required:true"></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">业务经理：</td>
				<td width="70%" align="left"><input name="contacts" id="contacts" class="easyui-textbox" size="35px" value="${schedule.contacts}" data-options="required:true" missingMessage="业务经理姓名不能空" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">电话：</td>
				<td width="70%" align="left"><input name="phone" id="phone" validType='phoneNum' class="easyui-textbox" value="${schedule.phone}" size="35px" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">单位名称：</td>
				<td width="70%" align="left">
				<select id="cc" class="easyui-combogrid" name="unitName" style="width: 99%;" onkeydown="if(event.keyCode==13)query()" data-options="
    panelWidth:330,
    value:'${schedule.unitName}',
    idField:'unitName',
    textField:'单位名称',
    url:'${ctx}/customer/queryCustomerList',
    type:'post',
    pagination:true,
    columns:[[
    {field:'unitName',title:'公司名称',width:200},
    {field:'name',title:'联系人',width:100},
    ]]
    "></select> 
    </td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">项目单位名称：</td>
				<td width="70%" align="left"><input name="projUnitName" id="projUnitName" class="easyui-textbox" value="${schedule.projUnitName}" size="35px" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">甲方联系人：</td>
				<td width="70%" align="left"><input name="projContacts" id="projContacts" class="easyui-textbox" value="${schedule.projContacts}" size="35px" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">甲方联系电话：</td>
				<td width="70%" align="left"><input name="projTel" id="projTel" class="easyui-textbox" value="${schedule.projTel}" size="35px" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">甲方地址：</td>
				<td width="70%" align="left"><input name="projAddress" id="projAddress" class="easyui-textbox" value="${schedule.projAddress}" size="35px" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">职位：</td>
				<td width="70%" align="left"><input name="projPosition" id="projPosition" class="easyui-textbox" size="35px" value="${schedule.projPosition}" /></td>
			</tr>
			<tr height="35px;">
				<td width="30%" height="35px;" align="right">项目进程：</td>
				<td width="70%" align="left"><select class="easyui-combobox" name="projStatus" style="width: 90px; text-align: center;" panelHeight="90px" data-options="required:true">
						<option value="0" ${'1'==schedule.projStatus?'selected':'' }>沟通中</option>
						<option value="1" ${'2'==schedule.projStatus?'selected':'' }>进行中</option>
						<option value="2" ${'3'==schedule.projStatus?'selected':'' }>已完成</option>
				</select></td>
			</tr>
			</tr>
			<tr height="80px;">
				<td width="30%" height="35px;" align="right">备注：</td>
				<td width="70%" align="left"><input class="easyui-textbox" data-options="multiline:true" size="35px" style="height: 80px" value="${schedule.remark}" id="remark" name="remark"></td>
			</tr>
		</table>
		<input type="hidden" name="id" id="id" value="${schedule.id}" />
	</form>
</body>
<script type="text/javascript">
$('#cc').combogrid({ 
	  keyHandler: {
        up: function() {},
        down: function() {},
        enter: function() {},
        query: function(q) {
            //动态搜索
            $('#cc').combogrid("grid").datagrid("reload", { 'unitName': q });
            $('#cc').combogrid("setValue", q);
        }
    }, onSelect: function (rowIndex, rowData) {
  	  var g = $('#cc').combogrid('grid');	// 获取表格控件对象
  	  var r = g.datagrid('getSelected');	//获取表格当前选中行
    }
});
</script>
</html>