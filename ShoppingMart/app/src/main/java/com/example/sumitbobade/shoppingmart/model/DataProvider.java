package com.example.sumitbobade.shoppingmart.model;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.sumitbobade.shoppingmart.Database.ShoppingMartdb;
import com.example.sumitbobade.shoppingmart.Provider.ProductListProvider;
import com.example.sumitbobade.shoppingmart.api.ApiConnector;
import com.example.sumitbobade.shoppingmart.api.JsonParser;
import com.example.sumitbobade.shoppingmart.interfaces.ResponceStatusListener;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 21/04/18.
 */

public class DataProvider
{

    ResponceStatusListener productListProvider;
    String responceString;
    ArrayList<ProductList> productListArrayList;
    Context context;
    ShoppingMartdb shoppingMartdb;

    public void getData(ProductListProvider productListProvider,Context context)
    {
        this.productListProvider=productListProvider;
        this.context=context;
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run() {
                new ApiResolver().execute();
            }
        },2000);

    }

    class ApiResolver extends AsyncTask
    {
        @Override
        protected Object doInBackground(Object[] objects)
        {
            responceString= ApiConnector.getResponce();
            return null;
        }

        @Override
        protected void onPostExecute(Object o)
        {
            productListArrayList=new ArrayList<>();
            if(responceString!=null)
            {

                 productListArrayList=JsonParser.parseProuductListJason(responceString,productListArrayList);
                 productListProvider.onSuccess(productListArrayList);

                 shoppingMartdb=ShoppingMartdb.getInstance(context);

                 for(ProductList product:productListArrayList)
                 {
                    Boolean isInsert=shoppingMartdb.insertProductList(product.getProductname(),
                                                product.getPrice(),
                                                product.getVendorname(),
                                                product.getVendoraddress(),
                                                product.getProductImg(),
                                                //prooduct.getProductGallery()
                                                    null  ,
                                                product.getPhoneNumber());

                 }
            }
            else
            {
                if(shoppingMartdb!=null)
                {
                    Cursor cursor = shoppingMartdb.getAllProductList();

                    if (cursor.getCount() != 0) {

                        while (cursor.moveToNext())
                        {
                            ProductList productList = new ProductList();

                            productList.setProductname(cursor.getString(0));
                            productList.setPrice(cursor.getDouble(1));
                            productList.setVendorname(cursor.getString(2));
                            productList.setVendoraddress(cursor.getString(3));
                            productList.setProductImg(cursor.getString(4));
                            //productList.setProductGallery(cursor.getString(5));
                            productList.setProductGallery(null);
                            productList.setPhoneNumber(cursor.getString(6));
                            productListArrayList.add(productList);
                        }
                        productListProvider.onSuccess(productListArrayList);
                    }
                }else
                {
                    productListProvider.onFailure(ApiConnector.statuscode);
                }

            }

        }
    }


}
