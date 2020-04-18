<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<style type="text/css">
img {
	width: 1200px;
	position: absolute;
	left:18%;
	height: 99%;
	border:1px  #000000 ;
}</style>
</head>
<body style="background-color: #000000;margin: 1;padding: 1;">
<div><img src="${ctx}/file/previewImg?id=${id}"></div>


</body>

</html>