package com.ecube.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cube.common.utils.FastDFSClient;
import com.cube.pojo.EasyUITreeNode;
import com.ecube.service.ItemCatService;


@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;

	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> getCatList(@RequestParam(value="id",defaultValue="0" ) long parentId){
		List<EasyUITreeNode> list = itemCatService.getCatList(parentId);
			return list;
	}
	
}
