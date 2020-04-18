<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>应付供应商款列表</title>
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/columns.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui-me/style.css">
<link rel="stylesheet" type="text/css" href="${ctx }/resource/plugins/easyui/themes/icon.css">
  <link rel="stylesheet" href="${ctx }/resource/countDown/countDown.css">
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/resource/plugins/easyui-me/common.js"></script> 
<script type="text/javascript" src="${ctx }/resource/plugins/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx }/resource/countDown/countDown.js"></script>
<body style="magin:0;padding:0">
		 <div id="ReceProjectGridToolbar" style="display: none;">	
		<a class="actions easyui-linkbutton " onclick="javascript:history.back(-1);" iconCls="icon-edit" plain="true">返回上一级</a>
		<a class="actions create easyui-linkbutton "  iconCls="icon-add" plain="true">添加付款款记录</a>
		<a class="actions delete easyui-linkbutton "  iconCls="icon-add" plain="true">删除付款记录</a>
	   </div>
	   <div class="container" style="text-align:right;font-size: 10px;magin:0;padding:0;background-color: #438EB9">
	<input type="hidden" name="countDown" data-prefix="" data-suffix="" value="2018/11/20 12:25:32"> 
	   </div>
	   <%-- 新增修改弹出框 begin --%>
			<div id="dlg" class="easyui-dialog" style="display:none;width:600px; height: 440px;top:10px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="moneyForm" method="post"  enctype="multipart/form-data"> 
	       <table width="98%" border="0" cellspacing="5" cellpadding="0" align="center" style="margin-top: 10px;">
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">付款期数：</td>
					<td width="75%" align="left">
					<input class="easyui-textbox"  required="true" style="width:90%;" id="cishu" name="cishu">
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">应付款时间：</td>
					<td width="75%" align="left">
					<input class="easyui-datebox" editable="fasle"  required="true" style="width:90%;" id="yingDate" name="yingDate">
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">实际付款时间：</td>
					<td width="75%" align="left">
						<input class="easyui-datebox" editable="fasle"  required="true" style="width:90%;" id="shiDate" name="shiDate">
					</td>
				</tr>
				</tr>
					<tr height="35px;">
					<td width="25%" height="35px;" align="right">应付金额：</td>
					<td width="75%" align="left">
						<input  name="yingMoney" id="yingMoney" class="easyui-numberbox" style="width:90%;"  required="true"/>
					</td>
				</tr>
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">实付金额：</td>
					<td width="75%" align="left">
						<input  name="shiMoney"  id="shiMoney" required="true" class="easyui-numberbox" style="width:90%;" />
					</td>
				
				
				<tr height="35px;">
					<td width="25%" height="35px;" align="right">备注：</td>
					<td width="70%" align="left">
					<input class="easyui-textbox" data-options="multiline:true"  style="width:90%;height:100px"  id="detailRemark" name="detailRemark">
				</td>
			</tr>
				<tr height="35px">
					<td width="25%" height="20px" align="right">凭证：</td>
					<td width="75%" height="20px" align="left">
    					<input class="easyui-filebox" data-options=" buttonText: '选择文件',prompt:'浏览'"  multiple="true" id=" file"  name="file" style="width:90%"/>
					</td>
				</tr>
			</table>
	         <input type="hidden" name="parentId" id="parentId" value="${id}"/>
	           <input type="hidden" name="id" id="id" />
	       
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
		    </div>
	  
	<table id="dg"></table>
	<audio id="audio" style="display:none"></audio>
		<script type="text/javascript">
				var url;
				$(document).ready(function() {
					updateMsg();
				$('#dg').datagrid({
					title:'应付款详情',
					nowrap : false,
					locale : "zh_CN",
					iconCls : 'icon-save',
					striped : true,
					 height: $(window).height()-100,
					collapsible : true,
					url : '${ctx}/pay/queryPayableDetails?id=${id}',
					rownumbers : false,
					pagination : true,//表示在datagrid设置分页     
					singleSelect : true,
					columns : [ [ 
						{
							field : 'cishu',
							title : '付款期数',
							width : '5%',
							align : 'center' 
						},{
						field : 'yingDate',
						title : '应付款时间',
						width : '20%',
						align : 'center' 
					},{
						field : 'shiDate',
						title : '实际付款时间',
						width : '10%',
						align : 'center' 
					}, {
						field : 'yingMoney',
						title : '应收金额',
						width : '10%',
						align : 'center'
					}, {
						field : 'shiMoney',
						title : '实际支出金额',
						width : '20%',
						align : 'center'
					},{
						field:'id',
						title:'凭证',
						width : '20%',
						align : 'center',
						formatter : function(value, row, index) {
							var bts = [];
							bts.push('<a data-index="' + index + '"  class="chakan">查看文件</a>');
							return bts.join('');
						}
					},{
						field:'detailRemark',
						title:'备注',
						width : '20%',
						align : 'center'
					}] ] , toolbar:'#ReceProjectGridToolbar', 
					   onLoadSuccess: function(data) {  
						$('.chakan').linkbutton({text:'查看附件',plain:true,iconCls:'icon-chakan'});  
						      //所有列进行合并操作       
						$(this).datagrid("autoMergeCells",['cishu']);   
						         }
						
					  });  
			var TaskgridPanel = $('#dg').datagrid("getPanel");
			TaskgridPanel.on("click", "a.create", function() {
				  $("#dlg").dialog("open").dialog("setTitle", "添加应收账款");
		          $("#moneyForm").form('clear');
					$("#parentId").val('${id}');
					url='${ctx}/pay/addPayableDetails';
			}).on("click", "a.edit", function() {
					var selectedRows= $('#dg').datagrid("getSelections");
					 if(selectedRows.length!=1){
						 $.messager.alert("系统提示","请选择一条要修改的数据！");
						 return;
					 }
					 var row=selectedRows[0];
					  $("#dlg").dialog("open").dialog("setTitle", "编辑应收账款");
			          $("#moneyForm").form('clear');
					 $("#moneyForm").form("load",row);               
	                $("#yingDate").datebox("setValue", row.yingDate);
	                $("#shiDate").datebox("setValue", row.shiDate);
	                url='${ctx}/pay/editPayableDetails';
			}).on("click", "a.chakan", function() {
				var index = this.dataset.index;
				// 如果只有下标，没有id的情况，要获取id
				var rows =$('#dg').datagrid("getRows");
				// 同下标获取对应行的数据对象
				var id = rows[index].id;
				//加载数据
				window.open("${ctx}/file/fileList/?parentId=" + id);
			}).on("click", "a.delete", function() {
				 var row = $('#dg').datagrid('getSelected');
				 var rows =  $('#dg').datagrid('getSelections');
 				if (rows.length <= 0) {
 					$.messager.alert('提示','请选择要删除的行', 'error');
 				}
		           var id=row.id;
		     				 	$.messager.confirm("提示", "您确定要删除此数据?", function(r) {
		     						// 点击了确定按钮，r返回的结果为true
		     						
		     						if (r) {
		     							$.get("../pay/deletePayableDetails/" + id+"/"+row.parentId, function(data) {
		     								if (data.success) {
		     									// 刷新表格
		     									$("#dg").datagrid("reload");
		     								} else {
		     									$.message.alert("提示", "删除失败");
		     								}
		     							});
		     						}
		     					}); 
			})
			
			
				/* $('#dg').datagrid({
					onLoadSuccess: function(data) {
						//合并列
						$('#dg').datagrid("autoMergeCells", ['cishu']);
				 	var rows = $('#dg').datagrid('getRows') //获取当前的数据行
						var ptotal = 0 //计算money的总和
						
						for(var i = 0; i < rows.length; i++) {
							ptotal += parseFloat(rows[i]['money']);
						}
						//新增一行显示统计信息
						$('#dg').datagrid('appendRow', {
							id:'2',
							financeTel: '<b>合计：</b>',
							money:ptotal,
						});
					},
					rowStyler: function(index, row) {
						if(row.financeTel == '<b>合计：</b>') {
							return 'background-color:#EAEAEA;color:blue';
						}
					} 
					
				});  */
	
			});
				//系统报警
				function updateMsg() {
					var time=$('.active-time').text();
			/* 	  var d=$('time_d').text;
				   var h=$('time_h').text;
				 var m=$('time_m').text;
				var s=$('time_s').text;
				
				console.log(d=='0' &&  h=='00'&& m=='00' && s=='10'); */
				console.log(time);
				/*   if(d=='0' &&  h=='00'&& m=='00' && s=='00'){
					  playMusic("${ctx}/resource/countDown/bojin.mp3");
				  } */
				    //每隔4秒，读取一次.
				    setTimeout('updateMsg()', 3000);
				}
				
				function playMusic(path) {
				    var audioEle = document.getElementById("audio");
				    audioEle.src=path;
				    audioEle.load();
				    if (audioEle.paused){ /*如果已经暂停*/
				        audioEle.play();   //播放
				    } else {
				        audioEle.pause();  //暂停
				    }
				}
				//添加和修改方法
				 function save(){
					  $("#moneyForm").form("submit", {
			                url: url,
			                onsubmit: function () {
			                    return $(this).form("validate");
			                },
			                success: function (result) {
			                    if (result == "1") {
			                        $.messager.alert("提示信息", "操作成功");
			                        $("#dlg").dialog("close");
			                        $('#dg').datagrid("reload");
			                    }
			                    else {
			                        $.messager.alert("提示信息", "操作失败");
			                    }
			                }
			            }); 
				}

				
			

		</script>
		<script>
    (function(){

        var currentYear  = new Date().getFullYear();
        //示例数据
        var preData=[
            {prefix:"距离下次收款时间是还有",suffix:"！",value:currentYear+ "/8/17 11:36:00"},
         
        ],preDataLength=preData.length;

        var htmlContainer=$(".container");//示例容器

        var countDownHtml = "<span><span></span></span><a class='easyui-linkbutton set'   plain='true'>设置</a><a class='easyui-linkbutton stop'   plain='true'>停止</a>";//示例结构
        var viewFunction=function (needms) {

            var allHtml=[];

            /**
             * 虚拟渲染每个示例
             * @param data
             * @returns {jQuery|HTMLElement}
             */
            var deal=function (data) {
                var html= $(countDownHtml);
                var time_end=data.value;
                var con=html.find("span");

                //初始化倒计时
                countDown(con,{
                    title:data.title,//优先级最高,填充在prefix位置
                    prefix:data.prefix,//前缀部分
                    suffix:data.suffix,//后缀部分
                    time_end:time_end,//要到达的时间,
                    needms:needms
                })
                //提供3个事件分别为:启动,重启,停止
                    .on("countDownStarted countDownRestarted   ",function () {
                        console.info(arguments);
                    });

                return html;
            };
            //虚拟渲染所有示例
            for(var i=0;i<preDataLength;i++){
                var data=preData[i];
                allHtml.push(deal(data));
            }
            htmlContainer.empty();
            //实际渲染示例集
            htmlContainer.append(allHtml);
        };

        viewFunction();

    }());

