<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人资料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	

  </head>
  
  <body>
  		
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		 <div id="main" class="uploadImage">
    			<h1>个人资料</h1>
				<form action="updateAdmin" method="post">
					<div id="rightParts">
						<div class="labels">
						<input type="hidden" name="adminId" value='<s:property value="#session.currentAdmin.getUserId()"/>'/>
							<h3>您的的昵称</h3>
							<input class="inputfield" name="adminName" value='<s:property value="#session.currentAdmin.getUserName()"/>'/>
							<br>
							<h3>您的的邮箱</h3>
							<input class="inputfield" name="adminEmail" value='<s:property value="#session.currentAdmin.getUserEmail()"/>'/>
							<br>
							<h3>您的的密码</h3>
							<input class="inputfield" name="adminPassword" value='<s:property value="#session.currentAdmin.getUserPassword()"/>'/> 
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
