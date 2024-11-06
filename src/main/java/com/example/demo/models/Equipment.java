package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity(name="equipments")
@Access(AccessType.FIELD)
public class Equipment {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long equipment_id;

    private String label;
    private String icon;

    @ManyToMany(mappedBy = "equipments")  // mappedBy indique que la relation est déjà définie dans Bivouac
    private Set<Bivouac> bivouacs = new HashSet<>();

    public long getEquipment_id() {
        return equipment_id;
    }

    public String getLabel() {
        return label;
    }

    public String getIcon() {
        return icon;
    }

    public Set<Bivouac> getBivouacs() {
        return bivouacs;
    }

    public void setEquipment_id(long equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setBivouacs(Set<Bivouac> bivouacs) {
        this.bivouacs = bivouacs;
    }

}
