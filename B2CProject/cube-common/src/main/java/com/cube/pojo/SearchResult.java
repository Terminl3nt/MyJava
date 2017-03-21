package com.cube.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResult  implements Serializable{

	/**
	 * 
	 */
	//进行索引搜索的分页处理
	private static final long serialVersionUID = 1L;
	private long recourdCount;
	private  List<SearchItem> itemList;
	private int totalPages;
	public long getRecourdCount() {
		return recourdCount;
	}
	public void setRecourdCount(long recourdCount) {
		this.recourdCount = recourdCount;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
}
