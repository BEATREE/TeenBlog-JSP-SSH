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
    
    <title>图片信息修改</title>
    
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
    	<%
  			String iid = request.getParameter("iId");
  			Long liid = Long.parseLong(iid);
  			System.out.println(liid);
  			ImageDAO updateIdao = new ImageDAO();
  			Image updateImages = updateIdao.findById(liid);
  			session.setAttribute("updateImages", updateImages);
  		%>
    	<div id="container">
    		<%--文章编辑部分开始 --%>
    		<div id="main" class="uploadImage">
    			<h1>上传图片</h1>
				<form action="updateImage" method="post" enctype="multipart/form-data">
					<div id="rightParts">
						<div class="labels">
						<h2>图片信息修改</h2>
							<img alt="" src="<s:property value="#session.updateImages.getImageSrc()"/>" style="width: 38%;">
							<input name="imageid" type="hidden" value='<s:property value="#session.updateImages.getImageId()"/>'>
							<h3>更新名称</h3>
							<input type="text" name="imagename" placeholder="填写名称"  value="<s:property value="#session.updateImages.getImageName()"/>" />
							<br>
							<h3>更新描述</h3>
							<s:textarea name="desc" placeholder="添加描述" value="%{#session.updateImages.getImageDesc()}" cols="55" rows="5" ></s:textarea>
							<br>
							<h3>更新路径</h3>
							<input name="imagesrc" value="<s:property value="#session.updateImages.getImageSrc()"/>">
							<br>
							<h3>点击修改</h3>
							<button  class="submitButton" type="submit" >确定修改</button>
						</div>
					</div>
				</form>
    		</div>
    	</div>
  </body>
</html>
