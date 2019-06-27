<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="../../favicon.ico">
		
		<title>UserPagingList</title>
		
		<!-- css, js -->
		<%@include file="/WEB-INF/views/common/basicLib.jsp" %>
	
	<style type="text/css">
		.userTr:hover{
			cursor: pointer;
		}
	</style>
	
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			//사용자 tr태그 이벤트 등록
			$(".userTr").on("click", function() {
				console.log("userTr click");
				// 현재 클릭한 userId 찾기
				// 1.$(this).find(".userId").text();
				// 2.$(this).data("userid");
				
				// 사용자 아이디를 userId 값으로 설정
				var userId = $(this).find(".userId").text();
				$("#userId").val(userId);
				
				// #frm을 이용하여 submit();
				$("#frm").submit();
			});
			
		});
		
	</script>
	
	</head>
	
	
	<body>
		<!-- header -->
		<%@include file="/WEB-INF/views/common/header.jsp" %>
			
		<div class="container-fluid">
			<div class="row">
				<!-- left -->	
				<%@include file="/WEB-INF/views/common/left.jsp" %>
		
				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					<div class="row">
						<div class="col-sm-8 blog-main">
							<h2 class="sub-header">사용자(EL)</h2>
							
							<!-- 사용자 상세조회 : userId가 필요 -->
							<form id="frm" action="${cp}/user/user" method="get">
								<input type="hidden" id="userId" name="userId" />
							</form>
							
							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>사용자 아이디</th>
										<th>사용자 이름</th>
										<th>사용자 별명</th>
										<th>등록일시</th>
									</tr>
									
									<!-- 향상된 for -->
									<c:forEach items="${userPagingList}" var="user" varStatus="status">
										<tr class="userTr" data-userid="${user.userId}" data-name="${user.name}">
										<%-- <td>${status.index} / ${status.count} / ${user.userId}</td> --%>
											<td class="userId">${user.userId}</td>
											<td>${user.name}</td>
											<td>${user.alias}</td>
											<td>2019.05.23</td>
										</tr>
									</c:forEach>
									
								</table>
							</div>
							<a style="margin-left: 5px" href="${cp}/user/form" 
										class="btn btn-default pull-right">사용자 등록</a>
							<a href="${cp}/user/userListExcel?fileName=userList" 
										class="btn btn-default pull-right">Excel Download</a>
							
							<!-- 사용자수 : 105건, 페이지네이션 : 11건 -->
							<div class="text-center">
								<ul class="pagination">
								
									<c:choose>
										<c:when test="${pageVO.page == 1}">
											<li class="disabled"><span>«</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp}/user/pagingList?
												page=${pageVO.page-1}&pageSize=${pageVO.pageSize}"><span>«</span></a>
											</li>
										</c:otherwise>
									</c:choose>
									
									<c:forEach var="i" begin="1" end="${paginationSize}" step="1">
										<c:choose>
											<c:when test="${pageVO.page == i}">
												<li class="active">
													<span>${i}</span>
												</li>
											</c:when>
											<c:otherwise>
												<li>
													<a href="${cp}/user/pagingList?page=${i}
													&pageSize=${pageVO.pageSize}">${i}</a>
												</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
										
									<c:choose>
										<c:when test="${pageVO.page == paginationSize}">
											<li class="disabled"><span>»</span></li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="${cp}/user/pagingList?
												page=${pageVO.page+1}&pageSize=${pageVO.pageSize}"><span>»</span></a>
											</li>
										</c:otherwise>
									</c:choose>
										
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
