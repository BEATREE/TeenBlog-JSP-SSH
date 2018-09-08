<%@page import="xzy.dao.ImageDAO"%>
<%@page import="xzy.beans.Posts"%>
<%@page import="xzy.dao.PostsDAO"%>
<%@page import="xzy.beans.User"%>
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
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 		
	    <header>
		
		<!-- 定义头部logo -->
		<img alt="TeenBlog's logo" title="" src="images/logo.png" class="logo">
		<!-- 定义顶部小行 -->
		<a href="index.jsp" class="goBack">TeenBlog</a>
		<a style="margin-left: 12px;" href="logout.jsp" class="goBack">退出账号</a>
		<!-- 右侧工具栏 -->
		<div id="userpart">
		
		<a href=""> <span class="username"> <s:property value="#session.currentUser.UserName"/> </span> </a>
		<a href="admin/user/"><img alt="用户头像" src="<s:property value="#session.currentUser.getUserHead()"/>" class="userhead"></a>
		</div>
		</header>
  </body>
</html>
