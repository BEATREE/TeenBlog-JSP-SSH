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
    
    <title>我的文章</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">

  </head>
  
  <body>
  		
  		<%
  			User user = (User)session.getAttribute("currentUser");
  			PostsDAO userPd = new PostsDAO();
  			
  			List<Posts> currentPostList = userPd.findByUser(user);
  			session.removeAttribute("currentPostList");
  			session.setAttribute("currentPostList", currentPostList);
  		%>
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    	<h2>文章 <button class="jumpButton" onclick="window.location.href='admin/user/write.jsp'" type="button">写文章</button></h2>
    		<div class="searchPlace">
	    		<form action="searchPost" method="post">
	    			<input name="postTitle" class="inputfield" type="text" placeholder="请输入文章名称" />
	    			<button type="submit" class="jumpButton">点击查找</button>
	    		</form>
    		</div>
    		全部( <s:property value="#session.currentUser.getPostNumber()"/> )
    		<br />
    		<!-- 内容信息部分开始 -->
    		<s:if test="#session.currentUser.getPostNumber()!=0">
    		<table class="postsTable">
    		<thead>
    		<!-- 表格开始 -->
    			<tr>
    				<th>文章ID</th><th>文章标题</th><th>发布日期</th><th>点赞数目</th><th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<s:iterator value="#session.currentPostList" id="cpid" status="num">
    			
    			<tr <s:if test="#num.odd">class="changetr"</s:if>>
    				<th><s:property value="#cpid.getPostId()"/></th>
    				<th><a href="post.jsp?pId=<s:property value="#cpid.getPostId()"/>" target="_blank">
    				<s:property value="#cpid.getPostTitle()"/></a></th>
    				<th><s:date name="#cpid.getPostDate()" format="yyyy-MM-dd"/></th>
    				<th><s:property value="#cpid.getPostLikes()"/></th>
    				<th><a href="admin/user/reeditor.jsp?pId=<s:property value="#cpid.getPostId()"/>">编辑</a> 
    				<a href="deletePost.action?pId=<s:property value="#cpid.getPostId()"/>">删除</a> </th>
    			</tr>
    			</s:iterator>
    		</tbody>
    		</table>
    		</s:if><s:else>
	   			<h1 align="center">还未发表文章</h1>
	   		</s:else>
    	<%-- 	<!-- 确定是否显示搜索内容 -->
    		<s:if test="#session.postResults.size()!=0">
    		<div class="searchremind">搜索结果如下</div>
    		<table class="postsTable">
    		<thead>
    		<!-- 表格开始 -->
    			<tr>
    				<th>文章ID</th><th>文章标题</th><th>发布日期</th><th>点赞数目</th><th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<s:iterator value="#session.postResults" id="cpid" status="num">
    			<tr>
    				<th><s:property value="#cpid.getPostId()"/></th>
    				<th><a href="post.jsp?pId=<s:property value="#cpid.getPostId()"/>"><s:property value="#cpid.getPostTitle()"/></a></th>
    				<th><s:date name="#cpid.getPostDate()" format="yyyy-MM-dd"/></th>
    				<th><s:property value="#cpid.getPostLikes()"/></th>
    				<th><a href="admin/user/reeditor.jsp?pId=<s:property value="#cpid.getPostId()"/>">编辑</a> 
    				<a href="deletePost.action?pId=<s:property value="#cpid.getPostId()"/>">删除</a> </th>
    			</tr>
    			</s:iterator>
    		</tbody>
    		</table>
    		</s:if> --%>
    	</div>
  </body>
</html>
