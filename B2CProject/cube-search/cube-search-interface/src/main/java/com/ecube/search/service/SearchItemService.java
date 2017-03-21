package com.ecube.search.service;

import java.io.Serializable;
import java.util.List;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.SearchItem;
import com.cube.pojo.SearchResult;

public interface SearchItemService {
	//TODO 进行搜索引擎导入功能
	CubeResult importAiiItem();
	
	//根据查询条件进行查询
	SearchResult findSearchByQuery(String keyword , int page, int rows) throws Exception;
}
