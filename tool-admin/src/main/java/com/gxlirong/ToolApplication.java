package com.gxlirong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gxlirong.tool.mapper")
public class ToolApplication {
    public static void main(String[] args) {
        SpringApplication.run(ToolApplication.class);
    }
}
