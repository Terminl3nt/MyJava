package com.ecube.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import com.cube.pojo.SearchItem;
import com.ecube.search.mapper.ItemSearchMapper;

public class ItemAddMessageListener  implements MessageListener{

	@Autowired
	private ItemSearchMapper itemSearchMapper;
	@Autowired
	private SolrServer solrServer;
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			Long itemId = Long.parseLong(textMessage.getText());
			//等待事务提交，否则出现空指针异常
			Thread.sleep(1000);
			SearchItem searchItem = itemSearchMapper.getItemById(itemId);
			SolrInputDocument document = new SolrInputDocument();
			//添加消息文本域
			document.addField("id", searchItem.getId());
			document.addField("item_category_name", searchItem.getCategory_name());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			solrServer.add(document);
			solrServer.commit();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		
	}

}
