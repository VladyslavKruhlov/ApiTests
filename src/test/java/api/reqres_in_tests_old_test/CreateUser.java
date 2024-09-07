package api.reqres_in_tests_old_test;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateUser {

    String baseUrl = "https://reqres.in";
    String endPointUrl = "/api/users";

    String body = "{\n" +
            "    \"name\": \"vladislav\",\n" +
            "    \"job\": \"student\"\n" +
            "}";

    @Test
    public void createUser() {
        given()
                .log().all()
                .contentType("application/json")
                .body(body)
                .when()
                .log().all()
                .post(baseUrl+endPointUrl)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo("vladislav"))
                .body("job", equalTo("student"));
    }
}