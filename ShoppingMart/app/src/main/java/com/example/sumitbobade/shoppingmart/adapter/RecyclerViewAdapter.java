package com.example.sumitbobade.shoppingmart.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sumitbobade.shoppingmart.activity.CartProductFragment;
import com.example.sumitbobade.shoppingmart.Database.ShoppingMartdb;
import com.example.sumitbobade.shoppingmart.R;
import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;

/**
 * Created by sumitbobade on 21/04/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>
{


    Context context;
    ArrayList<ProductList> productListArrayList;
    public RecyclerViewAdapter(Context context, ArrayList<ProductList> productListArrayList )
    {
        this.context=context;
        this.productListArrayList=productListArrayList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.cardview_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        holder.productName.setText(productListArrayList.get(position).getProductname());
        holder.productPrice.setText(productListArrayList.get(position).getPrice().toString());
        holder.vendorName.setText(productListArrayList.get(position).getVendorname());
        holder.vendorAddress.setText(productListArrayList.get(position).getVendoraddress());

        Glide.with(context)
                .load(productListArrayList.get(position).getProductImg())
                .into(holder.productImg);


        holder.addToCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ShoppingMartdb shoppingMartdb=ShoppingMartdb.getInstance(context);

                Cursor curser=shoppingMartdb.getCartProductList();

                 Boolean isPresentProductInCart=false;
                 CartProductFragment.price+=productListArrayList.get(position).getPrice();
                 //System.out.println("total price :"+CartProductFragment.price);
                if(curser.getCount()!=0)
                {

                    while(curser.moveToNext())
                    {
                        if(curser.getString(0).equals(productListArrayList.get(position).getProductname()))
                        {
                           isPresentProductInCart =true;
                           Toast.makeText(context,"Added one more product ",Toast.LENGTH_SHORT).show();
                           shoppingMartdb.updateCount(curser.getString(0),curser.getInt(6)+1);
                           break;
                        }

                    }
                }

                if(!isPresentProductInCart)
                {
                    Boolean isInsert = shoppingMartdb.insertCartList(productListArrayList.get(position).getProductname(),
                            productListArrayList.get(position).getPrice(),
                            productListArrayList.get(position).getVendorname(),
                            productListArrayList.get(position).getVendoraddress(),
                            productListArrayList.get(position).getProductImg(),
                            productListArrayList.get(position).getPhoneNumber(),
                            productListArrayList.get(position).getCount()
                    );

                    if (isInsert) {
                        Toast.makeText(context, "Product added to Cart", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });
    }

    @Override
    public int getItemCount() {
        return productListArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView productImg;
        TextView productName;
        TextView productPrice;
        TextView vendorName;
        TextView vendorAddress;
        Button addToCart;


        public MyViewHolder(View itemView)
        {
            super(itemView);

            productImg=itemView.findViewById(R.id.productImg);
            productName=itemView.findViewById(R.id.productName);
            productPrice=itemView.findViewById(R.id.productPrice);
            vendorName=itemView.findViewById(R.id.productVenderName);
            vendorAddress=itemView.findViewById(R.id.productVenderAddress);
            addToCart=itemView.findViewById(R.id.addToCart);

        }
    }
}
