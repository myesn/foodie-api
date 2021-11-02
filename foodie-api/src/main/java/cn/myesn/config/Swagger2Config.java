package cn.myesn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    // todo: 这个 swagger ui 太老了，后期空了升级到最新版本
    // /swagger-ui.html  包：springfox-swagger-ui
    // /doc.html   包swagger-bootstrap-ui

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("cn.myesn.controller")
                )
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("foodie 接口文档")
                .contact(new Contact(
                        "myesn",
                        "https://github.com/myesn",
                        "myesn@foxmail.com"
                ))
                .description("专为 foodie 提供的 api 文档")
                .version("0.0.1")
                .termsOfServiceUrl("https://foodie.shop")
                .build();
    }
}
