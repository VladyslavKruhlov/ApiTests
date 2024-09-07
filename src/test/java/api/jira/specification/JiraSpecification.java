package api.jira.specification;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.Constants.*;

public class JiraSpecification {
    public static RequestSpecification requestSpecification =
            new RequestSpecBuilder()
                    .setBaseUri(JIRA_URL)
                    .setContentType(ContentType.JSON)
                    .setAccept("application/json")
                    .setAuth(RestAssured.preemptive().basic(JIRA_USERNAME, JIRA_PASSWORD))
                    .addHeader("User-Agent", "hillel_automationQA")
                    .log(LogDetail.ALL)
                    .build()
                    .filter(new AllureRestAssured());
}