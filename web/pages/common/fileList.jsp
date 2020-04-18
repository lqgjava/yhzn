<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>文件列表</title>

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
<table id="table">
</table>
<script type="text/javascript">
$(function () {
	var parentId='${parentId}';
 $("#table").datagrid({
	nowrap : false,
	border : false,
	locale : "zh_CN",
	title:'文件下载',
	iconCls : 'icon-save',
	striped : true,
	collapsible : true,
	url : '${ctx}/file/QueryFileByParentId?parentId='+parentId,
	pagination : true,//表示在datagrid设置分页              
	rownumbers : true,
	singleSelect : true,
	columns : [ [ 
			{
				field : 'id',
				title : '附件编号',
				width : '10%',
				align : 'center' 
			},{
				field : 'fileName',
				title : '原文件名',
				width : '20%',
				align : 'center' 
			}, {
				field : 'fileSize',
				title : '大小',
				width : '10%',
				align : 'center'
			}, {
				field : 'newName',
				title : '新文件名',
				width : '20%',
				align : 'center'
			},  {
				field : 'suffix',
				title : '尾缀',
				width : '10%',
				align : 'center',
				hidden:true,
			},
			
			{
				field : 'caozuo',
				title : '操作',
				width : '20%',
				align : 'center',
				formatter : function(value, row, index) {
					var bts = [];
					var id=row.id;
					bts.push('<a data-index="' + index + '" href="${ctx}/file/download/' +row.id +'"  class="upload">下载文件</a>');
					bts.push('<a data-index="' + index + '" onclick="previewFile('+index+')" class="preview">预览文件</a>');
					bts.push('<a data-index="' + index + '" onclick="deleteFile('+index+')" class="delete">删除文件</a>');
					return bts.join('');

				}
			}] ] ,
    onLoadSuccess:function(data){  
    	$('.preview').linkbutton({text:'预览文件',plain:true,iconCls:'icon-chakan'}); 
        $('.upload').linkbutton({text:'下载文件',plain:true,iconCls:'icon-upload'}); 
        $('.delete').linkbutton({text:'删除文件',plain:true,iconCls:'icon-remove'});
    }  
});

});
function previewFile(index){
	// 如果只有下标，没有id的情况，要获取id
	var rows = $("#table").datagrid("getRows");
	// 同下标获取对应行的数据对象
	var id = rows[index].id;
	var suffix = rows[index].suffix;
	if(suffix=="docx" || suffix=="doc" || suffix=="xls" || suffix=="xlsx" || suffix=="pdf"){
		window.location.href="${ctx}/resource/pdfJs/web/viewer.html?file=${ctx}/file/preview/"+id;
	}else{
		window.location.href="${ctx}/file/img?id="+id;
	}
}
function deleteFile(index){
	$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
		// 点击了确定按钮，r返回的结果为true
		 if (r) {
				// 如果只有下标，没有id的情况，要获取id
				var rows = $("#table").datagrid("getRows");
				// 同下标获取对应行的数据对象
				var id = rows[index].id;
			 $.get("../deleteFile/"+id, function(data) {
				if (data.success) {
					// 刷新表格
					$("#table").datagrid("reload");
				} else {
					$.message.alert("提示", "删除失败");
				}
			}); 
		} 
	});

}

</script>
</body>
</html>