package api.reqres_in_test_with_pojo.pojo.entity.resource;

public class Resource {
    private Data data;
    private Support support;

    public Resource() {}

    public Resource(Data data, Support support) {
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