$(function() {
	var bar =$("#bottonBar");
	var dg = $('#earlyProjectGrid');
	dg.datagrid({
		url : '../schedule/queryEarlyList?projStatus=0&isHide=0', // 显示列表
		title : "项目基本信息",
		fitColumns:false,
		nowrap : false,
		border : true,
		locale : "zh_CN",
		iconCls : 'icon-man',
		striped : true,
		height : $(window).height()-100,
		collapsible : true,
		pagination : true,// 表示在datagrid设置分页
		pageSize : 20,
		rownumbers : true,
		 onDblClickRow: function(rowIndex){//鼠标双击事件
		       dg.datagrid("selectRow",rowIndex);//选中此行
		        var currentRow = dg.datagrid("getSelections");//获得单机选中行，双击时只能单选 
		        if (currentRow.length > 1) {
					$.messager.alert('提示', '只能选中一条数据', 'error');
				}else{
					  var row=dg.datagrid("getSelected");
		        	var id=row.id;
		        	window.name=id;
		        	//linkForm(id);
		        	window.location.href="../schedule/projectLink?id="+id+"&projStatus=0";
				}
		    },
		columns : [ [ {
			field : 'id',
			title : '编号',
			width : 100,
			sortable : true,
			checkbox : true
		}, {
			field : 'projNo',
			title : '项目编号',
			width : '12%',
			align : 'center'
		}, {
			field : 'projName',
			title : '项目名称',
			width : '12%',
			align : 'center'
		}, {
			field : 'contacts',
			title : '业务经理',
			width : '9%',
			align : 'center'
		}, {
			field : 'phone',
			title : '电话',
			width : '10%',
			align : 'center'
		}, {
			field : 'unitName',
			title : '单位名称',
			width : '14%',
			align : 'center'
		},/* {
			field : 'projUnitName',
			title : '项目单位名称',
			width : '14%',
			align : 'center'
		}, {
			field : 'projContacts',
			title : '甲方联系人',
			width : '10%',
			align : 'center'
		},  {
			field : 'projTel',
			title : '甲方电话',
			width : '10%',
			align : 'center'
		},{
			field : 'projAddress',
			title : '甲方地址',
			width : '8%',
			align : 'center'
		},*/{
			field : 'projDutyTrueName',
			title : '项目负责人',
			width : '8%',
			align : 'center'
		},{
			field:'createName',
		    title:'数据上传人',
		    width:'8%',
		    align:'center'
		},{
			field : 'remark',
			hidden:true,
			title:'备注',
			align : 'center'
		}, {
			field : 'projStatus',
			title : '项目进程',
			width : '10%',
			align : 'center',
			halign : 'center',// 表头居中
			formatter:function formatterStatus(value, row, index){
				if(row.projStatus==0){
					return "沟通中";
				}else if(row.projStatus==1){
					return "进行中";
				}else if(row.projStatus==2){
					return "售后";
				}
			}
		},{
			field : '操作',
			title : '操作',
			width : '10%',
			align : 'center',
			halign : 'center',// 表头居中
			formatter : function(value, row, index) {
				var projStatus=row.projStatus;
			
			if(projStatus==0){
				return bar.children("a.actions").attr('data-index', index).end().html();
			}else{
				return
			}
			}
		} ] ],
		toolbar :'#earlyGridToolbar',
		onLoadSuccess : function(data) {
			$('#dg').datagrid('fixRowHeight');
		},
		rowStyler:function(index,row){  
	        if (row.isHide=="1"){  
	            return 'background-color:pink;color:red';  
	        }  
	    }  

	});
	var unitName = $("#unitName");
	var contacts = $("#contacts");
	var projName = $("#projName");

	
	// 搜索按钮事件
	$("#earlyProjectSearch").on('click', function() {
		dg.datagrid("load", {
			unitName : unitName.val(),
			contacts : contacts.val(),
			projName : projName.val(),

		});
	});
	// 重置事件
	var form = $("#earlyProjectSearchForm");
	$("#earlyReset").on('click', function() {
		location.href="../main/earlyPorject"
	});
	$("#hidelist").on('click', function() {
		location.href="../schedule/earlyProjectHide"
		
	});

	var earlygridPanel = $('#earlyProjectGrid').datagrid("getPanel");
		earlygridPanel.on("click", "a.create", function() {
			supplierForm();
		}).on("click","a.edit",function(){
			var rows = dg.datagrid('getSelections');
			if (rows.length <= 0) {
				$.messager.alert('提示', '请选择要修改的行', 'error');
			} else if (rows.length > 1) {
				$.messager.alert('提示', '只能选择一条数据进行修改', 'error');
			} else if (rows.length == 1) {
				var id = rows[0].id;
				supplierForm(id);
			}
		}).on("click","a.delete",function(){
			var ids = [];
			var rows = dg.datagrid('getSelections');
			if (rows.length <= 0) {
				$.messager.alert('提示', '请选择要删除的行', 'error');
			} else {
				for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
					// 点击了确定按钮，r返回的结果为true
					if (r) {
						$.get("../schedule/deleteProject/" + ids, function(data) {
							if (data.success == "1") {
								// 刷新表格
								dg.datagrid("reload");
							} else {
								$.message.alert("提示", "删除失败");
							}
						});
					}
				});
			}
		}).on("click","a.update",function(){
			var index = this.dataset.index;
			// 如果只有下标，没有id的情况，要获取id
			var rows = $('#earlyProjectGrid').datagrid("getRows");
			// 同下标获取对应行的数据对象
			var id = rows[index].id;
							
			$.messager.confirm("提示", "您确定要更改此项目的进程?", function(r) {
				// 点击了确定按钮，r返回的结果为true
				if (r) {
					$.get("../schedule/updateProjectStatus?id=" + id+"&status=1", function(data) {
						if (data.success) {
							// 刷新表格
							$('#earlyProjectGrid').datagrid("reload");
						} else {
							$.message.alert("提示", "更改失败");
						}
					});
				}
			});
			
		}).on("click","a.hide",function(){
			var ids = [];
			var rows = dg.datagrid('getSelections');
			if (rows.length <= 0) {
				$.messager.alert('提示', '请选择要隐藏的行', 'error');
			} else {
				for (var i = 0; i < rows.length; i++) {
					ids.push(rows[i].id);
				}
				$.messager.confirm("提示", "您确定要隐藏此数据?", function(r) {
					// 点击了确定按钮，r返回的结果为true
					if (r) {
						$.get("../schedule/hideProject/" + ids, function(data) {
							if (data.success == "1") {
								// 刷新表格
								dg.datagrid("reload");
							} else {
								$.message.alert("提示", "隐藏失败");
							}
						});
					}
				});
			}
		});
	function supplierForm(id) {
		// 创建窗口
		var dialog = $("<div/>").dialog(
				{
					href : '../schedule/addProject' + (id ? ("/" + id) : ""),
					title : id == null ? '添加' : '编辑' + "项目信息",
					height :390,
					width : 900,
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
							var ProjectForm = $("#ProjectForm");
							// 校验表单
							if (ProjectForm.form('validate')) {
								// serialize方法可以获取到表单所有数据
								$.post("../schedule/addProject", ProjectForm
										.serialize(), function(data) {
									if (data.success == "1") {
										// 刷新数据表格
										dg.datagrid("reload");
										// 关闭窗口
										dialog.dialog("close");
									} else {
										$.messager.alert("提示", "操作失败");
									}
								});
							}
						}
					} ]
				});
	}
	function linkForm(id) {
		// 创建窗口
		var dialog = $("<div/>").dialog(
				{
					href : '../schedule/projectLink',
					title : "沟通信息",
					height : '100%',
					width : '100%',
					modal : true,
					onClose : function() {
						// 窗口关闭的同时销毁此窗口
						$(this).dialog("destroy");
					},
					buttons: [{
						iconCls: 'icon-back',
						text: '返回',
						handler: function() {
							//关闭窗口
							dialog.dialog("close");
							//刷新数据表格
							dg.datagrid("reload");
						}
					}]
				});
	}
});
