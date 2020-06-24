package com.cy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.activity.pojo.Activity.Activity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class wwww {
	 @Test
     public void testAty01() {
             Activity aty=new Activity();
             aty.setId(10);
             aty.setTitle("A-Title");
             aty.setCategory("A-Type");
             System.out.println(aty.getId());
             System.out.println(aty.getTitle());
             System.out.println(aty.getCategory());
             log.info(aty.toString());
     }
}
