package com.ecube.service;

import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.ecube.pojo.TbContent;

public interface ContentService {
	//内容管理分页
			EasyUIDataGridResult getContentPageList(Long categoryId , int rows , int page);
	//内容添加
			CubeResult addContent(TbContent tbContent);
}
