package com.ecube.service;

import java.util.List;

import com.cube.pojo.EasyUITreeNode;

public interface ItemCatService {

	List<EasyUITreeNode> getCatList(long parentId);
}
