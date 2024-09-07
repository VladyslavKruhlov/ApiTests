package api.reqres_in_test_with_pojo.pojo.entity.login;


public class LoginFields {

    private String email;

    private String password;

    public LoginFields(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginFields(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}