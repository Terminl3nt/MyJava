package com.cube.pageTestHelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecube.mapper.TbItemMapper;
import com.ecube.pojo.TbItem;
import com.ecube.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class PageHelperTest {

	@Test
	public void testPageHelper(){
		//初始化spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbItemMapper tbItemMapper = ac.getBean(TbItemMapper.class);
		//执行sql语句之前设置分页信息PageHelper的start Page方法
		//第一页，每页显示10条
		PageHelper.startPage(1, 10);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//取分页信息，PageInfo，1，总记录数2，总页数。当前页码
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		System.out.println(pageInfo.getFirstPage());	//第一页
		System.out.println(pageInfo.getPageNum());//当前页码
		System.out.println(pageInfo.getTotal());//总记录数
		System.out.println(pageInfo.getPages());//总页数
		System.out.println(list.size());//每页显示条数
		
	}
}
