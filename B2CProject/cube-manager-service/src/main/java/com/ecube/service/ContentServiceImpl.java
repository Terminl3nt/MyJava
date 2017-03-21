package com.ecube.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cube.common.jedis.JedisClient;
import com.cube.common.utils.CubeResult;
import com.cube.pojo.EasyUIDataGridResult;
import com.ecube.mapper.TbContentMapper;
import com.ecube.pojo.TbContent;
import com.ecube.pojo.TbContentExample;
import com.ecube.pojo.TbContentExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper tbContentMapper;
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	
	@Override
	public EasyUIDataGridResult getContentPageList(Long categoryId, int rows, int page) {
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		PageHelper pageHelper = new PageHelper();
		//查询所有的parentId内容的集合
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = tbContentMapper.selectByExample(example);
		result.setRows(list);
		//设置分页信息
		pageHelper.startPage(page, rows);
		//获取总数
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}

	//添加
	@Override
	public CubeResult addContent(TbContent tbContent) {
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		tbContentMapper.insert(tbContent);
		CubeResult cubeResult = new CubeResult();
		//进行缓存同步
		jedisClient.hdel(CONTENT_LIST, tbContent.getCategoryId().toString());
		return cubeResult.ok();
	}

}
