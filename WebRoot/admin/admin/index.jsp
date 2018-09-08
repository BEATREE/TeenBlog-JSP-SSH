<%@page import="xzy.beans.Image"%>
<%@page import="xzy.dao.ImageDAO"%>
<%@page import="xzy.dao.PostsDAO"%>
<%@page import="xzy.beans.Posts"%>
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
    
    <title>后台主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	 
	
  </head>
  
  <body>
  		<%
  			PostsDAO postsDAO = new PostsDAO();
  			ImageDAO imageDAO = new ImageDAO();
  			//获取文章列表
  			List<Posts> allPostList = postsDAO.findAll();
  			session.setAttribute("allPostList", allPostList);
  			//获取图片列表
  			List<Image> allImageList = imageDAO.findAll();
  			session.setAttribute("allImageList", allImageList);
  		%>
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<div id="container">
    		<h2 style="margin-left: 6%;">管理员中心</h2>
    		<div id="welcome-panel">
	    			<div class="welcome-panel-column">
						<h2>欢迎 <span style="color:red;"> <s:property value="#session.currentAdmin.getUserName()"/></span>，您来个TeenBlog管理员后台</h2>
						<p class="about-description">我们准备了几个链接供您开始：</p>
					</div>
					<div class="welcome-panel-column">
						<h3>接下来</h3>
						<ul>
							<li><a href="admin/admin/posts.jsp">查看文章信息</a></li>
							<li><a href="admin/admin/picture.jsp">查看图片信息</a></li>
							<li><a href="admin/admin/profile.jsp">查看用户信息</a></li>
						</ul>
					</div>
    		</div>
    		<div id="posts">
    		<h2>文章列表</h2>
    			<s:if test="#session.allPostList.size()==0">
    				<p>目前还没有文章</p>
    			</s:if>
    			<s:iterator value="#session.allPostList" id="allPosts">
    				<p><s:property value="#allPosts.getPostTitle()"/>
    				 <a href="post.jsp?pId=<s:property value="#allPosts.getPostId()"/>">点击阅读</a></p>
    			</s:iterator>
    		</div>
    		<div id="images">
    		<h2>图片列表</h2>
    		
    			<s:if test="#session.allImageList.size()==0">
    				<p>目前列表没有图片</p>
    			</s:if>
    			<s:iterator value="#session.allImageList" id="allImages" status="num">
    				<s:if test="#num.count<7">
    					<img alt="<s:property value="#allImages.getImageName()"/>" src="<s:property value="#allImages.getImageSrc()"/>">
    				</s:if>
    			</s:iterator>
    			<br>
    			<a style="font-size: 18px;margin-left:37%; text-decoration: none;" href="admin/admin/picture.jsp">......</a>
    		</div>
    	</div>
  </body>
</html>
