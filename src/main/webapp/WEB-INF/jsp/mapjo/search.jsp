<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	

</script>
<script type="text/javascript">
	$(function () {
		//alert(1);
		
		 $("#keywordBtn").click(function() {
			alert($('input[name=keyword]').val())
			$.ajax({
				url: "/searchByKeyword",//서버주소 
				type: "get",// 요쳥방식 get post put delete
				dataType: "text",//서버가 보내오는 데이터 타입 -응답 : text, html, xml, json
				data: {keyword : $('input[name=keyword]').val()},//서버에게 보낼 parameter 정보 
				success: function (result) {
					$("#display").html("<h2>"+result+"</h2>");
				},
				error: function (err) {
					consol.log(err+": error occured")
				}
					
			}); 
		});//end of 
		 
		
		
	})//end of ready


</script>
</head>
<body>

search by keyword!<br>
검색:  <input type="text" name="keyword" id="keyword">
<input type="button" value="search" id="keywordBtn">

<hr>
<div id="display"></div>
</body>
</html>