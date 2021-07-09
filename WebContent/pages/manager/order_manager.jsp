<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

<!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 --> 
<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript">
	$(function() { //页面加载完后执行的函数
		$("a.Shoping").click(
				function() { 
					return confirm("你确认要发货吗?");
				})
	})
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/DRTLogo.png" >
			<span class="wel_word">订单管理系统</span>
		
		<!-- 这是manager管理模块的共同菜单  -->
		<%@ include file="/pages/common/manager_menu.jsp" %>


	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>	
			<c:forEach items="${ orders }" var="order">
			<tr>
				<td>${ order.orderId }</td>
				<td>${ order.createTime }</td>
				<td>${ order.totalMoney }</td>
				<td>
					<c:choose>
						<c:when test="${ order.status == 0 }">未发货</c:when>							
					</c:choose>
					<c:choose>
						<c:when test="${ order.status == 1 }">等待用户签收</c:when>							
					</c:choose>
					<c:choose>
						<c:when test="${ order.status == 2 }">用户已签收</c:when>							
					</c:choose>
				</td>
				<td><a href="managerOrderServlet?action=Ordersmsg&orderId=${ order.orderId }">查看详情</a></td>
				<td><a class="Shoping" href="managerOrderServlet?action=sendOrder&orderId=${ order.orderId }">点击发货</a></td>
			</tr>	
			</c:forEach>	
				
		</table>
	</div>
	
	
	<!-- 这是页脚的引入 -->
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>