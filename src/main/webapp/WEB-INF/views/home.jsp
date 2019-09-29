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
		<p><input type="text" id="searchText" onkeyup="ajaxSearch();" autocomplete="off" placeholder="검색어를 입력하세요." required="required" size="40px"></p>
		<h1>연관 검색어</h1>
		<p><span id="suggests"></span></p>
	</form>
</div>

<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/resources/js/homeSearch.js" charset="utf-8"></script>
</body>
</html>