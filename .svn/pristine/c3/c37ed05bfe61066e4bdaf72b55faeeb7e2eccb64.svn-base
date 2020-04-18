
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>库存核算</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
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
					<td width="10%" height="35px;" align="right">类别：</td>
					<td width="20%" align="left">
<!-- 						<input type="text" name="type" id="f_type" class="easyui-textbox" style="width:172px;" /> -->
						<select class="easyui-combobox" id="f_type" name="type"  style="width:172px;" data-options="valueField:'id', textField:'text'" >
	                        <option value="">全部</option>
	    				    <option value="材料">材料</option>
	    				    <option value="配件">配件</option>
	    				    <option value="成品">成品</option>
				       </select>
				    
					</td>
					<td width="10%" height="35px;" align="right">名称：</td>
					<td width="20%" align="left">
						<input type="text" name="name" id="f_name" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right">规格：</td>
					<td width="30%" align="left">
						<input type="text" name="standard" id="f_standard" class="easyui-textbox" style="width:172px;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="10%" height="35px;" align="right">型号：</td>
					<td width="20%" align="left">
						<input type="text" name="model" id="f_model" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right">品牌：</td>
					<td width="20%" align="left">
						<input type="text" name="brand" id="f_brand" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right">供应商：</td>
					<td width="30%" align="left">
						<input type="text" name="supplier" id="f_supplier" class="easyui-textbox" style="width:172px;" />
					</td>
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
	   
	<%-- 入库信息显示 begin --%>
	<div id="winEntry" class="easyui-window" title="入库信息记录" style="width:60%;height:60%;" data-options="iconCls:'icon-save',modal:true" closed="true">
		<table id="dgEntry" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;"data-options="rownumbers: true,toolbar: '#tbEntry',nowrap: false" > 
				<thead>
					<tr>
						<th field="purNo" width="14%" align="center" >采购单号</th>
						<th field="name" width="17%" align="center">名称</th>
						<th field="standard" width="11%" align="center">规格</th>
						<th field="model" width="11%" align="center">型号</th>
						<th field="supplier" width="16%" align="center">供应商</th>
						<th field="amount" width="10%" align="center">入库数</th>
						<th field="modifyName" width="10%" align="center">入库人</th>
						<th field="modifyDateStr" width="13%" align="center">入库时间</th>
					</tr>
				</thead>
			</table>
			<div id="tbEntry" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winEntry').window('close')">关闭</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel()">导出</a>
				
		   </div>
	</div>
	<%-- 入库信息信息显示 end --%> 
	
	<%-- 出库信息显示 begin --%>
	<div id="winOut" class="easyui-window" title="出库信息记录" style="width:60%;height:60%;" data-options="iconCls:'icon-save',modal:true" closed="true">
		<table id="dgOut" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;"data-options="rownumbers: true,toolbar: '#tbOut',nowrap: false" > 
				<thead>
					<tr>
						<th field="outNo" width="14%" align="center" >出库单号</th>
						<th field="name" width="17%" align="center">名称</th>
						<th field="standard" width="11%" align="center">规格</th>
						<th field="model" width="11%" align="center">型号</th>
						<th field="supplier" width="16%" align="center">供应商</th>
						<th field="outAmount" width="10%" align="center">出库数</th>
						<th field="modifyName" width="10%" align="center">出库人</th>
						<th field="modifyDateStr" width="13%" align="center">出库时间</th>
					</tr>
				</thead>
			</table>
			<div id="tbOut" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winOut').window('close')">关闭</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel()">导出</a>
				
		   </div>
	</div>
	<%-- 出库信息信息显示 end --%> 
	<div id="productGridToolbar" style="display: none;">	
			<shiro:hasPermission name="check:edit">
		<a class="actions edit easyui-linkbutton " iconCls="icon-edit"
			plain="true">修改</a>
	</shiro:hasPermission> 	
		<a class="actions save easyui-linkbutton " iconCls="icon-save"
			plain="true">保存</a>
			<a class="actions redo easyui-linkbutton " iconCls="icon-redo"
			plain="true">取消编辑</a>
	</div>
	<script type="text/javascript">
		var url;
		var tf;
		 var permission="<%=session.getAttribute("biaoshi")%>";
		  for(var i=0;i<permission.length;i++){
     	  if(permission[i]=="check:unitPrice"){	
     		  tf=false;
     			break;
     	  }else{
     		  tf=true;
     	  }
     	  }
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "库存信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/stockCheck/queryStockCheckList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'type',
				title : '类别',
				width : '6%',
				align : 'center' 
			}, {
				field : 'name',
				title : '名称',
				width : '15%',
				align : 'center'
			}, {
				field : 'standard',
				title : '规格',
				width : '13%',
				align : 'center'
			}, 
			{
				field : 'model',
				title : '型号',
				width : '9%',
				align : 'center'
			} ,
			{
				field : 'brand',
				title : '品牌',
				width : '9%',
				align : 'center'
			},
			{
				field : 'unit',
				title : '单位',
				width : '8%',
				align : 'center'
			},
			{
				field : 'costPrice',
				title : '成本价(元)',
				width : '7%',
				align : 'center',
				hidden:tf
			},
			{
				field : 'amount',
				title : '库存数量',
				width : '9%',
				align : 'center',
				editor: {
							type:'numberbox',
							options:{
								required:true
							}
						}
			},
			{
				field : 'stockAmount',
				title : '库存报警数',
				width : '9%',
				align : 'center',
				editor: {
							type:'numberbox',
							options:{
								required:true
							}
						}
			},
			{
				field : 'action',
				title : '操作',
				width : '14%',
				align : 'center',
				formatter:function(value,row,index){
						var str="<a style='color:blue' href='javascript:void(0)' title='入库记录' onclick='entryStock(&apos;" + row['id'] + "&apos;)'>入库记录</a>&nbsp;&nbsp;"
						       +"<a style='color:blue' href='javascript:void(0)' title='出库记录' onclick='outStock(&apos;" + row['id'] + "&apos;)'>出库记录</a>";
						return str;
					}
			}] ],
			toolbar:/* [  
			 { text: '修改', iconCls: 'icon-edit', 
                      handler: function () {
                          //修改时要获取选择到的行
                         var rows = datagrid.datagrid("getSelections");
                         //如果只选择了一行则可以进行修改，否则不操作
                          if(rows.length<=0){  
		                        $.messager.alert('提示','请选择要修改的行','error');  
		                    }else if(rows.length>1){  
		                        $.messager.alert('提示','只能选择一条数据进行修改','error');  
		                    }else if(rows.length==1){  
                             //当无编辑行时
                             if (editRow == undefined) {
                                 //获取到当前选择行的下标
                                var index = datagrid.datagrid("getRowIndex", rows[0]);
                                 //开启编辑
                                 datagrid.datagrid("beginEdit", index);
                                 //把当前开启编辑的行赋值给全局变量editRow
                                 editRow = index;
                                 //当开启了当前选择行的编辑状态之后，
                                 //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                                 datagrid.datagrid("unselectAll");
                             }
                         }
                     }
                 }, '-',
                { text: '保存', iconCls: 'icon-save', 
                     handler: function () {
                          //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                          datagrid.datagrid("endEdit", editRow);  
                          editRow = undefined;
                     }
                 }, '-',
                 { text: '取消编辑', iconCls: 'icon-redo', 
                     handler: function () {
                          //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                          editRow = undefined;
                          datagrid.datagrid("rejectChanges");
                          datagrid.datagrid("unselectAll");
                     }
                 }, '-'] */'#productGridToolbar',
                 onAfterEdit: function (rowIndex, rowData, changes) {
                     //endEdit该方法触发此事件                     
                     //var row = datagrid.datagrid("getData").rows[rowIndex];  //获取某一行的值  
                     var updated  = datagrid.datagrid('getChanges','updated');
                     if(updated.length <1){
                         editRow = undefined;
                         datagrid.datagrid('unselectAll');
                         return;
                     }
                           
                     $.ajax({
                        url : '${ctx}/stockCheck/editStockCheck',
                        type : 'POST',
                        data : {
                            'id':rowData.id,
                            'amount' :rowData.amount,
                            'stockAmount':rowData.stockAmount
                        },
                        beforeSend : function (){
                            $.messager.progress({
                                text : '正在处理中...'
                            })
                        },
                        success : function (data){
                            $.messager.progress('close');
                            if (data > 0){  
                                 datagrid.datagrid("acceptChanges");  
                                 $.messager.show({
                                    title : '操作提示',
                                     msg : '修改成功'
                                 });            
                                 editRow = undefined;
                                 datagrid.datagrid("reload");  
                            } else if (data == -999) {
                                $.messager.alert('添加失败', '抱歉！您没有权限！', 'warning');
                            } else {
                                datagrid.datagrid("beginEdit",editRow); 
                                $.messager.alert('警告操作', '未知错误！请重新刷新后提交！', 'warning');
                            }
                            datagrid.datagrid("unselectAll");  
                        }
                     });
                 },
                 onDblClickRow: function (rowIndex, rowData) {
                 //双击开启编辑行
                     if (editRow == undefined) {
                             datagrid.datagrid("beginEdit", rowIndex);
                             editRow = rowIndex;
                     }
                 }
		});
			 var productgridPanel = $("#dataGrid").datagrid("getPanel");
			  productgridPanel.on("click", "a.edit", function() {
				  //修改时要获取选择到的行
                  var rows = datagrid.datagrid("getSelections");
                  //如果只选择了一行则可以进行修改，否则不操作
                   if(rows.length<=0){  
	                        $.messager.alert('提示','请选择要修改的行','error');  
	                    }else if(rows.length>1){  
	                        $.messager.alert('提示','只能选择一条数据进行修改','error');  
	                    }else if(rows.length==1){  
                      //当无编辑行时
                      if (editRow == undefined) {
                          //获取到当前选择行的下标
                         var index = datagrid.datagrid("getRowIndex", rows[0]);
                          //开启编辑
                          datagrid.datagrid("beginEdit", index);
                          //把当前开启编辑的行赋值给全局变量editRow
                          editRow = index;
                          //当开启了当前选择行的编辑状态之后，
                          //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                          datagrid.datagrid("unselectAll");
                      }
                  }
            
				
			  }).on("click","a.save",function(){
				  
				  //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
                  datagrid.datagrid("endEdit", editRow);  
                  editRow = undefined;
			  }).on("click","a.redo",function(){
				  //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                  editRow = undefined;
                  datagrid.datagrid("rejectChanges");
                  datagrid.datagrid("unselectAll");
			  })
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
			queryParameter.type = $("#f_type").combobox("getValue");
			queryParameter.name = $("#f_name").val();
			queryParameter.standard = $("#f_standard").val();
			queryParameter.model = $("#f_model").val();
			queryParameter.brand = $("#f_brand").val();
			queryParameter.supplier = $("#f_supplier").val();
			$("#dataGrid").datagrid("reload");
		}
		

	
		//保存
		function saveProduct() {
             $("#productForm").form("submit", {
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
        
        //入库信息
        function entryStock(id){
        	$.ajax({
                 url: "${ctx}/stockCheck/queryEntryRecord?id="+id,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     if(data == "1"){
                     	 $.messager.alert("提示信息", "没有找到该产品的入库信息！");
                     }else{
                     	$("#winEntry").window("open");//打开清单窗口
                    	$("#dgEntry").datagrid("loadData", data.rows);  //动态取数据
                     }
                 }
             });
        }
        
        //出库信息
        function outStock(id){
        	$.ajax({
                 url: "${ctx}/stockCheck/queryOutRecord?id="+id,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                 	if(data == "1"){
                     	 $.messager.alert("提示信息", "没有找到该产品的出库信息！");
                     }else{
	                 	 $("#winOut").window("open");//打开清单窗口
	                     $("#dgOut").datagrid("loadData", data.rows);  //动态取数据
	                   }
                 }
             });
        }
	</script>
</body>
</html>
