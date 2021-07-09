<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

<!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 --> 
<%@ include file="/pages/common/header.jsp" %>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/DRTLogo.png" >
			<span class="wel_word">我的订单</span>
				
		<!-- 登录成功之后所有相同的菜单  -->
		<%@ include file="/pages/common/login_success_menu.jsp" %>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<th>商品名称</th>
				<th>商品数量</th>
				<th>单价</th>
				<th>总价</th>
				<th>订单号</th>
			</tr>
			<c:forEach items="${ items }" var="item">
			<tr>
				<td>${ item.name }</td>
				<td>${ item.count }</td>
				<td>${ item.price }</td>
				<td>${ item.totalMoney }</td>
				<td>${ item.orderId }</td>
			</tr>
			</c:forEach>
		</table>
		<c:if test="${ sessionScope.admin == null }">
			<div style="text-align:center;" ><a href="clientOrderServlet?action=myOrders">返回订单总页</a></div> 
		</c:if>
		<c:if test="${ sessionScope.admin != null }">
			<div style="text-align:center;" ><a href="managerOrderServlet?action=orders">返回订单总页</a></div> 
		</c:if>
		
		
	
	</div>

		<!-- 这是页脚的引入 -->
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>