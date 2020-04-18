<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>销售合同页面</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/plugins/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx }/resource/css/pur_supplier.css">
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui-me/common.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/js/dateFormatter.js"></script>
</head>
<body>
	<!--panel：面板  -->
	<div class="panel">
		<!-- easyui-dialog：easyui对话框  	display：规定元素应该生成的框的类型。 display:none 此元素不会被显示。  easyui-combobox：easyui下拉框  easyui-textbox：easyui时间框 -->
		<div id="dlg" class="easyui-dialog"
			style="display:none;width:600px; height: 440px;top:10px; padding: 5px 10px;"
			closed="true" buttons="#dlg-buttons">
			<%-- 新增弹出框 begin --%>
			<form id="customerForm" method="post">
				<table border="0" width="100%" cellpadding="0">
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">客户名称：</td>
						
						<td width="70%" align="left"><input name="customerId"
							id="addCustomerId" class="easyui-combobox" style="width: 86%;"
							required="true" /></td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">应收金额：</td>
						<td width="70%" align="left"><input name="money" value="0"
							class="easyui-textbox" size="50px" style="width: 84%;" required="true" />元</td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">已收金额：</td>
						<td width="70%" align="left"><input name="acceptMoney" value="0"
							class="easyui-textbox" size="50px" style="width: 84%;" />元</td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">合同名称：</td>
						<td width="70%" align="left"><input name="contractName"
							class="easyui-textbox" size="50px" style="width: 86%;" /></td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">合同编号：</td>
						<td width="70%" align="left"><input name="contractNo"
							class="easyui-textbox" size="35px" style="width: 86%;" /></td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">签订时间：</td>
						<td width="70%" align="left"><input name="sign"
							class="easyui-datebox" size="35px" style="width: 86%;" /></td>
					</tr>
					</tr>
					<tr height="80px;">
						<td width="30%" height="35px;" align="right">备注：</td>
						<td width="70%" align="left"><input class="easyui-textbox"
							data-options="multiline:true" size="35px" style="height:150px;width:200px;"
							name="remark"></td>
					</tr>
				</table>
			</form>
		</div>
		<%-- 新增弹出框 end --%>

		<%-- 修改弹出框 begin --%>
		<div id="editDlg" class="easyui-dialog"
			style="display:none;width:600px; height: 440px;top:10px; padding: 5px 10px;"
			closed="true" buttons="#dlg-buttons">
			<form id="editCustomerForm" method="post">
				<table border="0" width="100%" cellpadding="0">
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">客户名称：</td>
						<td width="70%" align="left"><input name="customerId"
							id="editCustomerId" class="easyui-combobox" size="35px"
							required="true" style="width: 86%;" /></td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">应收金额：</td>
						<td width="70%" align="left"><input name="money" id="money"
							class="easyui-textbox" size="33px" required="true" style="width: 84%;" />元</td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">已收金额：</td>
						<td width="70%" align="left"><input name="acceptMoney"
							id="acceptMoney" value="0" class="easyui-textbox" size="33px" style="width: 84%;" />元</td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">合同名称：</td>
						<td width="70%" align="left"><input name="contractName"
							id="contractName" class="easyui-textbox" size="35px" style="width: 86%;" /></td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">合同编号：</td>
						<td width="70%" align="left"><input name="contractNo"
							id="contractNo" class="easyui-textbox" size="35px" style="width: 86%;" /></td>
					</tr>
					<tr height="35px;">
						<td width="30%" height="35px;" align="right">签订时间：</td>
						<td width="70%" align="left"><input name="sign" id="sign"
							class="easyui-datebox" size="35px" style="width: 86%;" /></td>
					</tr>
					</tr>
					<tr height="80px;">
						<td width="30%" height="35px;" align="right">备注：</td>
						<td width="70%" align="left"><input class="easyui-textbox"
							data-options="multiline:true" size="35px" style="height:150px;width:200px;"
							name="remark" id="remark"></td>
					</tr>
				</table>
			</form>
		</div>
		<%-- 修改弹出框end --%>


		<div class="panel">
			<!-- 返回面板（panel）头部对象  -->
			<div class="panel-header" data-options="region:'north' " border="0"
				cellspacing="5" cellpadding="0"
				style="height: 20px;padding: 10px;overflow: hidden;width:98.5%">
				<form id="SearchForm">
					销售编号：<input id="company" type="text" class="easyui-textbox"
						data-options="prompt:'请输入名称...'" /> 负责人：<input id="chargeMan"
						type="text" class="easyui-textbox"
						data-options="prompt:'请输入编号...'" /> <a id="Search"
						iconCls="icon-search" class="button_blue">检索</a> <a id="Reset"
						iconCls="icon-reload" class="button_green">重置</a>
				</form>

			</div>
			<hr>
			<!-- 表格需要用js进行渲染 -->
			<table id="dg">
			</table>
			<div id="ReceGridToolbar" style="display: none;">
				<shiro:hasPermission name="pay:add">
					<a class="actions add easyui-linkbutton " iconCls="icon-add"
						plain="true">新建</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="pay:edit">
					<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
						plain="true">编辑</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="pay:delete">
					<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
						plain="true">删除</a>
				</shiro:hasPermission>
			</div>
		</div>
		<script>
			$(document).ready(function() {
		
				$('#dg').datagrid({
					title : '销售合同列表',
					nowrap : false,   /* 设置为 true，则把数据显示在一行里。设置为 true 可提高加载性能。 */
					locale : "zh_CN",
					iconCls : 'icon-save', /* 添加按钮 */
					striped : true,  /* 设置为 true，则把行条纹化。（即奇偶行使用不同背景色） */
					collapsible : true,
					scrollbarSize : 0,   /* 滚动条宽度（当滚动条是垂直的时候）或者滚动条的高度（当滚动条是水平的时候） */
					height : $(window).height() - 100,
					url : '${ctx}/financialSalesContract/queryFinancialSalesContractList',
					rownumbers : true,   /* 设置为 true，则显示带有行号的列 */
					pagination : true, //表示在datagrid设置分页     
					singleSelect : true,
					onDblClickRow : function(rowIndex) { //鼠标双击事件
						$('#dg').datagrid("selectRow", rowIndex); //选中此行
						var currentRow = $('#dg').datagrid("getSelections"); //获得单机选中行，双击时只能选择一条
						var row = $('#dg').datagrid("getSelected"); //获取选中行的数据
						//最后一行tr下标
						var lastIndex = $('#dg').datagrid('getRows').length - 1;
						if (currentRow.length > 1) {
							$.messager.alert('提示', '只能选中一条数据', 'error');
						} else if (lastIndex == rowIndex) {
		
						} else {
							var id = row.id;
							var customerId = row.customerId;
							linkForm(customerId);
						}
					},
					columns : [ [
						{
							field : 'customerName',
							title : '客户名称',
							width : '18%',
							align : 'center'
						}, {
							field : 'money',
							title : '应收金额',
							width : '6%',
							align : 'center'
						}, {
							field : 'acceptMoney',
							title : '已收金额',
							width : '6%',
							align : 'center'
						},
						{
							field : 'contractName',
							title : '合同名称',
							width : '14%',
							align : 'center'
						}, {
							field : 'contractNo',
							title : '合同编号',
							align : 'center',
							width : '14%'
						},
						{
							field : 'createName',
							title : '创建人',
							width : '9%',
							align : 'center'
						},
						{
							field : 'sign',
							title : '签订时间',
							width : '7%',
							align : 'center',
							formatter : function(value, row, index) {
								var str = "";
								if ("" != row.sign && null != row.sign) {
									return new Date(row.sign).format("yyyy-MM-dd");
								}
								return str;
							}
						},
						{
							field : 'remark',
							title : '备注',
							width : '26%',
							align : 'center'
						} ] ],
					toolbar : '#ReceGridToolbar',
				})
				var PaygridPanel = $('#dg').datagrid("getPanel");
				PaygridPanel.on("click", "a.edit", function() {
					var rows = $('#dg').datagrid('getSelections');  /* 获取数据表格的行 */
					if (rows.length <= 0) {
						$.messager.alert('提示', '请选择要修改的行', 'error');
					} else if (rows.length > 1) {
						$.messager.alert('提示', '只能选择一条数据进行修改', 'error');
					} else if (rows.length == 1) {
						var id = rows[0].id;
						editCustomerForm(id);
					}
				}).on("click", "a.add", function() {
					$("#customerForm").form('clear'); /* 清空form表单 */
					customerForm();  /* 添加方法 */
				}).on("click", "a.delete", function() {
					var rows = $('#dg').datagrid('getSelections');
					if (rows.length <= 0) {
						$.messager.alert('提示', '请选择要刪除的行', 'error');
					} else if (rows.length > 1) {
						$.messager.alert('提示', '只能选择一条数据进行修改', 'error');
					} else if (rows.length == 1) {
						$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
							 if(r){
							 	var id = rows[0].id;
								delCustomerForm(id);
								$("#dg").datagrid("reload");
							 }
						});
					}
				}).on("click", "a.chakan", function() {
					var row = $('#dg').datagrid('getSelected');
					var purNo = row.purNo;
					qingdan(purNo);
				});
		
				// 搜索按钮事件
				var chargeMan = $("#chargeMan");
				var company = $("#company");
				$("#Search").on('click', function() {
					$('#dg').datagrid("load", {
						chargeMan : "%" + chargeMan.val() + "%",
					/* company : "%" + company.val() + "%", */
					});
				});
				// 重置事件
				var form = $("#earlyProjectSearchForm");
				$("#earlyProjectReset").on('click', function() {
					form.form('clear');
					// 清除查询参数
					$('#dg').datagrid("load", {});
				});
			});
			 /* function save() {
				$("#linkForm").form("submit", {
					url : '${ctx}/pay/editPayable',
					onsubmit : function() {
						return $(this).form("validate");
					},
					success : function(result) {
						if (result == "1") {
							$.messager.alert("提示信息", "操作成功");
							$("#dlg").dialog("close");
							$('#dg').datagrid("reload");
						} else {
							$.messager.alert("提示信息", "操作失败");
						}
					}
				});
			}  */
			
			function linkForm(customerId) {
					// 创建窗口
					var dialog = $("<div/>").dialog(
							{
								href : '${ctx}/salesDetails/salesDetailsPage?customerId='+customerId,
								title :'销售合同详情',
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
				
			function qingdan(purNo) {
				// 创建窗口
				var dialog = $("<div/>").dialog(
					{
						href : '../purchase/purSupplier/' + purNo,
						title : "详情",
						height : '90%',
						width : '80%',
						modal : true,
						onClose : function() {
							// 窗口关闭的同时销毁此窗口
							$(this).dialog("destroy");
						},
						buttons : [ {
							iconCls : 'icon-back',
							text : '返回',
							handler : function() {
								//关闭窗口
								dialog.dialog("close");
							}
						} ]
					});
			}
			//获取下拉框值
			$.ajax({
				type : "get",
				url : "${ctx}/customer/combobox",
				async : false,
				success : function(data) {
					$("#addCustomerId").combobox({ //往下拉框塞值
						data : data,
						valueField : "id", //value值
						textFild : "text" //文本值
					});
				},
				error : function(data) {}
			})
			//获取修改下拉框数据
			$.ajax({
				type : "get",
				url : "${ctx}/customer/combobox",
				async : false,
				success : function(data) {
					$("#editCustomerId").combobox({ //往下拉框塞值
						data : data,
						valueField : "id", //value值
						textFild : "text" //文本值
					});
				},
				error : function(data) {}
			})
		
			/* 新增弹出框 */
			function customerForm() {
				var win = $('#dlg').dialog({ //创建弹出框
					width : '500',
					height : '500',
					modal : true, //遮罩层
					title : '添加销售合同',
					shadow : true, //阴影
					buttons : [ { //
						text : '提交',
						iconCls : 'icon-ok',
						handler : function() {
							$.messager.confirm('提示', '您确定要保存吗', function(t) {
								if (t) {
									if ($("#customerForm").form('validate')) {   /* 进行表单验证 */
										var formData = document.getElementById("customerForm"); /* 通过id获取到用户表单 */
										var data = new FormData(formData); /* 创建一个新的表单数据 */
										$.ajax({
											type : "post",
											url : "${ctx}/financialSalesContract/addFinancialSalesContract",
											data : data,
											async : false, /* 是否同步 */
											contentType : false,
											processData : false,
											success : function(data) {
												$("#dlg").dialog('close');  /* 关闭对话框 */
												$("#dg").datagrid("reload"); /* 重载表格 */
												$.messager.show({
													title : '消息提示',
													msg : '保存成功',
													timeout : 2000,
													showType : 'slide'
												});
											}
										});
									}
								}
							});
						}
					}, {
						text : '关闭',
						iconCls : 'icon-no',
						handler : function() {
							$("#dlg").dialog('close');
						}
					}
					]
				});
				win.dialog('open'); //打开添加对话框
				win.window('center'); //使Dialog居中显示 
			}
		
			/* 修改弹出框 */
			function editCustomerForm(id) {
				/* 修改之前先要根据id查询出销售合同信息，然后再把这些数据显示出来 */
				$.ajax({
					type : "post",
					url : "${ctx}/financialSalesContract/selFinancialSalesContractById?id=" + id,
					async : false,
					contentType : false,
					processData : false,
					success : function(data) {
					    $("#editCustomerId").combobox('setValue',data.customerId);
						$("#money").textbox("setValue", data.money);
						$("#acceptMoney").textbox("setValue", data.acceptMoney);
						$("#contractName").textbox("setValue", data.contractName);
						$("#contractNo").textbox("setValue", data.contractNo);
						$("#sign").datebox("setValue", myformatter(data.sign));
						$("#remark").textbox("setValue", data.remark);
					},
					error : function(data) {}
				})
				var win = $('#editDlg').dialog({ //创建弹出框
					width : '500',
					height : '500',
					modal : true, //遮罩层
					title : '修改销售合同',
					shadow : true, //阴影
					buttons : [ { //按钮
						text : '提交',
						iconCls : 'icon-ok',
						handler : function() {
							$.messager.confirm('提示', '您确定要修改吗', function(t) {
								if (t) {
									if ($("#editCustomerForm").form('validate')) {
										var formData = document.getElementById("editCustomerForm");
										var data = new FormData(formData);
										data.set("id", id);
										data.get("id");
										$.ajax({
											type : "post",
											url : "${ctx}/financialSalesContract/editFinancialSalesContract",
											data : data,
											async : false,
											contentType : false,
											processData : false,
											success : function(data) {
												$("#editDlg").dialog('close');
												$("#dg").datagrid("reload");
												$.messager.show({
													title : '消息提示',
													msg : '修改成功',
													timeout : 2000,
													showType : 'slide'
												});
											}
										});
									}
								}
							});
						}
					}, {
						text : '关闭',
						iconCls : 'icon-no',
						handler : function() {
							$("#editDlg").dialog('close');
						}
					}
					]
				});
				win.dialog('open'); //打开添加对话框
				win.window('center'); //使Dialog居中显示 
			}
			//时间格式化
			function myformatter(date) {
				var date = new Date(date);
				var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
				var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
				var hor = date.getHours();
				var min = date.getMinutes();
				var sec = date.getSeconds();
				return date.getFullYear() + '-' + month + '-' + day
			}
			
			function delCustomerForm(id){
				$.ajax({
					type : "get",
					url : "${ctx}/financialSalesContract/delFinancialSalesContractById?id=" + id,
					async : false,
					contentType : false,
					processData : false,
					success : function(data) {
						$.messager.show({
							title : '消息提示',
							msg : '刪除成功',
							timeout : 2000,
							showType : 'slide'
						});
					},
					error : function(data) {}
				})
			}
		</script>
</body>
</html>

