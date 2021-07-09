<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DRT书城会员登陆成功</title>
<%@include file="/pages/common/header.jsp" %>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}

h1 a {
	color: red;
}
</style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/DRTLogo.png">
		
		<%@include file="/pages/common/login_success_menu.jsp"%>
		
	</div>

	<div id="main">

		<h1>
			欢迎回来 <a href="">转到主页</a>
		</h1>

	</div>

	<%@include file="/pages/common/footer.jsp" %>
	
</body>
</html>
