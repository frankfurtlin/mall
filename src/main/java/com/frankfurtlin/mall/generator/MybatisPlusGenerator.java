package com.frankfurtlin.mall.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author Frankfurtlin
 * @version 1.0
 * @date 2022/4/22 9:18
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        //当前项目路径
        String projectPath = System.getProperty("user.dir");

        String url = "jdbc:mysql://localhost:3306/mall?serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        // 代码生成器
        FastAutoGenerator.create(url, username, password)
                // 全局配置 GlobalConfig
                .globalConfig(builder -> {
                    builder.author("Frankfurtlin")   // 设置作者名
//                            .fileOverride()  // 开启覆盖已生成文件，默认值false
                            .disableOpenDir() // 禁止打开输出目录，默认值:true
                            .enableSwagger() // 开启 swagger 模式，默认值false
//                            .dateType(DateType.ONLY_DATE)  // 定义生成的实体类中日期类型
                            .commentDate("yyyy-MM-dd") // 注释日期，默认值: yyyy-MM-dd
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                // 包配置 PackageConfig
                .packageConfig(builder -> {
                    builder.parent("com.frankfurtlin")        // 设置父包名
                            .moduleName("mall")     // 父包模块名，默认值:无
                            .entity("model.entity")       // Entity包名
                            .service("service")     // Service包名
                            .serviceImpl("service.impl") // ServiceImpl包名
                            .controller("controller")   // Controller包名
                            .mapper("mapper")           // Mapper包名
                            .xml("mapper")              // Mapper XML包名
                            // 路径配置信息，设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper"));
                })
                // 模板配置 TemplateConfig
                /*.templateConfig(builder -> {
                    builder.disable();
                })*/
                // 策略配置 Mapper
                .strategyConfig(builder -> {
                    builder.mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList();
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
