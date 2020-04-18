<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@  taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>角色管理</title>

<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">

<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>
<div class="easyui-layout" style="width:100%;height:98%;">
	<div data-options="region:'north'" border="true" style="height: 50px;padding: 12px;overflow: hidden;">
		<form id="jueseSearchForm">
			角色名称：<input id="jueseName" type="text" class="easyui-textbox" data-options="prompt:'请输入角色名称'" /> 
			<a id="jueseSearch" iconCls="icon-search" class="easyui-linkbutton">检索</a>
			<a id="jueseReset" iconCls="icon-reload" class="easyui-linkbutton">重置</a>
		</form>
	</div>
	<div data-options="region:'center'" border="false" >
		<table id="jueseGrid" ></table>
	</div>
	<div id="rolePermissionPanel" data-options="region:'east',collapsible:false,tools:'#rolePermissionTools'" style="width:200px;" title="请选择角色" >
		<ul id="rolePermissionTree"></ul>
	</div>
</div>
<div id="rolePermissionTools">
	<a id="rolePermissionSave" iconCls="icon-save"></a>
</div>
<div id="jueseGridAction" style="display: none;">
	<shiro:hasPermission name="role:edit">
			<a class="actions easyui-linkbutton ctr edit"
				iconCls="icon-edit" plain="true">编辑</a>
				</shiro:hasPermission>
	<shiro:hasPermission name="role:edit">
				<a class="actions easyui-linkbutton ctr delete"
				iconCls="icon-remove" plain="true">删除</a>
	</shiro:hasPermission>
</div>

<div id="jueseGridToolbar" style="display: none;">
<shiro:hasPermission name="role:add">
			<a class="actions create easyui-linkbutton " iconCls="icon-add"
				plain="true">创建角色</a>
		</shiro:hasPermission>
</div>
<script >

