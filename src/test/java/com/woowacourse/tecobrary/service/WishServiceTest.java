package com.woowacourse.tecobrary.service;

import com.woowacourse.tecobrary.controller.dto.WishRequestDto;
import com.woowacourse.tecobrary.domain.model.Wish;
import com.woowacourse.tecobrary.repository.WishRepository;
import com.woowacourse.tecobrary.service.dto.WishResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class WishServiceTest {
    private static final String IMAGE = "image";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String PUBLISHER = "publisher";
    private static final String ISBN = "isbn";
    private static final String DESC = "desc";
    private static final Long USER_ID = 1L;
    private static final Boolean DELETE_ACTIVE = false;

    private static final Wish WISH = Wish.builder()
            .title(TITLE).image(IMAGE)
            .author(AUTHOR).publisher(PUBLISHER)
            .desc(DESC).isbn(ISBN)
            .userId(USER_ID)
            .build();

    @InjectMocks
    WishService wishService;

    @Mock
    WishRepository wishRepository;

    @Test
    @DisplayName("희망 도서를 저장한다.")
    void save() {
        WishRequestDto wishRequestDto = WishRequestDto.builder()
                .title(TITLE).image(IMAGE)
                .author(AUTHOR).publisher(PUBLISHER)
                .desc(DESC).isbn(ISBN)
                .userId(USER_ID).build();

        wishService.save(wishRequestDto);

        verify(wishRepository, atLeast(1)).save(WISH);
    }

    @Test
    @DisplayName("희망 도서를 찾는다.")
    void findByTitle() {
        given(wishRepository.findByTitle(TITLE)).willReturn(Optional.of(WISH));

        WishResponseDto wishResponseDto = wishService.findByTitle(TITLE);

        assertThat(wishResponseDto.getTitle()).isEqualTo(TITLE);
        assertThat(wishResponseDto.getImage()).isEqualTo(IMAGE);
        assertThat(wishResponseDto.getAuthor()).isEqualTo(AUTHOR);
        assertThat(wishResponseDto.getPublisher()).isEqualTo(PUBLISHER);
        assertThat(wishResponseDto.getDesc()).isEqualTo(DESC);
        assertThat(wishResponseDto.getIsbn()).isEqualTo(ISBN);
        assertThat(wishResponseDto.getUserId()).isEqualTo(USER_ID);

    }

    @Test
    @DisplayName("희망 도서를 소프트 삭제 한다.")
    void softDelete() {
        given(wishRepository.findByTitle(TITLE)).willReturn(Optional.of(WISH));
        WishResponseDto wishResponseDto = wishService.delete(TITLE);

        assertThat(wishResponseDto.isActive()).isEqualTo(DELETE_ACTIVE);
    }
}
