package com.ecube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.ecube.pojo.TbItem;
import com.ecube.pojo.TbItemDesc;
import com.ecube.service.ItemService;
@Controller
public class TestFirst {
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public @ResponseBody
	TbItem getItemById(@PathVariable long itemId){
		TbItem tbItem = itemService.getItemById(itemId);
		return tbItem;
	}
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String getPage(@PathVariable String page){
		
		return page;
	}
	
	//商品分页列表，使用pageHelper
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page , Integer rows){
		EasyUIDataGridResult itemList = itemService.getItemList(page, rows);
		return  itemList;
	}
	
	//商品添加
	@RequestMapping("/item/save")
	@ResponseBody
	public CubeResult addItem(TbItem tbItem, String desc){
		CubeResult result = itemService.addItem(tbItem, desc);
		return result;
	}
	//商品编辑回显
	@RequestMapping("/rest/page/item-edit")
	@ResponseBody
	public TbItem showItem(TbItem tbItem){
		TbItem item = itemService.reshowItem(tbItem);
		return item;
	}
//	@RequestMapping("/rest/item/query/item/desc/")
//	@ResponseBody
//	public TbItemDesc showDesc(TbItem tbItem){
//		TbItemDesc itemDesc =  itemService.reshowDesc(tbItem);
//		return itemDesc;
//	}

}
