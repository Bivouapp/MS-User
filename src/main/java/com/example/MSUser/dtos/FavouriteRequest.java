package com.example.MSUser.dtos;

public class FavouriteRequest {
    private Long userId;
    private Long bivouacId;

    // Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBivouacId() {
        return bivouacId;
    }

    public void setBivouacId(Long bivouacId) {
        this.bivouacId = bivouacId;
    }
}
