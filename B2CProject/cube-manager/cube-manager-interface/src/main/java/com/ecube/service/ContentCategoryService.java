package com.ecube.service;

import java.util.List;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.cube.pojo.EasyUITreeNode;

public interface ContentCategoryService {
	//前台分类展示
	List<EasyUITreeNode> getContentCategoryList(long parentId);
	//前台内容分类添加
	CubeResult addContentCategory(long parentId,String name);
	
}
