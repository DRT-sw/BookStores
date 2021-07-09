<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script type="text/javascript">
	$(function(){
		// 给分页条确定按钮添加事件
		$("#searchBtn").click(function() {
			// 获取页码输入框的值
			var pageNo = $("#pn_input").val();
			// 通过 javaScript的Location对象，设置浏览器地址栏的地址
			location.href = "manager/bookServlet?action=page&pageNo=" + pageNo;
		});
	});
</script>

		<%-- 分页条开始 --%>
		<div id="page_nav">
			<%--
				如果不是第一页，才显示【首页】【上一页】
			 --%>
			<c:if test="${ requestScope.page.pageNo > 1 }">
				<a href="${requestScope.page.url}&pageNo=1">首页</a>
				<a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageNo-1 }">上一页</a>
			</c:if>
			
			<%-- 页码输出的开始 --%>		
			<c:choose>
				<%-- 第一种情况是：总页码数小于等于5的情况。页码的范围其实是：1 到 总页码 --%>
				<c:when test="${ requestScope.page.pageTotal <= 5 }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="${ requestScope.page.pageTotal }" />
				</c:when>
				<%-- 第二种情况是：总页码数大于5的情况 --%>
				<c:when test="${ requestScope.page.pageTotal > 5 }">
					<c:choose>
						<%-- 第一种小情况是：当前页码是前面三个。就是1,2,3。页码的范围是1 到 5 --%>
						<c:when test="${ requestScope.page.pageNo <= 3 }">
							<c:set var="begin" value="1" />
							<c:set var="end" value="5" />
						</c:when>
						<%-- 第二种小情况：当前页码是最后三个。就是8,9,10。页码范围是 总页码-4 到 总页码 --%>
						<c:when test="${ requestScope.page.pageNo >= requestScope.page.pageTotal-2 }">
							<c:set var="begin" value="${ requestScope.page.pageTotal-4 }" />
							<c:set var="end" value="${ requestScope.page.pageTotal }" />
						</c:when>
						<%-- 第三种小情况：是剩下的中间页码。就是4,5,6,7。页码范围是 当前页码-2 到 当前页码 + 2  --%>
						<c:otherwise>
							<c:set var="begin" value="${ requestScope.page.pageNo-2 }" />
							<c:set var="end" value="${ requestScope.page.pageNo+2 }" />
						</c:otherwise>
					</c:choose>
				</c:when>
			</c:choose>
			
			<c:forEach begin="${ begin }" end="${ end }" var="i">
				<c:if test="${ i == requestScope.page.pageNo }">
					【${ i }】
				</c:if>
				<c:if test="${ i != requestScope.page.pageNo }">
					<a href="${requestScope.page.url}&pageNo=${i}">${ i }</a>
				</c:if>
			</c:forEach>				
			<%-- 页码输出的结束 --%>	
				
			<%--
				如果当前页，等于最后一页。就不需要显示【末页】【下一页】
			 --%>
			 <c:if test="${ requestScope.page.pageNo < requestScope.page.pageTotal }">
				<a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageNo+1 }">下一页</a>
				<a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageTotal }">末页</a>
			 </c:if>
			共${ requestScope.page.pageTotal }页，${ requestScope.page.totalCount }条记录 到第<input value="${ requestScope.page.pageNo }" name="pn" id="pn_input"/>页
			<input id="searchPageNo" type="button" value="确定">
			
			<script type="text/javascript">
				
				$(function(){
					// 给搜索指定页码的按钮绑定单击事件
					$("#searchPageNo").click(function(){
						//获取输入框中的页码
						var pageNo = $("#pn_input").val();
						// 修改浏览器请求地址。刷新页面。
						/*
							javaScript语言中提供一个现成的对象叫location对象表示浏览器地址栏
							它有一个属性叫href，表示浏览器地址栏中的地址。
							href属性可读可写
						*/
						location.href = "${path}${requestScope.page.url}&pageNo=" + pageNo;
					});
					
				});
			
			</script>
			
		</div>
		<%-- 分页条结束 --%>