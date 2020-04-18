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
<title>入库清单信息</title>
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
		<div class="panel-header">
			<form action="" name="QueryForm">
			<table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
				<tr height="35px;">
					
					<td width="10%" height="35px;" align="right">采购清单编号：</td>
					<td width="18%" align="left">
						<input type="text" name="purNo" id="f_purNo" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="18%" align="left"></td>
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
		
	   <%-- 入库清单新增 begin --%>
	   <div id="win" class="easyui-window" title="入库清单" style="width:70%;height:70%;" data-options="iconCls:'icon-save',modal:true" closed="true">
	    <div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'west',title:'产品信息',split:true" style="width:15%">
			<ul id="tree"></ul>  
			</div>
			<div data-options="region:'north',split:true,title:'入库信息',split:true" style="width:100%;height:100px">
				<form id="entryForm" method="post"> 
		       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
		      	   <tr height="35px;">
						<td width="20%" height="35px;" align="right">入库清单编号：</td>
						<td width="30%" align="left">
							<input  name="entryNo" id="entryNo" class="easyui-textbox" style="width:180px;"  required="true"/>						
						</td>
						<td width="20%" height="35px;" align="right">仓库管理员：</td>
						<td width="30%" align="left">
							<input  name="checkId" id="checkId" class="easyui-combotree" style="width:180px;"  required="true"/>
						</td>
					</tr>
				</table>
		       <input type="hidden" name="id" id="id" />
		       </form> 
			</div>
			<div data-options="region:'center'" style="padding:5px;width:85%">
			  
				<table id="dg" class="easyui-datagrid" title="入库清单" style="width:99%;height:99%;text-align：center;"
							data-options="rownumbers: true,singleSelect: true,nowrap: false,onClickRow: onClickRow,toolbar: '#tb'," > 
						<thead>
							<tr>
								<th field="id" width="1%" align="center" hidden="true"></th>
								<th field="purId" width="1%" align="center" hidden="true"></th>
								<th field="type" width="1%" align="center" hidden="true"></th>
								<th field="name" width="20%" align="center">名称</th>
								<th field="brand" width="10%" align="center">品牌</th>
								<th field="model" width="10%" align="center">型号</th>
								<th field="standard" width="10%" align="center">规格</th>
								<th field="supplier" width="14%" align="center">供应商</th>
								<th field="unit" width="7%" align="center">单位</th>
								<th field="amount" width="7%" align="center" editor="{type:'numberbox',options:{required:true}}" >数量</th>
								<!-- <th field="unitPrice" width="7%" align="center"editor="{type:'numberbox',options:{required:true}}" >单价</th>
								<th field="totalPrice" width="7%" align="center">总价</th>
								<th field="costPrice" width="8%" align="center">成本价</th> -->
							</tr>
						</thead>
					</table>
					<div id="tb" style="height:auto">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeRow()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="save()">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="submitBill()">提交</a>
		</div>
			</div>
	    </div>
	</div>
	<%-- 入库清单新增 end --%>
	
	<%-- 产品清单信息显示 begin --%>
	<div id="winShow" class="easyui-window" title="入库清单" style="width:60%;height:70%;" data-options="iconCls:'icon-save',modal:true" closed="true">
		<table id="dgShow" class="easyui-datagrid"  style="width:100%;height:100%;text-align：center;"data-options="rownumbers: true,toolbar: '#tbShow'," > 
				<thead>
					<tr>
						<th field="name" width="20%" align="center">名称</th>
						<th field="brand" width="10%" align="center">品牌</th>
						<th field="model" width="10%" align="center">型号</th>
						<th field="standard" width="10%" align="center">规格</th>
						<th field="supplier" width="14%" align="center">供应商</th>
						<th field="unit" width="7%" align="center">单位</th>
						<th field="amount" width="7%" align="center">数量</th>
						<!--  <th field="unitPrice" width="7%" align="center" >单价</th>
						<th field="totalPrice" width="7%" align="center">总价</th>
						<th field="costPrice" width="8%" align="center">成本价</th> -->
					</tr>
				</thead>
			</table>
			<div id="tbShow" style="height:auto">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="javascript:$('#winShow').window('close')">关闭</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-filter',plain:true" onclick="ExporterExcel()">导出</a>
				
		   </div>
	</div>
	<%-- 产品清单信息显示 end --%>   
	<div id="entry" style="display: none;">	
	 	<shiro:hasPermission name="entry:delete">
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
			title : "入库清单信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/entryStock/queryEntryStockList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'purNo',
				title : '采购清单编号',
				width : '15%',
				align : 'center'
			},
			{
				field : 'type',
				title : '类别',
				width : '7%',
				align : 'center'
			},
			{
				field : 'name',
				title : '名称',
				width : '16%',
				align : 'center'
			},
			{
				field : 'brand',
				title : '品牌',
				width : '8%',
				align : 'center'
			},
			{
				field : 'model',
				title : '型号',
				width : '9%',
				align : 'center'
			},
			{
				field : 'supplier',
				title : '供应商',
				width : '12%',
				align : 'center'
			},
			{
				field : 'unit',
				title : '单位',
				width : '7%',
				align : 'center'
			},
			{
				field : 'amount',
				title : '数量',
				width : '7%',
				align : 'center'
			},
			{
				field : 'modifyDateStr',
				title : '入库时间',
				width : '10%',
				align : 'center'
			} ,
			{
				field : 'oper',
				title : '操作',
				width : '8%',
				align : 'center',
				formatter: function(value,row,index){
					var str = "<a style='color:blue' href='javascript:void(0)' onclick='openLoc(&apos;" + row['id'] + "&apos;)'>打开所在位置</a>"; 
                    return str;
                }
			} ] ],
			   toolbar:'#entry',              //工具条  
                /*{text:"添加清单",iconCls:"icon-add",handler:function(){//回调函数  
                   //$("#dlg").dialog("open").dialog("setTitle", "入库清单信息新增");
                  $("#entryForm").form("clear");
                  $("#checkId").combotree({
		          	url : "${ctx }/sysUser/getPersonList",  
               		data : {"personId":""},  
		            method: 'post',
		            ines: true,
		            multiple: false,
		            onBeforeSelect: function (node) {
		                if(node.name){
				          $("#checkId").tree("unselect");
				        }
		            }
			        })
                    addBill('');
                }}, */
               /*  {text:"删除",iconCls:"icon-remove",handler:function(){  
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
                                    url : "${ctx}/entryStock/deleteEntryStock",  
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
                }} */
                /*,  
                {text:"修改",iconCls:"icon-edit",handler:function(){  
                    var rows=datagrid.datagrid("getSelections");
                    if(rows.length<=0){  
                        $.messager.alert("提示","请选择要修改的行","error");  
                    }else if(rows.length>1){  
                        $.messager.alert("提示","只能选择一条数据进行修改","error");  
                    }else if(rows.length==1){  
		                $("#dlg").dialog("open").dialog("setTitle", "入库清单信息修改");
		                $("#entryForm").form("load", rows[0]);
		                $("#personId").combotree("setValue",rows[0].trueName);
		                getRoleList(rows[0].roleId);
		                url = "${ctx}/entryStock/editEntryStock";
                    }  
                }}*/
              
                //height : $("#query-data").height() - 5,
		});
			  var EntrygridPanel = $('#dataGrid').datagrid("getPanel");
			  EntrygridPanel.on("click", "a.delete", function() {
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
                                  url : "${ctx}/entryStock/deleteEntryStock",  
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
	});
	
		//查询条件  
		function queryFun() {
			var queryParameter = $("#dataGrid").datagrid("options").queryParams;
			queryParameter.purNo = $("#f_purNo").val();
			//queryParameter.entryName = $("#f_entryName").val();
			$("#dataGrid").datagrid("reload");
		}
		
		//保存
		function saveEntry() {
             $("#entryForm").form("submit", {
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
	    purId=id;
   		$("#win").window("open");//打开清单窗口
   		$("#dg").datagrid("loadData",{total:0,rows:[]}); //清空数据
        $("#tree").tree({  
             url:"${ctx}/entryStock/getProductTree",//请求路径，id为根节点的id 
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
		        name: node.text,
		        model: node.model,
		        unit: node.unit,
		        //unitPrice: node.unitPrice,
		        //totalPrice: node.unitPrice,
		        standard: node.standard,
		        supplier: node.supplier,
		        costPrice: node.costPrice,
		        brand: node.brand,
		        model: node.model,
		        type: node.type,
		        purId: purId,
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
       }
			  
	   //该方法用于关闭上一个焦点的editing状态  
 		function endEditing() {
		    if (editIndex == undefined) {  
		        return true  
		    }  
		    if ($("#dg").datagrid("validateRow", editIndex)) {  
		        /* var ed = $("#dg").datagrid("getEditor", {index:editIndex,field:"amount"});//获取修改后的数量
				var up = $("#dg").datagrid("getEditor", {index:editIndex,field:"unitPrice"});//获取修改后的单价
				var totalPrice = accMul($(ed.target).val(),$(up.target).val());
				//Math.round((($(ed.target).val())*(unitPrice))) ;//计算总价( 数量*单价)
				$("#dg").datagrid("getRows")[editIndex]["totalPrice"] = totalPrice; */
				$("#dg").datagrid("endEdit", editIndex);  
		        editIndex = undefined;  
		        return true;  
		    } else {  
		        return false;  
		    }  
		}  
		
		//精确算法
		/* function accMul(arg1,arg2){
			var m=0,s1=arg1.toString(),s2=arg2.toString();
			try{m+=s1.split(".")[1].length}catch(e){}
			try{m+=s2.split(".")[1].length}catch(e){}
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
		} */
		
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
		function editBill(purId){
			 $.ajax({
                 url: "${ctx}/purchase/queryPurBill?purId="+purId,
                 type: "post",
                 dataType: "json",
                 success: function (data) {
                     addBill(purId);
                     $("#dg").datagrid("loadData", data.rows);  //动态取数据
                 }
             });
		}
		
		//查看清单
		function viewBill(purId,purNo){
			 pNo = purNo;
			 $.ajax({
                 url: "${ctx}/entryStock/queryPurBill?purId="+purId,
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
			//var rows = $("#dg").datagrid("getRows");
		    var entities ="[";
			// 循环 datagrid 中现有的数据，并且逐行复制给Entities ，并且转换成json格式
			// 在后台反序列话成对象的对象集合。
		    for(i = 0;i < rows.length;i++)
		    {
		       entities  = entities + JSON.stringify(rows[i])+",";  
		    }
		    entities = entities.substring(0, entities.length-1)+"]";  
		    $.ajax({
             url: "${ctx}/entryStock/addPurBill",
             type: "post",
             async: true,
             dataType: "json",
             data: {"entities": entities,"purId":purId, "status":status},
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
	             url: "${ctx}/entryStock/exportExcel",
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