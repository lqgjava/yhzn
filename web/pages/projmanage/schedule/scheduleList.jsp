<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>项目进度管理</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/js/dateFormatter.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>

</head>

<body>
	<div class="panel">
		<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0" style="height: 60px; padding: 10px; overflow: hidden; width: 99%">
			<form action="" name="QueryForm">
			项目编号：<input type="text" name="projNo" id="f_projNo" class="easyui-textbox"  />
			项目名称：<input type="text" name="projName" id="f_projName" class="easyui-textbox"  />
			施工时间：<input class="easyui-datebox" name="beginDate" id="f_beginDate" />至 <input class="easyui-datebox" name="endDate" id="f_endDate"   />
			进度：<input type="text" name="projStatus" id="f_projStatus" class="easyui-textbox"  />
			结束时间：<input class="easyui-datebox" name="fromDate" id="f_fromDate" />至 <input class="easyui-datebox" name="toDate" id="f_toDate"   />
					
					<div style="text-align: right;margin-top:10px">
						<input type="button" value="查询" class="button_blue" onclick="queryFun()" />
						<input type="button" value="重置" class="button_green" onclick="resetQuery()"/>
					</div>
		</form>
		</div>
		<hr>
			<table id="dataGrid" ></table>
		
		<%-- 新增修改弹出框 begin --%>
	    <div id="dlg" class="easyui-dialog" style="width: 800px; height: 600px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="projForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
				<tr height="35px;">
					<td width="18%" height="35px;" align="right">项目名称：</td>
					<td width="25%" align="left">
						<input  name="projName" id="projName" class="easyui-textbox" style="width:180px;"  required="true"/>
					</td>
					<td width="18%" height="35px;" align="right">项目类别：</td>
					<td width="50%" align="left">
						<input class="easyui-combobox" id="projType" name="projType" style="width:180px"  
							   data-options="url: '${ctx }/customer/getCustomerTypeList', method: 'get',valueField:'id',textField:'text', required:true" >
					</td>
				</tr>
				 <tr height="35px;">
					<td width="18%" height="35px;" align="right">联系人：</td>
					<td width="30%" align="left">
						<input class="easyui-combobox" name="contacts" id="contacts" style="width:180px;"  
								data-options="url: '${ctx}/customer/getCustomerList', method: 'get',valueField:'id',textField:'text'" >
					</td>
					<td width="18%" height="35px;" align="right">电话：</td>
					<td width="40%" align="left">
						<input  name="phone" id="phone" class="easyui-textbox" style="width:180px;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="18%" height="35px;" align="right">单位名称：</td>
					<td width="30%" align="left">
						<input  name="unitName" id="unitName" class="easyui-textbox" style="width:180px;" />
					</td>
					<td width="18%" height="35px;" align="right">项目地址：</td>
					<td width="40%" align="left">
						<input  name="projAddr" id="projAddr" class="easyui-textbox" style="width:180px;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="18%" height="35px;" align="right">项目单位名称：</td>
					<td width="30%" align="left">
						<input  name="projUnitName" id="projUnitName" class="easyui-textbox" style="width:180px;" />
					</td>
					<td width="18%" height="35px;" align="right">项目单位联系人：</td>
					<td width="40%" align="left">
						<input  name="projContacts" id="projContacts" class="easyui-textbox" style="width:180px;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="18%" height="35px;" align="right">单位联系人职位：</td>
					<td width="30%" align="left">
						<input  name="projPosition" id="projPosition" class="easyui-textbox" style="width:180px;" />
					</td>
					<td width="18%" height="35px;" align="right">项目责任人：</td>
					<td width="40%" align="left"><input  name="projDuty" id="projDuty" class="easyui-combotree" style="width:180px;" 
								data-options="url: '${ctx}/sysUser/getPersonList', method: 'post',valueField:'id',textField:'text'"/></td>
				</tr>
				<tr height="35px;">
					<td width="18%" height="35px;" align="right">施工人员：</td>
					<td width="30%" align="left">
						<input  name="builder" id="builder" class="easyui-combotree" style="width:180px;" 
								data-options="url: '${ctx}/sysUser/getPersonList', type: 'post',valueField:'id',textField:'text', multiple:true"/>
					</td>
					<td width="18%" height="35px;" align="right">施工时间：</td>
					<td width="40%" align="left">
						<input class="easyui-datebox" name="startDateStr" id="startDateStr"  />
					</td>
				</tr>
				<tr height="35px;">
					<td width="18%" height="35px;" align="right">结束时间：</td>
					<td width="30%" align="left">
						<input class="easyui-datebox" name="endDateStr" id="endDateStr"  />
					</td>
					<td width="18%" height="35px;" align="right"></td>
					<td width="40%" align="left">
					</td>
				</tr>
				<tr height="80px;">
					<td width="18%" height="35px;" align="right">备注：</td>
					<td width="85%" align="left" colspan="3">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:80%;height:80px"  id="remark" name="remark">
					</td>
				</tr> 
