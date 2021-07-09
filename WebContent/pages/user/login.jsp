<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DRT书城会员登录页面</title>

<%@include file="/pages/common/header.jsp" %>

<script type="text/javascript">

	$(function(){			//页面加载完后执行的函数
		$("#sub_btn").click(function(){ //id选择器 绑定单击事件
			var usernameText = $("#username").val();//id选择器 获取选中的内容赋给变量
			var usernamePatt = /^\w{5,12}$/;//正则表达式要求
			if(!usernamePatt.test(usernameText)){//test方法验证符不符合要求 符合返回true
				$("span.errorMsg").text("用户名不合法!");//并列选择器 （元素选择器+类选择器） test方法改变文本内容
				return false;//阻止元素本身的事件
			}
			var passwordText = $("#password").val();//id选择器 获取选中的内容赋给变量
			var passwordPatt = /^\w{5,12}$/;//正则表达式要求
			if(!passwordPatt.test(passwordText)){//test方法验证符不符合要求 符合返回true
				$("span.errorMsg").text("密码不正确!");//并列选择器 （元素选择器+类选择器） test方法改变文本内容
				return false;//阻止元素本身的事件
			}
			
		})
	})

</script>
</head>
<body>
	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/DRTLogo.png">
	</div>

	<div class="login_banner">

		<div id="l_content">
			<span class="login_word">欢迎登录</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>DRT书城会员</h1>
						<a href=pages/user/regist.jsp>立即注册</a>
						<a href=pages/user/admin_login.jsp>管理员登陆</a>
					</div>
					<div class="msg_cont">
						<b></b> <span class="errorMsg"><%=request.getAttribute("msg")==null ? 
								"请输入用户名和密码" : request.getAttribute("msg")%></span>
					</div>
					<div class="form">
						<form action="/BookStores/userServlet?action=login" method="post">
							<label>用户名称：</label> <input class="itxt" type="text"
								placeholder="请输入用户名" autocomplete="off" tabindex="1"
								name="username" id="username"
								value="${ requestScope.username }" />
							<br /> <br /> <label>用户密码：</label> <input class="itxt"
								type="password" placeholder="请输入密码" autocomplete="off"
								tabindex="1" name="password" id="password" /> <br /> <br /> <input
								type="submit" value="登录" id="sub_btn" />
								<input type="hidden" name="need" value="login" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/pages/common/footer.jsp" %>
	
</body>
</html>
