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
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 返回顶部调用 begin -->
	<link rel="stylesheet" type="text/css" href="css/lrtk.css">
	
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/js.js"></script>
	<!-- 返回顶部调用 end-->

  </head>
  
  <body>
  		<%
  			CommentsDAO cd = new CommentsDAO();
  			List<Comments> commList = cd.findAll();
  		 %>
    	  <!-- 页脚开始 -->
    	<footer>
		  <div class="footer-mid">
		    <div class="links">
		      <h2>友情链接</h2>
		      <ul>
		        <li><a href="http://www.teenshare.club">TeenShare梯云分享</a></li>
		        <li><a href="http://teenshare.club/music">TeenMusic梯云音乐</a></li>
		        <li><a href="">TeenBlog梯云文博</a></li>
		      </ul>
		    </div>
		    <div class="visitors">
		      <h2>最新评论</h2>
		      <%int num = 0;
		      for(Comments comments:commList) {if(num<2){num++;%>
		      <dl>
		        <dt><img src="<%=comments.getPosts().getPostImage() %>"  alt="文章图片">
		        <dt>
		        <dd><%=comments.getUserName()%></dd>
		        <dd>在 <a href="post.jsp?pId=<%=comments.getPosts().getPostId() %>" class="title">
		        	<%=comments.getPosts().getPostTitle() %></a>中评论：</dd>
		        <dd><%=comments.getCommentContent() %></dd>
		      </dl>
		      <%}}%>
		    </div>
		    <section class="flickr">
		      <h2>壁纸</h2>
		      <ul>
		        <li><a href="picture.jsp"><img src="images/01.jpg"></a></li>
		        <li><a href="picture.jsp"><img src="images/02.jpg"></a></li>
		        <li><a href="picture.jsp"><img src="images/03.jpg"></a></li>
		        <li><a href="picture.jsp"><img src="images/04.jpg"></a></li>
		        
		      </ul>
		    </section>
		  </div>
		  <div class="footer-bottom">
		    <p>Copyright 2018 Worked by <a href="">201677I0944 肖尊严</a> </p>
		    <p><a href="adminlogin.jsp">管理员登陆</a></p>
		    
		  </div>
		</footer>
		 <!-- 页脚结束-->
		 <!-- jQuery仿腾讯回顶部和建议 代码开始 -->
		<div id="tbox"> <a id="gotop" href="javascript:void(0)"></a> </div>
		<!-- 代码结束 -->
  </body>
</html>