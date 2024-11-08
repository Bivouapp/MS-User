package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity(name="bivouacs")
@Access(AccessType.FIELD)
public class Bivouac {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long bivouac_id;
    private float price;
    private String rental_type;
    private String field_type;
    private float area;
    private String description;
    private boolean is_pmr;
    private String privacy;
    private boolean is_host;
    private boolean is_admin;

    @ManyToMany
    @JoinTable(name = "bivouac_equipments",joinColumns = @JoinColumn(name = "bivouac_id"),inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<Equipment> equipments = new HashSet<>();

    public long getBivouac_id() {
        return bivouac_id;
    }

    public float getPrice() {
        return price;
    }

    public String getRental_type() {
        return rental_type;
    }

    public String getField_type() {
        return field_type;
    }

    public float getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public boolean isIs_pmr() {
        return is_pmr;
    }

    public String getPrivacy() {
        return privacy;
    }

    public boolean isIs_host() {
        return is_host;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setBivouac_id(long bivouac_id) {
        this.bivouac_id = bivouac_id;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRental_type(String rentalType) {
        this.rental_type = rentalType;
    }

    public void setField_type(String fieldType) {
        this.field_type = fieldType;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIs_pmr(boolean is_pmr) {
        this.is_pmr = is_pmr;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setIs_host(boolean is_host) {
        this.is_host = is_host;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

}
