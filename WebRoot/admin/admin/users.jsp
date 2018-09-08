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
    
    <title>用户信息页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	

  </head>
  
  <body>
    	<%
  			UserDAO usersDAO = new UserDAO();
  			//获取图片列表
  			List<User> allUserList = usersDAO.findAll();
  			session.removeAttribute("allUserList");
  			session.setAttribute("allUserList", allUserList);
  		 %>
  		
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		<h2>用户信息 </h2>
    		<div class="searchPlace">
	    		<form action="asearchProfile" method="post">
	    			<input name="userName" class="inputfield" type="text" placeholder="请输入用户昵称" />
	    			<button type="submit" class="jumpButton">点击查找</button>
	    		</form>
    		</div>
    		全部( <s:property value="#session.allUserList.size()"/> )
    		<br />
    		<!-- 内容信息部分开始 -->
    		<table class="postsTable">
    		<thead>
    		<!-- 表格开始 -->
    			<tr>
    				<th>用户ID</th><th>用户名称</th><th>Email</th><th>登陆密码</th><th>文章数目</th><th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<s:iterator value="#session.allUserList" id="cpid" status="num">
    			<tr <s:if test="#num.odd">class="changetr"</s:if>>
    				<th><s:property value="#cpid.getUserId()"/></th>
    				<th><s:property value="#cpid.getUserName()"/></th>
    				<th><s:property value="#cpid.getUserEmail()"/></th>
    				<th><s:property value="#cpid.getUserPassword()"/></th>
    				<th><s:property value="#cpid.getPostNumber()"/></th>
    				<th><a href="admin/admin/userProfile.jsp?uId=<s:property value="#cpid.getUserId()"/>">编辑</a> 
    				<a href="deleteProfile.action?uId=<s:property value="#cpid.getUserId()"/>">删除</a> </th>
    			</tr>
    			</s:iterator>
    		</tbody>
    		</table>
    	</div>
  </body>
</html>
