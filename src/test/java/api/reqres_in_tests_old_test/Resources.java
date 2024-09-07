package api.reqres_in_tests_old_test;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static api.Constants.*;

public class Resources {

    @Test(groups = "ReqresIn", description = "Get Single Resource info")
    public void getSingleResourceInfo() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(REQRES_IN_URL+REQRES_IN_ENDPOINT_FOR_RESOURCE+REQRES_IN_RESOURCE_ID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("single-resource-schema.json"))
                .body("data.id", equalTo(REQRES_IN_RESOURCE_ID))
                .body("data.name", equalTo("fuchsia rose"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"));
    }

    @Test(groups = "ReqresIn", description = "Get List Resources info")
    public void getListResourcesInfo() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(REQRES_IN_URL+REQRES_IN_ENDPOINT_FOR_RESOURCE)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("list-resources-schema.json"))
                .body("data.size()", greaterThan(0))
                .body("data[0].name", equalTo(REQRES_IN_FIRST_RESOURCE_DATA_NAME));
    }

    @Test(groups = "ReqresIn", description = "Single Resource not found")
    public void getSingleResourceInfoNotFound() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(REQRES_IN_URL+REQRES_IN_ENDPOINT_FOR_RESOURCE+REQRES_IN_INCORRECT_RESOURCE_ID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("{}"));
    }
}