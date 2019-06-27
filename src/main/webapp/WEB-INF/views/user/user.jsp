<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>사용자 상세조회</title>

<!-- css, js -->
<%@include file="/WEB-INF/views/common/basicLib.jsp"%>

<style type="text/css">
	img{
		width: 180px;
		height: 200px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		<c:if test="${not empty msg}">
			alert("${msg}");
// 			<c:remove var="msg"/>
		</c:if>
		
		$("#modifyBtn").on("click", function() {
			var userId = $("#idLabel").text();
			$("#userId").val(userId);
			$("#frm").submit();
		});
	});
</script>

</head>

<body>
	<!-- header -->
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<!-- left -->
			<%@include file="/WEB-INF/views/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자 상세정보</h2>
						<form id="frm" class="form-horizontal" role="form" method="get"
								action="${cp}/user/modify">
								<input type="hidden" id="userId" name="userId"/>
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">프로필 사진</label>
								<div class="col-sm-10">
									<img src="${cp}/user/profile?userId=${userVO.userId}"/>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
								<div class="col-sm-10">
									<label id="idLabel" class="control-label">${userVO.userId}</label>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.name}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">별명</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.alias}</label>
								</div>
							</div>

							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">주소</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.addr1}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">상세주소</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.addr2}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">우편번호</label>
								<div class="col-sm-10">
									<label class="control-label">${userVO.zipcd}</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">생일</label>
								<div class="col-sm-10">
									<label class="control-label">
										<fmt:formatDate value="${userVO.birth}" pattern="yyyy-MM-dd"/>
									</label>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button id="modifyBtn" type="button" class="btn btn-default">사용자 수정</button>
								</div>
							</div>
							
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
