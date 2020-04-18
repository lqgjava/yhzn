<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
	String customerId = request.getParameter("customerId");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>客户报价信息</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx }/resource/js/print.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="panel">
		<div class="panel-header" data-options="region:'north' " border="0" cellspacing="5" cellpadding="0"  style="height: 20px;padding: 10px;overflow: hidden;width:99%">
		<form action="" name="QueryForm">
			项目编号：<input type="text" name="projectNo" id="f_projectNo" class="easyui-textbox" style="width:172px;" />
			项目名称：<input type="text" name="projectName" id="f_projectName" class="easyui-textbox" style="width:172px;" />
			采购单位：<input type="text" name="purchaseUnit" id="f_purchaseUnit" class="easyui-textbox" style="width:172px;" />
			
<!-- 				<tr> -->
<!-- 					<div class="easyui-panel" data-options="border:false,fit:true" style="margin: 6px;"> -->
<!-- 						<input class="easyui-searchbox" data-options="searcher:searchDept,prompt:'搜索部门'" id="searchText" style="width: 186px; height: 32px;"></input> -->
<!-- 						<ul id="ul_tree_dept" class="easyui-tree" style="width: 186px;"></ul> -->
<!-- 					</div> -->
<!-- 				</tr> -->
						<input type="button" value="查询" class="button_blue" onclick="queryFun()" />
						<input type="button" value="重置" class="button_green" onclick="resetQuery()"/>
						<input type="button" value="返回" class="button_blue" onclick="backFun()"/>
		</form>
		</div>
		<hr>
	
			<table id="dataGrid" ></table>			
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width: 420px; height: 310px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="custProjForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
	      		 <!-- <tr height="35px;">
					<td width="25%" height="35px;" align="right">项目编号：</td>
					<td width="75%" align="left">
						<input  name="projectNo" id="projectNo" class="easyui-textbox" style="width:180px;"  required="true"/>						
					</td>
				</tr> -->
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">项目名称：</td>
					<td width="75%" align="left">
						<input  name="projectName" id="projectName" class="easyui-textbox" style="width:90%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">采购单位：</td>
					<td width="75%" align="left">
						<input  name="purchaseUnit" id="purchaseUnit" class="easyui-textbox" style="width:90%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">单位地址：</td>
					<td width="75%" align="left">
						<input  name="purchaseAdd" id="purchaseAdd" class="easyui-textbox" style="width:90%;" />
					</td>
				</tr>
				</tr>
				<tr height="80px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="75%" align="left">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:80px"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" />
	       <input type="hidden" name="customerId" id="customerId" />  
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveCustomer()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   
	   <div id="win" class="easyui-window" title="报价清单" style="align:center;width:70%;height:70%;" data-options="iconCls:'icon-save',modal:true" closed="true">
	    <div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',title:'产品信息',split:true" style="width:15%">
			<input id="searchText" class="easyui-searchbox" style="width:90%"
    				data-options="searcher:searchDept,prompt:'输入产品信息'"></input>
			<ul id="tree"></ul>  
			</div>
			<div data-options="region:'center'" style="padding:5px;width:85%">
				<table id="dg" class="easyui-datagrid" title="报价清单" style="width:99%;height:99%;text-align：center;"
							data-options="rownumbers: true,singleSelect: true,onClickRow: onClickRow,toolbar: '#tb'," > 
						<thead>
							<tr>
								<th field="id" width="1%" align="center" hidden="true"></th>
								<th field="projId" width="1%" align="center" hidden="true"></th>
								<th field="name" width="20%" align="center">名称</th>
								<th field="brand" width="10%" align="center">品牌</th>
								<th field="model" width="14%" align="center">型号</th>
								<th field="unit" width="7%" align="center">单位</th>
								<th field="amount" width="7%" align="center" editor="{type:'numberbox',options:{required:true}}" >数量</th>
								<th field="unitPrice" width="9%" align="center" editor="{type:'numberbox',options:{required:true}}" >单价(元)</th>
<!-- 								<th field="discount" width="7%" align="center" editor="{type:'numberbox',options:{precision:1}}">折扣</th> -->
								<th field="totalPrice" width="9%" align="center">总价(元)</th>
								<th field="costPrice" width="10%" align="center">成本价(元)</th>
<!-- 								<th field="oper" width="9%" align="center">操作</th> -->
							</tr>
						</thead>
					</table>
					<div id="tb" style="height:auto">
