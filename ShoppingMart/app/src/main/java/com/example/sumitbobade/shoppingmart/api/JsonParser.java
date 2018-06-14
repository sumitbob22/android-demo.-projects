package com.example.sumitbobade.shoppingmart.api;

import com.example.sumitbobade.shoppingmart.Provider.ProductListProvider;
import com.example.sumitbobade.shoppingmart.model.ProductList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 21/04/18.
 */

public class JsonParser
{

       public static ArrayList<ProductList> parseProuductListJason(String responce, ArrayList<ProductList> productListArrayList)
        {

            try {
                JSONObject productjson=new JSONObject(responce);
                JSONArray productArray=productjson.getJSONArray("products");
                if(productArray!=null)
                {
                    for(int i=0;i<productArray.length();i++)
                    {
                        ProductList productList=new ProductList();

                         JSONObject product=productArray.getJSONObject(i);
                         String productname=product.getString("productname");
                         Double price=Double.parseDouble(product.getString("price"));
                         String vendorname=product.getString("vendorname");
                         String vendoraddress=product.getString("vendoraddress");
                         String productImg=product.getString("productImg");

                         JSONArray productgalleryarray=product.getJSONArray("productGallery");
                        String [] productGallery=new String[productgalleryarray.length()];
                         if(productgalleryarray!=null)
                         {
                             for(int j=0;j<productgalleryarray.length();j++)
                             {
                                 productGallery[j]=productgalleryarray.getString(j);
                             }
                         }
                         String phoneNumber=product.getString("phoneNumber");

                         productList.setProductname(productname);
                         productList.setPrice(price);
                         productList.setVendorname(vendorname);
                         productList.setVendoraddress(vendoraddress);
                         productList.setProductImg(productImg);
                         productList.setProductGallery(productGallery);
                         productList.setPhoneNumber(phoneNumber);
                         productListArrayList.add(productList);
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        return productListArrayList;
        }
}
