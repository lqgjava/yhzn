<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<title>项目详情</title>

<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/js/dateFormatter.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<style>
#backbtn {
	background-color: #438EB9;
}

.name {
	width: 10%;
	text-align: right;
}

.value {
	width: 20%;
	text-align: left;
}

#tables {
	border-collapse: collapse;
	border-spacing: 0;
	background-color: #E0ECFF;
	border:none;
}



/* Column Style */
#tables td {
color: #000;
}
/* Padding and font style */
#tables td {
padding: 5px 10px;
font-size: 12px;
font-family: Verdana;
font-weight: bold;
}
</style>
</head>

<body>
	<div class="panel">
		<div id="backbtn">
			<a href="javascript:history.back();" class="easyui-linkbutton"  iconCls="icon-back"  plain="true">返回上一页</a>
			<span style="margin-left:38% ;">${schedule.projName}</span>	
		</div>
		<div>
			<table border="1" width="100%" cellpadding="0" id="tables">
			
				<tr>

					<td class="name">项目类别：</td>
					<td class="value">${schedule.projType}</td>
					<td class="name">业务经理：</td>
					<td class="value">${schedule.contacts}</td>
					<td class="name">职位：</td>
					<td class="value">${schedule.projPosition}</td>
				</tr>


				<tr>

					<td class="name">电话：</td>
					<td class="value">${schedule.phone}</td>
					<td class="name">单位名称：</td>
					<td class="value">${schedule.unitName}</td>
					<td class="name">项目单位名称：</td>
					<td class="value">${schedule.projUnitName}</td>
				</tr>


				<tr>
					<td class="name">甲方联系人：</td>
					<td class="value">${schedule.projContacts}</td>
					<td class="name">甲方联系电话：</td>
					<td class="value">${schedule.projTel}</td>

					<td class="name">甲方地址：</td>
					<td class="value">${schedule.projAddress}</td>
				</tr>


				<tr>


					<td class="name">项目进程：</td>
					<td class="value">${schedule.projStatus=='0'?'沟通中':''}${schedule.projStatus=='1'?'进行中':''}${schedule.projStatus=='2'?'售后':''}</td>
					<td class="name">备注：</td>
					<td colspan="3">${schedule.remark}</td>
				</tr>
			</table>
		</div>	
		<hr>
					<div >
			<table id="dataGrid" id="query-data" style="padding: 1px;"></table>
		</div>		
		
		<div id="dlg" class="easyui-dialog" style="width: 600px; height: 500px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons">
			<form id="linkForm" method="post" enctype="multipart/form-data">
				<table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">沟通者：</td>
						<td width="75%" align="left"><input id="gtMen" name="gtMen" class="easyui-textbox" style="width: 172px;" data-options="required:true" missingMessage="沟通人员不能空" /></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">客户姓名：</td>
						<td width="75%" align="left"><input name="custaomer" id="custaomer" class="easyui-textbox" style="width: 172px;" missingMessage="客户姓名不能空" required="true" /></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">沟通方式：</td>
						<td width="75%" align="left"><input name="gtCode" id="gtCode" class="easyui-textbox" style="width: 172px;" /></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">沟通内容：</td>
						<td width="70%" align="left"><input class="easyui-textbox" data-options="multiline:true" style="width: 80%; height: 80px" id="gtContext" name="gtContext"></td>
						<tr height="35px;">
						<td width="25%" height="35px;" align="right">沟通时间：</td>
						<td width="70%" align="left"><input class="easyui-datetimebox" style="width: 172px;" id="gtTime" name="gtTime"></td>
					</tr>
					<tr height="35px">
					
						<td width="25%" height="20px" align="right">文件：</td>
						<td width="75%" height="20px" align="left"><input type="hidden"  id="status" name="status" value="0"/><input class="easyui-filebox" data-options="prompt:'浏览'" multiple="true" id=" file" name="file" style="width: 90%" /></td>
					</tr>

  									

				</table>
				<input type="hidden" id="projectId" name="projectId" value="${schedule.id }"> <input type="hidden" name="id" id="id" />
			</form>
			<div id="dlg-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveuser()" iconcls="icon-save">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a>
			</div>
		</div>
		
		
		<div id="dlgz" class="easyui-dialog" style="width: 600px; height: 500px; padding: 5px 10px;" closed="true" buttons="#dlgz-buttons">
			<form id="linkForm2" method="post" enctype="multipart/form-data">
				<table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
					<tr height="35px;">
					<td width="18%" height="35px;" align="right">施工人员：</td>
					<td width="30%" align="left">
					<input  name="gtMen" id="gtMen" class="easyui-combotree" style="width:180px;" 
								data-options="url: '${ctx}/sysUser/getPersonList', type: 'post',valueField:'id',textField:'text', multiple:true"/>
						<!-- <input  name="gtMen"  id="gtMen" class="easyui-textbox"  style="width: 172px;" missingMessage="施工人员不能空" required="true"/> -->
					</td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">负责人姓名：</td>
						<td width="75%" align="left">
						<input  name="custaomer" id="custaomer" class="easyui-combotree" style="width:180px;" 
								data-options="url: '${ctx}/sysUser/getPersonList', type: 'post',valueField:'id',textField:'text',multiple:true"/>
						
						</td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">工作地点：</td>
						<td width="75%" align="left"><input name="gtAddress" id="gtAddress" class="easyui-textbox" style="width: 172px;"  /></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">工作进度：</td>
						<td width="75%" align="left"><input name="gtCode" id="gtCode" class="easyui-textbox" style="width: 172px;" /></td>
					</tr>
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">工作内容：</td>
						<td width="70%" align="left"><input class="easyui-textbox" data-options="multiline:true" style="width: 80%; height: 80px" id="gtContext" name="gtContext"></td>
						
					<tr height="35px;">
						<td width="25%" height="35px;" align="right">施工时间：</td>
						<td width="70%" align="left"><input class="easyui-datetimebox" style="width: 172px;" id="gtTime" name="gtTime"></td>
					</tr>
					<tr height="35px">
						<td width="25%" height="20px" align="right">文件：</td>
						<td width="75%" height="20px" align="left"><input class="easyui-filebox" data-options="prompt:'浏览'" multiple="true" id=" file" name="file" style="width: 90%" /></td>
					</tr>

				</table>
				<input   id="status1" type="hidden" name="status" value="1"/>
				<input  id="projectId2" type="hidden" name="projectId" value="${schedule.id }"> <input type="hidden" name="id" id="id" />
			</form>
			<div id="dlgz-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveuser2()" iconcls="icon-save">保存</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlgz').dialog('close')" iconcls="icon-cancel">取消</a>
			</div>
		</div>
	</div>


	<div id="projectlinkGridToolbar" style="display: none;">
	<c:if test="${projStatus=='0'}">
		<shiro:hasPermission name="projectlink:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add" plain="true">新建</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectlink:edit">
			<a class="actions edit easyui-linkbutton " iconCls="icon-edit" plain="true">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectlink:delete">
			<a class="actions delete easyui-linkbutton " iconCls="icon-remove" plain="true">删除</a>
		</shiro:hasPermission>
		</c:if>
			<c:if test="${projStatus=='1'}">
		<shiro:hasPermission name="projectzz:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add" plain="true">新建</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectzz:edit">
			<a class="actions edit easyui-linkbutton " iconCls="icon-edit" plain="true">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectzz:delete">
			<a class="actions delete easyui-linkbutton " iconCls="icon-remove" plain="true">删除</a>
		</shiro:hasPermission>
		</c:if>
			<c:if test="${projStatus=='2'}">
		<shiro:hasPermission name="service:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add" plain="true">新建</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="service:edit">
			<a class="actions edit easyui-linkbutton " iconCls="icon-edit" plain="true">编辑</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="service:delete">
			<a class="actions delete easyui-linkbutton " iconCls="icon-remove" plain="true">删除</a>
		</shiro:hasPermission>
		</c:if>
	</div>
	
	<script type="text/javascript">
		var url;
		$(function() {
			var id = window.name;

			$("#dataGrid").datagrid({
								nowrap : false,
								border : true,
								locale : "zh_CN",
								iconCls : 'icon-save',
								width:'100%',
								striped : true,
								title : '详情记录',
								height : $(window).height()-250,
								collapsible : true,
								url : "${ctx}/schedule/linkdetails/" + id,
								pagination : true,//表示在datagrid设置分页              
								rownumbers : true,
								//singleSelect : true,
								columns : [ [
										{
											field : 'id',
											title : '编号',
											width : 100,
											sortable : true,
											checkbox : true
										},
										{
											field : 'gtMen',
											title : '沟通者/施工者/售后人员',
											width : '10%',
											align : 'center'
										},
										{
											field : 'gtAddress',
											title : '工作地点',
											width : '10%',
											align : 'center'
										},
										{
											field : 'gtTime',
											title : '沟通时间/施工时间',
											width : '10%',
											align : 'center'
										},
										{
											field : 'custaomer',
											title : '客户联系人/负责人',
											width : '13%',
											align : 'center'
										},
										{
											field : 'gtCode',
											title : '沟通方式',
											width : '10%',
											align : 'center'
										},
										{
											field : 'gtContext',
											title : '沟通内容/工作内容：',
											width : '26%',
											align : 'center'
										},
										{
											field : 'status',
											title : '项目进度：',
											width : '10%',
											align : 'center',
												formatter : function(value, row,index) {
													if(value=="0"){
														return "沟通中";
													}else if(value=="1"){
														return "进行中";
													}else{
														return "售后";
													}

												}
										},
										{
											field : 'caozuo',
											title : '操作',
											width : 160,
											align : 'center',
											formatter : function(value, row,index) {
												var bts = [];
												bts.push('<a data-index="' + index + '"  class="chakan">查看文件</a>');
												return bts.join('');

											}
										} ] ],
								onLoadSuccess : function(data) {
									$('.chakan').linkbutton({
										text : '查看文件',
										plain : true,
										iconCls : 'icon-upload'
									});
								},
								toolbar : '#projectlinkGridToolbar',
							//height : $("#query-data").height() - 5,
							});
			var gridPanel = $("#dataGrid").datagrid("getPanel");
			gridPanel.on("click", "a.chakan", function() {
				var index = this.dataset.index;
				// 如果只有下标，没有id的情况，要获取id
				var rows = $("#dataGrid").datagrid("getRows");
				// 同下标获取对应行的数据对象
				var id = rows[index].id;
				//加载数据
				window.open("${ctx}/file/fileList/?parentId=" + id);
			}).on("click", "a.create", function() {
				var proStatus=${projStatus};
				if(proStatus==0){
					$("#dlg").dialog("open").dialog("setTitle", "沟通信息新增");
					$("#linkForm").form('clear');
					var id='${schedule.id}';
					$("#projectId").val(id);
					$("#status").val("0");
				}else if(proStatus==1){
					$("#dlgz").dialog("open").dialog("setTitle", "项目进度信息新增");
					$("#linkForm2").form('clear');
					var id='${schedule.id}';
					$("#projectId2").val(id);
					$("#status1").val("1");
				}else{
					$("#dlg").dialog("open").dialog("setTitle", "售后沟通信息新增");
					$("#linkForm").form('clear');
					var id='${schedule.id}';
					$("#projectId").val(id);
					$("#status").val("2");
				}		
			
			}).on("click", "a.edit", function() {
				var rows = $("#dataGrid").datagrid('getSelections');
				if (rows.length <= 0) {
					$.messager.alert('提示', '请选择要修改的行', 'error');
				} else if (rows.length > 1) {
					$.messager.alert('提示', '只能选择一条数据进行修改', 'error');
				} else if (rows.length == 1) {
					var proStatus=${projStatus};
					if(proStatus==0){
						$("#dlg").dialog("open").dialog('setTitle', '沟通记录修改');
						$("#linkForm").form("load", rows[0]);
						var id='${schedule.id}';
						$("#projectId").val(id);
						$("#status").val("0");
						$("#dtTime").datebox('setValue', rows[0].gtTime);
					}else if(proStatus==1){
						$("#dlgz").dialog("open").dialog("setTitle", "项目进度信息修改");
						$("#linkForm2").form("load", rows[0]);
						var id='${schedule.id}';
						$("#projectId2").val(id);
						$("#status1").val("1");
						$("#dtTime").datebox('setValue', rows[0].gtTime);
					}else{
						$("#dlg").dialog("open").dialog('setTitle', '售后信息修改');
						$("#linkForm").form("load", rows[0]);
						var id='${schedule.id}';
						$("#projectId").val(id);
						$("#status").val("2");
						$("#dtTime").datebox('setValue', rows[0].gtTime);
					}	
					
				}
			}).on("click","a.delete",function() {
						var ids = [];
						var rows = $("#dataGrid").datagrid('getSelections');
						if (rows.length <= 0) {
							$.messager.alert('提示', '请选择要删除的行', 'error');
						} else {
							for (var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
								// 点击了确定按钮，r返回的结果为true
								if (r) {
									$.get("../schedule/deleteLink/" + ids,function(data) {
												if (data.success == "1") {
													// 刷新表格
													$("#dataGrid").datagrid("reload");
												} else {
													$.message.alert("提示","删除失败");
												}
											});
								}
							});
						}
					})

		});

		//保存
		function saveuser() {
			var id = $("#id").val();
			// 获取到表单对象
			$("#linkForm").form("submit",{url : id == "" ? "../schedule/addLink": "../schedule/editLink",
						onsubmit : function() {
							return $(this).form("validate");
						},
						success : function(result) {
							if (result == "1") {
								$.messager.alert("提示信息", "操作成功");
								$("#linkForm").form("clear");
								$("#dlg").dialog("close");
								$("#dataGrid").datagrid("reload");
							} else {
								$.messager.alert("提示信息", result);
							}
						}
					});

		}
		</script>
		<script>
		//保存
		function saveuser2() {
			var id = $("#id").val();
			// 获取到表单对象
			$("#linkForm2").form("submit",{url : id == "" ? "../schedule/addLink": "../schedule/editLink",
						onsubmit : function() {
							return $(this).form("validate");
						},
						success : function(result) {

							if (result == "1") {
								$.messager.alert("提示信息", "操作成功");
								$("#linkForm2").form("clear");
								$("#dlgz").dialog("close");
								$("#dataGrid").datagrid("reload");
							} else {
								$.messager.alert("提示信息", result);
							}
						}
					});

		}
					
	</script>

					
</body>

</html>