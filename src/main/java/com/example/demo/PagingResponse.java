package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"page", "item_per_page", "user"})
public class PagingResponse {
    @JsonProperty("users")
    private List<UserResponse> userResponsesList;
    private int page;

    @JsonProperty("item_per_page")
    private int itemPerPage;

    public PagingResponse(int page, int itemPerPage) {
        this.page = page;
        this.itemPerPage = itemPerPage;
    }

    public List<UserResponse> getUserResponsesList() {
        return userResponsesList;
    }

    public void setUserResponsesList(List<UserResponse> userResponsesList) {
        this.userResponsesList = userResponsesList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public void setUserResponse(List<UserResponse> users) {

    }
}
