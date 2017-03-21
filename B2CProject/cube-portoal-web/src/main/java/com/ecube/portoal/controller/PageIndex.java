package com.ecube.portoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecube.content.service.ContentService;
import com.ecube.pojo.TbContent;

@Controller
public class PageIndex {
	@Autowired
	private ContentService contentService;
	@Value("${CONTENT_LUNBO_IMAGE}")
	private long CONTENT_LUNBO_IMAGE;
	
	@RequestMapping("/index")
	public String page(Model model){
		List<TbContent> list = contentService.showContentPage(CONTENT_LUNBO_IMAGE);
		model.addAttribute("ad1List",list);
		return "index";
	}
	
}
