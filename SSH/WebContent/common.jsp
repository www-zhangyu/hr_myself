
<!DOCTYPE html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	String framePath = basePath + "/frame";
	request.setAttribute("path", path); 
	request.setAttribute("basePath", basePath); 
	request.setAttribute("framePath", framePath); 
	%>

<html>
<head>
<script type="text/javascript" src="${path}/js/jquery-2.0.3.js"></script>

<script type="text/javascript" src="${path}/js/bootstrap.min.js"></script>

<script type="text/javascript" src="${path}/js/bootstrapValidator.min.js"></script>

<script type="text/javascript" src="${path}/public/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript" src="${path}/js/tool.js"></script>

<link href="${path}/css/bootstrap.min.css" rel="stylesheet">
<link href="${path}/css/bootstrapValidator.min.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">

<meta  charset=utf-8">
<title>Insert title here</title>
</head>

</html>