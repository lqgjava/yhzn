<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
		<div class="panel-header">
			<form action="" name="QueryForm">
			<table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
				<tr height="35px;">
					<td width="10%" height="35px;" align="right">账号：</td>
					<td width="20%" align="left">
						<input type="text" name="userName" id="f_userName" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right">姓名：</td>
					<td width="20%" align="left">
						<input type="text" name="trueName" id="f_trueName" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right">单位：</td>
					<td width="30%" align="left">
						<input type="text" name="dept" id="f_dept" class="easyui-textbox" style="width:172px;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="10%" height="35px;" align="right">创建时间：</td>
					<td width="20%" align="left" colspan="3">
						<input class="easyui-datebox" name="beginDate" id="f_beginDate" style="width:100px;" />
						至 <input class="easyui-datebox" name="endDate" id="f_endDate" style="width:100px;" />
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="30%" align="left"></td>
				</tr>
				<tr>
					<td colspan="6" align="right">
						<input type="button" value="查询" class="button_blue" onclick="queryFun()" />
						<input type="button" value="重置" class="button_green" onclick="resetQuery()"/>
					</td>
				</tr>
			</table>
		</form>
		</div>
		<hr>
		<%-- 列表展示 begin --%>
		<div class="easyui-panel" id="query-data" style="padding:1px;">
			<table id="dataGrid" ></table>
		</div>
		<%-- 列表展示 end --%>
		
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width: 400px; height: 480px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="userForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
	      		 <tr height="35px;">
					<td width="25%" height="35px;" align="right">用户姓名：</td>
					<td width="75%" align="left">
						<input id="personId" name="personId" class="easyui-combotree" style="width:172px;" data-options="required:true"  />							
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">账号：</td>
					<td width="75%" align="left">
						<input  name="userName" id="userName" class="easyui-textbox" style="width:172px;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">密码：</td>
					<td width="75%" align="left">
						<input type="password" name="passwordNew" id="passwordNew" class="easyui-textbox" style="width:172px;" />新密码
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">角色：</td>
					<td width="70%" align="left">
						<input class="easyui-combobox" id="roleId" name="roleId" style="width:172px" data-options="valueField:'id', textField:'text',required:true" >
				</td>
				</tr>
				<tr height="80px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="75%" align="left">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:80%;height:80px"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" /> 
	       <input type="hidden" name="roleName" id="roleName" /> 
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveuser()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   
	
	<script type="text/javascript">
		var url;
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "用户信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/sysUser/querySysUserList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'userName',
				title : '账号',
				width : '18%',
				align : 'center' 
			}, {
				field : 'trueName',
				title : '姓名',
				width : '20%',
				align : 'center'
			}, {
				field : 'dept',
				title : '部门',
				width : '23%',
				align : 'center'
			}, 
			{
				field : 'roleName',
				title : '所属角色',
				width : '20%',
				align : 'center'
			} ,
			{
				field : 'createDateStr',
				title : '创建时间',
				width : '18%',
				align : 'center'
			} ] ],
			   toolbar:[              //工具条  
                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                   $("#dlg").dialog("open").dialog("setTitle", "用户信息新增");
                   $("#userForm").form("clear");
                    url = "/yhzn/sysUser/addSysUser";
                    getRoleList("");
                     $("#personId").combotree({
			          	url : "${ctx }/sysUser/getPersonList",  
                		data : {"personId":""},  
			            method: 'post',
			            ines: true,
			            multiple: false,
			            onBeforeSelect: function (node) {
			                if(node.name){
					          $("#personId").tree("unselect");
					        }
			            }
			        })
			        $("#personId").combobox({disabled:false});
                }},  
                {text:"删除",iconCls:"icon-remove",handler:function(){  
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
                        $.messager.confirm('提示','您确定要删除吗',function(t){  
                            if(t){  
                                $.ajax({  
                               		type : "post", 
                                    url : "${ctx}/sysUser/deleteSysUser",  
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
                }},  
                {text:"修改",iconCls:"icon-edit",handler:function(){  
                    var rows=datagrid.datagrid('getSelections');
                    if(rows.length<=0){  
                        $.messager.alert('提示','请选择要修改的行','error');  
                    }else if(rows.length>1){  
                        $.messager.alert('提示','只能选择一条数据进行修改','error');  
                    }else if(rows.length==1){  
		                $("#dlg").dialog("open").dialog('setTitle', '用户信息修改');
		                $("#userForm").form("load", rows[0]);
		                $("#personId").combobox({disabled:true});
		                $("#personId").combotree("setValue",rows[0].trueName);
		                url = "${ctx}/sysUser/editSysUser";
		                getRoleList(rows[0].roleId);
                    }  
                }}
                ], 
                //height : $("#query-data").height() - 5,
		});

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
			queryParameter.userName = $("#f_userName").val();
			queryParameter.trueName = $("#f_trueName").val();
			queryParameter.dept = $("#f_dept").val();
			queryParameter.beginDate = $("#f_beginDate").datebox("getValue");
			queryParameter.endDate = $("#f_endDate").datebox("getValue");
			$("#dataGrid").datagrid("reload");
		}
		
		//保存
		function saveuser() {
	    	var roleName= $("#roleId").combobox("getText"); //获取combobox中的textField的值
	    	 $("#roleName").val(roleName);
             $("#userForm").form("submit", {
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
        
        //获取角色列表
         function getRoleList(roleId){
        	$.ajax({  
             	 type : "post", 
                 url : "${ctx }/sysRole/getRoleList",  
                 data : {"roleId":roleId},  
                 dataType : "json",  
                 success : function(data) {
                 	 $("#roleId").combobox("loadData",data);
                 }  
             });  
        }
        
        
        //获取用户列表
         function getPersonList(personId){
        	$.ajax({  
             	 type : "get", 
                 url : "${ctx }/sysUser/getPersonList",  
                 data : {"personId":personId},  
                 dataType : "json",  
                 success : function(data) {
                 	 $("#trueName").combotree("loadData",data);
                 }  
             });  
        }
	</script>
	
</body>
</html>
