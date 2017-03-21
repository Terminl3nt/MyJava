package com.ecube.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.wrapper.BaseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.cube.common.jedis.JedisClient;
import com.cube.common.utils.CubeResult;
import com.cube.common.utils.IDUtils;
import com.cube.common.utils.JsonUtils;
import com.cube.pojo.EasyUIDataGridResult;
import com.ecube.mapper.TbItemDescMapper;
import com.ecube.mapper.TbItemMapper;
import com.ecube.pojo.TbItem;
import com.ecube.pojo.TbItemDesc;
import com.ecube.pojo.TbItemExample;
import com.ecube.pojo.TbItemExample.Criteria;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import redis.clients.jedis.Jedis;
/**
 * 商品管理
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	@Autowired
	private JmsTemplate  jmsTemplate;
	@Resource
	private Destination topicDestination;  
	
	@Autowired
	private JedisClient jedisClient;
	@Value("${ITEM_INFO_PRE}")
	private String ITEM_INFO_PRE;
	
	@Value("${ITEM_CACHE_EXPIRE}")
	private int ITEM_CACHE_EXPIRE;
	
	@Override
	public TbItem getItemById(long itemId) {
		//查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId+":BASE");
			if (StringUtils.isNotBlank(json)) {
			TbItem tbItem = JsonUtils.jsonToPojo(json, TbItem.class);
			return tbItem;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		TbItem tbItem = tbItemMapper.selectByPrimaryKey(itemId);
		TbItemExample example = new TbItemExample();
			Criteria createCriteria = example.createCriteria();
		//设置查询条件
		createCriteria.andIdEqualTo(itemId);
		List<TbItem> list = tbItemMapper.selectByExample(example);
		if (list!=null && list.size()>0) {
			TbItem tbItem = list.get(0);
			//添加缓存
			try {
				//没有使用hset的原因是因为hset只能给大key设置存活时间，不能给field——key设置存活时间
				jedisClient.set(ITEM_INFO_PRE+":" + itemId + ":BASE", JsonUtils.objectToJson(tbItem));
				//为了就提高redis效率，在这里设置存活时间
				jedisClient.expire(ITEM_INFO_PRE+":" +itemId + ":BASE", ITEM_CACHE_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return tbItem;
		}
		return null;
	}
	
	
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//取分页结果
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		result.setTotal(total);
		return result;
	}
	//商品添加
	
	@Override
	public CubeResult addItem(TbItem tbItem, String desc) {
		//生成随机ID
		final long id = IDUtils.genItemId();
		//补全Item属性
		tbItem.setId(id);
		//Item状态码
		tbItem.setStatus((byte)1);
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItemMapper.insert(tbItem);
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDescMapper.insert(tbItemDesc);
		jmsTemplate.send(topicDestination,new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = session.createTextMessage(id + "");
				return textMessage;
			}
		});
		return CubeResult.ok();
	}
	//回显商品
	@Override
	public TbItem reshowItem(TbItem tbItem) {
		TbItem item = tbItemMapper.selectByPrimaryKey(tbItem.getId());
		return item;
	}
//	@Override
//	public TbItemDesc reshowDesc(TbItem tbItem) {
//		TbItemDesc desc = tbItemDescMapper.selectByPrimaryKey(tbItem.getId());
//		return desc;
//	}
	@Override
	public TbItemDesc getItemDescById(long itemId) {
		//查询缓存
		try {
			String json = jedisClient.get(ITEM_INFO_PRE + ":" + itemId + ":DESC" );
			if (StringUtils.isNotBlank(json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return itemDesc;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		//添加缓存
		try {
			jedisClient.set(ITEM_INFO_PRE + ":" + itemId + ":DESC" ,JsonUtils.objectToJson(tbItemDesc));
			jedisClient.expire(ITEM_INFO_PRE + ":" + itemId + ":DESC" , ITEM_CACHE_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tbItemDesc;
	}
	

}
