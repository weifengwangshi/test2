<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>EXCEL文件数据采集系统</title>
    <link href="${basePath }assets/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${basePath }assets/css/font-awesome.min.css?v=4.3.0" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="${basePath }assets/css/animate.min.css" rel="stylesheet">
    <link href="${basePath }assets/css/style.min.css?v=3.0.0" rel="stylesheet">
    <link href="${basePath }assets/js/plugins/layer/skin/layer.css" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/chosen/chosen.css" rel="stylesheet">
    <link href="${basePath }assets/script/select2/select2.css" rel="stylesheet">
    <link href="${basePath }assets/css/common.css" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/datapicker/datepicker3.css" rel="stylesheet">
    <link href="${basePath }assets/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <script type="text/javascript">   
		var basePath='${basePath}';
	</script>
  </head>
