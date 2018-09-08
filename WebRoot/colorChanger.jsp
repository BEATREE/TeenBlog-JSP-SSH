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
    
    <title>在线颜色转换</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/styles.css">
	<link rel="stylesheet" type="text/css" href="css/login.css">
  </head>
  		
  <body>
  		<!-- 头部引入 -->
    	<jsp:include page="header.html"></jsp:include>
    	<!-- 头部引入 结束-->
		
		<div id="mainbody" style="background-color: <s:property value="#application.lastColor" />">
			<div id="centerPart" style="background-color: <s:property value="#application.lastColor" />">
			<s:form action="ColorChanger" method="post">
				<div id="loginPart" >
					<h2>请输入形式如 “100,100,100”的内容</h2>
					<input name="color" required="required" id="inputfield" style="width:46%;margin-left: 27%;" /><br />
					<div class="submitarea" style="margin-left:40%; margin-bottom: 15px;">
					<button id="submitButton" type="submit">点击提交</button><br/>
					</div>
					<s:if test="(color.rgb!=null||color.rgb!='')&&color.rgb!='输入数据类型有误！ '">
					<span>对应的16进制代码为： <s:property value="color.rgb" /></span>
					</s:if><s:else>
						<span><s:property value="color.rgb" /></span>
					</s:else>
				</div>
			</s:form>
			</div>
		</div>
		
		<!-- 底部引入 -->
		<jsp:include page="footer.jsp"></jsp:include>
		<!-- 代码结束 -->
  </body>
</html>
