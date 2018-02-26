package myapplication.example.com.entity;

import java.util.ArrayList;

public class Category {
    private int categoryId;
    private String categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return this.categoryId + ". " + this.categoryName ;
    }

    /*public ArrayList<Category> categoriesList (){

        return null;
    }*/
}
