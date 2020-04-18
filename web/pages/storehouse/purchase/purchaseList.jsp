<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>采购清单信息</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/js/print.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/js/dateFormatter.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="panel">
		<div class="panel-header">
			<form action="" name="QueryForm">
			<table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
				<tr height="35px;">
					
					<td width="10%" height="35px;" align="right">采购清单编号：</td>
					<td width="18%" align="left">
						<input type="text" name="purNo" id="f_purNo" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="18%" align="left">
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
		
	   <div id="win" class="easyui-window" title="采购清单" style="width:90%;height:60%;" data-options="iconCls:'icon-save',modal:true" closed="true">
	      <div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',title:'产品信息',split:true" style="width:15%">
			<input id="searchText" class="easyui-searchbox" style="width:90%"
    				data-options="searcher:searchDept,prompt:'输入产品信息'"></input>
			<ul id="tree"></ul> 
			</div>
			
			<div data-options="region:'center',title:'清单信息'" style="padding:5px;width:85%">
			<div>
			项目编号：<input id="cc" class="easyui-combogrid" name="projNo" style="width:250px;" onkeydown="if(event.keyCode==13)query()"
					    data-options="
					    panelWidth:450,
					    idField:'projNo',
					    textField:'项目编号',
					    url:'${ctx}/schedule/queryScheduleList?projStatus=1',
					    type:'post',
					    pagination:true,
					    columns:[[
					    {field:'projNo',title:'项目编号',width:200},
					    {field:'projUnitName',title:'项目公司名称',width:200}
					    ]]
					    ">
			公司名称：<input class="easyui-textbox" width="200px" name="companyName" id="companyName"/>
			
			</div>
				<table id="dg" class="easyui-datagrid" title="采购清单" style="width:99%;height:99%;text-align：center;"
							data-options="rownumbers: true,singleSelect: true,nowrap: false,onClickRow: onClickRow,toolbar: '#tb'" > 
						<thead>
							<tr>
								<th field="productId" width="1%" align="center" hidden="true"></th>
								<th field="id" width="1%" align="center" hidden="true"></th>
								<th field="purId" width="1%" align="center" hidden="true"></th>
								<th field="type" width="6%" align="center" >类别</th>
								<th field="name" width="18%" align="center">名称</th>
								<th field="brand" width="9%" align="center">品牌</th>
								<th field="standard" width="9%" align="center">规格</th>
								<th field="model" width="10%" align="center">型号</th>
								<th field="supplier" width="10%" align="center">供应商</th>
								<th field="unit" width="6%" align="center">单位</th>
								<th field="amount" width="6%" align="center" editor="{type:'numberbox',options:{required:true}}" >数量</th>
								<th field="unitPrice"  width="7%" align="center" editor="{type:'numberbox',options:{required:true}}" >单价(元)</th>
								<th field="totalPrice"  width="7%" align="center">总价(元)</th>
								<th field="remark" width="12%" align="center" editor="{type:'textbox'}" >备注</th>
