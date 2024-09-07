package api.reqres_in_test_with_pojo.pojo.entity.resource;

public class Support {
    private String url;
    private String text;

    public Support() {}

    public Support(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}