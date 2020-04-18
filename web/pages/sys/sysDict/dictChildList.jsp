<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
	String rootKey = request.getParameter("rootKey");
	String parentKey = request.getParameter("parentKey");
	String dictLevel = request.getParameter("dictLevel");
	String dictValue = request.getParameter("dictValue");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>字典管理</title>
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
					<td width="10%" height="35px;" align="right">字典名称：</td>
					<td width="20%" align="left">
						<input type="text" name="dictValue" id="f_dictValue" class="easyui-textbox" style="width:172px;" />
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="20%" align="left">
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="30%" align="left"></td>
				</tr>
				<tr>
					<td colspan="6" align="right">
						<input type="button" value="查询" class="button_blue" onclick="queryFun()" />
						<input type="button" value="重置" class="button_green" onclick="resetQuery()"/>
						<input type="button" value="返回" class="button_blue" onclick="backFun()" />
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
		<div id="dlg" class="easyui-dialog" style="width: 400px; height: 480px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="dictForm" method="post"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center">
	       		<tr height="35px;">
					<td width="25%" height="35px;" align="right">根节点值：</td>
					<td width="75%" align="left">
						<input type="text" name="rootKey" id="rootKey" class="easyui-textbox" style="width:172px;"   disabled="disabled"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">父节点值：</td>
					<td width="75%" align="left">
						<input type="text" name="parentKey" id="parentKey" class="easyui-textbox" style="width:172px;"  disabled="disabled"/>
					</td>
				</tr>
	      		 <tr height="35px;">
					<td width="25%" height="35px;" align="right">字典名称：</td>
					<td width="75%" align="left">
						<input type="text" name="dictValue" id="dictValue" class="easyui-textbox" style="width:172px;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">字典代码：</td>
					<td width="75%" align="left">
						<input type="text" name="dictKey" id="dictKey" class="easyui-textbox" style="width:172px;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">字典拼音：</td>
					<td width="75%" align="left">
						<input type="text" name="dictPy" id="dictPy" class="easyui-textbox" style="width:172px;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">显示顺序：</td>
					<td width="75%" align="left">
						<input type="text" name="dictSortStr" id="dictSortStr" class="easyui-textbox" style="width:172px;"  required="true"/>
					</td>
				</tr>
				</tr>
				<tr height="80px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="75%" align="left">
						<input class="easyui-textbox" data-options="multiline:true"  style="width:80%;height:80px"  id="remark" name="remark">
					</td>
				</tr>
			</table>
	       <input type="hidden" name="id" id="id" /> 
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saveDict()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>
	   
	
	<script type="text/javascript">
		var url;
		var rootKey = "<%=rootKey %>";
		var parentKey = "<%=parentKey %>";
		var dictLevel = "<%=dictLevel %>";
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			/* if(dictValue == "" || dictValue == "null"){
			 	url = "${ctx}/sysDict/querySysChildDictList?rootKey="+rootKey+"&parentKey="+parentKey;
			}else{
				url = "${ctx}/sysDict/querySysChildDictList?rootKey="+rootKey+"&parentKey="+parentKey+"&dictValue="+dictValue;
			}  */
			datagrid = $("#dataGrid").datagrid({
			title : "字典信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/sysDict/querySysChildDictList?rootKey="+rootKey+"&parentKey="+parentKey,
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'dictKey',
				title : '字典代码',
				width : '17%',
				align : 'center',
				formatter: function(value,row,index){
					if(row.dictLevel == '2'){
						return row.dictKey;
					}else{
				        return "<a style='color:blue' href='javascript:void(0)' onclick='toChildFun(&apos;" + row['rootKey'] + "&apos;,&apos;"+ row['dictKey'] +"&apos;)'>"+row.dictKey+"</a>";
					}
			     }
			}, {
				field : 'dictValue',
				title : '字典名称',
				width : '20%',
				align : 'center'
			}, {
				field : 'remark',
				title : '字典说明',
				width : '30%',
				align : 'center'
			}, {
				field : 'dictPy',
				title : '字典拼音',
				width : '20%',
				align : 'center'
			}, {
				field : 'dictSortStr',
				title : '显示顺序',
				width : '12%',
				align : 'center'
			}, 
			] ],
			 toolbar:[          //工具条  
                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                   $("#dlg").dialog("open").dialog('setTitle', '字典信息新增');
                   $("#dictForm").form("clear");
                   $("#rootKey").textbox("setValue",rootKey);
                   $("#parentKey").textbox("setValue",parentKey);
                   //$("#dictLevel").textbox("setValue",dictLevel);
                    url = "/yhzn/sysDict/addSysDict";
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
                                    url : "${ctx}/sysDict/deleteSysDict",  
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
                }},  
                {text:"修改",iconCls:"icon-edit",handler:function(){  
                    var rows=datagrid.datagrid('getSelections');
                    if(rows.length<=0){  
                        $.messager.alert('提示','请选择要修改的行','error');  
                    }else if(rows.length>1){  
                        $.messager.alert('提示','只能选择一条数据进行修改','error');  
                    }else if(rows.length==1){  
		                $("#dlg").dialog("open").dialog('setTitle', '字典信息修改');
		                $("#dictForm").form("load", rows[0]);
		                url = "${ctx}/sysDict/editSysDict";
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
		
	});

		//查询条件  
		function queryFun() {
			var queryParameter = $('#dataGrid').datagrid("options").queryParams;
			queryParameter.dictValue = $("#f_dictValue").val();
			queryParameter.rootKey =rootKey;
			queryParameter.parentKey = parentKey;
			$("#dataGrid").datagrid("reload");
		}
		
		//保存
		function saveDict() {
             $("#dictForm").form("submit", {
                url: url+"?rootKey="+rootKey+"&parentKey="+parentKey+"&dictLevel="+dictLevel,
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
        
         function toChildFun(rootKey,parentKey){
        	window.location.href ="${ctx}/main/toSysChildDict?rootKey="+rootKey+"&parentKey="+parentKey+"&dictLevel=1";
        }
        
        //
        function backFun(){
	        if(dictLevel=="1"){
	       	 	window.location.href ="${ctx}/main/toSysChildDict?rootKey="+rootKey+"&parentKey="+rootKey+"&dictLevel=0";
	        }else{
	        	window.location.href ="${ctx}/main/toSysDict";
	        }
        }
        
	</script>
</body>
</html>
