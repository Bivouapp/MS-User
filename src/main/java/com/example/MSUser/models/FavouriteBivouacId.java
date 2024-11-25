package com.example.MSUser.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FavouriteBivouacId implements Serializable {

    private long userId;
    private long bivouacId;

    // Default constructor
    public FavouriteBivouacId() {}

    public FavouriteBivouacId(long userId, long bivouacId) {
        this.userId = userId;
        this.bivouacId = bivouacId;
    }

    // Getters and Setters
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBivouacId() {
        return bivouacId;
    }

    public void setBivouacId(long bivouacId) {
        this.bivouacId = bivouacId;
    }

    // Override equals and hashCode for composite key comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavouriteBivouacId that = (FavouriteBivouacId) o;
        return userId == that.userId && bivouacId == that.bivouacId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, bivouacId);
    }
}
