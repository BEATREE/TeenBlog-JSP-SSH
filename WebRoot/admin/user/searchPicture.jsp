<%@page import="xzy.beans.Image"%>
<%@page import="xzy.beans.User"%>
<%@page import="xzy.beans.Posts"%>
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
    
    <title>搜索图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">

  </head>
  
  <body>
  		
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		<h2>图片 <button class="jumpButton" onclick="window.location.href='admin/user/allPicture.jsp'" type="button">图片列表</button></h2>
    		<div class="searchPlace">
	    		<form action="searchImage" method="post">
	    			<input name="imagename" class="inputfield" type="text" placeholder="请输入图片名称" />
	    			<button type="submit" class="jumpButton">点击查找</button>
	    		</form>
    		</div>
    		
    		<!-- 判断是否显示搜索结果 -->
    		<s:if test="#session.pictureResults.size()!=0">
    		<div class="searchremind">搜索结果如下</div>
    			<table class="postsTable">
    		<thead>
    		<!-- 表格开始 -->
    			<tr>
    				<th>图片名称</th><th>图片内容</th><th>图片简介</th><th>图片路径</th><th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<s:iterator value="#session.pictureResults" id="ciid" status="num">
    			<tr>
    				<th><s:property value="#ciid.getImageName()"/></th>
    				<th><img class="pictureSmall" alt="" src="<s:property value="#ciid.getImageSrc()"/>"></th>
    				<th><s:property value="#ciid.getImageDesc()"/></th>
    				<th><s:property value="#ciid.getImageSrc()"/></th>
    				<th><a href="admin/user/updatePicture.jsp?iId=<s:property value="#ciid.getImageId()"/>">编辑</a> 
    				<a href="deleteImage.action?iId=<s:property value="#ciid.getImageId()"/>">删除</a> </th>
    			</tr>
    			</s:iterator>
    		</tbody>
    			
    		</table>
    		</s:if><s:else>
	   			<h1 align="center">未搜索到相关结果</h1>
	   		</s:else>
    	</div>
  </body>
</html>
