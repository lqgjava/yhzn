$(document).ready(function(){
	
	context.init({preventDoubleContext: false});
	
	context.attach('.inline-menu', [
		{header: 'Options'},
		{text: 'Open', href: '#'},
		{text: 'Open in new Window', href: '#'},
		{divider: true},
		{text: 'Copy', href: '#'},
		{text: 'Dafuq!?', href: '#'}
	]);
	
	
	context.settings({compress: true});

	context.attach('#mynetwork', [
		{header: '实体操作'},
		{text: '&nbsp;删除案件实体', action: function(e){
			deleteTypeNode('dot');
		}},
		{text: '&nbsp;删除人员实体', action: function(e){
			deleteTypeNode('ellipse');
		}},
		{text: '&nbsp;删除物证实体', action: function(e){
			deleteTypeNode('square');
		}},
		{text: '&nbsp;删除落单实体', action: function(e){
			deleteLonlyNode();
		}},
		{divider: true},
		{header: '边线操作'},
		{text: '&nbsp;删除指纹串并', action: function(e){
			deleteTypeEdge('指纹串并');
		}},
		{text: '&nbsp;删除DNA串并', action: function(e){
			deleteTypeEdge('DNA串并');
		}},
		{text: '&nbsp;删除足迹串并', action: function(e){
			deleteTypeEdge('足迹串并');
		}},
		{text: '&nbsp;删除现勘', action: function(e){
			deleteTypeEdge('现勘');
		}},
		
		{divider: true},
		{header: '其他'},
		
		{text: '&nbsp;刷新', action: function(e){
			window.location.reload();
		}},
		{text: '&nbsp;取消', action: function(e){
		}}
		
	]);
	
	
	$(document).on('mouseover', '.me-codesta', function(){
		$('.finale h1:first').css({opacity:0});
		$('.finale h1:last').css({opacity:1});
	});
	
	$(document).on('mouseout', '.me-codesta', function(){
		$('.finale h1:last').css({opacity:0});
		$('.finale h1:first').css({opacity:1});
	});
	
});