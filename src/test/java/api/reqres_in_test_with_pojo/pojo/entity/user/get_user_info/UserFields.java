package api.reqres_in_test_with_pojo.pojo.entity.user.get_user_info;

public class UserFields {

    private Data data;

    private Support support;

    public UserFields() {}

    public UserFields(Data data, Support support) {
        this.data = data;
        this.support = support;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}