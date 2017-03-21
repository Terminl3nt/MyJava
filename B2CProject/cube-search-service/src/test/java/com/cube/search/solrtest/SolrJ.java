package com.cube.search.solrtest;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJ {

	//添加，更新一样，都是添加一个id一样的
	@Test
	public void addDocument() throws SolrServerException, IOException{
		//1. 创建一个solrServer对象，创建一个连接，参数solr服务的url
		SolrServer  server = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//创建一个文档对象SolrInputDocument
		SolrInputDocument document = new SolrInputDocument();
		//向文档对象中添加域，文档ixu包含一个id，所有域的名称必须再schema.xml中定义
		document.addField("id", "doc1");
		document.addField("item_title", "flyCup");
		document.addField("item_price", 200000);
		//把文档写入索引库
		server.add(document);
		//提交
		server.commit();
	}
	//删除
	@Test
	public void delDocument() throws Exception, IOException{
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/");
		solrServer.deleteByQuery("id:doc1");
		solrServer.commit();
	}
}
