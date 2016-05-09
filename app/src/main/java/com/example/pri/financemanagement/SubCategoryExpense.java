package com.example.pri.financemanagement;

/**
 * Created by user on 3/10/2016.
 */


/**
 * Store the necessary attributes of Subcategory an define the methods and implementation
 */
public class SubCategoryExpense {

    private int id;
    private int SuryaID;
    private String catName;
    private String name;

    public int getCatID() {
        return CatID;
    }

    public String getCatName() {
        return CatName;
    }

    private int CatID;

    public void setCatName(String catName) {
        CatName = catName;
    }

    public void setCatID(int catID) {
        CatID = catID;
    }

    public void setSuryaID(int SaaviID){
        this.id =SaaviID; }

    public int getSuryaID(){
        return SuryaID;
    }

    private String CatName;

    /*Default Constructor*/
    public SubCategoryExpense() {
    }

    /*Overriden Constructor with attributes id & name*/
    public SubCategoryExpense(int id, String name, int catid, String nameCat) {
        this.id = id;
        this.name = name;
        this.SuryaID = catid;
        this.catName = nameCat;
    }

    /*Overriden Constructor name*/
    public SubCategoryExpense(String name) {
        this.name = name;
    }

    /*Getter method for id*/
    public int getSubCatId() {
        return id;
    }

    /*Setter method for id*/
    public void setSubCatId(int id) {
        this.id = id;
    }

    /*Getter method for name*/
    public String getSubCatName() {
        return name;
    }

    /*Setter method for name*/
    public void setSubCatName(String name) {
        this.name = name;
    }
}
