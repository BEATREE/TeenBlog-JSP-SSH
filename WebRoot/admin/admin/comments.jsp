<%@page import="xzy.beans.Comments"%>
<%@page import="xzy.dao.CommentsDAO"%>
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
    
    <title>所有评论</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	

  </head>
  
  <body>
  		<%
  			CommentsDAO commentsDAO = new CommentsDAO();
  			//获取图片列表
  			List<Comments> allCommList = commentsDAO.findAll();
  			session.removeAttribute("allCommList");
  			session.setAttribute("allCommList", allCommList);
  		 %>
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		<h2>评论信息 </h2>
    		
    		全部( <s:property value="#session.allCommList.size()"/> )
    		<br />
    		<!-- 内容信息部分开始 -->
    		<table class="postsTable">
    		<thead>
    		<!-- 表格开始 -->
    			<tr>
    				<th>评论ID</th><th>游客昵称</th><th>相关文章</th><th>评论内容</th><th>发布日期</th><th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<s:iterator value="#session.allCommList" id="cpid" status="num">
    			<tr <s:if test="#num.odd">class="changetr"</s:if>>
    				<th><s:property value="#cpid.getCommentId()"/></th>
    				<th><s:property value="#cpid.getUserName()"/></th>
    				
    				<th><a href="post.jsp?pId=<s:property value="#cpid.getPosts().getPostId()"/>" target="_blank">
    				<s:property value="#cpid.getPosts().getPostTitle()"/></a></th>
    				
    				<th><s:property value="#cpid.getCommentContent()"/></th>
    				<th><s:date name="#cpid.getCommentDate()" format="yyyy-MM-dd"/></th>
    				<th> <a href="deleteComment.action?cId=<s:property value="#cpid.getCommentId()"/>">删除</a> </th>
    			</tr>
    			</s:iterator>
    		</tbody>
    		</table>
    	</div>
  </body>
</html>
