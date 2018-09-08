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
    
    <title>上传图片</title>
    
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
    		<%--文章编辑部分开始 --%>
    		<div id="main" class="uploadImage">
    			<h1>上传图片</h1>
				<form action="uploadImage" method="post" enctype="multipart/form-data">
					<div id="rightParts">
						<div class="labels">
						<h2>图片上传</h2>
							<h3>选择图片</h3>
							<s:file name="image" ></s:file>
							<br>
							<h3>填写名称</h3>
							<input type="text" name="imagename" placeholder="填写名称"  />
							<br>
							<h3>填写描述</h3>
							<textarea name="desc" placeholder="添加描述" cols="55" rows="5"></textarea>
							<br>
						
							<h3>上传图片</h3>
							<button  class="submitButton" type="submit" >点击上传</button>
						</div>
					</div>
				</form>
    		</div>
    	</div>
  </body>
</html>
