package com.api.assertionUtil;

import com.api.pojo.response.Datum;
import com.api.pojo.response.ResponsePojo;
import com.api.pojo.response.ResponsePostPojo;
import com.api.pojo.response.ResponsePutPojo;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class AssertionApiDetails {
    public static Logger logger = Logger.getLogger(AssertionApiDetails.class);

    public void assertPositiveResponse(SoftAssertions softAssertions,Response response, ResponsePojo responsePojo, String page,String company,String url,String text){
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(responsePojo.getPage().toString(), page);
        softAssert.assertEquals(responsePojo.getPerPage().intValue(), 6);
        softAssert.assertEquals(responsePojo.getTotal().intValue(), 12);
        softAssert.assertEquals(responsePojo.getTotalPages().intValue(), 2);

        if(responsePojo.getData().size()!=0) {
            List<Datum> actualList = responsePojo.getData();
            softAssert.assertEquals(actualList.size(), 6);
            for (int i = 0; i < actualList.size(); i++) {
                softAssertions.assertThat(actualList.get(i).getId()).isNotNull();
                softAssertions.assertThat(actualList.get(i).getEmail()).isNotNull();
                softAssertions.assertThat(actualList.get(i).getFirstName()).isNotNull();
                softAssertions.assertThat(actualList.get(i).getLastName()).isNotNull();
                softAssertions.assertThat(actualList.get(i).getAvatar()).isNotNull();
            }
        }
        softAssert.assertEquals(responsePojo.getAd().getCompany(),company);
        softAssert.assertEquals(responsePojo.getAd().getUrl(),url);
        softAssert.assertEquals(responsePojo.getAd().getText(),text);

    }

    public void assertDataDetails(SoftAssertions softAssertions,Response response, ResponsePojo responsePojo){
        SoftAssert softAssert=new SoftAssert();
        List<String> expectedIdList = Arrays.asList("7","8","9","10","11","12");
        List<String> expectedEmailList = Arrays.asList("michael.lawson@reqres.in","lindsay.ferguson@reqres.in","tobias.funke@reqres.in","byron.fields@reqres.in","george.edwards@reqres.in","rachel.howell@reqres.in");
        List<String> expectedFnList = Arrays.asList("Michael","Lindsay","Tobias","Byron","George","Rachel");
        List<String> expectedLnList = Arrays.asList("Lawson","Ferguson","Funke","Fields","Edwards","Howell");
        List<String> expectedAvatarList = Arrays.asList("https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg","https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg","https://s3.amazonaws.com/uifaces/faces/twitter/vivekprvr/128.jpg","https://s3.amazonaws.com/uifaces/faces/twitter/russoedu/128.jpg","https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg","https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg");
        if(response.getStatusCode()==200){
            List<Datum> actualList = responsePojo.getData();
            softAssert.assertEquals(actualList.size(), 6);
            for (int i = 0; i < actualList.size(); i++) {
                softAssertions.assertThat(actualList.get(i).getId()).asString().isEqualTo(expectedIdList.get(i));
                softAssertions.assertThat(actualList.get(i).getFirstName()).asString().isEqualTo(expectedFnList.get(i));
                softAssertions.assertThat(actualList.get(i).getLastName()).asString().isEqualTo(expectedLnList.get(i));
                softAssertions.assertThat(actualList.get(i).getEmail()).asString().isEqualTo(expectedEmailList.get(i));
                softAssertions.assertThat(actualList.get(i).getAvatar()).asString().isEqualTo(expectedAvatarList.get(i));
                logger.info("ID"+" "+actualList.get(i).getId());
                logger.info("First Name"+" "+actualList.get(i).getFirstName());
                logger.info("Last Name"+" "+actualList.get(i).getLastName());
                logger.info("Email"+" "+actualList.get(i).getEmail());
                logger.info("Avatar"+" "+actualList.get(i).getAvatar());
            }
        }

    }

    public void assertPostResponse(SoftAssertions softAssertions,Response response, ResponsePostPojo responsePostPojo){
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 201);
        softAssertions.assertThat(responsePostPojo.getId()).isNotNull();
        softAssertions.assertThat(responsePostPojo.getCreatedAt()).isNotNull();

    }

    public void assertPutResponse(SoftAssertions softAssertions,Response response, ResponsePutPojo responsePutPojo){
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssertions.assertThat(responsePutPojo.getUpdatedAt()).isNotNull();
        logger.info(responsePutPojo.getUpdatedAt());

    }
}