<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="append()">Append</a> -->
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRow()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="save()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="submitBill()">提交</a>
<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a> -->
<!-- 			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a> -->
		</div>
			</div>

	    </div>
	</div>
	
	<%-- 产品清单信息显示 begin --%>
	<div id="winShow" class="easyui-window" title="报价清单" style="width:60%;height:70%;" data-options="iconCls:'icon-save',modal:true" closed="true">
		<table id="dgShow" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;"data-options="rownumbers: true,toolbar: '#tbShow'," > 
				<thead>
					<tr>
						<th field="name" width="20%" align="center">名称</th>
						<th field="brand" width="10%" align="center">品牌</th>
						<th field="model" width="14%" align="center">型号</th>
						<th field="unit" width="7%" align="center">单位</th>
						<th field="amount" width="7%" align="center">数量</th>
						<th field="unitPrice" width="9%" align="center" >单价(元)</th>
<!-- 						<th field="discount" width="7%" align="center">折扣</th> -->
						<th field="totalPrice" width="9%" align="center">总价(元)</th>
						<th field="costPrice" width="10%" align="center">成本价(元)</th>
					</tr>
				</thead>
			</table>
			<div id="tbShow" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-print',plain:true" onclick="print()">打印</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winShow').window('close')">关闭</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel()">导出</a>
				
		   </div>
	</div>
	<%-- 产品清单信息显示 end --%>   
	
	<script type="text/javascript">
		var url;
		var customerId =  "<%=customerId %>";
		var editIndex = undefined;  
		var projId ="";
		var pNo="";
		
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "客户项目信息列表",
			nowrap : false,
			border : true,
			locale : "zh_CN",
			height : $(window).height()-100,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/customerProj/queryCustomerProjList?customerId="+customerId,
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'projectNo',
				title : '项目编号',
				width : '10%',
				align : 'center'
			}, {
				field : 'projectName',
				title : '项目名称',
				width : '18%',
				align : 'center'
			}, {
				field : 'purchaseUnit',
				title : '采购单位',
				width : '15%',
				align : 'center'
			}, 
			{
				field : 'purchaseAdd',
				title : '采购单位地址',
				width : '20%',
				align : 'center'
			} ,
			{
				field : 'remark',
				title : '备注',
				width : '19%',
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
					if(row.status == "2"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='viewBill(&apos;" + row['id'] + "&apos;,&apos;" + row['projectNo'] + "&apos;)'>查看清单</a>"; 
					}else if(row.status == "1"){
						str = "<a style='color:blue' href='javascript:void(0)' onclick='editBill(&apos;" + row['id'] + "&apos;)'>修改清单</a>"; 
					}else{
						str = "<a style='color:blue' href='javascript:void(0)' onclick='addBill(&apos;" + row['id'] + "&apos;)'>添加清单</a>"; 
					}
                    return str;
                }
			} ] ],
			   toolbar:[              //工具条  
                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                   $("#dlg").dialog("open").dialog("setTitle", "客户项目信息新增");
                   $("#custProjForm").form("clear");
                    url = "/yhzn/customerProj/addCustomerProj";
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
                                    url : "${ctx}/customerProj/deleteCustomerProj",  
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
                }},  
                {text:"修改",iconCls:"icon-edit",handler:function(){  
                    var rows=datagrid.datagrid("getSelections");
                    if(rows.length<=0){  
                        $.messager.alert("提示","请选择要修改的行","error");  
                    }else if(rows.length>1){  
                        $.messager.alert("提示","只能选择一条数据进行修改","error");  
                    }else if(rows.length==1){  
		                $("#dlg").dialog("open").dialog("setTitle", "客户项目信息修改");
		                $("#custProjForm").form("load", rows[0]);
		                url = "${ctx}/customerProj/editCustomerProj";
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
		
		//窗口关闭重新加载数据
		$("#win").window({
       		onClose:function(){ 
            $("#dataGrid").datagrid("reload");
       }
   	  });
	});

		//查询条件  
		function queryFun() {
			var queryParameter = $("#dataGrid").datagrid("options").queryParams;
			queryParameter.projectName = $("#f_projectName").val();
			queryParameter.projectNo = $("#f_projectNo").val();
			queryParameter.purchaseUnit = $("#f_purchaseUnit").val();
			queryParameter.customerId = customerId;
			$("#dataGrid").datagrid("reload");
		}
		
		//返回客户信息页面
		function backFun(){
			window.location.href ="${ctx}/main/toCustomer";
		}
		
		//保存
		function saveCustomer() {
			 $("#customerId").val(customerId);
             $("#custProjForm").form("submit", {
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
        
       //添加清单
	   function addBill(id){
	    projId = id;
   		$("#win").window("open");//打开清单窗口
   		$("#dg").datagrid("loadData",{total:0,rows:[]}); //清空数据
        $("#tree").tree({  
             url:"/yhzn/customerProj/getProductTree",//请求路径，id为根节点的id 
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
		        model: node.model,
		        unit: node.unit,
		        //unitPrice: node.unitPrice,
		        //totalPrice: node.unitPrice,
		        costPrice: node.costPrice,
		        brand: node.brand,
		        model: node.model,
		        projId: projId,
		        amount: 1
		        //oper: "<a style='color:blue' href='javascript:removeRow()'>删除<a>",
		    }
		});
	  }
	   
	  //删除表格中的行
	  function removeRow(){
	  	if (editIndex == undefined){return}
			$("#dg").datagrid("cancelEdit", editIndex)
					.datagrid("deleteRow", editIndex);
			editIndex = undefined;
	 	/*var row = $("#dg").datagrid("getSelected");
			if (row) {
		         var rowIndex = $("#dg").datagrid("getRowIndex", row);
		         $("#dg").datagrid("deleteRow", rowIndex);  
		         $("#dg").datagrid("reload");//删除后重新加载下
			 }*/
       }
			  
	   //该方法用于关闭上一个焦点的editing状态  
 		function endEditing() {
		    if (editIndex == undefined) {  
		        return true  
		    }  
		    if ($("#dg").datagrid("validateRow", editIndex)) {  
		        var at = $("#dg").datagrid("getEditor", {index:editIndex,field:"amount"});//获取修改后的数量
		        var up = $("#dg").datagrid("getEditor", {index:editIndex,field:"unitPrice"});//获取修改后的单价
		        var ds = $("#dg").datagrid("getEditor", {index:editIndex,field:"discount"});//获取修改后的折扣
				//var unitPrice = $("#dg").datagrid("getRows")[editIndex]["unitPrice"];//获取单价
				//var discount = $("#dg").datagrid("getRows")[editIndex]["discount"];//获取折扣
				var totalPrice ;
				//判断是否有折扣
				//if(discount !=="" && $(ds.target).val() != ""){
				//	 totalPrice = (($(at.target).val())*($(ds.target).val())*($(up.target).val()))/10 ;//计算总价( 数量*折扣*单价)/10
				//}else{
					totalPrice = (($(at.target).val())*($(up.target).val())) ;//计算总价( 数量*单价)
				//}
				$("#dg").datagrid("getRows")[editIndex]["totalPrice"] = totalPrice;
				$("#dg").datagrid("endEdit", editIndex);  
		        editIndex = undefined;  
		        return true;  
		    } else {  
		        return false;  
		    }  
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
		function editBill(projId){
			 $.ajax({
                 url: "${ctx}/customerProj/queryPriceBill?projId="+projId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     //var msg = $.parseJSON(data);
                     /* $("#dg").datagrid({
                         columns: [data.title]    //动态取标题
                     }); */
                     addBill(projId);
                     $("#dg").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		//查看清单
		function viewBill(projId,projectNo){
			 pNo = projectNo;
			 $.ajax({
                 url: "${ctx}/customerProj/queryPriceBill?projId="+projId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     //var msg = $.parseJSON(data);
                     /* $("#dg").datagrid({
                         columns: [data.title]    //动态取标题
                     }); */
                     $("#winShow").window("open");//打开清单窗口
                     $("#dgShow").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		//保存
		function save(){
			saveFun("1")
		}
		
		//提交清单
		function submitBill(){
			saveFun("2");
		}
		
		//提交清单
		function saveFun(status){
			accept();
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
             url: "/yhzn/customerProj/addPriceBill",
             type: "post",
             async: true,
             dataType: "json",
             data: {"entities": entities,"projId":projId, "status":status},
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
		
		function printBill(){
		
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
	             url: "/yhzn/customerProj/exportBillExcel",
	             type: "post",
	             dataType: "json",
	             data: {"entities": entities,"projectNo":pNo},
	             success: function (result) {
	               if (result == "1") {
                        window.location="${ctx}/main/toExportExcel";
                    }else {
                        $.messager.alert("提示信息", "出现异常，下载失败！");
                    }
	             	
	             }
          	 });  
		
		}
		
					//树查询
		function searchDept(){
			 var parentNode=$("#tree").tree("getRoots"); //得到tree顶级node
			 var searchCon = $("#searchText").val();
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
			CreateFormPage("报价清单", $('#dgShow'),pNo)
		}
	</script>
</body>
</html>