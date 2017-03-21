package com.ecube.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cube.pojo.EasyUITreeNode;
import com.ecube.mapper.TbItemCatMapper;
import com.ecube.pojo.TbItemCat;
import com.ecube.pojo.TbItemCatExample;
import com.ecube.pojo.TbItemCatExample.Criteria;
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Override
	public List<EasyUITreeNode> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> rEasyUITreeNodes = new ArrayList<EasyUITreeNode>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNode treeNode = new EasyUITreeNode();
			treeNode.setId(tbItemCat.getId());
			treeNode.setText(tbItemCat.getName());
			treeNode.setState(tbItemCat.getIsParent() ? "closed":"open");
			rEasyUITreeNodes.add(treeNode);
		}
		return rEasyUITreeNodes;
	}

}
