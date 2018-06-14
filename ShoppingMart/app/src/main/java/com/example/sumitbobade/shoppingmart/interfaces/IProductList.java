package com.example.sumitbobade.shoppingmart.interfaces;

import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 19/04/18.
 */

public interface IProductList
{

    void displayProuductList(ArrayList<ProductList> list);
    void DisplayFail(int status);

}
