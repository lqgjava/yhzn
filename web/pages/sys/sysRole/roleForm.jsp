<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="jueseForm">
	<input type="hidden" name="id" value="${role.id }"/>
	<div class="form-item required" style="float:left; padding-top: 10px;">
		<label for="">角色名称</label>
		<input value="${role.roleName }" data-options="required:true,prompt:'请输入角色名称...'" type="text" class="easyui-textbox" name="roleName"/>
	</div>
	<div class="form-item" style="float:left;padding-top: 10px;">
		<label for="">角色注释</label>
		<input value="${role.description }"  type="text" class="easyui-textbox" data-options="multiline:true,prompt:'请输入角色注释...'" style="width: 415px; height: 50px;" name="description"/>
	</div>
</form>