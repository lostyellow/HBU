package com.hyfang.healthbasedonuser.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {
    /**
     * 配置 OpenAPI 全局信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("健康管理平台 API 文档")  // 文档标题
                        .description("基于用户的健康管理系统接口文档")  // 文档描述
                        .version("1.0.0")  // API 版本
                        .contact(new Contact()
                                .name("开发者名称")  // 联系人姓名
                                .email("developer@hyfang.com"))  // 联系人邮箱
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }

    /**
     * 配置默认分组（扫描指定包）
     */
    @Bean
    public GroupedOpenApi defaultApi() {
        return GroupedOpenApi.builder()
                .group("default")  // 分组名称
                .packagesToScan("com.hyfang.healthbasedonuser")  // 你的包路径
                .build();
    }
}
