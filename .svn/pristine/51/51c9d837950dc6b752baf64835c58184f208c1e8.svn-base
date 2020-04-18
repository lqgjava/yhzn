<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>人员管理</title>
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
					
					<td width="10%" height="35px;" align="right">姓名：</td>
					<td width="23%" align="left">
						<input type="text" name="name" id="f_name" class="easyui-textbox" style="width:70%;" />
					</td>
					<td width="10%" height="35px;" align="right">部门：</td>
					<td width="23%" align="left">
						<input type="text" name="dept" id="f_dept" class="easyui-textbox" style="width:70%;" />
					</td>
					<td width="10%" height="35px;" align="right">身份证号：</td>
					<td width="23%" align="left">
						<input type="text" name="idCard" id="f_idCard" class="easyui-textbox" style="width:70%;" />
					</td>
				</tr>
				<tr height="35px;">
					<td width="10%" height="35px;" align="right">在岗情况：</td>
					<td width="23%" align="left">
						<input type="text" name="ifJob" id="f_ifJob" class="easyui-textbox" style="width:70%;" />
					</td>
					<td width="10%" height="35px;" align="right">入职时间：</td>
					<td width="23%" align="left">
						<input class="easyui-datebox" name="beginDate" id="f_beginDate" style="width:40%;" />
						至 <input class="easyui-datebox" name="endDate" id="f_endDate"  style="width:40%;" />
					</td>
					<td width="10%" height="35px;" align="right"></td>
					<td width="23%" align="left">
					</td>
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
		
		<%-- 新增修改弹出框 begin --%>
		<div id="dlg" class="easyui-dialog" style="width: 900px; height: 600px; padding: 5px 10px;" closed="true" buttons="#dlg-buttons"> 
	       <form id="personForm" method="post" enctype="multipart/form-data"> 
	       <table width="100%"  cellpadding="0" align="center">
				<tr height="45px;">
					<td width="15%" height="35px;" align="right">人员编号：</td>
					<td width="15%" align="left">
						<input type="text"  name="personNo" id="personNo" class="easyui-textbox" />
					</td>
					<td width="15%" height="35px;" align="right">姓名：</td>
					<td width="15%" align="left">
						<input type="text" name="name" id="name" class="easyui-textbox" required="true"/>
					</td>
 					<td width="60%" align="center" rowspan="4" colspan="2"> 
                       <div >
				    <img id="headPic" src="${ctx }/resource/img/touxiang.jpg"  width="200px" height="180px" style="padding: 5px;cursor: hand;" title="点击添加照片" >
				    <input id="image" name="image"  type="file" style="display: none" accept="image/jpeg,image/jpg,image/png" onchange="fileupload()"/>
					</div>
					</td> 
				</tr>
				  <tr height="45px;">
					<td width="15%" align="right">性别：</td>
					<td width="15%" align="left">
						<input type="text" name="sex" id="sex" class="easyui-textbox"  />
					</td>
					<td width="15%" align="right">身份证号：</td>
					<td width="15%" align="left">
					    <input type="text" name="idCard" id="idCard" class="easyui-textbox"  required="true"/>
					</td>
				</tr>
				<tr height="45px;">
					<td width="15%" align="right">名族：</td>
					<td width="15%" align="left">
						<input type="text" name="volk" id="volk" class="easyui-textbox"  />
					</td>
					<td width="15%" align="right">籍贯：</td>
					<td width="15%" align="left">
						<input type="text" name="nativePlace" id="nativePlace" class="easyui-textbox" />
					</td>
				</tr>
				<tr height="45px;">
				
				    <td width="15%" align="right" >出生日期：</td>
					<td width="15%" align="left">
						<input class="easyui-datebox" name="birthDateStr" id="birthDateStr" />
					</td>
					<td width="15%" align="right">电话号码：</td>
					<td width="15%" align="left">
						<input type="text" name="phoneNo" id="phoneNo" class="easyui-textbox"  required="true"/>
					</td>
				
				</tr>
				<tr height="45px;">
				    <td width="15%" align="right">工资：</td>
					<td width="15%" align="left">
						<input type="text" name="paymentStr" id="paymentStr" class="easyui-textbox" required="true"/>
					</td>
					<td width="15%" align="right">其他待遇：</td>
					<td width="15%" align="left">
						<input type="text" name="otherPayStr" id="otherPayStr" class="easyui-textbox"  />
					</td>
					<td width="15%" align="right" >入职时间：</td>
					<td width="25%" align="left">
						<input class="easyui-datebox" name="entryDateStr" id="entryDateStr"  />
					</td>
				</tr>
				<tr height="45px;">
					<td width="15%" align="right" >所属部门：</td>
					<td width="15%" align="left">
						<input class="easyui-combobox" id="dept" name="dept" data-options="valueField:'id', textField:'text',required:true"  />
					</td>
					<td width="15%" align="right" >在岗情况：</td>
					<td width="15%" align="left">
						<input type="text" name="ifJob" id="ifJob" class="easyui-textbox" />
					</td>
					<td></td>
					<td></td>
				</tr>
				
