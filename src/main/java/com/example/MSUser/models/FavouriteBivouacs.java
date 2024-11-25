package com.example.MSUser.models;

import jakarta.persistence.*;

@Entity(name = "favourite_bivouacs")
public class FavouriteBivouacs {

    @EmbeddedId
    private FavouriteBivouacId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Default constructor
    public FavouriteBivouacs() {}

    public FavouriteBivouacs(FavouriteBivouacId id, User user) {
        this.id = id;
        this.user = user;
    }

    // Getters and Setters
    public FavouriteBivouacId getId() {
        return id;
    }

    public void setId(FavouriteBivouacId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
