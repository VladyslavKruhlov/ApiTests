package api.jira;

import api.jira.pojo.IssueType;
import api.jira.pojo.Project;
import api.jira.pojo.body_request.JiraIssueRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.Constants.*;
import static api.Constants.JIRA_KEY;
import static api.jira.request.SendRequest.sendPostRequest;
import static api.jira.specification.JiraSpecification.requestSpecification;
import static org.hamcrest.Matchers.containsString;

public class JiraApiTestWithPojoClass {
    @Test
    public void createBugTestPojo(){
        JiraIssueRequest jiraIssueRequest = new JiraIssueRequest(
                new Project(JIRA_KEY),
                SUMMARY,
                DESCRIPTION,
                new IssueType(ISSUE_TYPE_BUG)
        );

        Response response = sendPostRequest(
                requestSpecification,
                jiraIssueRequest,
                JIRA_ENDPOINT_FOR_CREATE_ISSUE,
                HttpStatus.SC_CREATED
        );

        response
                .then()
                .log().all()
                .body("key", containsString(JIRA_KEY));
    }
}