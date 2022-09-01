package com.example.demo;

import com.example.demo.web.dto.HelloRequest;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void setUp() {
        RestAssured.port = serverPort;
        RestAssured.filters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter()));
    }

    @Test
    void successTest(){
        with().contentType(ContentType.JSON).body(new HelloRequest("Sanya"))
                .when()
                .post("/hello")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}