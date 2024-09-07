package api.reqres_in_test_with_pojo.data_provider;

import org.testng.annotations.DataProvider;

import static api.Constants.*;

public class UserDataProvider {
    @DataProvider(name = "userData")
    public static Object[][] userData() {
        return new Object[][]{
                {REQRES_IN_USER_NAME_FOR_NEW_USER, REQRES_IN_USER_JOB_FOR_NEW_USER},
                {REQRES_IN_USER_FIRST_NAME, REQRES_IN_USER_JOB_FOR_NEW_USER}
        };
    }
}