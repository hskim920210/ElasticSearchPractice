package com.elasticsearch.practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	private final String HOST_INETADDRESS = "121.143.40.8";
	private final int HOST_PORT = 9200;
	private final String HOST_SCHEME = "http";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "home";
	}
	
	@PostMapping("/ajaxSearch")
	@ResponseBody
	public Object ajaxSearch(@RequestBody(required = false) String searchWord, Model model) {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost(HOST_INETADDRESS, HOST_PORT, HOST_SCHEME)));
		
		String INDEX_NAME = "movie_search_copy";
		String TYPE_NAME = "_doc";
		String FIELD_NAME = "movieNmEn";
		String SORT_NAME = "openDt";
		String KEY_WORD = searchWord.substring(0, searchWord.length()-1)+"*";
		JSONArray sourceAsJsonArr = new JSONArray();
		JSONArray highlightAsJsonArr = new JSONArray();
		Map<Integer, String> highlightAsMap = new HashMap<Integer, String>();
		JSONArray result = new JSONArray();
		
		// 검색 쿼리 설정
		SearchSourceBuilder ssBuilder = new SearchSourceBuilder();
		ssBuilder.query(QueryBuilders.wildcardQuery(FIELD_NAME, KEY_WORD));
		ssBuilder.from(0);
		ssBuilder.size(10000);
		ssBuilder.sort(new FieldSortBuilder(SORT_NAME).order(SortOrder.DESC));
		
		HighlightBuilder hBuilder = new HighlightBuilder();
		hBuilder.field(FIELD_NAME);
		hBuilder.preTags("<strong style=\"font-size: 20px\">");
		hBuilder.postTags("</strong>");
		hBuilder.highlighterType("unified");
		ssBuilder.highlighter(hBuilder);
		
		// 검색 요청 생성
		SearchRequest sReq = new SearchRequest(INDEX_NAME);
		
		sReq.source(ssBuilder);
		
		// 검색 응답 생성
		try {
			SearchResponse sRes = client.search(sReq, RequestOptions.DEFAULT);
			SearchHits sHits = sRes.getHits();
			int index = 0;
			for (SearchHit sHit : sHits) {
				sHit.getId();
				highlightAsMap.put(index, ""+sHit.getHighlightFields().get("movieNmEn").getFragments()[0]);
				index += 1;
				sourceAsJsonArr.add(sHit.getSourceAsMap());
			}
			highlightAsJsonArr.add(highlightAsMap);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("응답 실패");
		}
		result.add(sourceAsJsonArr);
		result.add(highlightAsJsonArr);
		
		return result;
	}
	
	@GetMapping("/getInfo")
	public Object getInfo(@RequestParam(required = false) String movieCd, Model model) {
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost(HOST_INETADDRESS, HOST_PORT, HOST_SCHEME)));
		String INDEX_NAME = "movie_search_copy";
		String TYPE_NAME = "_doc";
		String FIELD_NAME = "movieCd";
		String KEY_WORD = movieCd;
		JSONObject sourceAsJsonObj = new JSONObject();
		// 검색 쿼리 설정
		SearchSourceBuilder ssBuilder = new SearchSourceBuilder();
		ssBuilder.query(QueryBuilders.termQuery(FIELD_NAME, KEY_WORD));
		ssBuilder.from(0);
		
		// 검색 요청 생성
		SearchRequest sReq = new SearchRequest(INDEX_NAME);
		sReq.types(TYPE_NAME);
		sReq.source(ssBuilder);
		try {
			SearchResponse sRes = client.search(sReq, RequestOptions.DEFAULT);
			SearchHits sHits = sRes.getHits();
			for (SearchHit sHit : sHits) {
				sourceAsJsonObj.putAll(sHit.getSourceAsMap());
			}
			model.addAttribute("result", sourceAsJsonObj);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("에러 발생");
		}
		
		return "getInfo";
	}
}
