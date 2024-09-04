package api.reqres_in_tests;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class SingleUser {
    String baseUrl = "https://reqres.in";
    String endPointUrl = "/api/users/";
    int userId = 2;
    int incorrectUserId = 100000;

    @Test(groups = "ReqresIn", description = "Get single user info")
    public void getSingleUserInfo() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(baseUrl+endPointUrl+userId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("single-user-schema.json"))
                .body("data.id", equalTo(userId))
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
                .get(baseUrl+endPointUrl+incorrectUserId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("{}"));
    }
}