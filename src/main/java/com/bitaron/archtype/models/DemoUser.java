package com.bitaron.archtype.models;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DemoUser {
    @Id
    int id;

    public DemoUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}