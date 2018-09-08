<%@page import="xzy.beans.Posts"%>
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
    
    <title>留言板</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link href="css/post.css" rel="stylesheet" type="text/css">

  </head>
  
  <body>	
  		<%
  			Posts cPost = new Posts();
  			cPost.setPostId(1l);
  			//获取当前评论列表
			session.removeAttribute("currentCommentsList");
			CommentsDAO cdao = new CommentsDAO();
			List<Comments> currentComments = cdao.findByPost(cPost);
			session.setAttribute("currentCommentsList", currentComments);
  		%>
  <!-- 头部引入 -->
<jsp:include page="header.html"></jsp:include>
<!-- 头部引入 结束-->
    	<div id="mainbody" >
	 		<!-- 文章内容部分读取开始 -->
	 		<div id="blogs" >
	 		<s:debug></s:debug>
	   			<div id="posttext2">
	   				<h1 class="p_title" style="text-align: center;font-family: '楷体';">留言板</h1>
	   				<div class="text">
	   					<img alt="" src="images/huihui.jpg" >
	   					<h2 class="haslefth2" align="justify">关于本站</h2>
	   					<img alt="groot" src="images/groot.gif" style="float: left;width: 100px;">
	   					<p>
	   						 这个网站呢，是一个文章博客，用户通过注册登陆可以发表文章，上传图片，其他功能后续会进行完善
	   					</p>
	   					<p>希望同学们和我呢，能够在这个学期中，取得很好的成绩，学业有成！！</p>
	   					<p>下面呢，就该你们表演了，尽情留言吧</p>
				     </div>
		   			<!-- 文章内容部分结束 -->
		   			<!-- 评论提交 -->
		   			<div class="comment">
		   				<h2>评论区</h2>
		   				<s:form action="submitComment" method="post">
		   					<input name="postId" type="hidden" value="1"/>
		   					您的昵称:<input type="text" placeholder="您的昵称" name="nickName" required="required">
							<s:textarea name="commentContent" rows="5" cols="120" placeholder="好不容易走一遭，留个言吧！">
							
							</s:textarea>
							
							<s:submit id="submitButton" value="提交评论" />
							
		   				</s:form>
		   				<br>
		   				<s:iterator value="#session.currentCommentsList" id="cid">
		   				<div id="commentArea">
		   				用户名称：<span class="nickName"><s:property value="#cid.getUserName()"/></span>
		   				&nbsp;&nbsp;&nbsp;&nbsp;
		   				评论日期：<s:date name="#cid.getCommentDate()" format="yyyy-MM-dd"/>
		   				<br>
		   				<s:property value="#cid.getCommentContent()"/>
		   				</div>
		   				</s:iterator>
		   			</div>
	    		</div>
	    	</div>
    	</div>
    	<!--mainbody end-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- 代码结束 -->
  </body>
</html>
