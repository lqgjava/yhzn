<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>单位支付详情</title>

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
			<table id="paydataGrid" ></table>
		</div>
		<%-- 列表展示 end --%>
	<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width:550px;height: 540px;top:10px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="linkForm" method="post"  enctype="multipart/form-data"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center" style="margin-top: 10px;">
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">供应商名称：</td>
					<td width="75%" align="left">
						<input id="supplier" class="easyui-textbox" name="supplier" readonly="readonly"  style="width:90%;" />
					
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">财务人员：</td>
					<td width="75%" align="left">
						<input id="financeMan" class="easyui-textbox" name="financeMan"  style="width:90%;" />
					
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">应付金额：</td>
					<td width="75%" align="left">
						<input  name="money" id="money" readonly="readonly" class="easyui-numberbox" style="width:90%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;"  align="right">支出：</td>
					<td width="75%" align="left">
						<input  name="recepitMoney" readonly="readonly" id="recepitMoney"  required="true" class="easyui-numberbox" style="width:90%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">结存：</td>
					<td width="75%" align="left">
						<input  name="unpaidMoney" readonly="readonly" id="unpaidMoney" class="easyui-numberbox" style="width:90%;" />
					</td>
				</tr>
				
			
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
					<input class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:100px"  id="remark" name="remark">
				</td>
			</tr>
				<!-- <tr height="35px">
					<td width="25%" height="20px" align="right">凭证：</td>
					<td width="75%" height="20px" align="left">
    					<input class="easyui-filebox" data-options="buttonText: '选择文件',prompt:'浏览'"  multiple="true" id=" file"  name="file" style="width:90%"/>
					</td>
				</tr> -->
			</table>
	         <input type="hidden" name="id" id="id" />
	          
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	
	 <div id="paydGridToolbar" style="display: none;">	
	 <a class="actions easyui-linkbutton " onclick="javascript:history.back(-1);" iconCls="icon-edit" plain="true">返回上一级</a>
	  	<shiro:hasPermission name="payd:edit">
		<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
			plain="true">编辑</a>
	</shiro:hasPermission>
	 	<%-- <shiro:hasPermission name="payd:delete">
		<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
			plain="true">删除</a>
	</shiro:hasPermission>  --%>
	结存条件<select id="select" name="queryunpay" class="easyui-combobox" style="width:150px;"> <option value="0">结存大于0</option>  <option value="1">结存小于60000</option> <option value="2">结存大于60000</option><option value="3">所有</option> </select>
	<a class="actions search easyui-linkbutton"  iconCls="icon-search">搜索</a>
	<a class="actions print easyui-linkbutton"  iconCls="icon-print">导出</a>
	</div>
	<script type="text/javascript">
	var purNo ='${purNo}';
		var url;
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid=$("#paydataGrid").datagrid({
				title:'应付款供应商列表',
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-200,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : '${ctx}/pay/queryByPurNo?purNo='+purNo,
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			 pageSize: 50, 
	         pageList: [50, 100, 150, 200],
	         onDblClickRow: function(rowIndex){//鼠标双击事件
					$('#paydataGrid').datagrid("selectRow",rowIndex);//选中此行
				        var currentRow = $('#paydataGrid').datagrid("getSelections");//获得单机选中行，双击时只能选择一条
				        var row=$('#paydataGrid').datagrid("getSelected");//获取选中行的数据
				        //最后一行tr下标
				       var lastIndex = $('#paydataGrid').datagrid('getRows').length-1;
				        if (currentRow.length > 1) {
							$.messager.alert('提示', '只能选中一条数据', 'error');
						} else{
							var id=row.id;
							console.log(id);
						
							location.href ='../pay/payableDetail?id='+id;
						}
				    },
			columns : [ [ 
					{
						field : 'supplier',
						title : '供应商',
						width : '9%',
						align : 'center' 
					},{
						field : 'money',
						title : '金额',
						width : '8%',
						align : 'center' 
					}, {
						field : 'recepitMoney',
						title : '支出',
						width : '10%',
						align : 'center'
					}, {
						field : 'unpaidMoney',
						title : '结存',
						width : '10%',
						align : 'center'
					} ,
					{
						field : 'insertDate',
						title : '录入时间',
						width : '10%',
						align : 'center'
					} ,
					
					{
						field : 'financeMan',
						title : '财务人员',
						width : '10%',
						align : 'center'
					}/* ,{
						field : 'caozuo',
						title : '采购单详情',
						width : '10%',
						align : 'center',
						formatter : function(value, row, index) {
							var bts = [];
							if(row.id!='2'){
							bts.push('<a data-index="' + index + '"  class="chakan">下载文件</a>');
							return bts.join('');
							}
						}
					}, */,{
						field : 'remark',
						title : '备注',
						width : '28%',
						align : 'center'
					}] ] ,
		    onLoadSuccess:function(data){  
		       // $('.chakan').linkbutton({text:'下载文件',plain:true,iconCls:'icon-chakan'});  
		    } ,
			   toolbar:'#paydGridToolbar', 
		});
		/* 	datagrid.datagrid({
				onLoadSuccess: function(data) {
					//新增一行显示统计信息
					datagrid.datagrid('appendRow', {
						id:'2',
						contractNo: '<b>合计：</b>',
						detailMoney:parseInt(data.map.unpaiMoney)+parseInt(data.map.receMoney),
						recepitMoney:data.map.receMoney,
						unPaydMoney:data.map.unpaiMoney,
					});
				}, */
				/* rowStyler: function(index, row) {
					if(row.contractNo == '<b>合计：</b>') {	
						return 'background-color:#EAEAEA;color:blue';
					}
				} 
				
			});*/
			var gridPanel = datagrid.datagrid("getPanel");
			gridPanel.on("click", "a.chakan", function() {
				var index = this.dataset.index;
				// 如果只有下标，没有id的情况，要获取id
				var rows = datagrid.datagrid("getRows");
				// 同下标获取对应行的数据对象
				var id = rows[index].id;
				  $.ajax({
				      type: "get",     
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
		
	}).on("click","a.delete",function(){
		var ids = [];
		var rows =  $("#paydataGrid").datagrid('getSelections');
		if (rows.length <= 0) {
			$.messager.alert('提示', '请选择要删除的行', 'error');
		} else {
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
				// 点击了确定按钮，r返回的结果为true
				if (r) {
					$.get("../pay/delete/" + ids, function(data) {
						if (data.success == "1") {
							// 刷新表格
							 $("#paydataGrid").datagrid("reload");
						} else {
							$.message.alert("提示", "删除失败");
						}
					});
				}
			});
		}
	}).on("click","a.edit",function(){
		var rows = $("#paydataGrid").datagrid('getSelections');
         if(rows.length<=0){  
             $.messager.alert('提示','请选择要修改的行','error');  
         }else if(rows.length>1){  
             $.messager.alert('提示','只能选择一条数据进行修改','error');  
         }else if(rows.length==1){  
             $("#dlg").dialog("open").dialog('setTitle', '应付款记录修改');
             $("#linkForm").form("load", rows[0]);
         }  
	}).on("click","a.search",function(){
		  var value = $("#select").combobox("getValue");
   	   datagrid.datagrid("load", {
   		   unPaydMoney:value,
			});
	}).on("click","a.print",function(){
		 var value = $("#select").combobox("getValue");
		                      window.location.href="${ctx}/exl/payableExl?id="+payId+"&& unPaydMoney="+value;
	});
		
		
		
		//保存
		function save() {
			var id=$("#id").val();
			// 获取到表单对象
	 	$("#linkForm").form("submit",{
				  url:  "../pay/editPayableSupplier" ,
				  onsubmit: function () {
	                    return $(this).form("validate");
	                },     success: function (result) {
	                    if (result == "1") {
	                        $.messager.alert("提示信息", "操作成功");
	                        $("#linkForm").form('clear');
	    					// 清除查询参数
	                        $("#dlg").dialog("close");
	                        $("#paydataGrid").datagrid('reload');
	                    }
	                    else {
	                        $.messager.alert("提示信息",result);
	                    }
	                }
			}); 
		
		}
	/* 	$('#cc').combogrid({ 
			  keyHandler: {
	              up: function() {},
	              down: function() {},
	              enter: function() {},
	              query: function(q) {
	                  //动态搜索
	                  $('#cc').combogrid("grid").datagrid("reload", { 'projNo': q });
	                  $('#cc').combogrid("setValue", q);
	              }
	          } , onSelect: function (rowIndex, rowData) {
	        	  var g = $('#cc').combogrid('grid');	// 获取表格控件对象
	        	  var r = g.datagrid('getSelected');	//获取表格当前选中行
	        	 $("#projName").textbox('setValue',r.projName);
	          } 
	      }); */
		/*  $("#recepitMoney").numberbox({
			    "onChange":function(){
			    var recepit=	$("#recepitMoney").numberbox("getValue");//支出
			    var detailMoney=$("#detailMoney").numberbox("getValue");//应该支出金额
			    if(parseInt(detailMoney)<parseInt(recepit)){
			    	$("#recepitMoney").numberbox("setValue","");
			    	 $.messager.alert("提示","值不能大于应付金额");
			    }else{
			    	var yue=detailMoney-recepit;
			    	$("#unPaydMoney").numberbox("setValue",yue);
			    }
			    }
			  });
		 
		 
		 */

	</script>
	
</body>
</html>
