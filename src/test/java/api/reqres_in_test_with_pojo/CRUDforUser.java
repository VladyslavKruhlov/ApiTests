package api.reqres_in_test_with_pojo;

import api.reqres_in_test_with_pojo.data_provider.UserDataProvider;
import api.reqres_in_test_with_pojo.pojo.body_request.ReqresInCRUDRequest;
import api.reqres_in_test_with_pojo.pojo.entity.user.CRUD_user.UserFieldsForCreation;
import api.reqres_in_test_with_pojo.pojo.entity.user.CRUD_user.UserFieldsUpdate;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.Constants.*;
import static api.reqres_in_test_with_pojo.request.SendRequest.*;
import static api.reqres_in_test_with_pojo.specification.RequestSpecification.requestSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class CRUDforUser {
    @Test(groups = "ReqresInPojo", description = "Create user")
    public void createUser() {
        ReqresInCRUDRequest reqresInCRUDRequest = new ReqresInCRUDRequest(
                REQRES_IN_USER_NAME_FOR_NEW_USER,
                REQRES_IN_USER_JOB_FOR_NEW_USER
        );

        Response response = sendPostRequest(
                requestSpecification,
                reqresInCRUDRequest,
                REQRES_IN_ENDPOINT_FOR_USERS,
                HttpStatus.SC_CREATED
        );
        UserFieldsForCreation user = response.getBody().as(UserFieldsForCreation.class);

        response
                .then()
                .log()
                .all()
                .body(matchesJsonSchemaInClasspath("created-user-schema.json"));

        assertEquals(user.getName(), REQRES_IN_USER_NAME_FOR_NEW_USER);
        assertEquals(user.getJob(), REQRES_IN_USER_JOB_FOR_NEW_USER);
        assertThat(user.getId(), not(emptyOrNullString()));
        assertThat(user.getCreatedAt(), not(emptyOrNullString()));
    }

    @Test(groups = "ReqresInPojo", description = "Update user")
    public void updateUser() {
        ReqresInCRUDRequest reqresInCRUDRequest = new ReqresInCRUDRequest(
                REQRES_IN_USER_NAME_FOR_NEW_USER,
                REQRES_IN_USER_JOB_FOR_NEW_USER
        );

        Response response = sendPutRequest(
                requestSpecification,
                reqresInCRUDRequest,
                REQRES_IN_ENDPOINT_FOR_USERS+REQRES_IN_USER_ID,
                HttpStatus.SC_OK
        );

        UserFieldsUpdate userFieldsUpdate = response.getBody().as(UserFieldsUpdate.class);

        response
                .then()
                .log()
                .all()
                .body(not(emptyOrNullString()));

        assertEquals(userFieldsUpdate.getName(), REQRES_IN_USER_NAME_FOR_NEW_USER);
        assertEquals(userFieldsUpdate.getJob(), REQRES_IN_USER_JOB_FOR_NEW_USER);
        assertThat(userFieldsUpdate.getUpdatedAt(), not(emptyOrNullString()));
    }

    @Test(groups = "ReqresInPojo", description = "Delete user")
    public void deleteUser() {
        Response response = sendDeleteRequest(
                requestSpecification,
                REQRES_IN_ENDPOINT_FOR_USERS+REQRES_IN_USER_ID,
                HttpStatus.SC_NO_CONTENT
        );

        response
                .then()
                .log().all()
                .body(isEmptyOrNullString());
    }

        @Test(groups = "ReqresInPojo", description = "Create user", dataProvider = "userData", dataProviderClass = UserDataProvider.class)
        public void createUsers(String name, String job) {
            ReqresInCRUDRequest reqresInCRUDRequest = new ReqresInCRUDRequest(name, job);

            Response response = sendPostRequest(
                    requestSpecification,
                    reqresInCRUDRequest,
                    REQRES_IN_ENDPOINT_FOR_USERS,
                    HttpStatus.SC_CREATED
            );
            UserFieldsForCreation user = response.getBody().as(UserFieldsForCreation.class);

            response
                    .then()
                    .log()
                    .all()
                    .body(matchesJsonSchemaInClasspath("created-user-schema.json"));

            assertEquals(user.getName(), name);
            assertEquals(user.getJob(), job);
            assertThat(user.getId(), not(emptyOrNullString()));
            assertThat(user.getCreatedAt(), not(emptyOrNullString()));
        }
}