$(function() {
	var resoucesGrid = $("#resoucesGrid");
	var resoucesGridAction =$("#resoucesGridAction");
	var types = {
			"0":"菜单",
			"2":"功能",
			"1":"区域"
	};
	resoucesGrid.treegrid({
		border : true,
		locale : "zh_CN",
		striped : true,
		collapsible : true,
		url : '../permission/tree',
		type:'post',
		rownumbers : true,
		fitColumns : true,// 表格列自适应
		 height: $(window).height()-55,
		nowrap : false,// 内容换行
		idField : 'id',
		treeField : 'name',
		columns : [ [ {
			title : '权限名称',
			field : 'name',
			width : 160
		}, {
			field : 'perms',
			title : '标识',
			width : 130
		}
		, {
			field : 'icon',
			title : '图标',
			width : 130
		}, {
			field : 'type',
			title : '类型',
			width : 60,
			align : 'center',
			formatter:function(val){
				return types[val];
			}
		},{
			field : 'url',
			title : '路径',
			width : 150
		}, {
			field : 'orderNum',
			title : '排序',
			width : 60,
			align : 'center'
		}, {
			field : 'status',
			title : '状态',
			width : 60,
			align : 'center',
			formatter:function(val){
				return val=="1" ? "可用" : "禁用";
			}
		},{
			field : 'ac',
			title : '操作',
			width : 130,
			align : 'center',
			formatter : function(value, row, index) {
				
				return resoucesGridAction.children("a.actions").attr('data-id', row.id).end().html();
			}
		}] ],
		onLoadSuccess:function(data){ 
			
			$('#resoucesGrid').datagrid('fixRowHeight');  
		},
		toolbar:'#resoucesGridToolbar'
	});
	
	var ziyaungridPanel = resoucesGrid.treegrid("getPanel");
	ziyaungridPanel.on("click", "a.edit", function() {
		var id = this.dataset.id;
		ziyuanForm(id);
	}).on("click", "a.delete", function() {
		// 获取data-xxx绑定的标签属性数据
		var id = this.dataset.id;
		$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
			// 点击了确定按钮，r返回的结果为true
			if (r) {
				$.post("../permission/delete?id=" + id, function(rs) {
				
					if (rs.success) {
						// 刷新表格
						resoucesGrid.treegrid("reload");
					} else {
						$.messager.alert("提示", rs.error);
					}
				});
			}
		});
	}).on("click", "a.create", function() {
		ziyuanForm();
	});

	
	/**
	 * 资源表单，新建和编辑都调用此方法
	 */
	function ziyuanForm(id) {
		// 创建窗口
		var dialog = $("<div/>").dialog(
				{
					href : id==null?'../permission/addmodule':'../permission/addmodule?id='+id	,
					title : "资源信息",
					height : 420,
					width : 570,
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
							var ziyuanForm = $("#ziyuanForm");
							// 校验表单
							if (ziyuanForm.form('validate')) {
								// serialize方法可以获取到表单所有数据
								$.post(id==null?"../permission/add":"../permission/edit", ziyuanForm
										.serialize(), function(rs) {
									if (rs.success) {
										// 刷新数据表格
										resoucesGrid.treegrid("reload");
										// 关闭窗口
										dialog.dialog("close");
									} else {
										$.messager.alert("提示", rs.error);
									}
								});
							}
						}
					} ]
				});
	}
});
