package com.group4.model.relationshipAccount;

public class Relationship {
    int Id_relationship;
    String name_relationship;

    public Relationship(int id_relationship, String name_relationship) {
        Id_relationship = id_relationship;
        this.name_relationship = name_relationship;
    }

    public Relationship() {
    }

    public Relationship(String name_relationship) {
        this.name_relationship = name_relationship;
    }

    public int getId_relationship() {
        return Id_relationship;
    }

    public void setId_relationship(int id_relationship) {
        Id_relationship = id_relationship;
    }

    public String getName_relationship() {
        return name_relationship;
    }

    public void setName_relationship(String name_relationship) {
        this.name_relationship = name_relationship;
    }
}