</script>
<script type="text/javascript">
$.extend($.fn.datagrid.methods, {  
	  autoMergeCells: function(jq, fields) { 
      			 return jq.each(function() {   
			         var target = $(this);  
			          if (!fields) {
			                fields = target.datagrid("getColumnFields");  
		              	 }     
			        var rows = target.datagrid("getRows");    
			        var i = 0, j = 0,temp = {};        
			   	 for (i; i < rows.length; i++) {      
			          var row = rows[i];
                		j = 0; 
             		  for (j; j < fields.length; j++) {       
            			 var field = fields[j];         
          				 var tf = temp[field];        
          				  if (!tf) { 
                    		   tf = temp[field] = {};        
               					 tf[row[field]] = [i];            
       					 } else {             
          					 var tfv = tf[row[field]];    
                   			 if (tfv) {     
                     		 tfv.push(i);    
                    		} else {   
                        	 tfv = tf[row[field]] = [i];   
                    		 }      
            		  }             
  				 }  
          }       
   				  $.each(temp, function(field, colunm) {         
       						$.each(colunm,function() {     
             				  var group = this;   
                			 if (group.length > 1) { 
                      			 var before, after, megerIndex = group[0];     
                  				 for (var i = 0; i < group.length; i++) {     
                      					 before = group[i];                     
      									 after = group[i + 1];                    
       								 if (after && (after - before) == 1) {       
                        				 continue;     
                       				}                      
    									 var rowspan = before - megerIndex + 1;     
                      				 if (rowspan > 1) {       
                       					  target.datagrid('mergeCells', {   
                       			          index: megerIndex,      
                        			      field: field,                 
                  						  rowspan: rowspan                 
              							 });   
	                         }               
             					if (after && (after - before) != 1) {       
                     		    megerIndex = after;                      
							      }                 	   
							    }               
							     }          
							      });        
							    });      
							  }); 
							   }});

</script>
</body>
</html>