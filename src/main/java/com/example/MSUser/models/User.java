package com.example.MSUser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity(name="users")
@Access(AccessType.FIELD)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long user_id;

    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private String password;
    private Boolean is_admin;
    @Column(name = "is_host")
    private Boolean isHost;



    public long getUser_id() {
        return user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    /*public List<Location> getLocations() {
        return locations;
    }*/

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isAdmin() {
        return is_admin;
    }

    public void setIs_host(Boolean isHost) {
        this.is_host = isHost;
    }


}
