package api.reqres_in_test_with_pojo.specification;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

import static api.Constants.*;

public class RequestSpecification {
    public static io.restassured.specification.RequestSpecification requestSpecification =
            new RequestSpecBuilder()
                    .setBaseUri(REQRES_IN_URL)
                    .setContentType(ContentType.JSON)
                    .setAccept("application/json")
                    .addHeader("User-Agent", "AQA")
                    .log(LogDetail.ALL)
                    .build()
                    .filter(new AllureRestAssured());
}