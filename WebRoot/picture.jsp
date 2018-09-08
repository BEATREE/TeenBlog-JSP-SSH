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
    
    <title>图片壁纸</title>
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
	<link rel="stylesheet" type="text/css" href="css/evenFlow.css">
	<link rel="stylesheet" type="text/css" href="css/picture_style.css">
  </head>
  
  <body>
  <%
  		//获取图片列表
		ImageDAO id = new ImageDAO();
		
		List<Image> imageList = id.findAll();
		session.putValue("imageList", imageList);
		//文章列表获取完毕
   %>
  		<%--头部导航栏开始 --%>
  		<!-- 头部引入 -->
    	<jsp:include page="header.html"></jsp:include>
    	<!-- 头部引入 结束-->
		
		<div class="container" style="margin-top: 10px;width:70%;height:500px;overflow-y: auto;">
			
			<ul class="evenflow sample_1">
			 <s:iterator value="#session.imageList" id="image">
				<li class="evenflow_scale">
					<a href="detailpicture.jsp?iId=<s:property value="#image.getImageId()"/>" target="_blank" >
					<img src="<s:property value="#image.getImageSrc()"/>" alt="" title="<s:property value="#image.getImageName()"/>"></a>
					<s:property value="#image.getImageName()"/>
					
				</li>
			</s:iterator>
			</ul>
		</div>
		
		<!--mainbody end-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- 代码结束 -->
  </body>
</html>