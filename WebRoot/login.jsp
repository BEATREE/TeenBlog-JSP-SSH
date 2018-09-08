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
    
    <title>登陆页面</title>
    
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
			<s:form action="doLogin" method="post">
				<div id="loginPart">
					<h2>登陆表单</h2>
					<span>邮箱：</span><input name="useremail" type="email" id="inputfield" /><br />
					<span>密码：</span><input name="userpassword" type="password" id="inputfield" /><br />
					<div class="submitarea">
					<a href="register.jsp">注册账号</a>
					<button id="submitButton" type="submit" >登陆</button>
					</div>
					<span><s:fielderror></s:fielderror> </span>
				</div>
			</s:form>
			
			</div>
		</div>
		
		<!-- 底部引入 -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- 代码结束 -->
  </body>
</html>
