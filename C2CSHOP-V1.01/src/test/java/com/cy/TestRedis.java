package com.cy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.shop.common.util.RedisUtil;
import com.cy.shop.common.util.SendEmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	@Autowired
	private RedisUtil redisUtil;
	@Test
	public void contextLoads() {
	}
	@Test
	public void test01() {
		System.out.println("hello");
		redisUtil.set("name", "aaaaaa");
		Object object = redisUtil.get("name");
		System.out.println("获取数据name="+object);
	}
	
	@Test
	public void testEmail() throws Exception{
		SendEmailUtil.sendEmail("742395105@qq.com","test","title");
	}
	
}
