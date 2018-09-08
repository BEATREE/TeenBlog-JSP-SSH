<%@page import="xzy.dao.CommentsDAO"%>
<%@page import="xzy.beans.Comments"%>
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
		<%
			String pid = request.getParameter("pId");
			long Pid = Long.parseLong(pid);
			if(Pid==1){response.setHeader("refresh", "0;message.jsp");}
			
			PostsDAO pdao = new PostsDAO();
			Posts currentPost =pdao.findById(Pid);
			session.setAttribute("currentPost", currentPost);
			 
			//获取当前评论列表
			session.removeAttribute("currentCommentsList");
			CommentsDAO cdao = new CommentsDAO();
			List<Comments> currentComments = cdao.findByPost(currentPost);
			session.setAttribute("currentCommentsList", currentComments);
			
			//清除旧数据
			session.removeAttribute("postList");
			session.removeAttribute("pictureList");
			//填入新数据
			//获取文章列表
			PostsDAO pd = new PostsDAO();
			List<Posts> postList = pd.findAll();
			//获取热门文章
			List<Posts> hotPosts = pd.findHotPosts();
			//获取图片列表
			ImageDAO idao = new ImageDAO();
			List<Image> pictureList = idao.findAll();
			session.setAttribute("postList", postList);
			session.setAttribute("pictureList", pictureList);
			session.setAttribute("hotPosts", hotPosts);
			//文章列表获取完毕
		 %>
  <head>
  		
    <base href="<%=basePath%>">
    
    <title> <s:property value="#session.currentPost.getPostTitle()"/> </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link href="css/styles.css" rel="stylesheet" type="text/css">
	<link href="css/post.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
  </head>
  
  <body>
  		
<!-- 头部引入 -->
<jsp:include page="header.html"></jsp:include>
<!-- 头部引入 结束-->
	
 	<div id="mainbody">
 		<!-- 文章内容部分读取开始 -->
 		<div id="blogs">
 		<s:debug></s:debug>
   			<div id="posttext">
   				<h2 class="p_nav">
   				<a href="#"><s:property value="#session.currentPost.getPostLabel().split(',')[0]"/></a></h2>
   				<h1 class="p_title"> <s:property value="#session.currentPost.getPostTitle()"/> </h1>
   				<p class="box">发布时间：<s:date name="#session.currentPost.getPostDate()" format="yyyy-MM-dd"/>
   					<span>作者： <s:property value="#session.currentPost.getUser().getUserName()"/></span></p>
   				<div class="text">
   					<s:property value="#session.currentPost.getPostContent()" escape="false"/>
			     </div>
	   			<!-- 文章内容部分结束 -->
	   			<!-- 评论提交 -->
	   			<div class="comment">
	   				<h2>评论区</h2>
	   				<s:form action="submitComment" method="post">
	   					<input name="postId" type="hidden" value='<s:property value="#session.currentPost.getPostId()"/>'>
	   					您的昵称:<input type="text" placeholder="您的昵称" name="nickName" required="required">
						<s:textarea name="commentContent" rows="5" cols="60" placeholder="好不容易走一遭，留个言吧！">
						
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
	   			<!-- 显示相关文章 -->
	   			<div class="likeposts">
			        <h2>相关文章</h2>
			        <ul>
			        <s:iterator value="#session.postList" id="pid" status="num">
			        <s:if test="#num<6"></s:if>
			          <li><a href='post.jsp?pId=<s:property value="#pid.getPostId()"/>' title="<s:property value="#pid.getPostTiltle()"/>">
			         	<s:property value="#pid.getPostTitle()"/>
			          </a></li>
			        </s:iterator>
			        </ul>
			     </div>
   			</div>
   		
   		<!-- 文章内容部分读取结束-->
   		<!-- 侧栏容部分读取结束-->
		<aside>		
		      <!-- 搜索框开始 -->
		      <div class="search">
		        <form class="searchform" method="get" action="findPost">
		          <input type="text" name="postTitle" value="Search" onfocus="this.value=''" onblur="this.value='Search'">
		        </form>
		      </div>
		      <!-- 搜索框结束 -->
		      
		      <!-- 热门文章开始 -->
		      <div class="clicks">
		        <h2>热门文章</h2>
		        <ol>
		        <s:iterator value="#session.hotPosts" id="post" status="num">
		        <s:if test="#num.count<6">
		          <li><span style="color:#DCDCDC;"><s:property value="#post.getPostLabel().split(',')[0]"/></span>
		          <a href='post.jsp?pId=<s:property value="#post.getPostId()"/>' target="_blank"> <s:property value="#post.getPostTitle()"/> </a>
		          </li>
		        </s:if>
		        </s:iterator>
		       </ol>
		      </div>
		      <!-- 热门文章结束 -->
		      <!-- 图片壁纸开始 -->
		      <div class="toppic">
		        <h2>图片壁纸</h2>
		        <ul>
		        <s:iterator value="#session.pictureList" id="picture" status="num">
		        <s:if test="#num.count<6">
		          <li><a href='<s:property value="#picture.getImageSrc()"/>' target="_blank"><img src='<s:property value="#picture.getImageSrc()"/>'>
		          		<s:property value="#picture.getImageDesc()"/>
		            </a></li>
		         </s:if>
		         </s:iterator>
		        </ul>
		      </div>
		      <!-- 图片壁纸结束 -->
		      <div class="viny">
		        <dl>
		          <dt class="art"><img src="images/artwork.png" alt="专辑"></dt>
		          <dd class="icon-song"><span></span>南方姑娘</dd>
		          <dd class="icon-artist"><span></span>歌手：赵雷</dd>
		          <dd class="icon-album"><span></span>所属专辑：《吉姆餐厅》</dd>
		          <dd class="icon-like"><span></span><a href="/">喜欢</a></dd>
		          <dd class="music">
		            <audio src="images/nf.mp3" loop="true" controls ></audio>
		          </dd>
		          <!--也可以添加loop属性 音频加载到末尾时，会重新播放-->
		        </dl>
		      </div>
		    </aside>
	    </div>
   	</div>
<!-- 页脚引入开始 -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- 页脚引入结束 -->
  </body>
</html>
