package com.gxlirong.tool.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Controller层单元测试 注入Spring 上下文的环境到 MockMvc 中  测试时会启动项目
 *
 * @author lirong
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ToolRbacAccountControllerTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
    }

    @Test
    public void testALogin() {
        String lirong801 = passwordEncoder.encode("lirong801");
        System.out.println(lirong801);
    }

}
