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
    
    <title>所有图片</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	<link rel="stylesheet" type="text/css" href="css/admin.css">

  </head>
  
  <body>
  		<%
  			ImageDAO imageDAO = new ImageDAO();
  			//获取图片列表
  			List<Image> allImageList = imageDAO.findAll();
  			session.removeAttribute("allImageList");
  			session.setAttribute("allImageList", allImageList);
  		 %>
    	<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		<h2>图片信息 </h2>
    		<div class="searchPlace">
	    		<form action="asearchImage" method="post">
	    			<input name="imagename" class="inputfield" type="text" placeholder="请输入图片名称" />
	    			<button type="submit" class="jumpButton">点击查找</button>
	    		</form>
    		</div>
    		全部( <s:property value="#session.allImageList.size()"/> )
    		<!-- 内容信息部分开始 -->
    		<s:if test="#session.allImageList.size()!=0">
    		<table class="postsTable">
    		<thead>
    		<!-- 表格开始 -->
    			<tr>
    				<th>图片名称</th><th>图片内容</th><th>作者</th><th>图片简介</th><th>图片路径</th><th>操作</th>
    			</tr>
    		</thead>
    		<tbody>
    			<s:iterator value="#session.allImageList" id="ciid" status="num">
    			<tr <s:if test="#num.odd">class="changetr"</s:if>>
    				<th><s:property value="#ciid.getImageName()"/></th>
					<th><a href='detailpicture.jsp?iId=<s:property value="#ciid.getImageId()"/>' target="_blank">
    					<img class="pictureSmall" alt='<s:property value="#ciid.getImageName()"/>' src="<s:property value="#ciid.getImageSrc()"/>"></a>
    				</th>
    				<th><s:property value="#ciid.getUser().getUserName()"/></th>
    				<th><s:property value="#ciid.getImageDesc()"/></th>
    				<th><s:property value="#ciid.getImageSrc()"/></th>
    				<th><a href="admin/admin/updatePicture.jsp?iId=<s:property value="#ciid.getImageId()"/>">编辑</a> 
    				<a href="adeleteImage.action?iId=<s:property value="#ciid.getImageId()"/>">删除</a> </th>
    			</tr>
    			</s:iterator>
    		</tbody>
    		</table>
    		</s:if><s:else>
	   			<h1 align="center">欸，还没有人上传图片呢~</h1>
	   		</s:else>
    	</div>
  </body>
</html>
