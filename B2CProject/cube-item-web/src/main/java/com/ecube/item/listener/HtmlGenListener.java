package com.ecube.item.listener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.ecube.pojo.TbItem;
import com.ecube.pojo.TbItemDesc;
import com.ecube.service.ItemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenListener implements MessageListener{
	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;
	
	@Override
	public void onMessage(Message message) {
		try {
		//创建一个模板，参考jsp
		//从消息中获取商品id
		TextMessage textMessage = (TextMessage) message;
		Long itemId = Long.parseLong(textMessage.getText());
		//等待事务提交
		Thread.sleep(1000);
		//从商品id查询商品信息，商品基本信息和商品描述
		TbItem tbItem = itemService.getItemById(itemId);
		TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		//创建一个数据集，把商品数据进行item封装
		Map dataSource = new HashMap<>();
		dataSource.put("item", tbItem);
		dataSource.put("itemDesc", itemDesc);
		//加载模板对象
		Configuration configuration = freeMarkerConfig.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			//创建一个输出流，指定输出的目录及文件名
			Writer out = new FileWriter(HTML_GEN_PATH+ itemId +".html" );
			//生成静态页面
			template.process(dataSource, out);
			//关闭流
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
