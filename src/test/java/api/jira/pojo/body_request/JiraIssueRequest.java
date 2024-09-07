package api.jira.pojo.body_request;

import api.jira.pojo.Fields;
import api.jira.pojo.IssueType;
import api.jira.pojo.Project;

public class JiraIssueRequest {
    private Fields fields;

    public JiraIssueRequest(Project project, String summary, String description, IssueType issueType) {
        this.fields = new Fields(project, summary, description, issueType);
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}