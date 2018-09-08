<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发表新文章</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/admin.css">
	<!-- 引入Ckeditor组件 -->
	<script charset="UTF-8"  type="text/javascript" src="ckeditor/ckeditor.js"></script>

  </head>
  
  <body>
   		<!-- 导入头部文件 -->
    	<jsp:include page="header.jsp"></jsp:include>
    	<!-- 导入导航文件 -->
    	<jsp:include page="aside.html"></jsp:include>
    	<%--主体部分开始 --%>
    	<div id="container">
    		<%--文章编辑部分开始 --%>
    		<div id="main" class="writing">
    			<h1>撰写新文章</h1>
				<form action="publishPost" method="post" enctype="multipart/form-data">
					<div id="leftParts">
						<s:textfield id="titles" name="postTitle" placeholder="在此输入文章标题" size="56px"></s:textfield>
						<s:textarea id="postParagraphs" class="ckeditor" name="postParagraphs" required="true"></s:textarea>
						<script type="text/javascript">
							CKEDITOR.replace("postParagraphs");
							/* ,{
							    extraPlugins: 'easyimage',
							    cloudServices_tokenUrl: 'https://example.com/cs-token-endpoint',
							    cloudServices_uploadUrl: 'https://your-organization-id.cke-cs.com/easyimage/upload/'
							} */
							CKBUILDER_CONFIG.languages.zh-cn;
							
						</script>
					</div>
					<div id="rightParts">
						<!-- 文章标签 -->
						<div class="labels">
							<h3>填写标签</h3>
							<s:textfield name="labels" placeholder="添加标签"></s:textfield>
							<br>
							<small>多个标签，请用（,）分隔开</small>
						</div>
						<!-- 标志图片 -->
						<div class="featuredPicture">
							<h3>选择特色图片</h3>
							<s:file name="featuredPicture" required="required"></s:file>
						</div>
						<!-- 标志图片 -->
						<div class="submitPart">
							<h3>发表文章</h3>
							<button  class="submitButton" type="submit" >点击发表</button>
						</div>
					</div>
				</form>
    		</div>
    	</div>
  </body>
</html>
