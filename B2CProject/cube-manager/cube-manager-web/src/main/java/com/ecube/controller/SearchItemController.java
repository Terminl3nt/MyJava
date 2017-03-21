package com.ecube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CubeResult;
import com.ecube.search.service.SearchItemService;

@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/index/item/import")
	@ResponseBody
	public CubeResult searchItemImport(){
		CubeResult cubeResult = searchItemService.importAiiItem();
		return cubeResult;
	}
}
