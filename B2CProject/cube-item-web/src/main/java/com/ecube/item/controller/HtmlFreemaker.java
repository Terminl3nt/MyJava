package com.ecube.item.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

@Controller
public class HtmlFreemaker {

	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	
	@RequestMapping("/free")
	@ResponseBody
	public String genhtml() throws Exception{
		Configuration configuration = freeMarkerConfig.getConfiguration();
		Template template = configuration.getTemplate("hello.ftl");
		Map dataSource = new HashMap();
		dataSource.put("hello", "oooooooooooooooo");
		Writer out = new FileWriter(new File("D:/就业班/宜立方/项目二_day10/项目二_day10/hh.txt"));
		template.process(dataSource, out);
		out.close();
		return "ok";
	}
}
