# tool


## 前言

`tool`用来做工具自己工具的。
    - 已有功能:我的世界模组汉化组件

## 项目文档

- 文档地址：[暂无](暂无)

## 项目介绍

`tool`

### 项目演示

#### 后台管理系统

前端项目`tool-admin-web`地址：https://github.com/gtppoplp/tool-admin-web

项目演示地址： [http://tool.gxlirong.com](http://tool.gxlirong.com)  

### 组织结构

``` lua
tool
├── tool-common -- 工具类及通用代码
├── tool-mbg -- MyBatisGenerator生成的数据库操作代码
├── tool-admin -- 后台管理系统接口
└── tool-demo -- 框架搭建时的测试代码
```

### 技术选型

#### 后端技术

| 技术                 | 说明             
| -------------------- | -------------
| Spring Boot          | 容器+MVC框架     
| Spring Security      | 认证和授权框架      
| MyBatisPlus          | ORM框架        
| MyBatisGenerator     | 数据层代码生成      
| Swagger-UI           | 文档生产工具       
| Hibernator-Validator | 验证框架         
| RabbitMq             | 消息队列         
| Redis                | 分布式缓存        
| Mysql                | Mysql数据库     
| Docker               | 应用容器引擎       
| Druid                | 数据库连接池       
| JWT                  | JWT登录支持      
| logback              | 日志收集工具       
| Lombok               | 简化对象封装工具   