<!-- 				<tr height="35px;"> -->
<!-- 					<td width="18%" height="35px;" align="right">项目情况：</td> -->
<!-- 					<td width="85%" align="left" colspan="3"> -->
<!-- 						 <div > -->
<!-- 						    <img id="headPic" src="${ctx }/resource/img/touxiang.jpg"  width="120px" height="110px" style="padding: 5px;cursor: hand;" title="点击添加照片" > -->
<!-- 						    <input id="fileName" name="fileName"  type="file" style="display: none" accept="image/jpeg,image/jpg,image/png" onchange="fileupload()"/> -->
<!-- 						</div> -->
<!-- 						<input  name="projCondition" id="projCondition" class="easyui-textbox" style="width:80%;" /> -->
<!-- 					</td> -->
<!-- 				</tr> -->
				
			</table>
	       <input type="hidden" name="id" id="id" />
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveProj()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   
	   	    <div id="statusdlg" class="easyui-dialog" style="width: 280px; height: 80px; padding: 5px 10px;" closed="true" buttons="#statusdlg-buttons"> 
	       <input type="hidden" id="projectStatusid">
	      <select class="easyui-combobox" id="zt" style="width:200px">
	      <option value="">暂不安排</option>
	       <option value="1">可准备</option>
	        <option value="2">可发货</option>
	      </select><a class="easyui-linkbutton" onclick="updateStatus()" style="margin-left:10px">确定</a>
	   
	       </div>
	 
	 <div id="scheduleGridToolbar" style="display: none;">	
	 	<shiro:hasPermission name="projectz:delete">
		     <a class="actions delete easyui-linkbutton " iconCls="icon-remove" plain="true">删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="projectz:edit">
		     <a class="actions edit easyui-linkbutton " iconCls="icon-edit" plain="true">编辑</a>
	   </shiro:hasPermission>
	    <shiro:hasPermission name="projectz:editStatus">
		     <a class="actions editzt easyui-linkbutton " iconCls="icon-edit" plain="true">修改状态</a>
		</shiro:hasPermission>
	</div>
	<div id="bottonBar" style="display:none">
	<a class="actions update easyui-linkbutton " iconCls="icon-edit" plain="true">更改项目进度</a>
	</div>
	<script type="text/javascript">
		var url;
		var bottonBar=$("#bottonBar")
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "项目进度信息列表",
			nowrap : false,
			border : true,
			locale : "zh_CN",
			height : $(window).height()-150,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/schedule/queryScheduleList?projStatus=1",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'projNo',
				title : '项目编号',
				width : '12%',
				align : 'center'
			}, {
				field : 'projName',
				title : '项目名称',
				width : '12%',
				align : 'center'
			},  {
				field : 'contacts',
				title : '联系人',
				width : '9%',
				align : 'center'
			}, {
				field : 'phone',
				title : '电话',
				width : '10%',
				align : 'center'
			},  {
				field : 'unitName',
				title : '单位名称',
				width : '14%',
				align : 'center'
			},{
				field : 'projUnitName',
				title : '项目单位名称',
				width : '14%',
				align : 'center'
			},{
				field : 'projContacts',
				title : '项目单位联系人',
				width : '10%',
				align : 'center'
			},{
				field : 'projPosition',
				title : '单位联系人职位',
				width : '8%',
				align : 'center'
			},{
				field : 'startDateStr',
				title : '施工时间',
				width : '10%',
				align : 'center'
			} ,{
				field : 'projZhuangtai',
				title : '状态',
				width : '8%',
				align : 'center',
					formatter : function(value, row, index) {
						if(value==null){
							return "暂不安排";
						}else if(value=="1"){
							return "<label style='color:red'>可准备</label>";
						}else{
							return "<label style='color:red'>可发货</label>";
						}		
					}
			},{
				field : '操作',
				title : '操作',
				width : '10%',
				align : 'center',
				halign : 'center',// 表头居中
				formatter : function(value, row, index) {
					
					return bottonBar.children("a.actions").attr('data-index', index).end().html();
				
				
				}
			} ] ],
			   toolbar: '#scheduleGridToolbar', 
                onDblClickRow: function (index, rows) {
                	
                	datagrid.datagrid("selectRow",index);//选中此行
      		        var currentRow = datagrid.datagrid("getSelections");//获得单机选中行，双击时只能单选 
      		        console.log(currentRow.length);
      		        if (currentRow.length > 1) {
      					$.messager.alert('提示', '只能选中一条数据', 'error');
      				}else{
      					  var row=datagrid.datagrid("getSelected");
      		        	var id=row.id;
      		        	window.name=id;
      		        	//linkForm(id);
      		        	window.location.href="../schedule/projectLink?id="+id+"&projStatus=1";
      				}
         		}
		});
			
			var gridPanel = $("#dataGrid").datagrid("getPanel");
			gridPanel.on("click","a.delete",function(){
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
                                   url : "${ctx}/schedule/deleteProject",  
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
			}).on("click","a.update",function(){
				var index = this.dataset.index;
				// 如果只有下标，没有id的情况，要获取id
				var rows = $('#dataGrid').datagrid("getRows");
				// 同下标获取对应行的数据对象
				var id = rows[index].id;
								
				$.messager.confirm("提示", "您确定要更改此项目的进程?", function(r) {
					// 点击了确定按钮，r返回的结果为true
					if (r) {
						$.get("../schedule/updateProjectStatus?id=" + id+"&status=2", function(data) {
							if (data.success) {
								// 刷新表格
								$('#dataGrid').datagrid("reload");
							} else {
								$.message.alert("提示", "更改失败");
							}
						});
					}
				});
				
			}).on("click","a.edit",function(){		
			
				var selectedRows=$("#dataGrid").datagrid("getSelections");
				 if(selectedRows.length!=1){
					 $.messager.alert("系统提示","请选择一条要修改的数据！");
					 return;
				 }
				 var row=selectedRows[0];
					$("#dlg").dialog("open").dialog("setTitle", "项目信息修改");
				 $("#projForm").form("load",row);               
                $("#startDateStr").datebox("setValue", row.startDateStr);
                $("#endDateStr").datebox("setValue", row.endDateStr);
                url = "${ctx}/schedule/editProject";
			}).on("click","a.editzt",function(){
				var selectedRows=$("#dataGrid").datagrid("getSelections");
				 if(selectedRows.length!=1){
					 $.messager.alert("系统提示","请选择一条要修改的数据！");
					 return;
				 }else{
						$("#statusdlg").dialog("open").dialog("setTitle", "状态修改");
						$("#projectStatusid").val(selectedRows[0].id);
						$("#zt").combobox("setValue", selectedRows[0].projZhuangtai);
				
				 }
				
			});

		//分页处理
		$("#dataGrid").datagrid("getPager").pagination({
			pageSize : 10,
			pageNumber : 1,
			pageList : [ 10, 20, 30, 40, 50 ],
			beforePageText : "第",//页数文本框前显示的汉字   
			afterPageText : "页    共 {pages} 页",
			displayMsg : "当前显示 {from} - {to} 条记录   共 {total} 条记录",
		});
		
		  $("#contacts").combobox({
					  onSelect: function(rec) {
					   $("#phone").textbox("setValue",rec.phone);
					    $("#unitName").textbox("setValue",rec.unitName);
					 }
               });
          $("#projDuty").combotree({
		            onBeforeSelect: function (node) {
		                if(node.name){
				          $("#projDuty").tree("unselect");
				        }
		            }
		     });
		   $("#builder").combotree({
		            onBeforeSelect: function (node) {
		                if(node.name){
				          $("#builder").tree("unselect");
				        }
		            }
		     });
    });
    
    //查询条件  
		function queryFun() {
			var queryParameter = $("#dataGrid").datagrid("options").queryParams;
			queryParameter.projNo = $("#f_projNo").val();
			queryParameter.projName = $("#f_projName").val();
			queryParameter.projStatus = $("#f_projStatus").val();
			queryParameter.beginDate = $("#f_beginDate").datebox("getValue");
			queryParameter.endDate = $("#f_endDate").datebox("getValue");
			queryParameter.fromDate = $("#f_fromDate").datebox("getValue");
			queryParameter.toDate = $("#f_toDate").datebox("getValue");
			$("#dataGrid").datagrid("reload");
		}
		
		
		//保存
		function saveProj() {
             $("#projForm").form("submit", {
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
		function updateStatus(obj){
			var zt=$("#zt").combobox("getValue");
			var id=$("#projectStatusid").val();
			console.log(id);
			$.get("../schedule/updateStatus?id=" + id+"&zt="+zt, function(data) {
				if (data.success) {
					// 刷新表格
					  $("#statusdlg").dialog("close");
					$('#dataGrid').datagrid("reload");
				} else {
					$.message.alert("提示", "更改失败");
				}
			});
		}
        
</script>
</body>
</html>
