/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recipefinder;

/**
 *
 * @author Ben
 */

public  class Recipes {
    private int recipeCount;
    
    public void setCount(int count){//grab the count of recipes in the recipes array
        this.recipeCount = count;
    }
    public int getCount(){//Returns recipes array count
        return recipeCount;
    }
    
}
