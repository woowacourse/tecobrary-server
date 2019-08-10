package com.woowacourse.tecobrary.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowacourse.tecobrary.controller.dto.WishRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;


public class Utils {

    public static String createWishBook(String baseUrl, WishRequestDto wishRequestDto) {
        RestAssured.baseURI = baseUrl;

        return given().
                contentType(String.valueOf(MediaType.APPLICATION_JSON_UTF8)).
                body(wishRequestDto).
                accept(ContentType.JSON.getAcceptHeader()).
                when().
                post("/wishes").
                getBody().
                print();
    }

    public static String getWishTitle(byte[] responseBody) throws IOException {
        HashMap jsonData = new ObjectMapper().readValue(new String(responseBody), HashMap.class);
        return jsonData.get("title").toString();
    }

    public static String findByWishId(String baseUrl, String title) {
        RestAssured.baseURI = baseUrl;

        return given().
                    contentType(String.valueOf(MediaType.APPLICATION_JSON_UTF8)).
                    param("title", title).
                when().
                    get("/wishes").
                    getBody().
                    print();
    }

    public static String getWishId(byte[] responseBody) throws IOException {
        HashMap jsonData = new ObjectMapper().readValue(new String(responseBody), HashMap.class);
        return jsonData.get("id").toString();
    }
}
