package com.worhard.study.dao.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TestMapper {
    public Map<String,String> test1();
}
