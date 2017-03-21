package com.cube.redis.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cube.common.jedis.JedisClient;


public class JedisSpringTest {

	@Test
	public void jedisStandalone(){
		ApplicationContext aContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = aContext.getBean(JedisClient.class);
		jedisClient.hset("hash1", "a", "b1");
		String string = jedisClient.hget("hash1", "a");
		
		System.out.println(string);
	}
}
