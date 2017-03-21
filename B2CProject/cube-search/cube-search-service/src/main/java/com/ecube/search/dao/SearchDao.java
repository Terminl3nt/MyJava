package com.ecube.search.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cube.pojo.SearchItem;
import com.cube.pojo.SearchResult;

@Repository
public class SearchDao {
	@Autowired
	private SolrServer solrServer;
	
	public SearchResult getSearchResult(SolrQuery query) throws SolrServerException{
		//根据query查询索引库
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList results = response.getResults();
		SearchResult searchResult = new SearchResult();
		searchResult.setRecourdCount(results.getNumFound());
		//取商品列表，需要高亮显示
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		List<SearchItem> searchItems = new ArrayList<>();
		for (SolrDocument solrDocument : results) {
			SearchItem searchItem = new SearchItem();
			searchItem.setId((String) solrDocument.get("id"));
			searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
			searchItem.setImage((String) solrDocument.get("item_image"));
			searchItem.setPrice((long) solrDocument.get("item_price"));
			searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
	
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			String title =null;
			if (list!=null && list.size()>0) {
				title = list.get(0);
			}else {
				title=(String) solrDocument.get("item_title");
			}
			searchItem.setTitle(title);
			searchItems.add(searchItem);
		}
		searchResult.setItemList(searchItems);
		
		//返回列表
		return searchResult;
	}
}
