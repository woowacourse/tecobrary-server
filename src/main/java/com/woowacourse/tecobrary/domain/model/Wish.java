package com.woowacourse.tecobrary.domain.model;

import com.woowacourse.tecobrary.domain.model.core.BasicEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"image", "title", "author", "publisher", "isbn", "desc", "userId"}, callSuper = false)
public class Wish extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "IMAGE")
    private String image;

    @Column(name = "TITLE", length = 100, unique = true)
    private String title;

    @Column(name = "AUTHOR", length = 20)
    private String author;

    @Column(name = "PUBLISHER", length = 50)
    private String publisher;

    @Column(name = "ISBN", length = 100)
    private String isbn;

    @Lob
    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String desc;

    @Column(name = "USER_ID")
    private Long userId;

    @Builder
    public Wish(String image, String title, String author, String publisher, String isbn, String desc, Long userId) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.desc = desc;
        this.userId = userId;
    }
}
