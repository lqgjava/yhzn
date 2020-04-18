<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>公司项目应收详情</title>

<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/js/dateFormatter.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/jquery/jquery.form.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script> 

</head>

<body>

	<div class="panel">
		<%-- 列表展示 begin --%>
		<div class="easyui-panel" id="query-data" style="padding:1px;">
			<table id="dataGrid" ></table>
		</div>
		<%-- 列表展示 end --%>
	<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width:600px; height: 540px;top:10px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="linkForm" method="post"  enctype="multipart/form-data"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center" style="margin-top: 10px;">

				<tr height="35px;">
					<td width="25%" height="35px;" align="right">项目编号：</td>
					<td width="75%" align="left">
					<input  class="easyui-textbox" name="projNo"  style="width:90%;" readonly="readonly" />
					
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">项目名称：</td>
					<td width="75%" align="left">
						<input  name="projName" id="projName" readonly="readonly"  class="easyui-textbox" style="width:90%;" />
					</td>
				</tr>
				</tr>
					<tr height="35px;">
					<td width="25%" height="35px;" align="right">应收金额：</td>
					<td width="75%" align="left">
						<input  name="detailMoney" id="detailMoney" class="easyui-numberbox" style="width:90%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">已收入金额：</td>
					<td width="75%" align="left">
						<input  name="recepitMoney" readonly="readonly" readonly="readonly"  id="recepitMoney"  class="easyui-numberbox" style="width:90%;" />
					</td>
				
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">结存：</td>
					<td width="75%" align="left">
						<input  name="unpaidMoney" id="unpaidMoney" readonly="readonly" class="easyui-numberbox" style="width:90%;" />
					</td>
				</tr>
			
			
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
					<input class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:100px"  id="detailRemark" name="detailRemark">
				</td>
			</tr>
				<tr height="35px">
					<td width="25%" height="20px" align="right">凭证：</td>
					<td width="75%" height="20px" align="left">
    					<input class="easyui-filebox" data-options=" buttonText: '选择文件',prompt:'浏览'"  multiple="true" id=" file"  name="file" style="width:90%"/>
					</td>
				</tr>
			</table>
	         <input type="hidden" name="id" id="id" />
	          <input type="hidden" name="company" id="company" />
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>		
	 </div>
	 <div id="receGridToolbar" style="display: none;">	
		<a class="actions back easyui-linkbutton " iconCls="icon-edit" plain="true">返回上一级</a>
	  	<shiro:hasPermission name="financed:edit">
		<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
			plain="true">编辑</a>
	</shiro:hasPermission>
	 	<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
			plain="true">删除</a>
	     结存条件<select id="select" name="queryunpay" class="easyui-combobox" style="width:150px;"> <option value="0">结存大于0</option>  <option value="1">结存小于60000</option> <option value="2">结存大于60000</option><option value="3">所有</option> </select>
	<a class="actions search easyui-linkbutton"  iconCls="icon-search">搜索</a>
	<a class="actions print easyui-linkbutton"  iconCls="icon-print">导出</a>
	</div>
	<script type="text/javascript">
		var url;
		$(function () {
		 $("#dataGrid").datagrid({
			 title:'应收款项目列表',
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-200,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			singleSelect: true,
			url : '${ctx}/finance/QueryById?company=${company}',
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			 pageSize: 50, 
	         pageList: [50, 100, 150, 200],
	         onDblClickRow: function(rowIndex){//鼠标双击事件
	        	 $("#dataGrid").datagrid("selectRow",rowIndex);//选中此行
				        var currentRow =  $("#dataGrid").datagrid("getSelections");//获得单机选中行，双击时只能选择一条
				        var row= $("#dataGrid").datagrid("getSelected");//获取选中行的数据
				        //最后一行tr下标
				       var lastIndex = $('#dataGrid').datagrid('getRows').length-1;
				        if (currentRow.length > 1) {
							$.messager.alert('提示', '只能选中一条数据', 'error');
						}else if(row.detailMoney==0){
							$.messager.alert('提示', '请完善数据再进入收款详情', 'error');
						}else{
							var id=row.id
							location.href="${ctx}/finance/receivableDetails?id="+id;
						}
				    },
			columns : [ [ {
						field:'id',
					title:'编号',
					hidden:true
						},
					 {
						field : 'projNo',
						title : '项目编号',
						width : '10%',
						align : 'center' 
					}, {
						field : 'projName',
						title : '项目名称',
						width : '10%',
						align : 'center' 
					},{
						field : 'detailMoney',
						title : '应收金额',
						width : '10%',
						align : 'center' 
					},  {
						field : 'recepitMoney',
						title : '已收入金额',
						width : '10%',
						align : 'center'
					},{
						field : 'unpaidMoney',
						title : '结存',
						width : '10%',
						align : 'center'
					} ,
					{
						field : 'state',
						title : '应收状况',
						width : '10%',
						align : 'center'	
					},
					{
						field : 'createName',
						title : '财务人员',
						width : '10%',
						align : 'center'
					},{
						field : 'caozuo',
						title : '凭证',
						width : 160,
						align : 'center',
						formatter : function(value, row, index) {
							
							if(row.id!='2'){
								var bts = [];
								bts.push('<a data-index="' + index + '"  class="chakan">查看文件</a>');
							return bts.join('');
								
							}
						}
					} ,{
						field : 'detailRemark',
						title : '备注',
						width : '20%',
						align : 'center'
					}] ]  ,
				   
			   toolbar:'#receGridToolbar'
		})	
			var gridPanel =  $("#dataGrid").datagrid("getPanel");
			gridPanel.on("click", "a.chakan", function() {
				var index = this.dataset.index;
				// 如果只有下标，没有id的情况，要获取id
				var rows = $("#dataGrid").datagrid("getRows");
				// 同下标获取对应行的数据对象
				var id = rows[index].id;
				//判断是否有附件
			//加载数据
				  $.ajax({  type: "get",     
								      dataType: 'json',  
								      url: "${ctx}/file/QueryFileByParentId?parentId="+id,
								    success: function (data) {
								    var count=	data.total
								         if(parseInt(count)==0){	
								        		$.messager.alert("提示信息","无附件信息");
								         }else{								     
								        	 window.open("${ctx}/file/fileList/?parentId="+id);
								         }
								      }
								})	
				
			})
		
	}).on("click","a.edit",function(){
		  $("#dlg").dialog("open").dialog("setTitle", "编辑项目应收款");
		  var row= $("#dataGrid").datagrid("getSelected");//获取选中行的数据
          $("#linkForm").form('clear');
          $("#linkForm").form("load",row);  
          $("#company").val('${company}');
	}).on("click","a.delete",function(){
		var rows =  $("#dataGrid").datagrid('getSelections');
		if (rows.length <= 0) {
			$.messager.alert('提示', '请选择要删除的行', 'error');
		} else {
			$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
				// 点击了确定按钮，r返回的结果为true
				if (r) {
					$.get("../finance/deleteReceivableProjById/" + rows[0].id+"/"+rows[0].company, function(data) {
						if (data.success) {
							// 刷新表格
							 $("#dataGrid").datagrid("reload");
						} else {
							$.message.alert("提示", "删除失败");
						}
					});
				}
			});
		}
	}).on("click","a.back",function(){
		location.href="${ctx}/finance/receivable";
	}).on("click","a.search",function(){
		   var value = $("#select").combobox("getValue");
		   $("#dataGrid").datagrid("load", {
    		   unpaidMoney:value,
			});
	}).on("click","a.print",function(){
		 var value = $("#select").combobox("getValue");
		                      window.location.href="${ctx}/exl/recevibleExl?id="+financeId+"&& unpaidMoney="+value;
	});
		
		
		 $("#dataGrid").datagrid({
			onLoadSuccess: function(data) {   
			    $('.chakan').linkbutton({text:'查看文件',plain:true,iconCls:'icon-chakan'});  
			/* 	//新增一行显示统计信息
				 $("#dataGrid").datagrid('appendRow', {
					id:'2',
					contractNo: '<b>合计：</b>',
					detailMoney:parseInt(data.map.receMoney)+parseInt(data.map.unpaiMoney),
					recepitMoney:data.map.receMoney,
					unpaidMoney:data.map.unpaiMoney,
				}); */
				
			},
		/* 	rowStyler: function(index, row) {
				if(row.contractNo == '<b>合计：</b>') {	
					return 'background-color:#EAEAEA;color:blue';
				}
			} */
			
		});
		//保存
		function save() {
			var id=$("#id").val();
			// 获取到表单对象
	 	$("#linkForm").form("submit",{
				  url: '../finance/editReceivableProject' ,
				  onsubmit: function () {
	                    return $(this).form("validate");
	                }, 
	                success: function (result) {
	              
	                    if (result==1 ) {
	                        $.messager.alert("提示信息", "操作成功");
	                        $("#linkForm").form('clear');
	    					// 清除查询参数
	                        $("#dlg").dialog("close");
	                        $("#dataGrid").datagrid('reload');
	                    }
	                    else {
	                        $.messager.alert("提示信息", '编辑失败');
	                    }
	                }
			}); 
		
		}
	
		/*  $("#recepitMoney").numberbox({
			    "onChange":function(){
			    var recepit=	$("#recepitMoney").numberbox("getValue");
			    var detailMoney=$("#detailMoney").numberbox("getValue");
			    if(parseInt(detailMoney)<parseInt(recepit)){
			    	$("#recepitMoney").numberbox("setValue","");
			    	 $.messager.alert("提示","值不能大于应收金额");
			    }else{
			    	var yue=detailMoney-recepit;
			    	$("#unpaidMoney").numberbox("setValue",yue);
			    }
			    }
			  }); */
	</script>
</body>
</html>
