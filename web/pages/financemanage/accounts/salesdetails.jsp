<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>销售合同详情</title>

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
	src="${ctx }/resource/plugins/jquery/jquery.form.js"></script>
<script type="text/javascript"
	src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>

<body>

	<div class="panel">
		<%-- 列表展示 begin --%>
		<%-- 列表展示 end --%>
		<%-- 新增弹出框 begin --%>
		<div id="dlg" class="easyui-dialog"
			style="width:600px; height: 540px;top:10px; padding: 5px 10px;display:none;"
			closed="true" buttons="#dlg-buttons">
			<form id="linkForm" method="post" enctype="multipart/form-data">
				<table width="98%" border="0" cellspacing="5" cellpadding="0"
					align="center" style="margin-top: 10px;">
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">应收金额：</td>
						<td width="75%" align="left"><input name="money"
							class="easyui-numberbox" style="width:90%;" required="true" /></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">已收金额：</td>
						<td width="75%" align="left"><input name="acceptMoney"
							required="true" class="easyui-numberbox" style="width:90%;" /></td>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">付款单位：</td>
						<td width="70%" align="left"><input class="easyui-textbox"
							 required="true" style="width:90%;"
							name="payCompany"></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">收款时间：</td>
						<td width="70%" align="left"><input class="easyui-datebox"
							editable="fasle" required="true" style="width:90%;"
							name="acceptDate"></td>
					</tr>
					

					<tr height="35px;">
						<td width="25%" height="35px;" align="right">备注：</td>
						<td width="70%" align="left"><input class="easyui-textbox"
							data-options="multiline:true" style="width:90%;height:100px"
							name="remark"></td>
					</tr>
					<tr height="35px">
						<td width="25%" height="20px" align="right">凭证：</td>
						<td width="75%" height="20px" align="left"><input
							class="easyui-filebox"
							data-options=" buttonText: '选择文件',prompt:'浏览'" multiple="true"
							id=" file" name="file" style="width:90%" /></td>
					</tr>
				</table>
				<input type="hidden" name="id" id="id" /> <input type="hidden"
					name="financeId" id="financeId" />
			</form>
			<div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="save()" iconcls="icon-save">保存</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="javascript:$('#dlg').dialog('close')"
					iconcls="icon-cancel">取消</a>
			</div>
			</div>
			<%-- 新增弹出框 end --%>

			<%-- 修改弹出框 begin --%>
			<div id="editDlg" class="easyui-dialog"
				style="width:600px; height: 540px;top:10px; padding: 5px 10px;display:none;"
				closed="true" buttons="#dlg-buttons">
				<form id="editLinkForm" method="post" enctype="multipart/form-data">
					<table width="98%" border="0" cellspacing="5" cellpadding="0"
						align="center" style="margin-top: 10px;">
						<tr height="35px;">
							<td width="25%" height="35px;" align="right">应收金额：</td>
							<td width="75%" align="left"><input name="money" id="money"
								class="easyui-numberbox" style="width:90%;" required="true" /></td>
						</tr>
						<tr height="35px;">
							<td width="25%" height="35px;" align="right">已收金额：</td>
							<td width="75%" align="left"><input id="acceptMoney" name="acceptMoney"
								required="true" class="easyui-numberbox" style="width:90%;" /></td>
						<tr height="35px;">
							<td width="25%" height="35px;" align="right">收款时间：</td>
							<td width="70%" align="left"><input class="easyui-datebox"
								editable="fasle" required="true" style="width:90%;"
								id="acceptDate" name="acceptDate"></td>
						</tr>
						<tr height="35px;">
							<td width="25%" height="35px;" align="right">付款单位：</td>
							<td width="70%" align="left"><input class="easyui-textbox"
								 required="true" style="width:90%;"
								id="payCompany" name="payCompany"></td>
						</tr>

						<tr height="35px;">
							<td width="25%" height="35px;" align="right">备注：</td>
							<td width="70%" align="left"><input class="easyui-textbox"
								data-options="multiline:true" style="width:90%;height:100px"
								id="remark" name="remark"></td>
						</tr>
						<tr height="35px">
							<td width="25%" height="20px" align="right">凭证：</td>
							<td width="75%" height="20px" align="left"><input
								class="easyui-filebox"
								data-options=" buttonText: '选择文件',prompt:'浏览'" multiple="true"
								id=" file" name="file" style="width:90%" /></td>
						</tr>
					</table>
					<input type="hidden" name="id" id="id" /> <input type="hidden"
						name="financeId" id="financeId" />
				</form>
				<div id="dlg-buttons">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="save()" iconcls="icon-save">保存</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						onclick="javascript:$('#dlg').dialog('close')"
						iconcls="icon-cancel">取消</a>
				</div>
				<%-- 修改弹出框 end --%>
			</div>
		<table id="dataGrid"></table>
		<div id="receGridToolbar" style="display: none;">
			<shiro:hasPermission name="financed:add">
				<a class="actions create easyui-linkbutton " iconCls="icon-add"
					plain="true">新建</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="financed:edit">
				<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
					plain="true">编辑</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="finance:delete">
				<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
					plain="true">删除</a>
			</shiro:hasPermission>
			<!-- 结存条件 <select id="select" name="queryunpay" class="easyui-combobox"
				style="width:150px;"> 
				<option value="0">结存大于0</option>  
				<option value="1">结存小于60000</option> 
				<option value="2">结存大于60000</option>
				<option value="3">所有</option> 
			</select> --> 
			<a class="actions search easyui-linkbutton" iconCls="icon-search">搜索</a>
			<a class="actions print easyui-linkbutton" iconCls="icon-print">导出</a>
		</div>
		<script type="text/javascript">
			var financeId = window.name;
			var url;
			$(function() {
				var editRow = undefined; //定义全局变量：当前编辑的行
				datagrid = $("#dataGrid").datagrid({
					nowrap : false,
					border : false,
					locale : "zh_CN",
					height : $(window).height() - 200,
					iconCls : 'icon-save',
					striped : true,
					collapsible : true,
					url : '${ctx}/salesDetails/querySalesDetailsList',
					pagination : true, //表示在datagrid设置分页              
					rownumbers : true,
					pageSize : 50,
					pageList : [ 50, 100, 150, 200 ],
					columns : [ [ {
						field : 'id',
						title : '编号',
						hidden : true
					},
						{
							field : 'contractNo',
							title : '合同编号',
							width : '20%',
							align : 'center'
						}, {
							field : 'money',
							title : '应收金额',
							width : '20%',
							align : 'center'
						}, {
							field : 'acceptMoney',
							title : '已收金额',
							width : '20%',
							align : 'center'
						}, {
							field : 'unacceptMoney',
							title : '结存',
							width : '10%',
							align : 'center'
						},
						
						{
							field : 'acceptDate',
							title : '收款时间',
							width : '10%',
							align : 'center'
						}, {
							field : 'payCompany',
							title : '付款单位',
							width : '10%',
							align : 'center'
						}, {
							field : 'detailRemark',
							title : '备注',
							width : '10%',
							align : 'center'
						} ] ],
		
					toolbar : '#receGridToolbar'
				})
				var gridPanel = datagrid.datagrid("getPanel");
				gridPanel.on("click", "a.chakan", function() {
					var index = this.dataset.index;
					// 如果只有下标，没有id的情况，要获取id
					var rows = datagrid.datagrid("getRows");
					// 同下标获取对应行的数据对象
					var id = rows[index].id;
					//判断是否有附件
					//加载数据
					$.ajax({
						type : "get",
						dataType : 'json',
						url : "${ctx}/file/QueryFileByParentId?parentId=" + id,
						success : function(data) {
							var count = data.total
							if (parseInt(count) == 0) {
								$.messager.alert("提示信息", "无附件信息");
							} else {
								window.open("${ctx}/file/fileList/?parentId=" + id);
							}
						}
					})
		
				})
		
			}).on("click", "a.create", function() {
				$("#dlg").dialog("open").dialog("setTitle", "添加销售合同详情");
				$("#linkForm").form('clear');
				linkForm();
			}).on("click", "a.delete", function() {
				var ids = [];
				var rows = datagrid.datagrid('getSelections');
				if (rows.length <= 0) {
					$.messager.alert('提示', '请选择要删除的行', 'error');
				} else {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
						// 点击了确定按钮，r返回的结果为true
						if (r) {
							$.get("../finance/delete/" + ids, function(data) {
								if (data.success == "1") {
									// 刷新表格
									datagrid.datagrid("reload");
								} else {
									$.message.alert("提示", "删除失败");
								}
							});
						}
					});
				}
			}).on("click", "a.edit", function() {
				var rows = datagrid.datagrid('getSelections');
				if (rows.length <= 0) {
					$.messager.alert('提示', '请选择要修改的行', 'error');
				} else if (rows.length > 1) {
					$.messager.alert('提示', '只能选择一条数据进行修改', 'error');
				} else if (rows.length == 1) {
					$("#dlg").dialog("open").dialog('setTitle', '合同收帐款修改');
					$("#linkForm").form("load", rows[0]);
					$("#dtTime").datebox('setValue', rows[0].gtTime);
				}
			}).on("click", "a.search", function() {
				var value = $("#select").combobox("getValue");
				datagrid.datagrid("load", {
					unpaidMoney : value,
				});
			}).on("click", "a.print", function() {
				var value = $("#select").combobox("getValue");
				window.location.href = "${ctx}/exl/recevibleExl?id=" + financeId + "&& unpaidMoney=" + value;
			});
		
		
 		/* 	$("#dataGrid").datagrid({
				onLoadSuccess : function(data) {
					$('.chakan').linkbutton({
						text : '查看文件',
						plain : true,
						iconCls : 'icon-chakan'
					});
					//新增一行显示统计信息
					datagrid.datagrid('appendRow', {
						id : '2',
						contractNo : '<b>合计：</b>',
						detailMoney : parseInt(data.map.receMoney) + parseInt(data.map.unpaiMoney),
						recepitMoney : data.map.receMoney,
						unpaidMoney : data.map.unpaiMoney,
					});
		
				},
				rowStyler : function(index, row) {
					if (row.contractNo == '<b>合计：</b>') {
						return 'background-color:#EAEAEA;color:blue';
					}
				}
			}); */
			//保存
		/* 	function save() {
				var id = $("#id").val();
				// 获取到表单对象
				$("#linkForm").form("submit", {
					url : id == "" ? "../finance/addReceivableDetails" : "../finance/editReceivableDetails",
					onsubmit : function() {
						return $(this).form("validate");
					},
					success : function(result) {
		
						if (result == "1") {
							$.messager.alert("提示信息", "操作成功");
							$("#linkForm").form('clear');
							// 清除查询参数
							$("#dlg").dialog("close");
							$("#dataGrid").datagrid('reload');
						} else {
							$.messager.alert("提示信息", result);
						}
					}
				});
		
			} */
			$('#cc').combogrid({
				keyHandler : {
					up : function() {},
					down : function() {},
					enter : function() {},
					query : function(q) {
						//动态搜索
						$('#cc').combogrid("grid").datagrid("reload", {
							'projNo' : q
						});
						$('#cc').combogrid("setValue", q);
					}
				},
				onSelect : function(rowIndex, rowData) {
					var g = $('#cc').combogrid('grid'); // 获取表格控件对象
					var r = g.datagrid('getSelected'); //获取表格当前选中行
					$("#projName").textbox('setValue', r.projName);
				}
			});
			$("#recepitMoney").numberbox({
				"onChange" : function() {
					var recepit = $("#recepitMoney").numberbox("getValue");
					var detailMoney = $("#detailMoney").numberbox("getValue");
					if (parseInt(detailMoney) < parseInt(recepit)) {
						$("#recepitMoney").numberbox("setValue", "");
						$.messager.alert("提示", "值不能大于应收金额");
					} else {
						var yue = detailMoney - recepit;
						$("#unpaidMoney").numberbox("setValue", yue);
					}
				}
			});
			/* 添加方法 */
			function linkForm() {
				var win = $('#dlg').dialog({ //创建弹出框
					width : '500',
					height : '500',
					modal : true, //遮罩层
					title : '添加销售合同详情',
					shadow : true, //阴影
					buttons : [ { //
						text : '提交',
						iconCls : 'icon-ok',
						handler : function() {
							$.messager.confirm('提示', '您确定要保存吗', function(t) {
								if (t) {
									if ($("#linkForm").form('validate')) {   /* 进行表单验证 */
										var formData = document.getElementById("linkForm"); /* 通过id获取到用户表单 */
										var data = new FormData(formData); /* 创建一个新的表单数据 */
										alert(data.get("payCompany"));
										$.ajax({
											type : "post",
											url : "${ctx}/salesDetails/addSalesDetails",
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
		</script>
</body>
</html>
