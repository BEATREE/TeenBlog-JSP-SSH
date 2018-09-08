<%@page import="xzy.beans.Image"%>
<%@page import="xzy.dao.ImageDAO"%>
<%@page import="xzy.beans.User"%>
<%@page import="xzy.beans.Posts"%>
<%@page import="xzy.dao.PostsDAO"%>
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
  			User user = (User)session.getAttribute("currentUser");
  			PostsDAO userPd = new PostsDAO();
  			ImageDAO userImd = new ImageDAO();
  			//获取文章列表
  			List<Posts> currentPostList = userPd.findByUser(user);
  			session.setAttribute("currentPostList", currentPostList);
  			//获取图片列表
  			List<Image> currentImageList = userImd.findByUser(user);
  			session.setAttribute("currentImageList", currentImageList);
  		%>
  		
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<div id="container">
    		<h2 style="margin-left: 6%;">个人中心</h2>
    		<div id="welcome-panel">
	    			<div class="welcome-panel-column">
						<h2>欢迎<span style="color:red;"> <s:property value="#session.currentUser.getUserName()"/> </span>，您使用TeenBlog！</h2>
						<p class="about-description">我们准备了几个链接供您开始：</p>
					</div>
					<div class="welcome-panel-column">
						<h3>接下来</h3>
						<ul>
							<li><a href="admin/user/write.jsp">撰写您的博文</a></li>
							<li><a href="admin/user/uploadPicture.jsp">前去上传图片</a></li>
							<li><a href="admin/user/profile.jsp">查看个人资料</a></li>
						</ul>
					</div>
    		</div>
    		<div id="posts">
    		<h2>文章列表</h2>
    			<s:if test="#session.currentPostList.size()==0">
    				<p>您暂时还没有发表文章，<a href="admin/user/write.jsp">前去创作</a></p>
    			</s:if>
    			<s:iterator value="#session.currentPostList" id="currentPosts">
    				<p><s:property value="#currentPosts.getPostTitle()"/>
    				 <a href="post.jsp?pId=<s:property value="#currentPosts.getPostId()"/>">点击阅读</a></p>
    			</s:iterator>
    		</div>
    		<div id="images">
    		<h2>图片列表</h2>
    		
    			<s:if test="#session.currentImageList.size()==0">
    				<p>您暂时还没有上传图片，<a href="admin/user/uploadPicture.jsp">前去上传</a></p>
    			</s:if>
    			<s:iterator value="#session.currentImageList" id="currentImages">
    				<img alt="<s:property value="#currentImages.getImageName()"/>" src="<s:property value="#currentImages.getImageSrc()"/>">
    			</s:iterator>
    		</div>
    	</div>
  </body>
</html>
