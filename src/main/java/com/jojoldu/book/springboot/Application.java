package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
스프링부트의 자동설정, 스프링 Bean 읽기와 생성을 자동으로 설정
SpringBootApplication 어노테이션이 있는 위치부터 설정을 읽기 때문에 프로젝트 최상단에 위치
*/
//@EnableJpaAuditing > WebMvcTest 에서 스캔해서 분리
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);//내장 WAS 서버 실행
    }
}
