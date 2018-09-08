<%@page import="xzy.beans.User"%>
<%@page import="xzy.dao.UserDAO"%>
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
    
    <title>用户资料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">

  </head>
  
  <body>
  		<%
  			String uid = request.getParameter("uId");
  			Long luid = Long.parseLong(uid);
  			System.out.println(luid);
  			UserDAO editorPd = new UserDAO();
  			User editorUser = editorPd.findById(luid);
  			session.setAttribute("editorUser", editorUser);
  		%>
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		 <div id="main" class="uploadImage">
    			<h1>个人资料</h1>
				<form action="aupdateProfile" method="post" enctype="multipart/form-data">
					<div id="rightParts">
						<div class="labels">
							<h3>TA的唯一标识</h3>
							<input class="inputfield" readonly="true" name="userId" value="<s:property value="#session.editorUser.getUserId()"/>"/>
							<br>
							<h3>TA的头像</h3>
							<img src="<s:property value="#session.editorUser.getUserHead()"/>" style="width: 64px;">
							<s:file name="userHead" type="file" value="点击选择头像"/>
							<br>
							<h3>TA的昵称</h3>
							<input class="inputfield" name="userName" value="<s:property value="#session.editorUser.getUserName()"/>"/>
							<br>
							<h3>TA的邮箱</h3>
							<input class="inputfield" name="userEmail" value="<s:property value="#session.editorUser.getUserEmail()"/>"/>
							<br>
							<h3>TA的密码</h3>
							<input class="inputfield" name="userPassword" value="<s:property value="#session.editorUser.getUserPassword()"/>"/>
							<br>
							<h3>进行修改</h3>
							<button  class="submitButton" type="submit" >点击修改</button>
						</div>
					</div>
				</form>
    		</div>
    	</div>
  </body>
</html>
