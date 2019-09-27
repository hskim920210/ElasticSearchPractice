<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ElasticSearch 검색 서비스 테스트</title>
</head>
<body>

<div id="outer" align="center">
	<h1>ElasticSearch 테스트 서비스</h1>
	<form action="<%= request.getContextPath() %>/search" method="get">
		<p><input type="text" id="searchText" onkeyup="ajaxSearch();" placeholder="검색어를 입력하세요." required="required" size="40px"><input style="margin-left: 10px" type="submit" value="검색하기"></p>
		<span id="suggests"></span>
	</form>
</div>

<jsp:include page="js/homeSearch.jsp"></jsp:include>
</body>
</html>