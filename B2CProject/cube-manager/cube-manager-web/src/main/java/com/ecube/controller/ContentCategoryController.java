package com.ecube.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUITreeNode;
import com.ecube.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService  contentCategoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0") long parentId){
		List<EasyUITreeNode> contentCategoryList = contentCategoryService.getContentCategoryList(parentId);
		return contentCategoryList;
	}
	@RequestMapping("/content/category/create")
	@ResponseBody
	public CubeResult addContentCategory(long parentId , String name){
		CubeResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
}
