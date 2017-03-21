package com.ecube.search.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.SearchItem;
import com.cube.pojo.SearchResult;
import com.ecube.search.dao.SearchDao;
import com.ecube.search.mapper.ItemSearchMapper;
import com.ecube.search.service.SearchItemService;


@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private ItemSearchMapper itemSeachMapper;
	@Autowired
	private SolrServer solrserver;
	
	@Autowired
	private SearchDao searchDao;
	@Value("${DEFAULT_FEILED}")
	private String DEFAULT_FEILED;
	
	@Override
	public CubeResult importAiiItem() {
			try {
				//查询商品列表
				List<SearchItem> itemList = itemSeachMapper.getItemList();
				//遍历商品列表
				for (SearchItem searchItem : itemList) {
					//创建文本对象
					SolrInputDocument document = new SolrInputDocument();
					//向文本对象中添加域
					document.addField("id", searchItem.getId());
					document.addField("item_title", searchItem.getTitle());
					document.addField("item_category_name", searchItem.getCategory_name());
					document.addField("item_sell_point", searchItem.getSell_point());
					document.addField("item_image", searchItem.getImage());
					document.addField("item_price", searchItem.getPrice());
						//将文本写入索引库
						solrserver.add(document);
				}
					//	提交	
				solrserver.commit();
				return CubeResult.ok();
			} catch (SolrServerException | IOException e) {
				e.printStackTrace();
				return CubeResult.build(1000, "数据导入时拉屎");
			}
	}
	@Override
	public SearchResult findSearchByQuery(String keyword, int page, int rows) throws Exception {
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(keyword);
		//设置默认查询域
		query.set("df",DEFAULT_FEILED );
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置高亮显示
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style = \"color:red\">");
		query.setHighlightSimplePost("</em");
		//执行查询
		SearchResult searchResult = searchDao.getSearchResult(query);
		//计算总页数，总页数=总个数/每页显示条数
		int recourdCount = (int) searchResult.getRecourdCount();
		int pages = recourdCount/rows;
		if (recourdCount%rows >0) {
			pages++;
		}
		searchResult.setTotalPages(pages);
		return searchResult;
	}
}
