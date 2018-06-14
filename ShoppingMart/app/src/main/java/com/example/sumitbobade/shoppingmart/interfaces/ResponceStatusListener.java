package com.example.sumitbobade.shoppingmart.interfaces;

import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 19/04/18.
 */

public interface ResponceStatusListener
{

    void onSuccess(ArrayList<ProductList> responce);
    void onFailure(int statusCode);

}
