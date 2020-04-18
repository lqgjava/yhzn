<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>产品基本信息</title>

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
		
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width: 800px; height: 520px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="productForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
	       		<tr height="40px;">
					<td width="15%" height="35px;" align="right">产品类别：</td>
					<td width="25%" align="left">
						<input class="easyui-combobox" id="type" name="type" style="width:172px" data-options="valueField:'id', textField:'text',required:true" >
					</td>
					<td width="20%" height="35px;" align="right" style="display: none;" class="cpzl">成品种类</td>
					<td width="30%" align="left" style="display: none;" class="cpzl">
							<input class="easyui-combobox" id="category" name="category" style="width:172px" data-options="valueField:'id', textField:'text'" >
					</td>
				</tr>
	      		<tr height="40px;">
					<td width="15%" height="35px;" align="right">名称：</td>
					<td width="25%" align="left">
						<input type="text"  name="name" id="name" class="easyui-textbox" style="width:172px;" required="true"/>
					</td>
					<td width="20%" height="35px;" align="right">序号：</td>
					<td width="30%" align="left">
						<input type="text"  name="serialNumber" id="serialNumber" class="easyui-textbox" style="width:172px;" />
					</td>
				</tr>
				<tr height="40px;">
					<td width="15%" height="35px;" align="right">规格：</td>
					<td width="25%" align="left">
						<input type="text"  name="standard" id="standard" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="20%" height="35px;" align="right">型号：</td>
					<td width="30%" align="left">
						<input type="text"  name="model" id="model" class="easyui-textbox" style="width:172px;" />
					</td>
				</tr>
				<tr height="40px;">
					<td width="15%" height="35px;" align="right">单位：</td>
					<td width="25%" align="left">
						<input type="text"  name="unit" id="unit" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="20%" height="35px;" align="right">品牌：</td>
					<td width="30%" align="left">
						<input type="text"  name="brand" id="brand" class="easyui-textbox" style="width:172px;"  />
					</td>
				</tr>
				
				<tr height="40px;">
					<td width="15%" height="35px;" align="right">供应商：</td>
					<td width="25%" align="left">
						<input class="easyui-combobox" id="supplier" name="supplier" style="width:172px" data-options="valueField:'id', textField:'text',required:true" >
					</td>
					<td width="20%" height="35px;" align="right">成本价(元)：</td>
					<td width="30%" align="left">
						<input type="text"  name="costPrice" id="costPrice" class="easyui-textbox" style="width:172px;" required="true"/>
					</td>
				</tr>
				<tr height="40px;">
					<td width="15%" height="35px;" align="right">一维码：</td>
					<td width="25%" align="left">
						<input type="text"  name="barCode" id="barCode" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="20%" height="35px;" align="right"></td>
					<td width="30%" align="left"></td>
				</tr>
				<!-- <tr height="40px;">
					<td width="15%" height="35px;" align="right">库存数量：</td>
					<td width="25%" align="left">
						<input type="text"  name="amount" id="amount" class="easyui-textbox" style="width:172px;" required="true"/>
					</td>
					<td width="20%" height="35px;" align="right">库存报警数量：</td>
					<td width="30%" align="left">
						<input type="text"  name="stockAmount" id="stockAmount" class="easyui-textbox" style="width:172px;" required="true"/>
					</td>
				</tr> -->
				<!-- <tr height="40px;">
					<td width="15%" height="35px;" align="right">供应商：</td>
					<td width="25%" align="left">
						<input type="text"  name="supplier" id="supplier" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="20%" height="35px;" align="right"></td>
					<td width="30%" align="left"></td>
				</tr> -->
				<tr height="80px;">
					<td width="15%" height="35px;" align="right">备注：</td>
					<td width="75%" align="left"  colspan="3">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:80%;height:80px"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" /> 
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveProduct()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   
	   <div id="productGridToolbar" style="display: none;">	
			<shiro:hasPermission name="product:add">
		<a class="actions create easyui-linkbutton " iconCls="icon-add"
			plain="true">添加</a>
	</shiro:hasPermission>
	 	<shiro:hasPermission name="product:delete">
		<a class="actions delete easyui-linkbutton " iconCls="icon-remove"
			plain="true">删除</a>
	</shiro:hasPermission>
	</div>
	
	<script type="text/javascript">
		var url;
		var tf;
		 var permission="<%=session.getAttribute("biaoshi")%>";
		  for(var i=0;i<permission.length;i++){
       	  if(permission[i]=="product:unitPrice"){	
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
			title : "产品基本信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/product/queryProductList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'type',
				title : '类别',
				width : '10%',
				align : 'center' 
			}, {
				field : 'name',
				title : '名称',
				width : '18%',
				align : 'center'
			}, {
				field : 'standard',
				title : '规格',
				width : '15%',
				align : 'center'
			}, 
			{
				field : 'model',
				title : '型号',
				width : '12%',
				align : 'center'
			} ,
			{
				field : 'brand',
				title : '品牌',
				width : '11%',
				align : 'center'
			},
			 {
				field : 'unit',
				title : '单位',
				width : '7%',
				align : 'center'
			}, 
			{
				field : 'costPrice',
				title : '成本价(元)',
				width : '8%',
				align : 'center',
				hidden:tf,
			},
			{
				field : 'supplier',
				title : '供应商',
				width : '18%',
				align : 'center'
			}] ],
			   toolbar:'#productGridToolbar',/* [              //工具条  
                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                   $("#dlg").dialog("open").dialog("setTitle", "产品基本信息新增");
                   $("#productForm").form("clear");
                   getCustomerList("","");
                    url = "/yhzn/product/addProduct";
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
                                    url : "${ctx}/product/deleteProduct",  
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
                }}  
                 /*,{text:"修改",iconCls:"icon-edit",handler:function(){  
                    var rows=datagrid.datagrid('getSelections');
                    if(rows.length<=0){  
                        $.messager.alert('提示','请选择要修改的行','error');  
                    }else if(rows.length>1){  
                        $.messager.alert('提示','只能选择一条数据进行修改','error');  
                    }else if(rows.length==1){  
		                $("#dlg").dialog("open").dialog('setTitle', '产品基本信息修改');
		                $("#productForm").form("clear");
		                $("#productForm").form("load", rows[0]);
		                url = "${ctx}/product/editProduct";
		                getCustomerList(rows[0].type,rows[0].category);
		                chooseGysDict(rows[0].supplier);
                    }  
                }}
                ] */ 
                onDblClickRow: function (index, rows) {
                	 $("#dlg").dialog("open").dialog('setTitle', '产品基本信息修改');
	                 $("#productForm").form("clear");
		             $("#productForm").form("load", rows);
		             url = "${ctx}/product/editProduct";
		             getCustomerList(rows.type,rows.category);
		             chooseGysDict(rows.supplier);
	               // getCustomerList(rows.projType);
         		}
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
		

		
		  var productgridPanel = $("#dataGrid").datagrid("getPanel");
		   productgridPanel.on("click", "a.create", function() {
			  $("#dlg").dialog("open").dialog("setTitle", "产品基本信息新增");
             $("#productForm").form("clear");
              getCustomerList("","");
               url = "/yhzn/product/addProduct";
		  }).on("click","a.delete",function(){
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
                              url : "${ctx}/product/deleteProduct",  
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
		  }) 
		});

		//查询条件  
		function queryFun() {
			var queryParameter = $('#dataGrid').datagrid("options").queryParams;
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
        
        
        //获取客户行业列表
         function getCustomerList(type,category){
         	  var str;
	          if(type==""){
	         	str='[{"id":"材料","text":"材料"},{"id":"配件","text":"配件"},{"id":"成品","text":"成品"}]';
	         	chooseType(category);
	         	$("#type").combobox({disabled:false});
	          }else{
	          	if(type=="材料"){
	          		str='[{"id":"材料","text":"材料","selected":true},{"id":"配件","text":"配件"},{"id":"成品","text":"成品"}]';
	          	}else if(type=="配件"){
	          		str='[{"id":"材料","text":"材料"},{"id":"配件","text":"配件","selected":true},{"id":"成品","text":"成品"}]';
	          	}else if(type=="成品"){
	          		str='[{"id":"材料","text":"材料"},{"id":"配件","text":"配件"},{"id":"成品","text":"成品","selected":true}]';
	          		$(".cpzl").show();
	          		chooseType(category);
	          	}
	          //	$("#type").combobox({disabled:true});
	          }
		      var jsonData = JSON.parse(str);
                 $("#type").combobox({
                      data : jsonData,//获取要显示的json数据
                     valueField: 'id',
                     textField: 'text',
					  onSelect: function(rec) {
						  if(rec.text == "成品"){
							 $(".cpzl").show();
						   }else{
							 $(".cpzl").hide();
						  }
					 }
               });
               
               chooseGysDict("");
        }
        
        //获取客户行业类别
        function chooseType(category){
        	$.ajax({  
             	 type : "GET", 
                 url : "${ctx}/customer/getCustomerTypeList",  
                 data : {"type":category},  
                 dataType : "json",  
                 success : function(data) {
                 	$("#category").combobox("loadData",data);
                 }  
             });  
        }
        
        //获取供应商字典代码
        function chooseGysDict(key){
        	$.ajax({  
             	 type : "GET", 
                 url : "${ctx}/supplier/combobox",  
                 dataType : "json",  
                 success : function(data) {
                 	$("#supplier").combobox("loadData",data);
                 }  
             });  
        }
		
	</script>
</body>
</html>
