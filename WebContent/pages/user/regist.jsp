<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DRT书城会员注册页面</title>
<%@include file="/pages/common/header.jsp" %>
<style type="text/css">
.login_form {
	height: 420px;
	margin-top: 25px;
}
</style>
<script type="text/javascript">

	$(function(){
		
		//用户名是否存在验证
		$("#username").blur(function(){
			// 获取用户名
			var usernameValue = this.value;
			//判断用户名不能为空
			if (usernameValue == "") {
				$("span.errorMsg").text("用户名不能为空！");
				return;
			}
			
			// 发送ajax请求验证
			$.getJSON("${path}userServlet","action=existsUsername&username="+usernameValue,function(data){
				// result 等于0，说明用户名不存在
				//alert("userServlet?action=existsUsername&username="+usernameValue);
				if (data.result == 0) {
					$("span.errorMsg").text("");
				} 
				// result 等于1，说明用户名存在
				else if (data.result == 1) {
					$("span.errorMsg").text("用户名已存在");
				}
			});
		});
		
		//alert($);
		$("#sub_btn").click(function(){
			var usernameText = $("#username").val();
			var usernamePatt = /^\w{5,12}$/;
			if(!usernamePatt.test(usernameText)){
				$("span.errorMsg").text("用户名不合法！");
				return false;
			}
			
			var passwordText = $("#password").val();
			var passwordPatt = /^\w{5,12}$/;
			if(!passwordPatt.test(passwordText)){
				$("span.errorMsg").text("密码不合法！");
				return false;
			}
			
			var passwordText = $("#password").val();
			var repwdText = $("#repwd").val();;
			if(passwordText!=repwdText){
				$("span.errorMsg").text("两次密码不相同！");
				return false;
			}
			
			var emailText = $("#email").val();
			var emailPatt = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
			if(!emailPatt.test(emailText)){
				$("span.errorMsg").text("邮箱格式不正确！");
				return false;
			}
			
			var codeText = $("#code").val();
			var codePatt = /^\w{5}$/;
			if(!codePatt.test(codeText)){
				$("span.errorMsg").text("验证码不正确！");
				return false;
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
			<span class="login_word">欢迎注册</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>注册DRT书城会员</h1>
						<span class="errorMsg">${ requestScope.msg }</span>
					</div>
					<div class="form">
						<form action="/BookStores/userServlet?action=regist" method="post">
							<label>用户名称：</label> 
							<input class="itxt" type="text"
								placeholder="请输入用户名" autocomplete="off" tabindex="1"
								name="username" id="username" value="${ requestScope.username }" /> <br /> <br /> 
							<label>用户密码：</label>
							<input class="itxt" type="password" placeholder="请输入密码"
								autocomplete="off" tabindex="1" name="password" id="password" />
							<br /> <br /> 
							<label>确认密码：</label> 
							<input class="itxt"
								type="password" placeholder="确认密码" autocomplete="off"
								tabindex="1" name="repwd" id="repwd" /> <br /> <br /> 
							<label>电子邮件：</label>
							<input class="itxt" type="text" placeholder="请输入邮箱地址"
								autocomplete="off" tabindex="1" name="email" id="email"  /> <br />
							<br /> 
							<label>验证码：</label> 
							<input class="itxt" type="text"
								style="width: 150px;" id="code" /> <img alt=""
								src="static/img/code.bmp"
								style="float: right; margin-right: 40px"> <br /> <br />
							<input type="submit" value="注册" id="sub_btn" />
							<input type="hidden" name="need" value="regist" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
