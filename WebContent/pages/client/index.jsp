<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>

<!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 --> 
<%@ include file="/pages/common/header.jsp" %>
<script type="text/javascript">

	$(function(){
		// 给加入购物车绑定单击事件
		$("button.addItemClass").click(function(){
			// 获取属性的值（自定义的属性）
			var id = $(this).attr("itemId");
			// 发起请求给CartServlet程序，添加商品
			location.href = "${path}cartServlet?action=addItem&id="+id;
		});
		
	});

</script>

</head>
<body>
	
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/DRTLogo.png" >
			<span class="wel_word">首页</span>
			<div>
				<a href="pages/user/login.jsp">登录</a> | 
				<a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">购物车</a>
				<a href="manager?action=admin">后台管理</a>
			</div>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="bookClientServlet" method="get">
						<input type="hidden" name="action" value="pageByPrice" />
					价格：<input id="min" type="text" name="min" value="${ param.min }"> 元 - 
						<input id="max" type="text" name="max" value="${ param.max }"> 元 
						<input type="submit" value="查询" id="price_search"/>
				</form>
				<span class="errorMsg" style="color: red"><%=request.getAttribute("msg")==null ? 
								"" : request.getAttribute("msg")%></span>
			</div>
			<div style="text-align: center">
				<c:if test="${ empty sessionScope.cart.items }">
					<%-- 购物车为空的情况 --%>
					<span></span>
					<div>
						<span style="color: red">当前购物车为空！</span>
					</div>
				</c:if>
				<c:if test="${ not empty sessionScope.cart.items }">
					<%-- 购物车非空的情况 --%>
					<span>您的购物车中有${ sessionScope.cart.totalCount }件商品</span>
					<div>
						您刚刚将<span style="color: red">${ sessionScope.last_name }</span>加入到了购物车中
					</div>
				</c:if>
			</div>
			
			<c:forEach items="${ page.items }" var="item">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${ item.imgPath }" />
					</div>
					<div class="book_info"  >
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${ item.name }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${ item.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${ item.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${ item.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${ item.stock }</span>
						</div>
						<div class="book_add">
							<button itemId="${ item.id }" class="addItemClass">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>

			


			


		</div>
		
		<%@ include file="/pages/common/page.jsp" %>
	
	</div>
	
	<!-- 这是页脚的引入 -->
	<%@ include file="/pages/common/footer.jsp" %>

</body>
</html>