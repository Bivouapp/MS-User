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
    private String rentalType;
    private String fieldType;
    private float area;
    private String description;
    private boolean isPmr;
    private String privacy;
    private boolean isHost;
    private boolean isAdmin;

    @ManyToMany
    @JoinTable(name = "bivouac_equipments",joinColumns = @JoinColumn(name = "bivouac_id"),inverseJoinColumns = @JoinColumn(name = "equipment_id"))
    private Set<Equipment> equipments = new HashSet<>();

    public long getBivouac_id() {
        return bivouac_id;
    }

    public float getPrice() {
        return price;
    }

    public String getRentalType() {
        return rentalType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public float getArea() {
        return area;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPmr() {
        return isPmr;
    }

    public String getPrivacy() {
        return privacy;
    }

    public boolean isHost() {
        return isHost;
    }

    public boolean isAdmin() {
        return isAdmin;
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

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPmr(boolean pmr) {
        isPmr = pmr;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

}
