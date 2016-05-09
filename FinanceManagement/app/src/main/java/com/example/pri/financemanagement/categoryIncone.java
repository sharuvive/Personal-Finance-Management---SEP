package com.example.pri.financemanagement;

/**
 * Created by user on 3/10/2016.
 */

/**
 * Store the necessary attributes of income type category an define the methods and implementation
 */

public class categoryIncone {

    /*Attributes of income type category class*/
    private int id;
    private String name;

    /*Default Constructor*/
    public categoryIncone() {
    }

    /*Overriden Constructor with attributes id & name*/
    public categoryIncone(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*Overriden Constructor name*/
    public categoryIncone(String name) {
        this.name = name;
    }


    /*Getter method for id*/
    public int getCatIdIncome() {
        return id;
    }

    /*Setter method for id*/
    public void setIdIncome(int id) {
        this.id = id;
    }


    /*Getter method for name*/
    public String getNameIncome() {
        return name;
    }
}
