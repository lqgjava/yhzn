<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>我的任务</title>
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
				
					<label>任务编号：</label><input type="text" name="taskNo" id="f_taskNo" class="easyui-textbox"  />	
					<label>任务名称：</label>	<input type="text" name="taskName" id="f_taskName" class="easyui-textbox"  />
					<label>发布时间：</label><input class="easyui-datebox" name="beginDate" id="f_beginDate" />至 <input class="easyui-datebox" name="endDate" id="f_endDate" />
					<input type="button" value="发布任务" class="button_green" onclick="sentTask()" />
					<input type="button" value="查询" class="button_blue" onclick="queryFun()" />
					<input type="button" value="重置" class="button_green" onclick="resetQuery()"/>
		
		</form>
		</div>
		<hr>
		<%-- 列表展示 begin --%>
			<table id="dataGrid"></table>
		<%-- 列表展示 end --%>
		
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width: 500px; height: 300px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="taskForm" method="post"> 
		       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
					<tr>
						<td width="20%"  align="right">反馈内容：</td>
						<td width="80%"  align="left">
							<input class="easyui-textbox"  data-options="multiline:true"  style="width:95%;height:150px"  id="reportResult" name="reportResult"   required="true">
						</td>
					</tr>
				</table>
	       <input type="hidden" name="id" id="id" />
	       <input type="hidden" name="taskNo" id="taskNo" /> 
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveTask()" iconcls="icon-save">保存</a> 
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
						<th field="reportResult" width="51%" align="center">完成情况</th>
					</tr>
				</thead>
			</table>
			<div id="tbShow" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winShow').window('close')">关闭</a>
<!-- 				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel()">导出</a> -->
		   </div>
	</div>
	</div>
	<%--任务列表信息显示 end --%>   
	
	<script type="text/javascript">
		var url;
		var editIndex = undefined;  
		var purId ="";
		var pNo="";
		
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "我的任务信息列表",
			nowrap : false,
			border : true,
			locale : "zh_CN",
			height : $(window).height()-100,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/workbench/queryRecTaskList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
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
				field : 'createName',
				title : '任务发送人',
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
					if(row.status == "0" || row.status == null){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='signTask(&apos;" + row['id'] + "&apos;,&apos;" + row['taskNo'] + "&apos;)'>签收</a>"; 
					}else if(row.status == "1"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='fbTask(&apos;" + row['id'] + "&apos;,&apos;" + row['taskNo'] + "&apos;)'>反馈</a>"; 
					}else if(row.status == "2" || row.status == "3"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='viewTask(&apos;" + row['taskId'] + "&apos;)'>查看任务</a>"; 
					}else if(row.status == "4"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='fbTask(&apos;" + row['id'] + "&apos;,&apos;" + row['taskNo'] + "&apos;)'>重新反馈</a>"; 
					}else{
						str = "<a style='color:blue' href='javascript:void(0)' onclick='viewTask(&apos;" + row['taskId'] + "&apos;)'>查看任务</a>"; 
						
					}
                    return str;
                }
			} ] ]
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
			var queryParameter = $("#dataGrid").datagrid("options").queryParams;
			queryParameter.taskName = $("#f_taskName").val();
			queryParameter.taskNo = $("#f_taskNo").val();
			queryParameter.beginDate = $("#f_beginDate").datebox("getValue");
			queryParameter.endDate = $("#f_endDate").datebox("getValue");
			$("#dataGrid").datagrid("reload");
		}
		
        
        //进入我的任务
        function sentTask(){
        	window.location.href ="${ctx}/main/toWorkbench";
        }
        //查看任务
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
		//签收
		function signTask(id,taskNo){
		 $.messager.confirm("提示","您确定要签收吗",function(t){  
            if(t){  
				$.ajax({  
	            	 type : "post", 
	                 url : "${ctx}/workbench/signTask",  
	                 data : {"id":id,"taskNo":taskNo},  
	                 dataType : "json",  
	                 success : function(r) {  
	                     if (r.success) {  
	                          $.messager.show({  
	                             msg : r.msg,  
	                             title : "签收成功"  
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
		
		//反馈
		function fbTask(id,taskNo){
			 $("#dlg").dialog("open").dialog("setTitle", "任务反馈");
			 $("#id").val(id);
			 $("#taskNo").val(taskNo);
		     url = "${ctx}/workbench/feedbackTask";
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
	
		//提交任务信息
		function saveTask(){
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
	</script>
</body>
</html>