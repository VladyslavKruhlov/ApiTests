package api.reqres_in_test_with_pojo.request;

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
    public static Response sendGetRequest(RequestSpecification respSpec, String endpoint, int expectedStatusCode) {
        return given()
                .spec(respSpec)
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public static Response sendPutRequest(RequestSpecification respSpec, Object request, String endpoint, int expectedStatusCode) {
        return given()
                .spec(respSpec)
                .when()
                .body(request)
                .log().all()
                .put(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }

    public static Response sendDeleteRequest(RequestSpecification respSpec, String endpoint, int expectedStatusCode) {
        return given()
                .spec(respSpec)
                .when()
                .log().all()
                .delete(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCode)
                .extract()
                .response();
    }
}