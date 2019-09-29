var searchWord; 
var autoCompleWords;
var customKey;
function ajaxSearch() {
	searchWord = $("#searchText").val();
	$.ajax({
		url: "/practice/ajaxSearch",
		type: "POST",
		data: searchWord,
		dataType: "json",
		success: function (data) {
			$("#suggests").empty();
			if (data[0].length == 0){
				$("#suggests").append("<p>결과가 없습니다.</p>");
			} else {
				for ( var i = 0 ; i < data[0].length ; i++ ){
					$("#suggests").append("<p><a href='/practice/getInfo?movieCd="+data[0][i].movieCd+"'>"+data[0][i].movieNm+" ("+data[1][0][i]+")"+"</a></p>");	
				}
			}
		},
		error: function () {
			console.log("error");
		}
	});
}