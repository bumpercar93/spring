<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 향상된 for -->
<c:forEach items="${data.userList}" var="user" varStatus="status">
	<tr class="userTr" data-userid="${user.userId}"
		data-name="${user.name}">
		<%-- <td>${status.index} / ${status.count} / ${user.userId}</td> --%>
		<td class="userId">${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.alias}</td>
		<td>2019.05.23</td>
	</tr>
</c:forEach>

SEPERATORSEPERATOR

<!-- 사용자수 : 105건, 페이지네이션 : 11건 -->
<c:choose>
	<c:when test="${pageVO.page == 1}">
		<li class="disabled"><span>«</span></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:userPagingListAjaxHtml(${pageVO.page-1}, ${pageVO.pageSize});"><span>«</span></a>
		</li>
	</c:otherwise>
</c:choose>

<c:forEach var="i" begin="1" end="${data.paginationSize}" step="1">
	<c:choose>
		<c:when test="${pageVO.page == i}">
			<li class="active"><span>${i}</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="javascript:userPagingListAjaxHtml(${i}, ${pageVO.pageSize});">${i}</a>
			</li>
		</c:otherwise>
	</c:choose>
</c:forEach>

<c:choose>
	<c:when test="${pageVO.page == data.paginationSize}">
		<li class="disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:userPagingListAjaxHtml(${pageVO.page+1}, ${pageVO.pageSize});"><span>»</span></a>
		</li>
	</c:otherwise>
</c:choose>
