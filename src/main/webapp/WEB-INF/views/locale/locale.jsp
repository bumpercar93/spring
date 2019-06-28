<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(document).ready(function() {
		// $("select[name=lang]") == $("#lang")
		$("select[name=lang]").val("${lang}");
		
		$("select[name=lang]").on("change", function() {
			//console.log("select box changed");
			$("select[name=lang]").parent().submit();
		});
	});
</script>

<form id="frm" action="/locale/view" method="post">
	<select id="lang" name="lang">
		<option value="ko">한국어</option>
		<option value="en">English</option>
		<option value="ja">日本語</option>
	</select>
</form>
<spring:message code="GREETING"/> <br>
<spring:message code="VISITOR">
	<spring:argument value="김범휘"/>
</spring:message>