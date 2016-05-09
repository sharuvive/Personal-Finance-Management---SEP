package com.example.pri.financemanagement;

/**
 * Created by user on 2/2/2016.
 */
//Shangavi
/**
 * Store the necessary attributes of category and define the methods and implementation of Getter and setter since the attributes are
 * stored as private. The attributes for category is Category ID and it's name. We are giving the access modifier as private. There
 * are two constructors one is the default and other one is overloaded constructor.
 */

public class category {

   // Attributes of category class
    private int id;
    private String name;


    //Default Constructor
    public category() {
    }

    //Overriden Constructor with attributes id & name
    public category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //Overriden Constructor name
    public category(String name) {
        this.name = name;
    }

    //Getter method for id
    public int getId() {
        return id;
    }

    //Setter method for id
    public void setId(int id) {
        this.id = id;
    }

    //Getter method for name
    public String getName() {
        return name;
    }

    //Setter method for name
    public void setName(String name) {
        this.name = name;
    }
}
