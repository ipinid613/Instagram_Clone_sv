package com.sparta.instagram_clone_sv.configuration;

import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfiguration {
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
//                .paths(PathSelectors.ant("/api/**")) // 그중 /api/ 인 URL들만 필터링
//                .build();
//    }
//}

// SWAGGER 3.0을 사용하기 위한 코드 //
// build.gradle에 관련된 dependency 추가 필요함(build.gradle)에서 확인할 것 //
// 3.0 이하 버전들을 작성할 때에는 @EnableSwagger2 어노테이션 작성이 필요했으나, 3.0부터는 제외 가능 //
// 접속 주소 : http://localhost:8080/swagger-ui/#/ //
@Configuration
public class SpringFoxConfig{
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //apiInfo는 선택 요소임. 본 프로젝트에서 사용한 api들을 소개하는 내용을 담을 수 있는 부분임. 아래 apiInfo 메서드 볼 것.
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    // 위 Docket의 apiInfo에 포함될 내용들 작성
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Instagram Clone Project")
                .version("1.0")
                .description("This is a teamwork-based project to clone a service [Instagram]. 2 Frontend devs," +
                        " 2 backend devs used React.js, Redux for client-side & Java Spring for server-side to make this service. We focused on making" +
                        " exactly same major functions on Instagram ; Likes, Follow(Following), Comments, Posts and etc.")
                .license("HangHae99 Team 22nd")
                .build();
    }
}
