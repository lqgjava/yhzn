<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>成本分析管理</title>
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
					
					<td width="10%" height="35px;" align="right">项目编号：</td>
					<td width="23%" align="left">
						<input type="text" name="name" id="f_name" class="easyui-textbox" style="width:70%;" />
					</td>
					<td width="10%" height="35px;" align="right">项目名称：</td>
					<td width="23%" align="left">
						<input type="text" name="dept" id="f_dept" class="easyui-textbox" style="width:70%;" />
					</td>
					<td width="10%" height="35px;" align="right">项目状态：</td>
					<td width="23%" align="left">
						<input type="text" name="idCard" id="f_idCard" class="easyui-textbox" style="width:70%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="10%" height="35px;" align="right">开始时间：</td>
					<td width="23%" align="left">
						<input class="easyui-datebox" name="beginDate" id="f_beginDate" style="width:40%;" />
						至 <input class="easyui-datebox" name="endDate" id="f_endDate"  style="width:40%;" />
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="23%" align="left"></td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="23%" align="left"></td>
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
		
	<script type="text/javascript">
		var url;
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "成本分析信息列表",
			nowrap : false,
			border : false,
			locale : "zh_CN",
			height : $(window).height()-250,
			iconCls : 'icon-save',
			striped : true,
			collapsible : true,
			url : "${ctx}/person/queryPersonList",
			pagination : true,//表示在datagrid设置分页              
			rownumbers : true,
			//singleSelect : true,
			columns : [ [ 
			{field: 'id', title: '编号', width: 100, sortable: true, checkbox: true },
			{
				field : 'name',
				title : '项目编号',
				width : '12%',
				align : 'center'
			}, {
				field : 'dept',
				title : '项目名称',
				width : '12%',
				align : 'center'
			}, 
			{
				field : 'idCard',
				title : '项目状态',
				width : '15%',
				align : 'center'
			} ,
			{
				field : 'ifJob',
				title : '人力成本',
				width : '15%',
				align : 'center'
			} ,
			 {
				field : 'nativePlace',
				title : '货物成本',
				width : '12%',
				align : 'center'
			},
			 {
				field : 'phoneNo',
				title : '其他成本',
				width : '15%',
				align : 'center'
			},
			 {
				field : 'entryDateStr',
				title : '利润',
				width : '18%',
				align : 'center'
			} ] ],
			   toolbar:[              //工具条  
                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                   $("#dlg").dialog("open").dialog('setTitle', '员工信息新增');
                   $("#personForm").form('clear');
                   $("#headPic").attr("src", "${ctx }/resource/img/touxiang.jpg") ;
                    url = "${ctx}/person/addPerson";
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
                                    url : "${ctx}/person/deletePerson",  
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
		                $("#dlg").dialog("open").dialog('setTitle', '用户信息修改');
		                //判断是否后台加载照片
		                if(rows[0].photoId == "" || rows[0].photoId == null){
		                	$("#headPic").attr("src", "${ctx}/resource/img/touxiang.jpg");
		                }else{
		                	$("#headPic").attr("src", "${ctx}/person/getPhoto?id="+rows[0].photoId);
		                }
		                $("#personForm").form("load", rows[0]);
		                url = "${ctx}/person/editPerson";
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
</script>
</body>
</html>
