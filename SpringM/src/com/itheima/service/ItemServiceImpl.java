package com.itheima.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itheima.Dao.ItemsMapper;
import com.itheima.pojo.Items;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemsMapper itemMapper;

	@Override
	public List<Items> QueryItem() {
		List<Items> list = itemMapper.selectByExampleWithBLOBs(null);
		return list;
	}

}
