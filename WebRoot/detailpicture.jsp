<%@page import="xzy.beans.Posts"%>
<%@page import="xzy.dao.PostsDAO"%>
<%@page import="xzy.beans.Image"%>
<%@page import="xzy.dao.ImageDAO"%>
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
    
    <title>图片详情页</title>
    
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
  </head>
  
  <body>
    	<%
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
			String pid = request.getParameter("iId");
			long Pid = Long.parseLong(pid);
			
			ImageDAO cIDao = new ImageDAO();
			Image currentImage =cIDao.findById(Pid);
			session.setAttribute("currentImage", currentImage);
			
			
			
		 %>
		 <!-- 头部引入 -->
<jsp:include page="header.html"></jsp:include>
<!-- 头部引入 结束-->
 	<div id="mainbody">
 		<!-- 文章内容部分读取开始 -->
 		<div id="blogs">
 		<s:debug></s:debug>
   			<div id="posttext">
   				<h2 class="p_nav"><a href="">网站首页</a><a href="picture.jsp">壁纸详情</a></h2>
   				<h1 class="p_title"> <s:property value="#session.currentImage.getImageName()"/> </h1>
   				<p class="box">	<span>发布者： <s:property value="#session.currentImage.getUser().getUserName()"/></span></p>
   				<div class="text">
   					<img alt="<s:property value="#session.currentImage.getImageName()"/>" 
   					src="<s:property value="#session.currentImage.getImageSrc()"/>" >
   					<h2 class="haslefth2">简要描述</h2>
   					<s:property value="#session.currentImage.getImageDesc()"/>
					<h2 class="haslefth2">下载地址</h2>
					<a href="<s:property value="#session.currentImage.getImageSrc()"/>"><button class="submitButton" type="button">查看原图</button></a>
			     </div>
   			</div>
   		
   		<!-- 文章内容部分读取结束-->
   			<!-- <aside> 的内容可用作文章的侧栏。 -->
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
