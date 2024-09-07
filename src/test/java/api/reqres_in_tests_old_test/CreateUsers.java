package api.reqres_in_tests_old_test;

import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUsers {
    String baseUrl = "https://reqres.in";
    String endPointUrl = "/api/users";

    @DataProvider(name = "userData")
    public Object[][] userData(){
        return new Object[][] {
                {"Vladislav", "QA"},
                {"Sasha", "WebDev"},
                {"Bodya", "BeckDev"},
                {"Kolya", "MobileDev"},
                {"Andriy", "Designer"},
                {"Ihor", "AQA"}
        };
    }

    @Test(dataProvider = "userData")
    public void createUsers(String name, String job) {

        given()
                .log().all()
                .contentType("application/json")
                .body("{\n" +
                        "    \"name\": \""+name+"\",\n" +
                        "    \"job\": \""+job+"\"\n" +
                        "}")
                .when()
                .log().all()
                .post(baseUrl+endPointUrl)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }
}