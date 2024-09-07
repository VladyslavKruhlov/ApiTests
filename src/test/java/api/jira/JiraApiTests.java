package api.jira;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.Constants.*;

public class JiraApiTests {

    String requestBody = "{\n" +
            "    \"fields\": {\n" +
            "       \"project\":\n" +
            "       {\n" +
            "          \"key\": \"U3QAJ201023\"\n" +
            "       },\n" +
            "       \"summary\": \"Example for autotests\",\n" +
            "       \"description\": \"Test for API which related with created bug ticket\",\n" +
            "       \"issuetype\": {\n" +
            "          \"name\": \"Bug\"\n" +
            "       }\n" +
            "   }\n" +
            "}\n";

    @Test
    public void createBugTest(){
        RestAssured.baseURI = "https://jira.ithillel.com";

        Response response = RestAssured.given()
                .log().all()
                .auth()
                .preemptive()
                .basic(JIRA_USERNAME, JIRA_PASSWORD)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .log().all()
                .post("/rest/api/2/issue/");

            int statusCode = response.statusCode();
            String issueID = response.jsonPath().getString("id");
            String key = response.jsonPath().getString("key");
            String issueSelf = response.jsonPath().getString("self");

        Assert.assertEquals(statusCode, HttpStatus.SC_CREATED);
        Assert.assertTrue(key.contains(JIRA_KEY));

        System.out.println("Bug created \n id: "+ issueID + "\n key: "+key+"\n self: "+issueSelf);
    }
}