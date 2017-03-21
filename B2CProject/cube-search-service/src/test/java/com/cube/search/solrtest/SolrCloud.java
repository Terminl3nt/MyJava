package com.cube.search.solrtest;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrCloud {

	@Test
	public void addSolrCloud() throws SolrServerException, IOException{
//		第二步：创建一个SolrServer对象，需要使用CloudSolrServer子类。构造方法的参数是zookeeper的地址列表。
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.131:2181,192.168.25.131:2182,192.168.25.131:2183,192.168.25.131:2184");
//		第三步：需要设置DefaultCollection属性。
		solrServer.setDefaultCollection("collection2");
//		第四步：创建一SolrInputDocument对象。
		SolrInputDocument document = new SolrInputDocument();
//		第五步：向文档对象中添加域
		document.addField("item_title", "LOL外设");
		document.addField("item_price", 10100);
		document.addField("id", "solrCluster");
//		第六步：把文档对象写入索引库。
		UpdateResponse response = solrServer.add(document);
//		第七步：提交。
		solrServer.commit();

	}
}
