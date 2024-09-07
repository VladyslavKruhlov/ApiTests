package api.reqres_in_tests_old_test;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static api.Constants.*;

public class SingleUser {

    @Test(groups = "ReqresIn", description = "Get single user info")
    public void getSingleUserInfo() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(REQRES_IN_URL+REQRES_IN_ENDPOINT_FOR_USERS+REQRES_IN_USER_ID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("single-user-schema.json"))
                .body("data.id", equalTo(REQRES_IN_USER_ID))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"));
    }

    @Test(groups = "ReqresIn", description = "Single user info not found")
    public void getSingleUserInfoNotFound() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(REQRES_IN_URL+REQRES_IN_ENDPOINT_FOR_USERS+REQRES_IN_INCORRECT_USER_ID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("{}"));
    }
}