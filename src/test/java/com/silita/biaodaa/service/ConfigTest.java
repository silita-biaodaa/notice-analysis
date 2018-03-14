package com.silita.biaodaa.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Created by mayongbin01 on 2017/3/9.</p>
 */
//指定bean的配置文件
@ContextConfiguration(locations = {"classpath:config/spring/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigTest extends AbstractJUnit4SpringContextTests {




}
