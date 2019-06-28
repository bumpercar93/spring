<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	$(document).ready(function() {
		console.log("ready");
		
		$("#requestData").on("click", function() {
			$.ajax({
				url : "/ajax/requestData",
				method : "post",
				success : function(data) {
					// pageVO.page, pageVO.pageSize
					$("#page").text(data.pageVO.page);
					$("#pageSize").text(data.pageVO.pageSize);
					console.log(data);
				}
			});
		});
		
		$("#user").on("click", function() {
			$.ajax({
				url : "/ajax/userData",
				method : "post",
				data : "userId=" + $("#userId").val(),
				success : function(data) {
					console.log(data);
					/*
					name : <input type="text" id="name" readonly/>
					alias : <input type="text" id="alias" readonly/>
					birth : <input type="text" id="birth" readonly/>
					$("#name").val(data.userVO.name);
					$("#alias").val(data.userVO.alias);
					$("#birth").val(data.birth);
					*/
					var html = "";
					html += "name : <input type=\"text\" id=\"name\" readonly value=\"" + data.userVO.name + "\" />";
					html += "alias : <input type=\"text\" id=\"alias\" readonly value=\"" + data.userVO.alias + "\" />";
					html += "birth : <input type=\"text\" id=\"birth\" readonly value=\"" + data.birth + "\" />";
					$("#userInfo").append(html);
				}
			})
		});
		
		$("#userHtml").on("click", function() {
			$.ajax({
				url : "/ajax/userHtml",
				method : "post",
				data : $("#frm").serialize(),
				success : function(data) {
					console.log(data);
					$("#frm").append(data);
				}
			})
		});
		
	});
</script>
<h2>AJAX JSON 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a> <br>
page : <span id="page"></span> <br>
pageSize : <span id="pageSize"></span> <br>

<h2>AJAX JSON 데이터 요청(USER)</h2>
<a id="user">데이터 가져오기</a> <br>
<div id="userInfo">
	userId : <input type="text" id="userId" value="admin"/>
</div>


<h2>AJAX HTML 데이터 요청(USER)</h2>
<a id="userHtml">데이터 가져오기</a> <br>
<form id="frm">
	userId : <input type="text" id="userIdHtml" name="userId" value="brown"/>
</form>

