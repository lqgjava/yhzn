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
<title>我的工作台</title>
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
		<div class="panel-header"  border="0" cellspacing="5" cellpadding="0"  style="height: 20px;padding: 10px;overflow: hidden;width:98.5%" >
			<form action="" name="QueryForm">
				任务编号：<input type="text" name="taskNo" id="f_taskNo" class="easyui-textbox"  />
				任务名称：<input type="text" name="taskName" id="f_taskName" class="easyui-textbox"  />
				操作时间：<input class="easyui-datebox" name="beginDate" id="f_beginDate"  />
						<input type="button" value="查看我的任务" class="button_green" onclick="queryTask()" />
						<input type="button" value="查询" class="button_blue" onclick="queryFun()" />
						<input type="button" value="重置" class="button_green" onclick="resetQuery()"/>					
		</form>
		</div>
		<hr>	
			<table id="dataGrid" ></table>
		<%-- 列表展示 end --%>
		
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width:25%; height: 44%; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="taskForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">任务名称：</td>
					<td width="70%" align="left">
						<input  name="taskName" id="taskName" class="easyui-textbox" style="width:80%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">任务内容：</td>
					<td width="70%" align="left">
						<input  name="taskContent" id="taskContent" class="easyui-textbox" style="width:80%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">任务接收人：</td>
					<td width="70%" align="left">
						<input id="receiveId" name="receiveId" class="easyui-combotree" style="width:80%;" data-options="required:true"  />			
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">任务奖励金：</td>
					<td width="70%" align="left">
						<input  name="reward" id="reward" class="easyui-textbox" style="width:80%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="30%" height="35px;" align="right">任务完成日期：</td>
					<td width="70%" align="left">
						<input class="easyui-datebox" name="finishDateStr" id="finishDateStr"  />
					</td>
				</tr>
				</tr>
				<tr height="80px;">
					<td width="30%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:80%;height:80px"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" />
	       <input type="hidden" name="receiveName" id="receiveName" /> 
	       <input type="hidden" name="status" id="status" /> 
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveTask()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="sentTask()" iconcls="icon-save">保存并下发任务</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   
	   <%-- 任务列表信息显示 begin --%>
	<div id="winShow" class="easyui-window" title="任务列表" style="align:center;width:70%;height:60%;" data-options="iconCls:'icon-save',modal:true" closed="true">
		<table id="dgShow" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;"data-options="rownumbers: true,toolbar: '#tbShow'," > 
				<thead>
					<tr>
						<th field="taskNo" width="15%" align="center">任务编号</th>
						<th field="status" width="10%" align="center" formatter="formatStatus">任务状态</th>
						<th field="singedr" width="12%" align="center">任务接收人</th>
						<th field="singedDateStr" width="12%" align="center">签收时间</th>
<!-- 						<th field="reason" width="22%" align="center">延期原因</th> -->
						<th field="reportResult" width="40%" align="center">完成情况</th>
						<th field="oper" width="10%" align="center" formatter="formatCheck">操作</th>
					</tr>
				</thead>
			</table>
			<div id="tbShow" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winShow').window('close')">关闭</a>
<!-- 				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel()">导出</a> -->
		   </div>
	</div>
	<%--任务列表信息显示 end --%>   
	  
	<div id="TaskGridToolbar" style="display: none;">	
		<shiro:hasPermission name="workbench:addTask">
			<a class="actions create easyui-linkbutton " iconCls="icon-add"
				plain="true">发布任务</a>	
				</shiro:hasPermission>
				<shiro:hasPermission name="workbench:deleteTask">
				<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
				plain="true">删除</a>	
				</shiro:hasPermission>
