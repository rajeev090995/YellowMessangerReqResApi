package com.api.BaseClass;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;

public class BaseTest {

    /**
     * Written By : Rajeev Kumar
     *
     * This is a base class for API Automation, this class can be used to quickly automate any API's using @test
     * annotation in the child class.
     *
     * This Base class uses Restassured, TestNG libraries.
     *
     */

    public static Logger logger = Logger.getLogger(BaseTest.class);
    public static RequestSpecification requestSpecification = given();

    @BeforeTest
    @Parameters({"baseURI"})
    public void beforeTest(@Optional String baseURI) {
        if (baseURI != null) {
            requestSpecification.baseUri(baseURI);
        }
        requestSpecification.header("Content-Type","application/json");
    }

    @BeforeMethod
    @Parameters({"basePath"})
    public void beforeMethod(@Optional String basePath) {
        if (basePath != null) {
            requestSpecification.basePath(basePath);
        }
    }

    @AfterTest
    public void afterTest() {
        reset();
    }
}
