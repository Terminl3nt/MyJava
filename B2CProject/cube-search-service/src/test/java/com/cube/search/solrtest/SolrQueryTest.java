package com.cube.search.solrtest;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SolrQueryTest {

	@Test
	public void QueryItem() throws SolrServerException{
//		第二步：创建一个SolrServer，使用HttpSolrServer创建对象。
		SolrServer server = new HttpSolrServer("http://192.168.25.131:8080/solr/");
//		第二步：创建一个SolrQuery对象。
		SolrQuery query = new SolrQuery();
//		第三步：向SolrQuery中添加查询条件、过滤条件。。。
//		query.setQuery("*:*");
		query.set("q", "*:*");
//		第四步：执行查询。得到一个Response对象。
		QueryResponse response = server.query(query);
		SolrDocumentList results = response.getResults();
//		第五步：取查询结果。
		System.out.println("查询的总数量是" + results.getNumFound());
//		第六步：遍历结果并打印。
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.getFieldValue("item_title"));
			System.out.println(solrDocument.getFieldValue("item_category_name"));
		}
	}
	//含有高亮显示
	@Test
	public void highLightQuery() throws SolrServerException{
		//创建SolrServer对象
		SolrServer solrServer = new HttpSolrServer("http://192.168.25.131:8080/solr/collection1");
		//创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		//向SolrQuery添加查询条件
		query.set("q", "手机");
		//指定默认搜索域\
		query.set("df", "item_title");
		//开启高亮显示
		query.setHighlight(true);
		//高亮显示的域
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		//执行查询得到一个Response对象
		QueryResponse response = solrServer.query(query);
		//获取查询结果
		SolrDocumentList results = response.getResults();
		System.out.println("总数为" + results.getNumFound());
		for (SolrDocument solrDocument : results) {
			//取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title=null;
			if (list!=null && list.size()>0) {
				title = list.get(0);
			}else {
				title = (String) solrDocument.get("item_title");
			}
			System.out.println(title);
			System.out.println(solrDocument.getFieldValue("item_category_name"));
			System.out.println(solrDocument.getFieldValue("item_price"));
		}
	}
}
