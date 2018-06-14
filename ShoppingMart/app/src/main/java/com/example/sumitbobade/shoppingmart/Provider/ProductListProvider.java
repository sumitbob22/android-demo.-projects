package com.example.sumitbobade.shoppingmart.Provider;

import android.content.Context;

import com.example.sumitbobade.shoppingmart.interfaces.IProductList;
import com.example.sumitbobade.shoppingmart.activity.ProductListFragment;
import com.example.sumitbobade.shoppingmart.interfaces.ResponceStatusListener;
import com.example.sumitbobade.shoppingmart.model.DataProvider;
import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 19/04/18.
 */

public class ProductListProvider implements ResponceStatusListener
{

    IProductList iProductList;
    public DataProvider dataProvider=new DataProvider();
    public ArrayList<ProductList>productListArrayList=new ArrayList<>();
    public static ProductListProvider productListProvider;

    public void getProductList(IProductList iProductList, Context context)
    {
        this.iProductList=iProductList;
        dataProvider.getData(this,context);
    }

    public static ProductListProvider getInstance()
    {
        if(productListProvider==null)
        {
            productListProvider=new ProductListProvider();
            return productListProvider;
        }else
        {
            return productListProvider;
        }
    }

    @Override
    public void onSuccess(ArrayList<ProductList> responce)
    {
        iProductList.displayProuductList(responce);
    }

    @Override
    public void onFailure(int statusCode)
    {
        iProductList.DisplayFail(statusCode);
    }

}
