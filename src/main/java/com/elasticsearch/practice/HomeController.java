package com.elasticsearch.practice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "home";
	}
	
	@PostMapping("/ajaxSearch")
	@ResponseBody
	public String ajaxSearch(@RequestBody(required = false) String searchWord) {
		String result = "<p><a href='/'>"+searchWord+"</a></p>";
		
		
		return result;
	}
	
}
