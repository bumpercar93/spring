<%@page import="kr.or.ddit.paging.model.PageVO"%>
<%@page import="kr.or.ddit.user.model.UserVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.userTr:hover {
	cursor: pointer;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		//사용자 tr태그 이벤트 등록
		$("#userListTbody").on("click", ".userTr", function() {
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
		
		// 첫번째 페이지의 사용자 정보를 요청
		//userPagingListAjax(1,10);
		userPagingListAjaxHtml(1,10);
	});

// 데이터 응답을 html로 받는 경우
function userPagingListAjaxHtml(page, pageSize){
	$.ajax({
		url : "/user/pagingListAjaxHtml",
		method : "post",
		data : "page=" + page + "&pageSize=" + pageSize,
		success : function(data) {
			var htmlArr = data.split("SEPERATORSEPERATOR");
			$("#userListTbody").html(htmlArr[0]);
			$(".pagination").html(htmlArr[1]);
		}
	});
}

// 데이터 응답을 json으로 받는 경우
function userPagingListAjax(page, pageSize) {
	$.ajax({
		url : "/user/pagingListAjax",
		method : "post",
		data : "page=" + page + "&pageSize=" + pageSize,
		success : function(data) {
			// 사용자 리스트
			var html = "";
			data.data.userList.forEach(function(user) {
				html += "<tr class='userTr' data-userId='" + user.userId + "'>";
				html += "<td class='userId'>" + user.userId + "</td>";
				html += "<td>" + user.name + "</td>";
				html += "<td>" + user.alias + "</td>";
				html += "<td></td>";
				html += "</tr>";
			});
			// 페이지네이션 생성
			var pHtml = "";
			var pageVO = data.pageVO;
			if(pageVO.page == 1)
				pHtml += "<li class='disabled'><span>«</span></li>";
			else
				pHtml += "<li><a href='javascript:userPagingListAjax(" 
						+ (pageVO.page - 1) + ", " + pageVO.pageSize + ");'>«</a></li>";
			
			for (var i = 1; i <= data.data.paginationSize; i++) {
				if(pageVO.page == i)
					pHtml += "<li class='active'><span>" + i + "</span><li>";
				else
					pHtml += "<li><a href='javascript:userPagingListAjax("
							+ i + ", " + pageVO.pageSize + ");'>" + i + "</a></li>";
			}
			
			if(pageVO.page == data.data.paginationSize)
				pHtml += "<li class='disabled'><span>»</span></li>";
			else
				pHtml += "<li><a href='javascript:userPagingListAjax(" 
						+ (pageVO.page + 1) + ", " + pageVO.pageSize + ");'>»</a></li>";
			
			$("#userListTbody").html(html);
			$(".pagination").html(pHtml);
		}
	});
}
	
</script>

<div class="row">
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">사용자(AJAX)</h2>

		<!-- 사용자 상세조회 : userId가 필요 -->
		<form id="frm" action="${cp}/user/user" method="get">
			<input type="hidden" id="userId" name="userId" />
		</form>

		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>사용자 아이디</th>
						<th>사용자 이름</th>
						<th>사용자 별명</th>
						<th>등록일시</th>
					</tr>
				</thead>
				<tbody id="userListTbody">
				</tbody>
			</table>
		</div>
		<a style="margin-left: 5px" href="${cp}/user/form"
			class="btn btn-default pull-right">사용자 등록</a> <a
			href="${cp}/user/userListExcel?fileName=userList"
			class="btn btn-default pull-right">Excel Download</a>

		<!-- 사용자수 : 105건, 페이지네이션 : 11건 -->
		<div class="text-center">
			<ul class="pagination">

			</ul>
		</div>
	</div>
</div>