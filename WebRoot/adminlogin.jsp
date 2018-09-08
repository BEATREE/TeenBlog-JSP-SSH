<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>管理员登陆页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/styles.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<link rel="stylesheet" type="text/css"
	href="HubSpot-messenger/build/css/messenger.css">
<link rel="stylesheet" type="text/css"
	href="HubSpot-messenger/build/css/messenger-theme-future.css">
<script charset="utf-8" type="text/javascript"
	src="HubSpot-messenger/build/js/messenger.min.js"></script>
</head>

<body>
	<!-- 头部引入 -->
	<jsp:include page="header.html"></jsp:include>
	<!-- 头部引入 结束-->

	<div id="mainbody">
		<div id="centerPart">
			<s:form action="loginAdmin" method="post">
				<div id="loginPart">
					<h2>管理员登陆</h2>
					<span>邮箱：</span><input  name="adminEmail" type="email" id="inputfield"  /><br /> 
					<span>密码：</span><input name="adminPassword" type="password" id="inputfield" /><br />
					<div class="submitarea">
						
						<button id="submitButton" type="submit">登陆</button>
					</div>
				</div>
			</s:form>
			<span> <s:fielderror></s:fielderror> </span>
		</div>
	</div>

	<!-- 底部引入 -->
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- 代码结束 -->
</body>
</html>
