package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name="locations")
@Access(AccessType.FIELD)
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long location_id;
    private double latitude;
    private double longitude;
    private LocalDate location_date;

    @ManyToMany(mappedBy="locations")
    @JsonIgnore // Pour ne pas produire des cycles
    private List<User> users;

    public long getLocation_id() {
        return location_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDate getLocation_date() {
        return location_date;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setLocation_id(long location_id) {
        this.location_id = location_id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLocation_date(LocalDate location_date) {
        this.location_date = location_date;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
