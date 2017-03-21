package com.itheima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.pojo.Items;
import com.itheima.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/items.action")
	public ModelAndView queryList(){
		List<Items> list = itemService.QueryItem();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("item", list);
		modelAndView.setViewName("items");
		return modelAndView;
	}
}
