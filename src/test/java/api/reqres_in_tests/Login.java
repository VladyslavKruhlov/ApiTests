package api.reqres_in_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {
    String baseUrl = "https://reqres.in";
    String endPointUrl = "/api/login";

    String bodyForSuccessfulLogin = "{\n" +
            "    \"email\": \"eve.holt@reqres.in\",\n" +
            "    \"password\": \"cityslicka\"\n" +
            "}";
    String bodyForUnsuccessfulLogin = "{\n" +
            "    \"email\": \"peter@klaven\"\n" +
            "}";

    @Test (groups = "ReqresIn", description = "Successful login")
    public void loginSuccessful() {
        Response response = RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(bodyForSuccessfulLogin)
                .when()
                .log().all()
                .post(baseUrl+endPointUrl);

        int statusCode = response.statusCode();
        String token = response.jsonPath().getString("token");

        Assert.assertEquals(statusCode, HttpStatus.SC_OK);
        Assert.assertFalse(token.isEmpty());

        System.out.println("Login successful \n status code: "+ statusCode + "\n token: "+token);
    }

    @Test (groups = "ReqresIn", description = "Unsuccessful login")
    public void loginUnsuccessful() {
        Response response = RestAssured.given()
                .log().all()
                .contentType("application/json")
                .body(bodyForUnsuccessfulLogin)
                .when()
                .log().all()
                .post(baseUrl+endPointUrl);

        int statusCode = response.statusCode();
        String errorField = response.jsonPath().getString("error");

        Assert.assertEquals(statusCode, HttpStatus.SC_BAD_REQUEST);
        Assert.assertTrue(errorField.equals("Missing password"));

        System.out.println("Unsuccessful login \n status code: "+ statusCode + "\n error: "+errorField);
    }
}