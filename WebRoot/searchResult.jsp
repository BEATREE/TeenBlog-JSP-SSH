<%@page import="xzy.beans.Image"%>
<%@page import="xzy.dao.ImageDAO"%>
<%@page import="xzy.utils.SimplifyText"%>
<%@page import="xzy.dao.PostsDAO"%>
<%@page import="xzy.beans.Posts"%>
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
    
    <title>搜索结果页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/animations.css">
	
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
		//获取图片列表
		ImageDAO idao = new ImageDAO();
		List<Image> pictureList = idao.findAll();
		//获取热门文章
		session.setAttribute("postList", postList);
		session.setAttribute("pictureList", pictureList);
		//文章列表获取完毕
  		
  		int a;	//用于判断是否有用户登陆
  		User user;
  		if(session.getAttribute("currentUser")!=null){
  			user = (User)session.getAttribute("currentUser");
  			a = 1;
  		}else{
  			user = null;
  			a = 0;
  		}
  	%>
  		
  		<%--头部导航栏开始 --%>
  		<!-- 头部引入 -->
    	<jsp:include page="header.html"></jsp:include>
    	<!-- 头部引入 结束-->
		<%--头部导航栏结束--%>
		<s:debug></s:debug>
		<%--头部导航栏结束--%>
		<div id="mainbody">
		  <!--info 开始-->
		  <div class="info">
		    <figure> <img src="images/art.jpg"  alt="Panama Hat">
		      <figcaption><strong>微励志语录</strong> 
		      	<br><br>
		      	如果心胸不似海，又怎能有海一样的事业。<br>
		      	这些年，我已逐渐学会接受，接受意外，接受变节，接受误解，接受努力了却得不到回报，接受世界的残忍和人性的残缺。
		      	但这不代表我妥协，我还会去努力，去爱，去为遥不可及的一切付出心血。
		      	因为，我还相信梦想，相信奇迹。这样，我会活得快乐一些。<br>
		      	</figcaption>
		    </figure>
		    <!-- 名片开始 -->
		    <div class="card">
		    <%if(a==0){ %>
		    	<h1 style="margin-bottom: 13.2%;">您还尚未登陆</h1>
		      	<span><a href="login.jsp">点击前往登陆</a></span>
		      </ul>
		    <%}else{ %>
		      <h1>我的名片</h1>
		      <p>网名：<a style="color: red;"> <%=user.getUserName() %> </a></p>
		      <p>介绍：<%=user.getUserName() %></p>
		      <p>Email：<%=user.getUserEmail() %></p>
		      <p>发表文章数：<%=user.getPostNumber() %></p>
		      <ul class="linkmore">
		      	<li><a href="admin/user/" class="address" title="我的主页"></a></li>
		        <li><a href="admin/user/posts.jsp" class="talk" title="我的文章"></a></li>
		        <li><a href="admin/user/allPicture.jsp" class="photos" title="我的图片"></a></li>
		        <li><a href="mailto:<%=user.getUserEmail() %>" class="email" title="给我写信"></a></li>
		        <li><a href="logout.jsp" class="heart" title="退出登陆"></a></li>
		      </ul>
		      <%} %>
		    </div>
		  	<!-- 名片开始 -->
		  </div>
		 
		  <!--info 结束-->
		  <div class="blank"></div>
		  
		  <div class="blogs">
		   <!-- 判断是否有相关结果 -->
		  <s:if test="#session.searchResults.size()!=0">
		   <h1 style="padding-left:20px;color: white;border-left: 5px red solid;">搜索结果如下：</h1> 
		    <ul class="bloglist">
		    
		    <s:iterator value="#session.searchResults" id="post">
		      <li>
		      	<!--arrow_box 结束--> 
		        <div class="arrow_box">
		          <div class="ti"></div>
		          <!--三角形-->
		          <div class="ci"></div>
		          <!--圆形-->
		          <h2 class="title">
		          	<a href='post.jsp?pId=<s:property value="#post.getPostId()"/>' target="_blank"> <s:property value="#post.getPostTitle()"/> </a>
		          </h2>
		          <ul class="textinfo">
		            <a href='post.jsp?pId=<s:property value="#post.getPostId()"/>' target="_blank"><img src='<s:property value="#post.getPostImage()"/>'></a>
		            <p> 
		            <s:if test="#post.getPostContent().length() > 120">
					    <s:property value="#post.getPostLesscontent().substring(0, 120)+ '...'"   />
					</s:if>
					<s:else>
					    <s:property value="#post.getPostContent()" escape="false"/>
					</s:else>
		            <%-- <s:property value="#post.getPostContent()" escape="false" /> --%>
		            </p>
		          </ul>
		          <ul class="details">
		            <li class="likes"><a href="#">10</a></li>
		            <li class="comments"><a href="#">34</a></li>
		            <li class="icon-time"><a href="#"><s:date name="#post.getPostDate()" format="yyyy-MM-dd"/></a></li>
		          </ul>
		        </div>
		        <!--arrow_box 结束--> 
		      </li>
		      </s:iterator>
		      
		    </ul>
		    <!--bloglist 结束-->
		    
		    <!-- <aside> 的内容可用作文章的侧栏。 -->
		    <aside>		
		      <!-- 搜索框开始 -->
		      <div class="search">
		        <form class="searchform" method="post" action="findPost">
		          <input type="text" name="postTitle" value="Search" onfocus="this.value=''" onblur="this.value='Search'">
		        </form>
		      </div>
		      <!-- 搜索框结束 -->
		      
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
		    <!-- 判断是否有结果结束 -->
		  </s:if><s:else>
		      		<li> <h1 style="padding-left:20px;color: white;border-left: 5px red solid;">对不起没有找到您要搜索的文章</h1> </li>
		      		<li><a href="index.jsp"><img alt="未找到相关结果" src="images/404.jpg"></a>  </li>
		  </s:else>
		  </div>
		  <!--blogs end--> 
		  
		</div>
		<!--mainbody end-->
		<jsp:include page="footer.jsp"></jsp:include>
		
		<!-- 代码结束 -->
  </body>
</html>