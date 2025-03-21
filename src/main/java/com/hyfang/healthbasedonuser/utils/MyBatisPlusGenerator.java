package com.hyfang.healthbasedonuser.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.sql.Types;
import java.util.Collections;

@Slf4j
public class MyBatisPlusGenerator {
    static final String url = "jdbc:mysql://127.0.0.1:3306/hbu?serverTimezone=UTC";    // 数据库地址
    static final String username = "root";  // 数据库用户名
    static final String password = "fhy200245";  // 数据库密码
    static final String authorName = "hyfang"; // 作者名
    static final String parentPackageNameResource = "com/hyfang/healthbasedonuser";  // mapper.xml路径
    static final String parentPackageNameJava = "com.hyfang.healthbasedonuser";  // java 文件父包名
    // 要生成代码对应的数据表名
    static final String tableName = "health_category";


    public static void main(String[] args) {

        FastAutoGenerator.create(url, username, password)
                // 1.全局配置
                .globalConfig(builder -> {
                    builder.author(authorName)                // 设置作者
                            .enableSpringdoc()               // 开启 swagger 模式
                            // 获取当前工程路径并定位到项目java目录下
                            .outputDir(System.getProperty("user.dir") + "/src/main/java");            // 指定输出目录
                })

                // 2.数据源配置
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))

                // 3.包名策略配置
                .packageConfig(builder -> {
                    builder.parent(parentPackageNameJava) // 设置父包名
                            .entity("entity")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/" + parentPackageNameResource + "/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            // 覆盖已生成文件
                            .entityBuilder().enableFileOverride().enableLombok()
                            .mapperBuilder().enableFileOverride()
                            .serviceBuilder().enableFileOverride().formatServiceFileName("%sService");
                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀

                })

                // 配置模板
                .templateConfig(builder -> {
                    //builder.controller("");         // 不生成controller
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
