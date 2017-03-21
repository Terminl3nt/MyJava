package com.cube.redis.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	@Test
	public void jedisTest(){
		Jedis jedis  = new Jedis("192.168.25.131", 6379);
		String string = jedis.hget("ha", "b");
		System.out.println(string);
		jedis.close();
	}
	//使用Jedis连接池
	@Test
	public void jedisPoolTest(){
		JedisPool jedisPool = new JedisPool("192.168.25.131", 6379);
		Jedis jedis = jedisPool.getResource();
		String string = jedis.hget("ha", "b");
		System.out.println(string);
		jedis.close();
		jedisPool.close();
	}
	//链接集群版
	@Test
	public void JedisCluster(){
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.25.131", 7001));
		nodes.add(new HostAndPort("192.168.25.131", 7002));
		nodes.add(new HostAndPort("192.168.25.131", 7003));
		nodes.add(new HostAndPort("192.168.25.131", 7004));
		nodes.add(new HostAndPort("192.168.25.131", 7005));
		nodes.add(new HostAndPort("192.168.25.131", 7006));
		redis.clients.jedis.JedisCluster jedisCluster = new redis.clients.jedis.JedisCluster(nodes);
		jedisCluster.set("hehe", "haha");
		System.out.println(jedisCluster.get("hehe"));
		jedisCluster.close();
		
	
	}
}
