package api.jira.request;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SendRequest {
    public static Response sendPostRequest(RequestSpecification respSpec, Object request, String endpoint, int expectedStatusCode) {
        return given()
                .spec(respSpec)
                .when()
                .body(request)
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }
}