</div>
	<script type="text/javascript">
		var url;
		var editIndex = undefined;  
		var purId ="";
		var pNo="";
		
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "任务信息列表",
			nowrap : false,
			border : true,
			locale : "zh_CN",
			height : $(window).height()-100,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/workbench/queryTaskList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			onDblClickRow: function(rowIndex){//鼠标双击事件
				datagrid.datagrid("selectRow",rowIndex);//选中此行
			        var rows=datagrid.datagrid("getSelections");
                    if(rows.length<=0){  
                        $.messager.alert("提示","请选择要修改的行","error");  
                    }else if(rows.length>1){  
                        $.messager.alert("提示","只能选择一条数据进行修改","error");  
                    }else if(rows.length==1){ 
                    	console.log(rows[0].status);
                    	if(rows[0].status ==null){
                    		 $("#dlg").dialog("open").dialog("setTitle", "任务信息修改");
     		                $("#taskForm").form("load", rows[0]);
     		                url = "${ctx}/workbench/editTask";
     		                getPersonList(rows[0].receiveId,rows[0].receiveName);
                    	}else{
                    		 $.messager.alert("提示","任务已发布，不能进行修改","error");  
                    	}
		               
                    }
		
			    },
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'taskNo',
				title : '任务编号',
				width : '12%',
				align : 'center'
			}, {
				field : 'taskName',
				title : '任务名称',
				width : '18%',
				align : 'center'
			}, {
				field : 'taskContent',
				title : '任务内容',
				width : '17%',
				align : 'center'
			}, 
			{
				field : 'receiveName',
				title : '任务接收人',
				width : '10%',
				align : 'center'
			}, 
			{
				field : 'status',
				title : '任务状态',
				width : '8%',
				align : 'center',
				formatter: function(value,row,index){
					var str;
					if(row.status == "2"){
						str = "<span style='color:red'>已完成</span>"; 
					}else if(row.status == "1"){
						str = "<span style='color:red'>进行中</span>"; 
					}else if(row.status == "0"){
						str = "<span style='color:red'>代签收</span>"; 
					}else{
						str = "<span style='color:red'>待发送</span>"; 
					}
                    return str;
                }
			} ,
			{
				field : 'reward',
				title : '任务奖金',
				width : '8%',
				align : 'center'
			} ,
			{
				field : 'finishDateStr',
				title : '任务完成时间',
				width : '9%',
				align : 'center'
			} ,
			{
				field : 'createDateStr',
				title : '创建时间',
				width : '9%',
				align : 'center'
			} ,
			{
				field : 'oper',
				title : '操作',
				width : '8%',
				align : 'center',
				formatter: function(value,row,index){
					var str;
					if(row.status == "" || row.status == null){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='sentFun(&apos;" + row['id'] + "&apos;,&apos;" + row['taskNo'] + "&apos;)'>发送任务</a>"; 
					}else if(row.status == "2"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='checkTask(&apos;" + row['id'] + "&apos;)'>审核</a>"; 
					}else{
						str = "<a style='color:blue' href='javascript:void(0)' onclick='viewTask(&apos;" + row['id'] + "&apos;)'>查看任务</a>"; 
						
					}
                    return str;
                }
			} ] ],
			   toolbar:'#TaskGridToolbar',  
                //height : $("#query-data").height() - 5,
		});
			var TaskgridPanel = datagrid.datagrid("getPanel");
			TaskgridPanel.on("click", "a.create", function() {
	                   $("#dlg").dialog("open").dialog("setTitle", "发布任务信息");
	                   $("#taskForm").form("clear");
	                    url = "${ctx}/workbench/addTask";
	                    $("#receiveId").combotree({
				          	url : "${ctx }/sysUser/getPersonList",  
	                		data : {"personId":""},  
				            method: 'post',
				            multiple: true,
				            onlyLeafCheck:true
				        })
				       // $("#receiveId").combobox({disabled:false});
	               
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
                                  url : "${ctx}/workbench/deleteTask",  
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
                 }) ;
		
			
				
			

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
			var queryParameter = $("#dataGrid").datagrid("options").queryParams;
			queryParameter.taskName = $("#f_taskName").val();
			queryParameter.taskNo = $("#f_taskNo").val();
			queryParameter.beginDate = $("#f_beginDate").datebox("getValue");
			queryParameter.endDate = $("#f_endDate").datebox("getValue");
			$("#dataGrid").datagrid("reload");
		}
		
		//保存并发送任务
		function sentTask(){
			 $.messager.confirm("提示","确定保存并下发任务吗？",function(t){  
                 if(t){  
                 	var receiveName = $("#receiveId").combotree("getText");
						$("#receiveName").val(receiveName);
						$("#status").val("0");
			              $("#taskForm").form("submit", {
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
             }); 
		}
		
		
		//保存并发送任务
		function sentFun(id,taskNo){
			 $.messager.confirm("提示","确定下发任务吗？",function(t){  
                 if(t){  
                 	$.ajax({  
                   		type : "post", 
                        url : "${ctx}/workbench/sentTask",  
                        data : {"id":id,"taskNo":taskNo},  
                        dataType : "json",  
                        success : function(r) {  
                            if (r.success) {  
                                 $.messager.show({  
                                    msg : r.msg,  
                                    title : "发送成功"  
                                }); 
                                //重新加载数据
                                $("#dataGrid").datagrid("reload"); 
                            } else {  
                                $.messager.alert("错误", r.msg, "error");  
                            }  
                        }  
                    });
                 
                 }  
             }); 
		}
		
		
		
		//保存
		function saveTask() {
			var receiveName = $("#receiveId").combotree("getText");
			$("#receiveName").val(receiveName);
              $("#taskForm").form("submit", {
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
        
        //获取用户列表
         function getPersonList(personId,receiveName){
	          $("#receiveId").combotree({
	            url : "${ctx }/sysUser/getPersonList?personId="+personId,  
	            method: 'post',
	            multiple: true,
	            onlyLeafCheck:true,
	            onLoadSuccess: function (node, data) {
	              $("#receiveId").combotree("setValue",receiveName);
	            }
	        });
        }
        
        //查看清单
		function checkTask(taskId){
			 $.ajax({
                 url: "${ctx}/workbench/findRecTaskList?taskId="+taskId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     $("#winShow").window("open");//打开清单窗口
                     $("#dgShow").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		function viewTask(taskId){
			 $.ajax({
                 url: "${ctx}/workbench/findRecTaskList?taskId="+taskId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     $("#winShow").window("open");//打开清单窗口
                     $("#dgShow").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		function formatStatus(value,row){
			var str;
			if(row.status == "4"){
				str = "<span style='color:red'>审核不通过</span>"; 
			}else if(row.status == "3"){
				str = "<span style='color:red'>审核通过</span>"; 
			}else if(row.status == "2"){
				str = "<span style='color:red'>已反馈</span>"; 
			}else if(row.status == "1"){
				str = "<span style='color:red'>已签收</span>"; 
			}else{
				str = "<span style='color:red'>待签收</span>"; 
			}
            return str;
		}
		
		function formatCheck(value,row){
	        if(row.status == "2"){
				return "<a style='color:blue' href='javascript:void(0)' onclick='viewTask(&apos;" + row['id'] + "&apos;)'>审核</a>";
	        }else{
	        	return "无";
	        }
		}
        
        //进入我的任务
        function queryTask(){
        	window.location.href ="${ctx}/main/toTask";
        }
		
		
	</script>
</body>
</html>