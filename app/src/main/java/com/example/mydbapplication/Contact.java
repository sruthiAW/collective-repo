package com.example.mydbapplication;

/**
 * Created by ssurendran on 10/13/2015.
 */
public class Contact {

    private int id;
    private String name;
    private int age;

    public Contact() {
    }

    public Contact(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Contact(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getId() + " Name: " + getName() + " Age: " + getAge();
    }
}
