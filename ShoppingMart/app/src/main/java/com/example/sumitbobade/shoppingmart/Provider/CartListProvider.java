package com.example.sumitbobade.shoppingmart.Provider;

import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 19/04/18.
 */

public class CartListProvider
{
    public  ArrayList<ProductList> cartproductlist=new ArrayList<>();
    public static  CartListProvider cartListProvider;

    public static CartListProvider getInstance()
    {
        if(cartListProvider==null)
        {
            cartListProvider=new CartListProvider();
            return cartListProvider;
        }else
        {
            return cartListProvider;
        }
    }

}