<!-- 				<tr id="inputCB1" > -->
<!-- 					<td><label> 横版图片 </label></td> -->
<!-- 					<td colspan="5"><input multiple style="width:300px" id="image1" name="image1" class="easyui-filebox" data-options='onChange:change_photo'></td> -->
<!-- 				</tr> -->
<!-- 				<tr id="inputCB4" > -->
<!-- 					<td><label>图片预览</label></td> -->
<!-- 					<td colspan="5"> <div id="Imgdiv"><img id="Img" width="200px" height="200px"/></div></td> -->
<!-- 				</tr> -->
					
					
				<tr>
					<td width="15%"  align="right">备注：</td>
					<td align="left" colspan="5" width="45%" >
						<input class="easyui-textbox" data-options="multiline:true"  style="width:70%;height:80px"  id="remark" name="remark">
					</td>
				</tr>  
			</table>
 	       <input type="hidden" name="id" id="id" />  
 	       <input type="hidden" name="photoId" id="photoId" />   
	       </form> 
	       <div id="dlg-buttons"> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="savePerson()" iconcls="icon-save">保存</a> 
		        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close')" iconcls="icon-cancel">取消</a> 
		    </div> 
	   </div>
	   <%-- 新增修改弹出框 end --%>

	   
	
	<script type="text/javascript">
		var url;
		$(function () {
			var datagrid; //定义全局变量datagrid
			var editRow = undefined; //定义全局变量：当前编辑的行
			datagrid = $("#dataGrid").datagrid({
			title : "员工信息列表",
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
				title : '姓名',
				width : '12%',
				align : 'center'
			}, {
				field : 'sex',
				title : '性别',
				width : '8%',
				align : 'center'
			}, {
				field : 'dept',
				title : '部门',
				width : '10%',
				align : 'center'
			}, 
			{
				field : 'idCard',
				title : '身份证号',
				width : '15%',
				align : 'center'
			} ,
			{
				field : 'ifJob',
				title : '在岗情况',
				width : '9%',
				align : 'center'
			} ,
			 {
				field : 'nativePlace',
				title : '籍贯',
				width : '18%',
				align : 'center'
			},
			 {
				field : 'phoneNo',
				title : '电话号码',
				width : '12%',
				align : 'center'
			},
			 {
				field : 'entryDateStr',
				title : '入职时间',
				width : '15%',
				align : 'center'
			} ] ],
			   toolbar:[              //工具条  
                {text:"增加",iconCls:"icon-add",handler:function(){//回调函数  
                   $("#dlg").dialog("open").dialog('setTitle', '员工信息新增');
                   $("#personForm").form('clear');
                   $("#headPic").attr("src", "${ctx }/resource/img/touxiang.jpg") ;
                    url = "${ctx}/person/addPerson";
                    getCustomerList("");
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
                }}  
                /*,{text:"修改",iconCls:"icon-edit",handler:function(){  
                    var rows=datagrid.datagrid('getSelections');
                    if(rows.length<=0){  
                        $.messager.alert('提示','请选择要修改的行','error');  
                    }else if(rows.length>1){  
                        $.messager.alert('提示','只能选择一条数据进行修改','error');  
                    }else if(rows.length==1){  
		                $("#dlg").dialog("open").dialog('setTitle', '用户信息修改');
		                $("#personForm").form('clear');
		                //判断是否后台加载照片
		                if(rows[0].photoId == "" || rows[0].photoId == null){
		                	$("#headPic").attr("src", "${ctx}/resource/img/touxiang.jpg");
		                }else{
		                	$("#headPic").attr("src", "${ctx}/person/getPhoto?id="+rows[0].photoId);
		                }
		                $("#personForm").form("load", rows[0]);
		                url = "${ctx}/person/editPerson";
		                getCustomerList(rows[0].type);
                    }  
                }}*/
                ], 
                onDblClickRow: function (index, rows) {
                	$("#dlg").dialog("open").dialog('setTitle', '用户信息修改');
	                $("#personForm").form('clear');
	                //判断是否后台加载照片
	                if(rows.photoId == "" || rows.photoId == null){
	                	$("#headPic").attr("src", "${ctx}/resource/img/touxiang.jpg");
	                }else{
	                	$("#headPic").attr("src", "${ctx}/person/getPhoto?id="+rows.photoId);
	                }
	                $("#personForm").form("load", rows);
	                url = "${ctx}/person/editPerson";
	                getCustomerList(rows.type);
         		}
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
		
		
		//头像预览
	    $("#headPic").click(function () {
	        $("#image").click(); //隐藏了input:file样式后，点击头像就可以本地上传
	    }); 
		
	});
	
	//上传图片预览
	function fileupload(){
		var f =document.getElementById("image");
		var objUrl = getImgUrl(f.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
           if (objUrl) {
            $("#headPic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
               $("#filePath").val(getPath(f));
           }
	}
	
		//查询条件  
		function queryFun() {
			var queryParameter = $('#dataGrid').datagrid("options").queryParams;
			queryParameter.name = $("#f_name").val();
			queryParameter.dept = $("#f_dept").val();
			queryParameter.idCard = $("#f_idCard").val();
			queryParameter.ifJob = $("#f_ifJob").val();
			queryParameter.beginDate = $("#f_beginDate").datebox("getValue");
			queryParameter.endDate = $("#f_endDate").datebox("getValue");
			$("#dataGrid").datagrid("reload");
		}
		
		//保存
		function savePerson() {
            $("#personForm").form("submit", {
                url: url,
                onsubmit: function () {
                    return $(this).form("validate");
                },
                success: function (result) {
                var r= eval('(' + result + ')');
                    if (r.success == "1") {
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
	
	 //获取预览图片路径
     function getImgUrl(file) {
         var url = null;
         if (window.createObjectURL != undefined) {
             url = window.createObjectURL(file)
         } else if (window.URL != undefined) {
             url = window.URL.createObjectURL(file)
         } else if (window.webkitURL != undefined) {
             url = window.webkitURL.createObjectURL(file)
         }
         return url;
     };
     
        //获取部门列表
         function getCustomerList(type){
        	$.ajax({  
             	 type : "get", 
                 url : "${ctx }/person/getPersonTypeList",  
                 data : {"type":type},  
                 dataType : "json",  
                 success : function(data) {
                 	 $("#dept").combobox("loadData",data);
                 }  
             });  
        }
        
     //获取上传图片本地路径
     function getPath(obj){    
		  if(obj)    
		    {    
		   if (window.navigator.userAgent.indexOf("MSIE")>=1){    
		        obj.select();    
		     	return document.selection.createRange().text;    
		      } else if(window.navigator.userAgent.indexOf("Firefox")>=1){    
			      if(obj.files) { 
			        return obj.files.item(0).getAsDataURL();    
			        }    
			      return obj.value;    
		        }    
		      return obj.value;    
		    }    
		}    

function change_photo(){
        PreviewImage($("input[name='image']")[0], 'Img', 'Imgdiv');
    }

function PreviewImage(fileObj,imgPreviewId,divPreviewId){  
	    var allowExtention=".jpg,.bmp,.gif,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;  
	    var extention=fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();              
	    var browserVersion= window.navigator.userAgent.toUpperCase();  
	    if(allowExtention.indexOf(extention)>-1){   
	        if(fileObj.files){//HTML5实现预览，兼容chrome、火狐7+等  
	            if(window.FileReader){  
	                var reader = new FileReader();   
	                reader.onload = function(e){  
	                    document.getElementById(imgPreviewId).setAttribute("src",e.target.result);  
	                }    
	                reader.readAsDataURL(fileObj.files[0]);  
	            }else if(browserVersion.indexOf("SAFARI")>-1){  
	                alert("不支持Safari6.0以下浏览器的图片预览!");  
	            }  
	        }else if (browserVersion.indexOf("MSIE")>-1){  
	            if(browserVersion.indexOf("MSIE 6")>-1){//ie6  
	                document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);  
	            }else{//ie[7-9]  
	                fileObj.select();  
	                if(browserVersion.indexOf("MSIE 9")>-1)  
	                    fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问  
	                var newPreview =document.getElementById(divPreviewId+"New");  
	                if(newPreview==null){  
	                    newPreview =document.createElement("div");  
	                    newPreview.setAttribute("id",divPreviewId+"New");  
	                    newPreview.style.width = document.getElementById(imgPreviewId).width+"px";  
	                    newPreview.style.height = document.getElementById(imgPreviewId).height+"px";  
	                    newPreview.style.border="solid 1px #d2e2e2";  
	                }  
	                newPreview.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";                              
	                var tempDivPreview=document.getElementById(divPreviewId);  
	                tempDivPreview.parentNode.insertBefore(newPreview,tempDivPreview);  
	                tempDivPreview.style.display="none";                      
	            }  
	        }else if(browserVersion.indexOf("FIREFOX")>-1){//firefox  
	            var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);  
	            if(firefoxVersion<7){//firefox7以下版本  
	                document.getElementById(imgPreviewId).setAttribute("src",fileObj.files[0].getAsDataURL());  
	            }else{//firefox7.0+                      
	                document.getElementById(imgPreviewId).setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));  
	            }  
	        }else{  
	            document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);  
	        }           
	    }else{  
	        alert("仅支持"+allowExtention+"为后缀名的文件!");  
	        fileObj.value="";//清空选中文件  
	        if(browserVersion.indexOf("MSIE")>-1){                          
	            fileObj.select();  
	            document.selection.clear();  
	        }                  
	        fileObj.outerHTML=fileObj.outerHTML;  
	    }  
	}

</script>
</body>
</html>
