<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">

  </head>
  		
  <body>
  		<!-- 头部引入 -->
    	<jsp:include page="header.html"></jsp:include>
    	<!-- 头部引入 结束-->
		
		<div id="mainbody" >
			<div id="centerPart">
			<s:form action="registerLogin" method="post">
				<div id="loginPart" style="height:250px;">
					<h2>注册表单</h2>
					<span>昵称：</span><input name="username" type="text" id="inputfield" />
					<span>邮箱：</span><input name="useremail" type="email" id="inputfield" />
					<small>将用于账号登陆</small><br />
					<span>密码：</span><input name="userpassword" type="password" id="inputfield" /><br />
					<div class="submitarea">
					<a href="login.jsp">返回登陆</a>
					<button id="submitButton" type="submit" >注册</button>
					</div>
				</div>
			</s:form>
			</div>
		</div>
		
		<!-- 底部引入 -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- 代码结束 -->
  </body>
</html>
