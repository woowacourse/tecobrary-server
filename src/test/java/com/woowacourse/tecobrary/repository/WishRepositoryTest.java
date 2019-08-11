package com.woowacourse.tecobrary.repository;

import com.woowacourse.tecobrary.domain.model.Wish;
import com.woowacourse.tecobrary.exception.WishNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WishRepositoryTest {
    private static final String IMAGE = "image";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String PUBLISHER = "publisher";
    private static final String ISBN = "isbn";
    private static final String DESC = "desc";
    private static final Long USER_ID = 1L;

    @Autowired
    private WishRepository wishRepository;

    private Wish wish;

    @BeforeEach
    void setUp() {
        wish = Wish.builder()
                .title(TITLE).image(IMAGE)
                .author(AUTHOR).publisher(PUBLISHER)
                .desc(DESC).isbn(ISBN)
                .userId(USER_ID).build();
    }

    @Test
    @DisplayName("타이틀을 통해 희망도서를 찾는다.")
    void findByTitle() {
        wishRepository.save(wish);
        Wish foundWish = wishRepository.findByTitle(TITLE).orElseThrow(() -> new WishNotFoundException("희망도서를 찾을 수 없습니다."));

        assertThat(wish).isEqualTo(foundWish);
    }

    @AfterEach
    void tearDown() {
        wishRepository.deleteAll();
    }
}
