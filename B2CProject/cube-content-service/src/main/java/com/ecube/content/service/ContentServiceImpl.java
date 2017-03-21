package com.ecube.content.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cube.common.jedis.JedisClient;
import com.cube.common.utils.JsonUtils;
import com.ecube.mapper.TbContentMapper;
import com.ecube.pojo.TbContent;
import com.ecube.pojo.TbContentExample;
import com.ecube.pojo.TbContentExample.Criteria;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${CONTENT_LIST}")
	private String CONTENT_LIST;
	
	@Override
	public List<TbContent> showContentPage(long categoryid) {
		//查询数据库之前需要查询缓存
		//如果查询缓存的时候出现异常，不能影响到业务逻辑
		try {
			String json = jedisClient.hget(CONTENT_LIST, categoryid+"");
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//没有查到缓存后，需要进行查询数据库，然后再加入缓存
		try {
			jedisClient.hset(CONTENT_LIST,categoryid+"", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
