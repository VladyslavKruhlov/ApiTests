package api.reqres_in_test_with_pojo.pojo.body_request;

import api.reqres_in_test_with_pojo.pojo.entity.user.CRUD_user.UserFieldsForCreation;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ReqresInCRUDRequest {
    @JsonUnwrapped
    private UserFieldsForCreation userFieldsForCreation;

    public ReqresInCRUDRequest(String name, String job){
        this.userFieldsForCreation = new UserFieldsForCreation(name, job);
    }

    public UserFieldsForCreation getUserFieldsForCreation() {
        return userFieldsForCreation;
    }

    public void setUserFieldsForCreation(UserFieldsForCreation userFieldsForCreation) {
        this.userFieldsForCreation = userFieldsForCreation;
    }
}