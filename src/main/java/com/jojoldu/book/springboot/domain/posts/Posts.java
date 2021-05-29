package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
Posts 클래스는 실제 DB의 테이블과 매칭될 클래스로 `Entity 클래스`라고 부른다.
DB데이터에 작업할 경우 실제 쿼리를 날리기 보단 이 Entity 클래스의 수정을 통해 작업

1. @Entity
- 테이블과 링크될 클래스
- 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭한다.
- ex) SalesManager.java -> sales_manager table

아래 3개는 lombok 어노테이션
2. @NoArgsConstructor
- 기본생성자 자동추가
- public Posts() {} 와 같음

3. @Getter
- 클래스 내 모든 필드의 Getter 메소드 자동생성

4. @Builder
- 해당 클래스의 빌더 패턴 클래스를 생성
- 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
*/

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    /*
    1. @Id
    - PK 필드
    2. @GeneratedValue
    - PK 생성규칙
    - GenerationType.IDENTITY 옵션을 추가해야 auto_increment 된다.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    1. @Column
    - 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 된다.
    - 옵션을 추가할 때만 선언해도 된다.
    */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