<!-- 								<th field="oper" width="9%" align="center">操作</th> -->
							</tr>
						</thead>
					</table>
					<div id="tb" style="height:auto">
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRow()">删除产品</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="save()">保存清单</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="submitBill()">保存并提交</a>
					</div>
		
			</div>

	    </div>
	</div>
	
	<%-- 产品清单审核 begin --%>
	<div id="winCheck" class="easyui-window" title="审核清单" style="width:80%;height:70%;" data-options="iconCls:'icon-save',modal:true,tools:'#tt'" closed="true">
		<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'north',title:'审核信息',split:true" style="width:100%">
			<form id="checkForm" method="post"> 
	          <table  width="100%" border="0" cellspacing="5" cellpadding="0" style="width:100%;height:28%;text-align：center;" >
	      		  <tr height="35px;">
					<td width="30%" height="35px;" align="right">审核状态：</td>
					<td width="70%" align="left">
						<input type="radio"  name="status" id="status"  value="3" checked="checked" />审核通过
						<input type="radio"  name="status" id="status"  value="4" />审核不通过						
					</td>
				</tr> 
				<tr height="50px;">
					<td width="30%" height="35px;" align="right">审核意见：</td>
					<td width="70%" align="left">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:50%;height:50px"  id="notPass" name="notPass"  required="true">
					</td>
				</tr>
			</table>
	       </form> 
		</div>
		
		<div data-options="region:'center',title:'清单信息'" style="padding:5px;width:100%">
			<table id="dgShow" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;" data-options="rownumbers: true,nowrap: false" > 
					<thead>
						<tr>
							<th field="type" width="8%" align="center" >类别</th>
							<th field="name" width="16%" align="center">名称</th>
							<th field="brand" width="9%" align="center">品牌</th>
							<th field="standard" width="9%" align="center">规格</th>
							<th field="model" width="10%" align="center">型号</th>
							<th field="supplier" width="10%" align="center">供应商</th>
							<th field="unit" width="6%" align="center">单位</th>
							<th field="amount" width="6%" align="center">数量</th>
							<shiro:hasPermission name="purchase:unitPrice">
						<th field="unitPrice" width="6%"   align="center">单价(元)</th>
						<th field="totalPrice" width="6%"   align="center">总价(元)</th>
						</shiro:hasPermission>
							<th field="remark"  width="12%" align="center">备注</th>
						</tr>
					</thead>
				</table>
		   </div>
	     </div>
	</div>
	
	<div id="tt">
		<a href="javascript:void(0)" title="保存" class="icon-save" onclick="saveCheck()"></a>
		<a href="javascript:void(0)" title="导出" class="icon-filter" onclick="ExporterExcel()"></a>
	</div>
	<%-- 产品清单审核 end --%>
	
	<%-- 查看清单信息 begin --%>
	<div id="winShow" class="easyui-window" title="采购清单" style="width:80%;height:80%;" data-options="iconCls:'icon-save',modal:true" closed="true">
		<table id="dgView" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;"data-options="rownumbers: true,toolbar: '#tbShow',nowrap: false" > 
				<thead>
					<tr>
						<th field="type" width="8%" align="center" >类别</th>
						<th field="name" width="16%" align="center">名称</th>
						<th field="brand" width="8%" align="center">品牌</th>
						<th field="standard" width="8%" align="center">规格</th>
						<th field="model" width="9%" align="center">型号</th>
						<th field="supplier" width="9%" align="center">供应商</th>
						<th field="unit" width="6%" align="center">单位</th>
						<th field="amount" width="6%" align="center">数量</th>
						<th field="entryAmount" width="6%" align="center">已入库数量</th>
						<shiro:hasPermission name="purchase:unitPrice">
						<th field="unitPrice" width="6%"   align="center">单价(元)</th>
						<th field="totalPrice" width="6%"   align="center">总价(元)</th>
						</shiro:hasPermission>
						<th field="remark" width="12%" align="center">备注</th>
						<th field="oper" width="6%" align="center">操作</th>
					</tr>
				</thead>
			</table>
			<div id="tbShow" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="print()">打印</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winShow').window('close')">关闭</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel2()">导出</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-entry',plain:true" onclick="entryAll()">全部入库</a>
		   </div>
	</div>
	<%-- 查看清单信息 end --%>
		<div id="PurGridToolbar" style="display: none;">	
			<shiro:hasPermission name="purchase:add">
		<a class="actions create easyui-linkbutton " iconCls="icon-add"
			plain="true">添加</a>
	</shiro:hasPermission>
	 	<shiro:hasPermission name="purchase:delete">
		<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
			plain="true">删除</a>
	</shiro:hasPermission>
	</div>
	
	
	<script type="text/javascript">
	$('#cc').combogrid({ 
		  keyHandler: {
          up: function() {},
          down: function() {},
          enter: function() {},
          query: function(q) {
              //动态搜索
              $('#cc').combogrid("grid").datagrid("reload", { 'projNo': q });
              $('#cc').combogrid("setValue", q);
          }
      }, onSelect: function (rowIndex, rowData) {
    	  var g = $('#cc').combogrid('grid');	// 获取表格控件对象
    	  var r = g.datagrid('getSelected');	//获取表格当前选中行
    	 $("#companyName").textbox('setValue',r.projUnitName);
    	
      }
  });
		var url;
		var editIndex = undefined;  
		var purId ="";
		var pId="";
		var pNo="";
		  
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "采购清单信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/purchase/queryPurchaseList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'purNo',
				title : '采购清单编号/项目编号',
				width : '16%',
				align : 'center'
			}, 
			{
				field : 'status',
				title : '清单状态',
				width : '9%',
				align : 'center',
				formatter: function(value,row,index){
					var str;
					if(row.status == "4"){
						str = "<span style='color:red'>审核不通过</span>"; 
					}else if(row.status == "3"){
						str = "<span style='color:red'>审核通过</span>"; 
					}else if(row.status == "2"){
						str = "<span style='color:red'>待审核</span>"; 
					}else if(row.status == "1"){
						str = "<span style='color:red'>未提交</span>"; 
					}
                    return str;
                }
			},
			{
				field : 'entryStatus',
				title : '入库状态',
				width : '9%',
				align : 'center',
				formatter: function(value,row,index){
					var str;
					if(row.entryStatus == "1"){
						str = "<span style='color:red'>已入库</span>"; 
					}else {
						str = "<span style='color:red'>待入库</span>"; 
					}
                    return str;
                }
			},
			{
				field : 'checkUser',
				title : '审核人',
				width : '8%',
				align : 'center'
			} ,
			{
				field : 'checkDateStr',
				title : '审核时间',
				width : '9%',
				align : 'center'
			} ,
			{
				field : 'notPass',
				title : '审核意见',
				width : '20%',
				align : 'center'
			} ,
			{
				field : 'createName',
				title : '创建人',
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
				width : '10%',
				align : 'center',
				formatter: function(value,row,index){
					var str;
					if(row.status == "1" || row.status == "4"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='editBill(&apos;" + row['id'] + "&apos;,&apos;" + row['purNo'] + "&apos;)'>修改</a>"; 
					}else if(row.status == "2"){
						str = "<shiro:hasPermission name='purchase:check'> <a style='color:blue' href='javascript:void(0)' onclick='checkBill(&apos;" + row['id'] + "&apos;,&apos;" + row['purNo'] + "&apos;)'>审核</a></shiro:hasPermission>"; 
					}else{
						str = "<a style='color:blue' href='javascript:void(0)' onclick='viewBill(&apos;" + row['id'] + "&apos;,&apos;" + row['purNo'] + "&apos;)'>查看清单</a>"; 
						
					}
                    return str;
                }
			} ] ],
			   toolbar:'#PurGridToolbar'/* [              //工具条  
                {text:"添加清单",iconCls:"icon-add",handler:function(){//回调函数  
                   //$("#dlg").dialog("open").dialog("setTitle", "采购清单信息新增");
                   //$("#checkForm").form("clear");
                   //url = "${ctx}/purchase/addPurchase";
                   addBill('');
                }},  
                {text:"删除",iconCls:"icon-remove",handler:function(){  
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
                                    url : "${ctx}/purchase/deletePurchase",  
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
                }}
                ] */, 
                onDblClickRow: function (index, rows) {
                	if(rows.status == "1" || rows.status == "4"){
						editBill(rows.id,rows.purNo);
					}else if(rows.status == "2"){
						   var permission="<%=session.getAttribute("biaoshi")%>";
			              for(var i=0;i<permission.length;i++){
			            	  if(permission[i]=="purchase:check"){
			            		  checkBill(rows.id,rows.purNo);
			            	  }
			              }
						   
						  
					}else{
						viewBill(rows.id,rows.purNo);
					}
         		}
                //height : $("#query-data").height() - 5,
		});
			  var purchasegridPanel = $("#dataGrid").datagrid("getPanel");
			  purchasegridPanel.on("click", "a.create", function() {
				  addBill('');
			  }).on("click","a.delete",function(){
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
                                  url : "${ctx}/purchase/deletePurchase",  
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
		
		//窗口关闭重新加载数据
		$("#win").window({
       		onClose:function(){ 
            $("#dataGrid").datagrid("reload");
       }
   	  });
   	  
   	  $("#winShow").window({
       		onClose:function(){ 
            $("#dataGrid").datagrid("reload");
       }
   	  });
	});
	
		//查询条件  
		function queryFun() {
			var queryParameter = $("#dataGrid").datagrid("options").queryParams;
			queryParameter.purNo = $("#f_purNo").val();
			$("#dataGrid").datagrid("reload");
		}
		
		//保存
		/*function savePur() {
             $("#checkForm").form("submit", {
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
        }*/
        
        //审核
        function saveCheck(){
           $.messager.confirm("提示","您确定要保存吗",function(t){  
	          if(t){  
		        	 $("#checkForm").form("submit", {
		                url: "${ctx}/purchase/checkPurchase?id="+pId+"&purNo="+pNo,
		                onsubmit: function () {
		                    return $(this).form("validate");
		                },
		                success: function (result) {
		                    if (result == "1") {
		                        $.messager.alert("提示信息", "操作成功");
		                        $("#winCheck").window("close")
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
        
       //添加清单
	   function addBill(id){
	    purId=id;
   		$("#win").window("open");//打开清单窗口
   		$("#dg").datagrid("loadData",{total:0,rows:[]}); //清空数据
        $("#tree").tree({  
             url:"${ctx}/purchase/getProductTree",//请求路径，id为根节点的id 
             checkbox: false, 
             cascadeCheck:false,
             onLoadSuccess:function(node,data){ 
                 var tree = $(this);  
                 tree.tree("collapseAll");  
             },
             onDblClick:function(node) {//双击新增产品信息数据
                    addRow(node);
             },
             onBeforeSelect: function (node) {//不让父节点选中
              	if(node.name){
         		 $("#tree").tree("unselect");
        		}
             }
         });  
	   }
	   
	   //插入产品信息列
	   function addRow(node){
	   	$("#dg").datagrid("insertRow",{
		    row: {
		        id:node.id,
		        name: node.call,
		        unit: node.unit,
		        supplier: node.supplier,
		        costPrice: node.costPrice,
		        brand: node.brand,
		        model: node.model,
		        type: node.type,
		        productId: node.id,
		        standard: node.standard,
		        purId: purId,
		        amount: 1
		    }
		});
	  }
	   
	  //删除表格中的行
	  function removeRow(){
	  	if (editIndex == undefined){return}
			$("#dg").datagrid("cancelEdit", editIndex)
					.datagrid("deleteRow", editIndex);
			editIndex = undefined;
       }
			  
	   //该方法用于关闭上一个焦点的editing状态  
 		function endEditing() {
		    if (editIndex == undefined) {  
		        return true  
		    }  
		    if ($("#dg").datagrid("validateRow", editIndex)) {  
		        var ed = $("#dg").datagrid("getEditor", {index:editIndex,field:"amount"});//获取修改后的数量
				var up = $("#dg").datagrid("getEditor", {index:editIndex,field:"unitPrice"});//获取修改后的单价
				var totalPrice = accMul($(ed.target).val(),$(up.target).val());
				//Math.round((($(ed.target).val())*(unitPrice))) ;//计算总价( 数量*单价)
				$("#dg").datagrid("getRows")[editIndex]["totalPrice"] = totalPrice;
				$("#dg").datagrid("endEdit", editIndex);  
		        editIndex = undefined;  
		        return true;  
		    } else {  
		        return false;  
		    }  
		}  
		
		//精确算法
		function accMul(arg1,arg2){
			var m=0,s1=arg1.toString(),s2=arg2.toString();
			try{m+=s1.split(".")[1].length}catch(e){}
			try{m+=s2.split(".")[1].length}catch(e){}
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
		}
		
		//点击单元格事件：  
		function onClickRow(index, rowData) {
			if (editIndex != index){
			    if (endEditing()) {  
			           $("#dg").datagrid("selectRow", index)
							.datagrid("beginEdit", index);
					editIndex = index;
			    }  
		    }else{
		   		$("#dg").datagrid("selectRow", editIndex);
		   }
		}  
		
		//确定修改
		function accept(){
			if (endEditing()){
				$("#dg").datagrid("acceptChanges");
			}
		}
		
		//修改清单信息
		function editBill(purId,purNo){
		    pNo = purNo;
			 $.ajax({
                 url: "${ctx}/purchase/queryPurBill?purId="+purId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     //var msg = $.parseJSON(data);
                     /* $("#dg").datagrid({
                         columns: [data.title]    //动态取标题
                     }); */
                     addBill(purId);
                     $("#dg").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		
		//审核清单
		function checkBill(purId,purNo){
			 pNo = purNo;
			 pId = purId;
			 $.ajax({
                 url: "${ctx}/purchase/queryPurBill?purId="+purId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     $("#winCheck").window("open");//打开清单窗口
                     $("#dgShow").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		//查看清单
		function viewBill(purId,purNo){
			 pNo = purNo;
			 pId = purId;
			 $.ajax({
                 url: "${ctx}/purchase/queryPurBill?purId="+purId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     $("#winShow").window("open");//打开清单窗口
                     $("#dgView").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		//保存
		function save(){
			var rows = $("#dg").datagrid("getRows");
			if(rows.length == 0){
				 $.messager.alert("提示","请选择要采购的产品","error");  
			}else{
				//id不为空则修改，为空则新增
				if(purId != ""){
					url="${ctx}/purchase/editPurBill";
				}else{
					url="${ctx}/purchase/addPurBill";
				}
				saveFun("1",rows);
			}
		}
		
		//提交清单
		function submitBill(){
			var rows = $("#dg").datagrid("getRows");
			if(rows.length == 0){
				 $.messager.alert("提示","请选择要采购的产品","error");  
			}else{
				//id不为空则修改，为空则新增
				if(purId != ""){
					url="${ctx}/purchase/editPurBill";
				}else{
					url="${ctx}/purchase/addPurBill";
				}
				saveFun("2",rows);
			}
		}
		
		//保存方法
		function saveFun(status,rows){
			accept();
           var projNo= $("#cc").combogrid("getValue");
           if(projNo!=""){
        	   pNo=projNo;
           }
			var rows = $("#dg").datagrid("getRows");
		    var entities ="[";
			// 循环 datagrid 中现有的数据，并且逐行复制给Entities ，并且转换成json格式
			// 在后台反序列话成对象的对象集合。
		    for(i = 0;i < rows.length;i++)
		    {
		       entities  = entities + JSON.stringify(rows[i])+",";  
		    }
		    entities = entities.substring(0, entities.length-1)+"]";  
		    $.ajax({
             url: url,
             type: "post",
             async: true,
             dataType: "json",
             data: {"entities": entities,"purId":purId,"purNo":pNo,"status":status},
             success: function (result) {
             	if (result == "1") {
                        $.messager.alert("提示信息", "操作成功");
                        $("#win").window("close");
                    }
                    else {
                        $.messager.alert("提示信息", "操作失败");
                    }
             }
           });
		
		}
		//下载清单
		function ExporterExcel() {
			var rows = $("#dgShow").datagrid("getRows");
		    var entities ="[";
			// 循环 datagrid 中现有的数据，并且逐行复制给Entities ，并且转换成json格式
			// 在后台反序列话成对象的对象集合。
		    for(i = 0;i < rows.length;i++)
		    {
		       entities  = entities + JSON.stringify(rows[i])+",";  
		    }
		    entities = entities.substring(0, entities.length-1)+"]";  
		      $.ajax({
	             url: "${ctx}/purchase/exportExcel",
	             type: "post",
	             dataType: "json",
	             data: {"entities": entities,"purNo":pNo},
	             success: function (result) {
	               if (result == "1") {
                        window.location="${ctx}/main/toExportExcel";
                    }else {
                        $.messager.alert("提示信息", "出现异常，下载失败！");
                    }
	             	
	             }
          	 });  
		}
		//下载清单2
		function ExporterExcel2() {
			var rows = $("#dgView").datagrid("getRows");
		    var entities ="[";
			// 循环 datagrid 中现有的数据，并且逐行复制给Entities ，并且转换成json格式
			// 在后台反序列话成对象的对象集合。
		    for(i = 0;i < rows.length;i++)
		    {
		       entities  = entities + JSON.stringify(rows[i])+",";  
		    }
		    entities = entities.substring(0, entities.length-1)+"]";  
		      $.ajax({
	             url: "${ctx}/purchase/exportExcel",
	             type: "post",
	             dataType: "json",
	             data: {"entities": entities,"purNo":pNo},
	             success: function (result) {
	               if (result == "1") {
                        window.location="${ctx}/main/toExportExcel";
                    }else {
                        $.messager.alert("提示信息", "出现异常，下载失败！");
                    }
	             	
	             }
          	 });  
		}
		//入库
		function entry(){
			var row = $('#dgView').datagrid('getSelected');
			console.log(row);
			$.messager.prompt('提示', '请输入入库数量：', function(r){
				if (r){
					
					 $.messager.confirm("提示","您确定要入库吗",function(t){  
				          if(t){ 
							$.ajax({
				             url: "${ctx}/entryStock/updatePurBill",
				             type: "post",
				             async: true,
				             dataType: "json",
				             data: {"id": row.id, "entrycount":r},
				             success: function (result) {
				             	if (result == "1") {
				             			 refDgView();
								        // $.messager.alert("提示信息", "操作成功");
				                    } else {
				                        $.messager.alert("提示信息", "操作失败");
				                    }
				             }
				           });
						}
					  }); 
				
				}
			});
			
		}
		//全部入库
		function entryAll(){
			$.messager.confirm("提示","您确定要全部入库吗",function(t){ 
				if(t){
					var rows = $("#dgView").datagrid("getRows"); 
					var pids = new Array()
					for(var i=0;i<rows.length;i++){
						pids.push(rows[i].id);
					}
					var ids= pids.join(",");//转成字符串
					$.ajax({
			             url: "${ctx}/entryStock/updatePurBillAll",
			             type: "post",
			             dataType: "json",
			             data: {"ids": ids, "status":"1"},
			             success: function (result) {
			             	if (result == "1") {
			             			 refDgView();
							        // $.messager.alert("提示信息", "操作成功");
			                    } else {
			                        $.messager.alert("提示信息", "操作失败");
			                    }
			             }
			           });
				}
			})
		}
		//入库后刷新列表状态
		function refDgView(){
			$.ajax({
                 url: "${ctx}/purchase/queryPurBill?purId="+pId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     $("#dgView").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		//树查询
			function searchDept(){
			 var parentNode=$("#tree").tree("getRoots"); //得到tree顶级node
			 var searchCon = $("#searchText").val();
			  //alert("parentNode="+parentNode);
			 //alert("searchCon="+searchCon); 
			 var children; 
		     for(var i=0;i<parentNode.length;i++){ //循环顶级 node 
		         children = $("#tree").tree("getChildren",parentNode[i].target);//获取顶级node下所有子节点 
		         if(children){ //如果有子节点 
		             for(var j=0;j<children.length;j++){ //循环所有子节点 
		                 //if($('#tree').tree('isLeaf',children[j].target)){ //判断子级是否为叶子节点,即不是父节点 
		                     if(children[j].text.indexOf(searchCon)>=0||children[j].id.indexOf(searchCon)>=0){ //判断节点text是否包含搜索文本                     
		                         selectNode(children[j]); //设置此节点为选择状态 
		                         expandParent(children[j]); //设置此节点所有父级展开 
		                         return;
		                     } 
		                 //} 
		             } 
		         }else{ 
		             if(parentNode[i].text.indexOf(searchCon)||children[j].id.indexOf(searchCon)>=0>=0){ 
		                 selectNode(parentNode[i]); 
		                 expandParent(parentNode[i]); 
		                 return; 
		             } 
		         } 
		     } 
			 
		}
 
		function selectNode(node){ 
		    $("#tree").tree("select",node.target);            
		}; 
		 
		function expandParent(node){ 
		    var parent = node; 
		    var t = true; 
		    do { 
		    parent = $("#tree").tree("getParent",parent.target); //获取此节点父节点 
		    if(parent){ //如果存在 
		        t=true; 
		        $("#tree").tree("expand",parent.target); 
		    }else{ 
		        t=false; 
		    } 
		    }while (t);            
		}

 		//打印
		function print(){
			CreateFormPage("入库清单", $('#dgView'),pNo)
		}
	</script>
</body>
</html>