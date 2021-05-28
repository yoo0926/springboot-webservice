package com.jojoldu.book.springboot.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/*
1. @ExtendWith(SpringExtension.class)
- 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
- 여기서는 SpringExtension 이라는 스프링 실행자를 사용한다.
- 스프링 부트 테스트와 JUnit 사이에 연결자 역할
- JUnit4 -> 5로 넘어오면서 사용하는 어노테이션과 클래스가 각각 @RunWith -> @ExtendWith 로 SpringRunner -> SpringExtension 으로 변경되었다.

2. @WebMvcTest
- 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
- 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
- 단, @Service, @Component, @Repository 등은 사용할 수 없다.
*/
@ExtendWith(SpringExtension.class)//1
@WebMvcTest(controllers = HelloController.class)//2
public class HelloControllerTest {

    /*
    3. AutoWired
    - 스프링이 관리하는 빈(Bean)을 주입 받는다.

    4. private MockMvc mvc
    - 웹 API를 테스트할 때 사용한다.
    - 스프링 MVC 테스트의 시작점
    - 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.
    */
    @Autowired//3
    private MockMvc mvc;//4

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        /*
        5. mvc.perform(get("/hello"))
        - MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
        - 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있다.

        6. .andExpect(status().isOk())
        - mvc.perform의 결과를 검증한다.
        - HTTP Header의 Status를 검증한다.
        - 우리가 흔히 알고 있는 200, 404, 500 emddml 상태를 검증한다.
        - 여기선 OK 즉, 200인지 아닌지를 검증한다.

        7. .andExpect(content().string(hello))
        - mvc.perform의 결과를 검증한다.
        - 응답 본문의 내용을 검증한다.
        - Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증한다.
        */
        mvc.perform(get("/hello"))//5
                .andExpect(status().isOk())//6
                .andExpect(content().string(hello));//7
    }

    @Test
    public void helloDto가_리턴된다()throws Exception {
        String name = "name";
        int amount = 1000;

        /*
        1. param
        - api 테스트할 때 사용될 요청 파라미터를 설정
        - 단, 값은 String만 허용
        - 숫자, 날짜 모두 문자열로 변경해야한다.

        2. jsonPath
        - JSON 응답값을 필드별로 검증할 수 있는 메소드
        - $를 기준으로 필드명을 명시
        */
        mvc.perform(get("/hello/dto")
                            .param("name", name)//1
                            .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))//2
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}