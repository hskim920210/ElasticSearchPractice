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
	<h1>영화 정보</h1>
	<p>영화 제목 : ${ result.movieNm } (${ result.movieNmEn })</p>
	<p>제작 년도 : ${ result.prdtYear }</p>
	<p>개봉 년도 : ${ result.openDt }</p>
	<p>영화 유형 : ${ result.typeNm }</p>
	<p>제작 상태 : ${ result.prdtStatNm }</p>
	<p>제작 국가 : ${ result.nationAlt }</p>
	<p>장르 : ${ result.genreAlt }</p>
	<p>영화 감독 : ${ result.directors[0].peopleNm }</p>
	<p>대표 장르 : ${ result.repGenreNm }</p>
</div>
</body>
</html>