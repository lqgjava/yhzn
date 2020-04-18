<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>客户管理</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>

<body>
	<div class="panel">
		<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0"  style="height: 20px;padding: 10px;overflow: hidden;width:99%">
		<form action="" name="QueryForm">
			客户姓名：<input type="text" name="name" id="f_name" class="easyui-textbox"  />
			客户类别：<input type="text" name="type" id="f_type" class="easyui-textbox"  />
			单位名称：<input type="text" name="unitName" id="f_unitName" class="easyui-textbox"  />
				   <input type="button" value="查询" class="button_blue" onclick="queryFun()" />
				  <input type="button" value="重置" class="button_green" onclick="resetQuery()"/>
					
		</form>
		</div>
		<hr>	
			<table id="dataGrid" ></table>	
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width:30%; height:62%; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="customerForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
	      		 <tr height="35px;">
					<td width="30%" height="35px;" align="right">客户姓名：</td>
					<td width="70%" align="left">
						<input  name="name" id="name" class="easyui-textbox" style="width:100%;"  required="true"/>						
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">单位名称：</td>
					<td width="70%" align="left">
						<input  name="unitName" id="unitName" class="easyui-textbox" style="width:100%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">税号：</td>
					<td width="70%" align="left">
						<input  name="irdNum" id="irdNum" class="easyui-textbox" style="width:100%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">银行账号：</td>
					<td width="70%" align="left">
						<input  name="accountNum" id="accountNum" class="easyui-textbox" style="width:100%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">开户行：</td>
					<td width="70%" align="left">
						<input  name="openBank" id="openBank" class="easyui-textbox" style="width:100%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">单位地址：</td>
					<td width="70%" align="left">
						<input  name="unitAddr" id="unitAddr" class="easyui-textbox" style="width:100%;"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">电话：</td>
					<td width="70%" align="left">
						<input  name="phoneNo" id="phoneNo" class="easyui-textbox" style="width:100%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">所属区域：</td>
					<td width="70%" align="left">
						<input  name="area" id="area" class="easyui-textbox" style="width:100%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">客户类别：</td>
					<td width="70%" align="left">
						<input class="easyui-combobox" id="type" name="type" style="width:100%" data-options="valueField:'id', textField:'text',required:true" >
				</td>
				</tr>
				<tr height="80px;">
					<td width="30%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:100%;height:80px"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" /> 
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveCustomer()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   <div id="TaskGridToolbar" style="display: none;">	
		<shiro:hasPermission name="customer:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add"
				plain="true">添加</a>	
				</shiro:hasPermission>
				<shiro:hasPermission name="customer:delete">
				<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
				plain="true">删除</a>	
				</shiro:hasPermission>
</div>
	
	<script type="text/javascript">
		var url;
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "客户信息列表",
			nowrap : false,
			border : true,
			locale : "zh_CN",
			height : $(window).height()-100,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/customer/queryCustomerList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'name',
				title : '客户姓名',
				width : '10%',
				align : 'center',
				formatter: function(value,row,index){
                    var str = "<a style='color:blue' href='javascript:void(0)' title='查看客户报价信息' onclick='detailFun(&apos;" + row['id'] + "&apos;)'>"+row.name+"</a>"; 
                    return str;
                }
			}, {
				field : 'unitName',
				title : '单位名称',
				width : '14%',
				align : 'center'
			}, {
				field : 'irdNum',
				title : '税号',
				width : '12%',
				align : 'center'
			}, {
				field : 'unitAddr',
				title : '单位地址',
				width : '20%',
				align : 'center'
			}, {
				field : 'phoneNo',
				title : '电话',
				width : '9%',
				align : 'center'
			}, {
				field : 'area',
				title : '所属区域',
				width : '9%',
				align : 'center'
			}, 
			{
				field : 'type',
				title : '客户类别',
				width : '8%',
				align : 'center'
			} ,
			{
				field : 'remark',
				title : '备注',
				width : '17%',
				align : 'center'
			} ] ],
			   toolbar:'#TaskGridToolbar', 
                onDblClickRow: function (index, rows) {
                	$("#dlg").dialog("open").dialog("setTitle", "客户信息修改");
		                $("#customerForm").form("load", rows);
		                url = "${ctx}/customer/editCustomer";
		                getCustomerList(rows.type);
         		}
		});
			var TaskgridPanel = datagrid.datagrid("getPanel");
			TaskgridPanel.on("click", "a.create", function() {
				 $("#dlg").dialog("open").dialog("setTitle", "客户信息新增");
                 $("#customerForm").form("clear");
                  url = "/yhzn/customer/addCustomer";
                  getCustomerList("");
			}).on("click", "a.delete", function() {
				 var rows=datagrid.datagrid("getSelections");  
         		var ids = [];
					for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
					}
                 if(rows.length<=0){  
                     $.messager.alert("提示","请选择要删除的行","error");  
                 } 
                 else{  
                 	var idArray =  new Array();
						for (var i = 0; i < rows.length; i++) {
							idArray.push(rows[i].id);
						}
						var ids= idArray.join(",");//转成字符串
                     $.messager.confirm("提示","您确定要删除吗",function(t){  
                         if(t){  
                             $.ajax({  
                            		type : "post", 
                                 url : "${ctx}/customer/deleteCustomer",  
                                 data : {"ids":ids},  
                                 dataType : "json",  
                                 success : function(r) {  
                                     if (r.success) {  
                                         datagrid.datagrid("acceptChanges");  
                                         $.messager.show({  
                                             msg : r.msg,  
                                             title : "成功"  
                                         });  
                                         editRow = undefined;  
                                         datagrid.datagrid("reload");  
                                     } else {  
                                         datagrid.datagrid("beginEdit", editRow);  
                                         $.messager.alert("错误", r.msg, "error");  
                                     }  
                                     datagrid.datagrid("unselectAll");  
                                 }  
                             });  
                           
                         }  
                     }); 
                 }  
			})

		//分页处理
		$("#dataGrid").datagrid("getPager").pagination({
			pageSize : 10,
			pageNumber : 1,
			pageList : [ 10, 20, 30, 40, 50 ],
			beforePageText : '第',//页数文本框前显示的汉字   
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
		});
	});

		//查询条件  
		function queryFun() {
			var queryParameter = $('#dataGrid').datagrid("options").queryParams;
			queryParameter.name = $("#f_name").val();
			queryParameter.type = $("#f_type").val();
			queryParameter.unitName = $("#f_unitName").val();
			$("#dataGrid").datagrid("reload");
		}
		
		//保存
		function saveCustomer() {
             $("#customerForm").form("submit", {
                url: url,
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                    if (result == "1") {
                        $.messager.alert("提示信息", "操作成功");
                        $("#dlg").dialog("close");
                        $("#dataGrid").datagrid("reload");
                    }
                    else {
                        $.messager.alert("提示信息", "操作失败");
                    }
                }
            }); 
        }
        
        //获取客户行业列表
         function getCustomerList(type){
        	$.ajax({  
             	 type : "get", 
                 url : "${ctx }/customer/getCustomerTypeList",  
                 data : {"type":type},  
                 dataType : "json",  
                 success : function(data) {
                 	 $("#type").combobox("loadData",data);
                 }  
             });  
        }
        
        //客户报价详细信息
        function detailFun(id){
        	window.location.href ="${ctx}/main/toCustPrice?customerId="+id;
        }
        
	</script>
</body>
</html>

