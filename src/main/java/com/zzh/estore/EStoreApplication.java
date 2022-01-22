package com.zzh.estore;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

//@Configuration // 表示配置类（设置上传文件最大值用到）
@EnableOpenApi
@SpringBootApplication
@MapperScan("com.zzh.estore.mapper")
public class EStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(EStoreApplication.class, args);
    }

    // 设置上传文件最大值
//    @Bean
//    public MultipartConfigElement getMultipartConfigElement() {
//        // 创建一个配置的工厂类对象
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//
//        // 设置需要创建的对象的相关信息（大小，单位）
//        factory.setMaxFileSize(DataSize.of
//                (10, DataUnit.MEGABYTES));
//        factory.setMaxRequestSize(DataSize.of
//                (15, DataUnit.MEGABYTES));
//        // 通过工厂类来创建MultipartConfigElement对象
//        return factory.createMultipartConfig();
//
//    }
}
