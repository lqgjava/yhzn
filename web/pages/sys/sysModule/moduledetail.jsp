<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="ziyuanForm">
	<input type="hidden" name="id" value="${per.id}" />
	 <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center" style="margin-top: 10px;border-collapse:separate; border-spacing:10px; ">
	<tr class="form-item required" style="width:100%">
	<td align="right"><label for="">父资源</label> </td>
	<td><input class="easyui-combotree" name="parentId" value="${per.parentId }" style="width: 215px; "	data-options="editable:false,panelHeight:200,url:'../permission/menu'" /></td>
	</tr>
	<tr class="form-item required" style="width:100%">
		<td align="right"><label for="">资源名称</label> </td>
		<td><input data-options="required:true,prompt:'请输入资源名称...'" type="text" class="easyui-textbox" style="width: 415px; "  name="name" value="${per.name }" /></td>
	</tr>

	<tr class="form-item required" style="width:100%">
	<td align="right">	<label for="">图标</label></td> 
	<td><input style="width: 415px; " data-options="prompt:'请输入图标...'" type="text" class="easyui-textbox" value="${per.icon}" 	name="icon" /></td>
	</tr>
	<tr class="form-item required" style="width:100%">
	<td align="right">	<label for="">类型</label></td>
	<td> <select class="easyui-combobox" name="type" data-options="required:true,panelHeight:'auto'" style="width: 50%;">
			<option value="0" ${per.type=='0'?'selected':'' }>菜单</option>
			<option value="1" ${per.type=='1'?'selected':'' }>区域</option>
			<option value="2"${per.type=='2'?'selected':'' }>功能</option>
		</select></td>
	</tr>

	<tr>
	<td align="right">	<label for="">排序</label></td>
	<td> <input
			data-options="required:true,prompt:'请输入排序...'" type="text"
			class="easyui-textbox" value="${per.orderNum }" name="orderNum" /></td>
	

	<tr class="form-item required" style="width:100%">
	<td align="right">	<label for="">资源标识</label> </td><td><input style="width: 415px;"
			data-options="multiline:true,prompt:'请输入资源标识...'"
			type="text" class="easyui-textbox" value="${per.perms }"
			name="perms" /></td>
	</tr>

	<tr class="form-item required" style="width:100%">
	<td align="right">	<label for="">路径</label> </td><td><input style="width: 415px; height: 50px;"
			data-options="multiline:true,prompt:'请输入路径...'"
			type="text" class="easyui-textbox" value="${per.url}"
			name="url" /><td/>
	</tr>
<tr class="form-item required" style="width:100%">
	<td align="right">	<label for="">资源描述</label> </td><td><input style="width: 415px; height: 50px;"
			data-options="required:true,multiline:true,prompt:'请输入描述...'"
			type="text" class="easyui-textbox" value="${per.description }"
			name="description" /><td/>
	</tr>


	<tr class="form-item required" style="width:100%">
		<td align="right"><label for="">状态</label> </td>
		<td><input data-options="onText:'启用' ,offText:'禁用'  ,value:'1'"
			${per.status==1?'checked':''}
			type="text" class="easyui-switchbutton" name="status" /></td>
	</tr>
	</table>
</form>