<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理</title>

<!-- 头部共享信息的引入。包含jquery，base标签，以及css样式 -->
<%@ include file="/pages/common/header.jsp"%>
<script type="text/javascript">
	$(function() { //页面加载完后执行的函数
		$("a.deleteA").click(
				function() { //id选择器 绑定单击事件
					return confirm("你确认要删除【"
							+ $(this).parent().parent().find("td:first").text()
							+ "】吗?");
				})
	})
</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/DRTLogo.png"> <span
			class="wel_word">图书管理系统</span>

		<!-- 这是manager管理模块的共同菜单  -->
		<%@ include file="/pages/common/manager_menu.jsp"%>


	</div>

	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<tr>
					<td>${ book.name }</td>
					<td>${ book.price }</td>
					<td>${ book.author }</td>
					<td>${ book.sales }</td>
					<td>${ book.stock }</td>
					<td><a
						href="bookServlet?action=getBook&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">修改</a></td>
					<td><a class="deleteA"
						href="bookServlet?action=delete&id=${ book.id }&pageNo=${ requestScope.page.pageNo }">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>

			</tr>
		</table>
		
		<%@ include file="/pages/common/page.jsp" %> 
		
		
	</div>


	<!-- 这是页脚的引入 -->
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>