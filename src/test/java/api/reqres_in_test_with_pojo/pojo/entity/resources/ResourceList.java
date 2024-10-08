package api.reqres_in_test_with_pojo.pojo.entity.resources;

import api.reqres_in_test_with_pojo.pojo.entity.resource.Data;
import api.reqres_in_test_with_pojo.pojo.entity.resource.Support;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceList {
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private api.reqres_in_test_with_pojo.pojo.entity.resource.Data[] data;
    private Support support;

    public ResourceList() {}

    public ResourceList(int page, int perPage, int total, int totalPages, api.reqres_in_test_with_pojo.pojo.entity.resource.Data[] data, Support support) {
        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
        this.data = data;
        this.support = support;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public api.reqres_in_test_with_pojo.pojo.entity.resource.Data[] getData() {
        return data;
    }

    public void setData(Data[] data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}