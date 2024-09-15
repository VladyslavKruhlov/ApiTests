package api.reqres_in_test_with_pojo;

import api.reqres_in_test_with_pojo.pojo.entity.resource.Data;
import api.reqres_in_test_with_pojo.pojo.entity.resource.Resource;
import api.reqres_in_test_with_pojo.pojo.entity.resources.ResourceList;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.Constants.*;
import static api.reqres_in_test_with_pojo.request.SendRequest.sendGetRequest;
import static api.reqres_in_test_with_pojo.specification.RequestSpecification.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.testng.Assert.assertEquals;

public class ResourceTest {
    @Test(groups = "ReqresInPojo", description = "Get Single Resource info")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("v.kruhlov")
    @Epic("API")
    @Feature("CRUD")
    @Story("Get method")
    @Link(name = "Website", url = "https://reqres.in/")
    public void getSingleResourceInfo() {
        Response response = sendGetRequest(
                requestSpecification,
                REQRES_IN_ENDPOINT_FOR_RESOURCE+REQRES_IN_RESOURCE_ID,
                HttpStatus.SC_OK
        );

        Resource resource = response.getBody().as(Resource.class);

        response
                .then()
                .log()
                .all()
                .body(matchesJsonSchemaInClasspath("single-resource-schema.json"));

        assertEquals(resource.getData().getId(), REQRES_IN_RESOURCE_ID);
        assertEquals(resource.getData().getName(), REQRES_IN_RESOURCE_NAME);
        assertEquals(resource.getSupport().getUrl(), REQRES_IN_RESOURCE_SUPPORT_URL);
    }

    @Test(groups = "ReqresInPojo", description = "Single Resource not found")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("v.kruhlov")
    @Epic("API")
    @Feature("CRUD")
    @Story("Get method")
    @Link(name = "Website", url = "https://reqres.in/")
    public void getSingleResourceInfoNotFound() {
        Response response = sendGetRequest(
                requestSpecification,
                REQRES_IN_ENDPOINT_FOR_RESOURCE+REQRES_IN_INCORRECT_RESOURCE_ID,
                HttpStatus.SC_NOT_FOUND
        );

        response
                .then()
                .log().all()
                .body(equalTo("{}"));
    }

    @Test(groups = "ReqresInPojo", description = "Get List Resources info")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("v.kruhlov")
    @Epic("API")
    @Feature("CRUD")
    @Story("Get method")
    @Link(name = "Website", url = "https://reqres.in/")
    public void getListResourcesInfo() {
        Response response = sendGetRequest(
                requestSpecification,
                REQRES_IN_ENDPOINT_FOR_RESOURCE,
                HttpStatus.SC_OK
        );

        ResourceList resourcesList = response.getBody().as(ResourceList.class);

        Data firstData = resourcesList.getData()[0];

        response
                .then()
                .log()
                .all()
                .body(matchesJsonSchemaInClasspath("list-resources-schema.json"))
                .body("data.size()", greaterThan(0));

        assertEquals(firstData.getName(), REQRES_IN_FIRST_RESOURCE_DATA_NAME);
    }
}