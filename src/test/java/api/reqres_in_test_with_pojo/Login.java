package api.reqres_in_test_with_pojo;

import api.reqres_in_test_with_pojo.pojo.body_request.ReqresInLoginRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.Constants.*;
import static api.reqres_in_test_with_pojo.request.SendRequest.sendPostRequest;
import static api.reqres_in_test_with_pojo.specification.RequestSpecification.requestSpecification;
import static org.hamcrest.Matchers.containsString;

public class Login {
    @Test(groups = "ReqresInPojo", description = "Successful login with pojo")
    public void loginSuccessful() {

        ReqresInLoginRequest reqresInLoginRequest = new ReqresInLoginRequest(
                REQRES_IN_EMAIL,
                REQRES_IN_PASSWORD);

        Response response = sendPostRequest(
                requestSpecification,
                reqresInLoginRequest,
                REQURES_IN_ENDPOINT_FOR_LOGIN,
                HttpStatus.SC_OK
        );

        String token = response.jsonPath().getString("token");
        Assert.assertFalse(token.isEmpty());

        response
                .then()
                .log().all()
                .body("token", containsString(REQRES_IN_TOKEN));
    }

    @Test (groups = "ReqresInPojo", description = "Unsuccessful login with pojo")
    public void loginUnsuccessful() {

        ReqresInLoginRequest reqresInLoginRequest = new ReqresInLoginRequest(
                REQRES_IN_EMAIL);

        Response response = sendPostRequest(
                requestSpecification,
                reqresInLoginRequest,
                REQURES_IN_ENDPOINT_FOR_LOGIN,
                HttpStatus.SC_BAD_REQUEST
        );

        String error = response.jsonPath().getString("error");
        Assert.assertFalse(error.isEmpty());

        response
                .then()
                .log().all()
                .body("error", containsString(REQRES_IN_BAD_REQUEST_MESSAGE));
    }
}