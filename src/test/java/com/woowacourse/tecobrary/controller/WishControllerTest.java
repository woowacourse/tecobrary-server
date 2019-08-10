package com.woowacourse.tecobrary.controller;

import com.woowacourse.tecobrary.controller.dto.WishRequestDto;
import com.woowacourse.tecobrary.repository.WishRepository;
import com.woowacourse.tecobrary.util.Utils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WishControllerTest {
    private static final String IMAGE = "image";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String PUBLISHER = "publisher";
    private static final String ISBN = "isbn";
    private static final String DESC = "desc";
    private static final Long USER_ID = 1L;

    private static final String NEW_IMAGE = "new_image";
    private static final String NEW_TITLE = "new_title";
    private static final String NEW_AUTHOR = "new_author";
    private static final String NEW_PUBLISHER = "new_publisher";
    private static final String NEW_ISBN = "new_isbn";
    private static final String NEW_DESC = "new_desc";
    private static final Long NEW_USER_ID = 2L;

    @LocalServerPort
    private int serverPort;

    @Autowired
    private WishRepository wishRepository;

    private String wishId;

    @BeforeEach
    void setUp() throws IOException {
        String baseUrl = "http://localhost:" + serverPort;
        String responseBody = Utils.createWishBook(baseUrl, WishRequestDto.builder()
                .image(IMAGE).title(TITLE)
                .author(AUTHOR).publisher(PUBLISHER)
                .isbn(ISBN).desc(DESC)
                .userId(USER_ID).build()
        );
        String title = Utils.getWishTitle(responseBody.getBytes());
        String responseBodyByFind = Utils.findByWishId(baseUrl, title);

        wishId = Utils.getWishId(responseBodyByFind.getBytes());
    }

    @Test
    @DisplayName("희망 도서 책 요청 데이터를 url 을 요청한다.")
    void save() {
        WishRequestDto newWishRequestDto = WishRequestDto.builder()
                .image(NEW_IMAGE).title(NEW_TITLE)
                .author(NEW_AUTHOR).publisher(NEW_PUBLISHER)
                .isbn(NEW_ISBN).desc(NEW_DESC)
                .userId(NEW_USER_ID).build();

        given().
                contentType(String.valueOf(MediaType.APPLICATION_JSON_UTF8)).
                body(newWishRequestDto).
                accept(ContentType.JSON.getAcceptHeader()).
        when().
                post("/wishes").
        then().
                assertThat().
                statusCode(is(200))
                .body("title", equalTo(NEW_TITLE));
    }

    @AfterEach
    void tearDown() {
        wishRepository.deleteAll();
    }
}