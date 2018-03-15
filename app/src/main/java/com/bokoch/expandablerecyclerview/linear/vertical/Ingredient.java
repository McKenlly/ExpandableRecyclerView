package com.bokoch.expandablerecyclerview.linear.vertical;

/**
 * Created by bokoch on 3/15/2018.
 */

public class Ingredient {
    private String mName;
    private boolean mIsVegetarian;

    public Ingredient(String name, boolean isVegetarian) {
        mName = name;
        mIsVegetarian = isVegetarian;
    }

    public String getName() {
        return mName;
    }

    public boolean isVegetarian() {
        return mIsVegetarian;
    }
}
