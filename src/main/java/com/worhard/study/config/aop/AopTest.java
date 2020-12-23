package com.worhard.study.config.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author luowen
 * @description
 * @since 2020/12/23
 */
@Component
@Aspect
public class AopTest {

    @Pointcut("execution( * com.worhard.study.dao.mapper.*.*(..))")
    public void aoptest(){}

    @Before("aoptest()")
    public void beforeaoptest(){
        System.out.println("进入切面了=======================");
    }

}
