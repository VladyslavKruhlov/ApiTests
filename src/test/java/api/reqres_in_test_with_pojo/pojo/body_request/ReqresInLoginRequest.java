package api.reqres_in_test_with_pojo.pojo.body_request;

import api.reqres_in_test_with_pojo.pojo.entity.login.LoginFields;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class ReqresInLoginRequest {

    @JsonUnwrapped
    private LoginFields loginFields;

    public ReqresInLoginRequest(String email , String password) {
        this.loginFields = new LoginFields(email, password);
    }

    public ReqresInLoginRequest(String email) {
        this.loginFields = new LoginFields(email);
    }

    public LoginFields getLoginFields() {
        return loginFields;
    }

    public void setLoginFields(LoginFields loginFields) {
        this.loginFields = loginFields;
    }
}