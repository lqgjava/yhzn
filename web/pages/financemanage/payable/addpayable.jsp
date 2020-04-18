<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>用户管理</title>

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
					<select id="cc" class="easyui-combogrid" name="company" style="width:90%;" onkeydown="if(event.keyCode==13)query()"
    data-options="
    panelWidth:450,
    value:'${rece.company}',
    idField:'name',
    textField:'公司名称',
    url:'${ctx}/supplier/findAll',
    type:'post',
    pagination:true,
    columns:[[
    {field:'name',title:'公司名称',width:200},
    {field:'userName',title:'联系人',width:100},
    {field:'userTel',title:'电话',width:120},
    ]]
    "></select>
				
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">负责人：</td>
					<td width="75%" align="left">
						<input  name="chargeMan" id="man" value="${rece.chargeMan}" class="easyui-textbox" style="width:90%;" missingMessage="负责人不能空" required="true"/>
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
					<td width="25%" height="35px;" align="right">应付金额：</td>
					<td width="75%" align="left">
						<input  name="money" id="money"  value="${rece.money}" precision="2" max="999999999" class="easyui-numberbox" style="width:90%;" />
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
		<script type="text/javascript">

	$('#cc').combogrid({ 
		  keyHandler: {
              up: function() {},
              down: function() {},
              enter: function() {},
              query: function(q) {
                  //动态搜索
                  $('#cc').combogrid("grid").datagrid("reload", { 'name': q });
                  $('#cc').combogrid("setValue", q);
              }
          }, onSelect: function (rowIndex, rowData) {
        	  var g = $('#cc').combogrid('grid');	// 获取表格控件对象
        	  var r = g.datagrid('getSelected');	//获取表格当前选中行
        	  console.log(r);
        	 $("#man").textbox('setValue',r.userName);
        	  $('#chargeTel').textbox('setValue',r.userTel);
          }
      });
</script>
</body>

</html>
