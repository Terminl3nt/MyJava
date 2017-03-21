package com.ecube.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.cube.pojo.EasyUITreeNode;
import com.ecube.mapper.TbContentCategoryMapper;
import com.ecube.pojo.TbContent;
import com.ecube.pojo.TbContentCategory;
import com.ecube.pojo.TbContentCategoryExample;
import com.ecube.pojo.TbContentCategoryExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询所有的商品类目
		List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> result = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			Long id = tbContentCategory.getId();
			String name = tbContentCategory.getName();
			easyUITreeNode.setId(id);
			easyUITreeNode.setText(name);
			easyUITreeNode.setState(tbContentCategory.getIsParent()?"closed" : "open");
			result.add(easyUITreeNode);
		}
		return result;
	}
	//添加叶子节点
	@Override
	public CubeResult addContentCategory(long parentId, String name) {
		TbContentCategory tbContentCategory = new TbContentCategory();
		tbContentCategory.setName(name);
		tbContentCategory.setSortOrder(1);
		tbContentCategory.setIsParent(false);
		Date date = new Date();
		tbContentCategory.setCreated(date);
		tbContentCategory.setUpdated(date);
		tbContentCategory.setParentId(parentId);
		tbContentCategoryMapper.insert(tbContentCategory);
		// 判断父节点的isparent是否为true，如果是false则改为true
		TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			//更新父节点的id
			tbContentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//进行主键返回
		return CubeResult.ok(tbContentCategory);
	}
	

}
