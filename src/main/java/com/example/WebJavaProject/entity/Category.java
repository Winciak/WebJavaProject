package com.example.WebJavaProject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    @NotNull(message = "is required")
    @Pattern(regexp = "^[a-z]{3,20}", message = "only lowercase letters of length 3-20 allowed")
    private String name;

    @OneToMany(mappedBy = "category",
                    cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Information> informations;


    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, List<Information> informations) {
        this.id = id;
        this.name = name;
        this.informations = informations;
    }

    public void add(Information information){
        if(informations == null){
            informations = new ArrayList<>();
        }

        informations.add(information);

        information.setCategory(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Information> getInformations() {
        return informations;
    }

    public void setInformations(List<Information> informations) {
        this.informations = informations;
    }

    @Override
    public String toString() {
        return  name ;
    }
}
