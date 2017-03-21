package com.ecube.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cube.pojo.SearchResult;
import com.ecube.search.service.SearchItemService;

@Controller
public class SearchController {

	@Autowired
	private SearchItemService searchItemService; 
	@Value("${DEFAULT_PAGE}")
	private int DEFAULT_PAGE;
	
	@RequestMapping("/search")
	public String SeacherQuery(String keyword , @RequestParam(defaultValue="1")int page , Model model) throws Exception{
		keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
		SearchResult searchResult = searchItemService.findSearchByQuery(keyword, page, DEFAULT_PAGE); 
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("page", searchResult.getRecourdCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);
		model.addAttribute("query", keyword);
		return "search";
	}
}
