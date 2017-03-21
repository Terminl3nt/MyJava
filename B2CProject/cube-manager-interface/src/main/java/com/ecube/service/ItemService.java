package com.ecube.service;

import java.util.List;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.ecube.pojo.TbItem;
import com.ecube.pojo.TbItemDesc;

public interface ItemService {

	TbItem getItemById(long itemId);
	EasyUIDataGridResult getItemList(int page,int rows);
	//商品添加
	CubeResult addItem(TbItem tbItem , String desc);
	//编辑回显商品
	TbItem reshowItem(TbItem tbItem);
//	TbItemDesc reshowDesc(TbItem tbItem);
	//商品详情的之商品描述
	TbItemDesc getItemDescById(long itemId);
	
}
