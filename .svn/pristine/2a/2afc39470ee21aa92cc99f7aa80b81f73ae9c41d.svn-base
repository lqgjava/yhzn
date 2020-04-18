$(function() {
	var dg = $('#supplierGrid');
	dg.datagrid({
				url :'../supplier/findAll', // 显示列表
				title : "供应商列表",
				nowrap : false,
				border : true,
				locale : "zh_CN",
				iconCls : 'icon-man',
				height : $(window).height()-100,
				striped : true,
				collapsible : true,
				pagination : true,//表示在datagrid设置分页         
				pageSize:20,
				rownumbers : true, 
				columns : [ [
						{
							field : 'id',
							title : '编号',
							width : '10%',
							align : 'center',
							sortable: true,
							checkbox: true
						},
						{
							field : 'name',
							title : '供应商名称',
							width : '20%',
							align : 'center'
						},
						{
							field : 'userName',
							title : '联系人',
							width : '8%',
							align: 'center', 
							halign: 'center',//表头居中
						},
						{
							field : 'userTel',
							title : '电话',
							width : '10%',
							align: 'center', //内容靠左
							halign: 'center',//表头居中
						},
						{
							field : 'dutyPargraph',
							title : '税号',
							width : '15%',
							align : 'center'
						},
						{
							field : 'bankAccount',
							title : '银行账号',
							width : '15%',
							align : 'center'
						},
						{
							field : 'openBank',
							title : '开户行',
							width :'10%',
							align: 'center',
							halign: 'center'//表头居中
						},{
							field : 'supplierAddress',
							title : '供应商位置',
							width : '21%',
							align: 'center',
							halign: 'center'//表头居中
						}
						] ],  toolbar:'#supplierGridToolbar',/*[              //工具条  
			                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
			                	supplierForm();
			                 }},  
			                 {text:"删除",iconCls:"icon-remove",handler:function(){  
			             		var ids = [];
			             		var rows = dg.datagrid('getSelections');
			             		 if(rows.length<=0){  
			                         $.messager.alert('提示','请选择要修改的行','error');  
			                     }else{
			                    		for(var i=0; i<rows.length; i++){
						             		ids.push(rows[i].id);
						             		}
						             		$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
						             			// 点击了确定按钮，r返回的结果为true
						             			if (r) {
						             				$.get("../supplier/delete/" + ids, function(data) {
						             					if (data.success=="1") {
						             						// 刷新表格
						             						dg.datagrid("reload");
						             					} else {
						             						$.message.alert("提示", "删除失败");
						             					}
						             				});
						             			}
						             		});
			                     }
			             	
			                 }}  
			                 ,{text:"修改",iconCls:"icon-edit",handler:function(){  
			                	 var rows=dg.datagrid('getSelections');
			                     if(rows.length<=0){  
			                         $.messager.alert('提示','请选择要修改的行','error');  
			                     }else if(rows.length>1){  
			                         $.messager.alert('提示','只能选择一条数据进行修改','error');  
			                     }else if(rows.length==1){  
			                    	 var id=rows[0].id;
			                    	 supplierForm(id);			 		               
			                     }  
			                 }}]*/
						onLoadSuccess:function(data){
							$('#dg').datagrid('fixRowHeight'); 
						},
			});
	
	
	var name = $("#name");
	var userName = $("#userName");
	var area = $("#area");
	// 搜索按钮事件
	$("#SupplierSearch").on('click', function() {
		dg.datagrid("load", {
			name : "%" + name.val() + "%",
		    userName : "%" + userName.val() + "%",
		    area:"%" +area.val()+"%"
		});
	});
	var suppliergridPanel = $("#supplierGrid").datagrid("getPanel");
	suppliergridPanel.on("click", "a.create", function() {
		supplierForm();
	}).on("click","a.edit",function(){
		var rows=dg.datagrid('getSelections');
        if(rows.length<=0){  
            $.messager.alert('提示','请选择要修改的行','error');  
        }else if(rows.length>1){  
            $.messager.alert('提示','只能选择一条数据进行修改','error');  
        }else if(rows.length==1){  
       	 var id=rows[0].id;
       	 supplierForm(id);			 		               
        }  
	}).on("click","a.delete",function(){
		var ids = [];
 		var rows = dg.datagrid('getSelections');
 		 if(rows.length<=0){  
             $.messager.alert('提示','请选择要删除的行','error');  
         }else{
        		for(var i=0; i<rows.length; i++){
             		ids.push(rows[i].id);
             		}
             		$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
             			// 点击了确定按钮，r返回的结果为true
             			if (r) {
             				$.get("../supplier/delete/" + ids, function(data) {
             					if (data.success=="1") {
             						// 刷新表格
             						dg.datagrid("reload");
             					} else {
             						$.message.alert("提示", "删除失败");
             					}
             				});
             			}
             		});
         }
	})
	//重置事件
	var form = $("#SupplierSearchForm");
	$("#SupplierReset").on('click', function() {
		form.form('clear');
		// 清除查询参数
		dg.datagrid("load", {});
	});
	
	
	function supplierForm(id) {
		// 创建窗口
		var dialog = $("<div/>").dialog(
				{
					href : '../supplier/addSupplier' + (id ? ("/" + id) : ""),
					title : id==null?'添加':'编辑'+"供应商",
					height : 520.006,
					width : 450,
					modal : true,
					onClose : function() {
						// 窗口关闭的同时销毁此窗口
						$(this).dialog("destroy");
					},
					buttons : [ {
						iconCls : 'icon-save',
						text : '保存',
						handler : function() {
							// 获取到表单对象
							var supplierForm = $("#supplierForm");
							// 校验表单
							if (supplierForm.form('validate')) {
								// serialize方法可以获取到表单所有数据
								$.post("../supplier/addsupplier",
										supplierForm.serialize(), function(
												data) {
											if (data.success=="1") {
												// 刷新数据表格
												dg.datagrid("reload");
												// 关闭窗口
												dialog.dialog("close");
											} else {
												$.messager.alert("提示",
														"操作失败");
											}
										});
							}
						}
					} ]
				});
	}
});

