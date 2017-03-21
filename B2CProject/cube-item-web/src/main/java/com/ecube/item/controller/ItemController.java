package com.ecube.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecube.item.pojo.Items;
import com.ecube.pojo.TbItem;
import com.ecube.pojo.TbItemDesc;
import com.ecube.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("item/{itemId}")
	public String itemInfo(@PathVariable long itemId,Model model){
		TbItem tbItem = itemService.getItemById(itemId);
		Items items = new Items(tbItem);
		TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		model.addAttribute("item", items);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
		
	}
	
}
