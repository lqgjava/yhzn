<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>应收款列表</title>
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
	<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0"  style="height: 20px;padding: 10px;overflow: hidden;width:98.5%">
		<form id="SearchForm">
			单位名称：<input id="company" type="text" class="easyui-textbox" data-options="prompt:'请输入名称...'"/>
			负责人：<input id="chargeMan" type="text" class="easyui-textbox" data-options="prompt:'请输入编号...'"/>  
			<a id="Search" iconCls="icon-search" class="button_blue">检索</a>
			<a id="Reset" iconCls="icon-reload" class="button_green">重置</a>
		</form>
		
	</div>
	<hr>
	<table id="dg">
		</table>
		
		 <div id="ReceGridToolbar" style="display: none;">	
			<shiro:hasPermission name="finance:add">
		<a class="actions add easyui-linkbutton " iconCls="icon-add"
			plain="true">新建</a>
	</shiro:hasPermission>
	  	<shiro:hasPermission name="finance:edit">
		<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
			plain="true">编辑</a>
	</shiro:hasPermission>
	 	<shiro:hasPermission name="finance:delete">
		<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
			plain="true">删除</a>
	</shiro:hasPermission>
</div>
	</div>
	
	
		<script>
			$(document).ready(function() {
				$('#dg').datagrid({
					nowrap : false,
					locale : "zh_CN",
					iconCls : 'icon-save',
					striped : true,
					 height: $(window).height()-100,
					collapsible : true,
					url : '${ctx}/finance/queryOweMoney',
					rownumbers : true,
					pagination : true,//表示在datagrid设置分页     
					singleSelect : true,
					onDblClickRow: function(rowIndex){//鼠标双击事件
						$('#dg').datagrid("selectRow",rowIndex);//选中此行
					        var currentRow = $('#dg').datagrid("getSelections");//获得单机选中行，双击时只能选择一条
					        var row=$('#dg').datagrid("getSelected");//获取选中行的数据
					        //最后一行tr下标
					       var lastIndex = $('#dg').datagrid('getRows').length-1;
					        if (currentRow.length > 1) {
								$.messager.alert('提示', '只能选中一条数据', 'error');
							}else if(lastIndex==rowIndex){
								  
							} else{
								var id=row.id;
								window.name=id;
								var company=row.company
					        	linkForm(company);
							}
					    },
					columns : [ [ 
						{
						field : 'company',
						title : '公司名称',
						width : '20%',
						align : 'center' 
					},{
						field : 'chargeMan',
						title : '负责人',
						width : '10%',
						align : 'center' 
					}, {
						field : 'chargeTel',
						title : '电话',
						width : '10%',
						align : 'center'
					}, {
						field : 'financeMan',
						title : '财务人员',
						width : '10%',
						align : 'center'
					}, 
					{
						field : 'financeTel',
						title : '电话',
						width : '8%',
						align : 'center'
					} ,
					{
						field : 'money',
						title : '应收金额',
						width : '8%',
						align : 'center'
					},
					{
						field : 'xx',
						title : '支付状况',
						width : '200',
						align : 'center',
						formatter : function(value, row, index) {
							if(row.id!='2'){
							if(row.money=="0"){
							return "<label>正常</label>";
							}else{
								return "<label style='color:red'>正常</label>";
							}
						}
						}
					},{
						field : 'insertDate',
						title : '新增时间',
						width : '10%',
						align : 'center'
						
					},
					{
						field : 'remark',
						title : '备注',
						width : '25%',
						align : 'center'
					}] ] , toolbar:'#ReceGridToolbar',
		       })    
		       
			var TaskgridPanel = $('#dg').datagrid("getPanel");
			TaskgridPanel.on("click", "a.add", function() {
				  receivableForm();
			}).on("click", "a.delete", function() {
				 var row = $('#dg').datagrid('getSelected');
				 var rows =  $('#dg').datagrid('getSelections');
 				if (rows.length <= 0) {
 					$.messager.alert('提示', '请选择要删除的行', 'error');
 				}
		           var id=row.id;
		     				 	$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
		     						// 点击了确定按钮，r返回的结果为true
		     						
		     						if (r) {
		     							$.get("../finance/deleteReceivable/" + id, function(data) {
		     								if (data.success) {
		     									// 刷新表格
		     									$("#dg").datagrid("reload");
		     								} else {
		     									$.message.alert("提示", "删除失败");
		     								}
		     							});
		     						}
		     					}); 
			}).on("click","a.edit",function(){
				var rows = $('#dg').datagrid('getSelections');
				if (rows.length <= 0) {
					$.messager.alert('提示', '请选择要修改的行', 'error');
				} else if (rows.length > 1) {
					$.messager.alert('提示', '只能选择一条数据进行修改', 'error');
				} else if (rows.length == 1) {
					var id = rows[0].id;
					receivableForm(id);
				}
				
				
		     				
			})
			
			
				$('#dg').datagrid({
					onLoadSuccess: function(data) {
						var rows = $('#dg').datagrid('getRows') //获取当前的数据行
						var ptotal = 0 //计算money的总和
						
						for(var i = 0; i < rows.length; i++) {
							ptotal += parseFloat(rows[i]['money']);
						}
						//新增一行显示统计信息
						$('#dg').datagrid('appendRow', {
							id:'2',
							financeTel: '<b>合计：</b>',
							money:ptotal,
						});
					},
					rowStyler: function(index, row) {
						if(row.financeTel == '<b>合计：</b>') {
							return 'background-color:#EAEAEA;color:blue';
						}
					}
					
				});
				function linkForm(company) {
					// 创建窗口
					var dialog = $("<div/>").dialog(	{
								href : '../finance/addreceivable?company='+company,
								title :'账款详情',
								height : '90%',
								width : '100%',
								modal : true,
								onClose : function() {
									// 窗口关闭的同时销毁此窗口
									$(this).dialog("destroy");
								},
								buttons: [{
									iconCls: 'icon-back',
									text: '返回',
									handler: function() {
										//关闭窗口
										dialog.dialog("close");
										//刷新数据表格
										$("#dg").datagrid("reload");
									}
								}]
							});
				}
				// 搜索按钮事件
				var chargeMan=$("#chargeMan");
				var company=$("#company");
				$("#Search").on('click', function() {
					$('#dg').datagrid("load", {
						chargeMan : "%" + chargeMan.val() + "%",
						company : "%" + company.val() + "%",
					});
				});
				// 重置事件
				var form = $("#earlyProjectSearchForm");
				$("#earlyProjectReset").on('click', function() {
					form.form('clear');
					// 清除查询参数
					$('#dg').datagrid("load", {});
				});
				function receivableForm(id) {
					// 创建窗口
					var dialog = $("<div/>").dialog(
							{
								href : '../finance/addcontract' + (id ? ("/" + id) : ""),
								title : "新增账款信息",
								height : '520',
								width : '500',
								modal : true,
								onClose : function() {
									// 窗口关闭的同时销毁此窗口
									$(this).dialog("destroy");
								},
								buttons: [{
									iconCls: 'icon-save',
									text: '保存',
									handler: function() {
										var linkForm = $("#linkForm");
										// 校验表单
										if (linkForm.form('validate')) {
											// serialize方法可以获取到表单所有数据
											$.post("../finance/addReceivableModel", linkForm
													.serialize(), function(data) {
												if (data.success) {
													// 刷新数据表格
													$("#dg").datagrid("reload");
													// 关闭窗口
													dialog.dialog("close");
												} else {
													$.messager.alert("提示", "操作失败");
												}
											});
										}
									}
								}]
							});
				}
			});
		</script>
	</body>
</html>

