package com.ecube.search.mapper;

import java.util.List;

import com.cube.pojo.SearchItem;

public interface ItemSearchMapper {

	List<SearchItem> getItemList();
	SearchItem getItemById(long itemId); 
	
}
