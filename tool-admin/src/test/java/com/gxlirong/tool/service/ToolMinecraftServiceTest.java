package com.gxlirong.tool.service;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ToolMinecraftServiceTest {
    @Autowired
    private ToolMinecraftService minecraftService;

    @Test
    void chineseLocalizationMod() {
        minecraftService.ChineseLocalizationMod("");
    }
}
