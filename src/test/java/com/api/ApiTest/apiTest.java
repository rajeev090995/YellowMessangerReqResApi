package com.api.ApiTest;

import com.api.BaseClass.BaseTest;
import com.api.assertionUtil.AssertionApiDetails;
import com.api.pojo.request.RequestPostPojo;
import com.api.pojo.response.ResponsePojo;
import com.api.pojo.response.ResponsePostPojo;
import com.api.pojo.response.ResponsePutPojo;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;
import org.assertj.core.api.SoftAssertions;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class apiTest extends BaseTest{

    ResponsePojo responsePojo=new ResponsePojo();
    RequestPostPojo requestPostPojo=new RequestPostPojo();
    AssertionApiDetails assertionApiDetails=new AssertionApiDetails();
    ResponsePostPojo responsePostPojo=new ResponsePostPojo();
    ResponsePutPojo responsePutPojo=new ResponsePutPojo();

    @Test(priority = 1)
    @Parameters({"page","company","url","text"})
    void listUsers(String page,String company,String url,String text) {
        Response response = requestSpecification.queryParam("page",page).get();
        logger.info(response.asString());
        responsePojo = response.as(ResponsePojo.class, ObjectMapperType.GSON);
        SoftAssertions softAssertions=new SoftAssertions();
        assertionApiDetails.assertPositiveResponse(softAssertions,response,responsePojo,page,company,url,text);
        assertionApiDetails.assertDataDetails(softAssertions,response,responsePojo);
        softAssertions.assertAll();
    }

    @Test(priority = 2)
    @Parameters({"baseURI","name","job"})
    void createUsers(String baseURI,String name,String job) {
        requestPostPojo.setName(name);
        requestPostPojo.setJob(job);
        RequestSpecification requestSpecification = given().config(new RestAssuredConfig());
        requestSpecification.body(requestPostPojo);
        Response response = requestSpecification.request("POST",baseURI);
        logger.info(response.asString());
        responsePostPojo = response.as(ResponsePostPojo.class, ObjectMapperType.GSON);
        SoftAssertions softAssertions=new SoftAssertions();
        assertionApiDetails.assertPostResponse(softAssertions,response,responsePostPojo);
        softAssertions.assertAll();
    }

    @Test(priority = 3)
    @Parameters({"baseURI","name","job","id"})
    void updateUsers(String baseURI,String name,String job,String id) {
        requestPostPojo.setName(name);
        requestPostPojo.setJob(job);
        RequestSpecification requestSpecification = given().config(new RestAssuredConfig());
        requestSpecification.body(requestPostPojo);
        baseURI=baseURI+"/"+id;
        Response response = requestSpecification.request("PUT",baseURI);
        logger.info(response.asString());
        responsePutPojo = response.as(ResponsePutPojo.class, ObjectMapperType.GSON);
        SoftAssertions softAssertions=new SoftAssertions();
        assertionApiDetails.assertPutResponse(softAssertions,response,responsePutPojo);
        softAssertions.assertAll();

    }

    @Test(priority = 4)
    @Parameters({"baseURI","id"})
    void deleteUsers(String baseURI,String id) {
        RequestSpecification requestSpecification = given().config(new RestAssuredConfig());
        baseURI=baseURI+"/"+id;
        logger.info(baseURI);
        Response response = requestSpecification.request("DELETE",baseURI);
        logger.info(response.asString());
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),204);
    }

}
