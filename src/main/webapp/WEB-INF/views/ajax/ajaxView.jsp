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
		
		$("#requestDataResponseBody").on("click", function() {
			$.ajax({
				url : "/ajax/requestDataResponseBody",
				method : "post",
				success : function(data) {
					// pageVO.page, pageVO.pageSize
					$("#pageResponseBody").text(data.page);
					$("#pageSizeResponseBody").text(data.pageSize);
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
		
		//전송할 json객체를 준비
		/*
		public class UserVO() {
			private String userId;
			public String getUserId(){ };
		}
		*/
		var user = {userId : "brown", pass : "brown1234"};
		// JSON.stringfy() : 자바스크립트 객체를 json 문자열로 생성
		// JSON.parse("json문자열") : json 문자열을 자바스크립트 객체로 변경
		$("#userFormString").text("userId=brown&pass=brown1234");
		$("#userJsonString").text(JSON.stringify(user));
		
		// @ResponseBody 데이터 전송
		$("#userJsonStringBtn").on("click", function() {
			$.ajax({
				url : "/ajax/requestBody",
				method : "post",
				contentType : "application/json", //ajax를 통해서 보내는 데이터 형식이 json임을 알려준다
				dataType : "json",
				data : JSON.stringify(user),
				success : function(data) {
					console.log(data);
					$("#userJsonResult .userId").text(data.userId);
					$("#userJsonResult .pass").text(data.pass);
				}
			});
		});
		
	});
</script>
<h2>AJAX JSON 데이터 요청</h2>
<a id="requestData">데이터 가져오기</a> <br>
page : <span id="page"></span> <br>
pageSize : <span id="pageSize"></span> <br>

<h2>AJAX JSON 데이터 요청(@ResponseBody)</h2>
<a id="requestDataResponseBody">데이터 가져오기</a> <br>
page : <span id="pageResponseBody"></span> <br>
pageSize : <span id="pageSizeResponseBody"></span> <br>

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

<h2>AJAX JSON 데이터 보내기</h2>
<a id="userJsonStringBtn">데이터 보내기</a> <br>
요청 보내는 데이터(기존) : <span id="userFormString"></span> <br>
요청 보내는 데이터 : <span id="userJsonString"></span> <br>
받는 데이터
<div id="userJsonResult">
	&nbsp;&nbsp;&nbsp;&nbsp;- userId : <span class="userId"></span> <br>
	&nbsp;&nbsp;&nbsp;&nbsp;- pass : <span class="pass"></span>
</div>
