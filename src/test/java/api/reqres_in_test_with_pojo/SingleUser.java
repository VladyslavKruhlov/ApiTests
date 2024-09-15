package api.reqres_in_test_with_pojo;

import api.reqres_in_test_with_pojo.pojo.entity.user.get_user_info.UserFields;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static api.Constants.*;

import static api.reqres_in_test_with_pojo.request.SendRequest.sendGetRequest;
import static api.reqres_in_test_with_pojo.specification.RequestSpecification.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class SingleUser {

    @Test(groups = "ReqresInPojo", description = "Get single user info")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("v.kruhlov")
    @Epic("API")
    @Feature("CRUD")
    @Story("Get method")
    @Link(name = "Website", url = "https://reqres.in/")
    public void getSingleUserInfo() {
        Response response = sendGetRequest(
                requestSpecification,
                REQRES_IN_ENDPOINT_FOR_USERS+REQRES_IN_USER_ID,
                HttpStatus.SC_OK
        );

        UserFields userFields = response.getBody().as(UserFields.class);

        response
                .then()
                .log().all()
                .body(matchesJsonSchemaInClasspath("single-user-schema.json"));

        assertEquals(userFields.getData().getId(), REQRES_IN_USER_ID);
        assertEquals(userFields.getData().getFirstName(), REQRES_IN_USER_FIRST_NAME);
        assertEquals(userFields.getData().getLastName(), REQRES_IN_USER_LAST_NAME);
        assertEquals(userFields.getSupport().getUrl(), REQRES_IN_USER_SUPPORT_URL);
    }

    @Test(groups = "ReqresInPojo", description = "Single user info not found")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("v.kruhlov")
    @Epic("API")
    @Feature("CRUD")
    @Story("Get method")
    @Link(name = "Website", url = "https://reqres.in/")
    public void getSingleUserInfoNotFound() {
        Response response = sendGetRequest(
                requestSpecification,
                REQRES_IN_ENDPOINT_FOR_USERS+REQRES_IN_INCORRECT_USER_ID,
                HttpStatus.SC_NOT_FOUND
        );

        response
                .then()
                .log().all()
                .body(equalTo("{}"));
    }
}