$(function() {

	// 角色
	var jueseGrid = $("#jueseGrid");
	var rolePermissionPanel=$("#rolePermissionPanel");
	var rolePermissionTree = $("#rolePermissionTree");
	var currentRow;
	var jueseGridAction =$("#jueseGridAction");
	// 初始化
	jueseGrid.datagrid({
		url : '${ctx}/sysRole/querySysRoleList',
		fit : true,
		rownumbers : true,
		pagination : true, // 是否显示分页栏
		singleSelect : true,// 单行选中
		fitColumns : true,// 表格列自适应
		nowrap : false,// 内容换行
		columns : [ [
			{
				field : 'id',
				title : 'id',
				align : 'center',
				hidden:true,
				width : 100
			},
				{
					field : 'roleName',
					title : '角色名称',
					align : 'center',
					width : 100
				},
				{
					field : 'description',
					title : '角色注释',
					align : 'center',
					width : 200
				},{
					field : 'createName',
					title : '创建人',
					align : 'center',
					width : 200
				},
				{
					field : 'ac',
					title : '操作',
					width : 100,
					align : 'center',
					formatter : function(value, row, index) {
						/*var bts = [];
						bts.push('<a data-index="' + index
								+ '" class="ctr edit"></a>');
						bts.push('<a data-id="' + row.id + '" data-index="'
								+ index + '" class="ctr delete"></a>');
						return bts.join('');*/
						return jueseGridAction.children("a.actions").attr('data-index', index).end().html();
					}
				} ] ],onLoadSuccess:function(data){ 
					/*$('a.edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-large-bianji'});
					$('a.delete').linkbutton({text:'删除',plain:true,iconCls:'icon-large-huishouzhan'});*/
					$('#jueseGrid').datagrid('fixRowHeight');  
				},
		toolbar : '#jueseGridToolbar'/*[ {
			iconCls : 'icon-add',
			text : "创建角色",
			handler : function() {
				jueseForm();
			}
		} ]*/,
		onSelect:function(index,row){
			//记录当前选中行
			currentRow=row;
			rolePermissionPanel.panel("setTitle","为["+row.roleName+"]分配权限");
			//取消以前已选中的项目
			$.each(rolePermissionTree.tree("getChecked"),function(){
				rolePermissionTree.tree("uncheck",this.target)
			});
			
			//加载当前选择角色的已有权限
			$.get("../sysRole/permissionById2?roleId="+row.id,function(data){
				console.log(data);
					$.each(data,function(){
						
						var node=rolePermissionTree.tree('find',this.id);
						//只当前权限是叶子节点时才执行节点的check方法进行选择
						if(rolePermissionTree.tree('isLeaf',node.target)){
							rolePermissionTree.tree("check",node.target)
						}
					});
				
			});
		}
	});

	var jueseName = $("#jueseName");

	$("#jueseSearch").on('click', function() {
		jueseGrid.datagrid("load", {
			roleName : "%" + jueseName.val() + "%"
		});
	});

	var jueseSearchForm = $("#jueseSearchForm");

	$("#jueseReset").on('click', function() {
		jueseSearchForm.form('clear');
		jueseGrid.datagrid("load", {});
	});

	var juesegridPanel = jueseGrid.datagrid("getPanel");
	juesegridPanel.on("click", "a.edit", function() {
		var index = this.dataset.index;
		// 如果只有下标，没有id的情况，要获取id
		var rows = jueseGrid.datagrid("getRows");
		// 同下标获取对应行的数据对象
		var id = rows[index].id;
		jueseForm(id);
	}).on("click", "a.delete", function() {
		// 获取data-xxx绑定的标签属性数据
		var index = this.dataset.index;
		// 如果只有下标，没有id的情况，要获取id
		var rows = jueseGrid.datagrid("getRows");
		// 同下标获取对应行的数据对象
		var id = rows[index].role_id;
		$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
			// 点击了确定按钮，r返回的结果为true
			if (r) {
				$.get("/crm_xt_jsp/juesedelete/" + id, function(rs) {
					if (rs.success) {
						// 刷新表格
						jueseGrid.datagrid("reload");
					} else {
						$.message.alert("提示", rs.message);
					}
				});
			}
		});
	}).on("click", "a.create", function () {
		jueseForm();
	}).on("click","a.delete",function(){
		 var rows=datagrid.datagrid('getSelections');  
 		var ids = [];
			for (var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
			}
         if(rows.length<=0){  
             $.messager.alert('提示','请选择要删除的行','error');  
         } 
         else{  
         	var idArray =  new Array();
				for (var i = 0; i < rows.length; i++) {
					idArray.push(rows[i].id);
				}
				var ids= idArray.join(",");//转成字符串
             $.messager.confirm('提示','删除角色的同时会删除用户权限，确定要删除吗',function(t){  
                 if(t){  
                     $.ajax({  
                    		type : "post", 
                         url : "/yhzn/sysRole/deleteSysRole",  
                         data : {"ids":ids},  
                         dataType : "json",  
                         success : function(r) {  
                             if (r.success) {  
                                 datagrid.datagrid('acceptChanges');  
                                 $.messager.show({  
                                     msg : r.msg,  
                                     title : "成功"  
                                 });  
                                 editRow = undefined;  
                                 datagrid.datagrid('reload');  
                             } else {  
                                 datagrid.datagrid('beginEdit', editRow);  
                                 $.messager.alert("错误", r.msg, 'error');  
                             }  
                             datagrid.datagrid('unselectAll');  
                         }  
                     });  
                   
                 }  
             }); 
         }  
	});

	/**
	 * 角色表单，新建和编辑都调用此方法
	 */
	function jueseForm(id) {
		// 创建窗口
		var dialog = $("<div/>").dialog(
				{
					href : id==null?'../sysRole/roleForm':'../sysRole/roleForm?id='+id,
					title : "角色信息",
					height : 260,
					width : 550,
					modal : true,
					onClose : function() {
						// 窗口关闭的同时销毁此窗口
						$(this).dialog("destroy");
					},
					buttons : [ {
						iconCls : 'icon-save',
						text : '保存',
						handler : function() {
							// 获取到表单对象
							var jueseForm = $("#jueseForm");
							// 校验表单
							if (jueseForm.form('validate')) {
								// serialize方法可以获取到表单所有数据
								$.post(id==null?"../sysRole/addSysRole":'../sysRole/editSysRole', jueseForm
										.serialize(), function(rs) {
									if (rs.success) {
										// 刷新数据表格
										jueseGrid.datagrid("reload");
										// 关闭窗口
										dialog.dialog("close");
									} else {
										$.messager.alert("提示", rs.message);
									}
								});
							}
						}
					} ]
				});
	}
	
	
	rolePermissionTree.tree({
		url:'../permission/tree',
		checkbox:true
	});

	//权限保存的按钮事件
	$("#rolePermissionSave").on('click',function(){
		if(currentRow){
		//	console.log(currentRow);
			
			//获取打钩和实心的节点
			var nodes = rolePermissionTree.tree("getChecked",["checked","indeterminate"]) ;
			var permissionIds=[];
			$.each(nodes,function(){
				permissionIds.push(this.id);
			});
			var params = "roleId="+currentRow.id+"&permissionId="+permissionIds.join("&permissionId=");
			console.log(params);
			$.post("../sysRole/rolePermissionsave",params,function(resp){
				if(resp.success){
					$.messager.alert("提示","授权成功！");
				}
			});
		}
		
	});
});

</script>
