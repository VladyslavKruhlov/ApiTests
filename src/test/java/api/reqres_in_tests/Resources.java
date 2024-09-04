package api.reqres_in_tests;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class Resources {
    String baseUrl = "https://reqres.in";
    String endPointUrl = "/api/unknown/";
    int resourceId = 2;
    int incorrectResourceId = 23;

    @Test(groups = "ReqresIn", description = "Get Single Resource info")
    public void getSingleResourceInfo() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(baseUrl+endPointUrl+resourceId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("single-resource-schema.json"))
                .body("data.id", equalTo(resourceId))
                .body("data.name", equalTo("fuchsia rose"))
                .body("support.url", equalTo("https://reqres.in/#support-heading"));
    }

    @Test(groups = "ReqresIn", description = "Get List Resources info")
    public void getListResourcesInfo() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(baseUrl+endPointUrl)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("list-resources-schema.json"))
                .body("data.size()", greaterThan(0))
                .body("data[0].name", equalTo("cerulean"));
    }

    @Test(groups = "ReqresIn", description = "Single Resource not found")
    public void getSingleResourceInfoNotFound() {
        given()
                .log().all()
                .when()
                .log().all()
                .get(baseUrl+endPointUrl+incorrectResourceId)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(equalTo("{}"));
    }
}