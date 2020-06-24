package com.cy.shop.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
public class MyObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;
    
    public MyObjectMapper() {
        super();
        // 去掉各种@JsonSerialize注解的解析
        this.configure(MapperFeature.USE_ANNOTATIONS, false);
        // 只针对非空的值进行序列化
        this.setSerializationInclusion(Include.NON_NULL);
        // 将类型序列化到属性json字符串中
        this.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
        
    }
}