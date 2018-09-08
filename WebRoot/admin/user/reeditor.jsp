<%@page import="xzy.dao.PostsDAO"%>
<%@page import="xzy.beans.Posts"%>
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
    	<%
  			String pid = request.getParameter("pId");
  			Long lpid = Long.parseLong(pid);
  			System.out.println(lpid);
  			PostsDAO editorPd = new PostsDAO();
  			Posts editorPost = editorPd.findById(lpid);
  			session.setAttribute("editorPost", editorPost);
  		 %>
    	<div id="container">
    		<%--文章编辑部分开始 --%>
    		<div id="main" class="writing">
    			<h1>修改文章</h1>
				<form action="updatePost" method="post" enctype="multipart/form-data">
					<div id="leftParts">
						<input id="titles" name="postTitle" value="<s:property value="#session.editorPost.getPostTitle()"/>" size="56px" />
						<input name="postid" type="hidden"  value="<s:property value="#session.editorPost.getPostId()"/>"  />
						<s:textarea id="postParagraphs"  class="ckeditor" name="postParagraphs" required="true" value="%{#session.editorPost.getPostContent()}">
						</s:textarea>
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
							<input name="labels" value="<s:property value="#session.editorPost.getPostLabel()"/>" />
							<br>
							<small>多个标签，请用（,）分隔开</small>
						</div>
						<!-- 标志图片 -->
						
						<!-- 标志图片 -->
						<div class="submitPart">
							<h3>发表文章</h3>
							<button  class="submitButton" type="submit" >提交修改</button>
						</div>
					</div>
				</form>
    		</div>
    	</div>
  </body>
</html>
