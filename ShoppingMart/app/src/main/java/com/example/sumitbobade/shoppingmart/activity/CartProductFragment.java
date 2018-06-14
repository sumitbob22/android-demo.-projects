package com.example.sumitbobade.shoppingmart.activity;


import android.database.Cursor;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sumitbobade.shoppingmart.Database.ShoppingMartdb;
import com.example.sumitbobade.shoppingmart.Provider.CartListProvider;
import com.example.sumitbobade.shoppingmart.R;
import com.example.sumitbobade.shoppingmart.adapter.RecyclerViewAdapterForCart;
import com.example.sumitbobade.shoppingmart.model.ProductList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartProductFragment extends Fragment
{

    private static CartProductFragment cartProductFragment=null;
    RecyclerView recyclerView;
    RecyclerViewAdapterForCart recyclerViewAdapter;
    TextView totalPrice;
    public static double price=0.0;
    public static CartProductFragment getInstance()
    {
        if(cartProductFragment==null)
        {
            cartProductFragment=new CartProductFragment();
        }
        return cartProductFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart_product, container, false);
        getCartProduct();
        return view;
    }

    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        totalPrice=getView().findViewById(R.id.totalprice);
        recyclerView=getView().findViewById(R.id.recyclerView_id_cart);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setAdapter(recyclerViewAdapter);
        setPrice();
    }

   public void  getCartProduct()
    {
         CartListProvider cartListProvider=CartListProvider.getInstance();
         ShoppingMartdb shoppingMartdb=ShoppingMartdb.getInstance(getContext());
         Cursor cursor=shoppingMartdb.getCartProductList();

        if(cursor.getCount()!=0)
        {
            cartListProvider.cartproductlist.clear();
            price=0.0;
            while (cursor.moveToNext())
            {
                ProductList productList = new ProductList();
                productList.setProductname(cursor.getString(0));
                productList.setPrice(cursor.getDouble(1));
                productList.setVendorname(cursor.getString(2));
                productList.setVendoraddress(cursor.getString(3));
                productList.setProductImg(cursor.getString(4));
                productList.setPhoneNumber(cursor.getString(5));
                productList.setCount(cursor.getInt(6));
                cartListProvider.cartproductlist.add(productList);
                price+=cursor.getDouble(1)*cursor.getInt(6);
            }
            recyclerViewAdapter = new RecyclerViewAdapterForCart(getContext(), cartListProvider.cartproductlist);
        }else
        {
            Toast.makeText(getContext(), "Empty Cart", Toast.LENGTH_SHORT).show();
            cartListProvider.cartproductlist.clear();
        }
    }
    
    public void setPrice()
    {
        String tprice="Price: "+price;
        totalPrice.setText(tprice);
    }
}
