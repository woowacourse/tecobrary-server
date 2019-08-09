package com.woowacourse.tecobrary.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Builder
@Entity
@Getter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 11)
    private Long id;

    @Column(nullable = true, length = 255, unique = true)
    private String url;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(nullable = true, length = 255)
    private String publisher;

    @Column(nullable = false, length = 255,unique = true)
    private String isbn;

    @Column(nullable = true, length = 255)
    private String desc;

    /* 추가해야 할 부분
    private createdAt;
    private updateAt;
    private deleteAt;*/
}
