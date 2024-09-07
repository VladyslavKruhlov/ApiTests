package api.reqres_in_test_with_pojo.pojo.entity.user.CRUD_user;

public class UserFieldsUpdate {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;

    public UserFieldsUpdate() {}

    public UserFieldsUpdate(String name, String job, String id, String createdAt, String updatedAt) {
        this.name = name;
        this.job = job;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}