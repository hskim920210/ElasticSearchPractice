<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="<%= request.getContextPath() %>/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
var searchWord; 
var autoCompleWords;
function ajaxSearch() {
	searchWord = $("#searchText").val();
	// alert("입력값 : " + searchWord);
	$.ajax({
		url: "<%= request.getContextPath() %>/ajaxSearch",
		type: "POST",
		data: searchWord,
		dataType: "text",
		success: function (data) {
			$("#suggests").append("<p><a href='/'>"+data+"</a></p>");	
		},
		error: function () {
			alert("데이터 수신 중 문제 발생");
		}
	});
}
</script>