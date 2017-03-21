package com.ecube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.ecube.pojo.TbContent;
import com.ecube.service.ContentService;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(long categoryId , int page , int rows){
		EasyUIDataGridResult result = contentService.getContentPageList(categoryId, rows, page);
		return result;
	}
	
	@RequestMapping("/content/save")
	@ResponseBody
	public CubeResult addContent(TbContent tbContent){
		CubeResult cubeResult = contentService.addContent(tbContent);
		return cubeResult;
	}
}
