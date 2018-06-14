package com.example.sumitbobade.shoppingmart.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sumitbobade.shoppingmart.activity.CartProductFragment;
import com.example.sumitbobade.shoppingmart.Database.ShoppingMartdb;
import com.example.sumitbobade.shoppingmart.R;
import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by sumitbobade on 23/04/18.
 */

public class RecyclerViewAdapterForCart extends RecyclerView.Adapter<RecyclerViewAdapterForCart.MyViewHolderCart>
{


    Context context;
    ArrayList<ProductList> productListArrayList;
    public RecyclerViewAdapterForCart(Context context, ArrayList<ProductList> productListArrayList )
    {
        this.context=context;
        this.productListArrayList=productListArrayList;
    }


    @Override
    public MyViewHolderCart onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        view=layoutInflater.inflate(R.layout.cardview_cart_product,parent,false);
        return new MyViewHolderCart(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCart holder, final int position)
    {
        holder.productName.setText(productListArrayList.get(position).getProductname());
        holder.productPrice.setText(productListArrayList.get(position).getPrice().toString());
        holder.vendorName.setText(productListArrayList.get(position).getVendorname());
        holder.vendorAddress.setText(productListArrayList.get(position).getVendoraddress());
        holder.count.setText(""+productListArrayList.get(position).getCount());

        Glide.with(context)
                .load(productListArrayList.get(position).getProductImg())
                .into(holder.productImg);

        holder.callToVendor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + productListArrayList.get(position).getPhoneNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(context,intent,null);
            }
        });

        holder.removeFromCart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                   ShoppingMartdb shoppingMartdb=ShoppingMartdb.getInstance(context);

                    int count=productListArrayList.get(position).getCount();
                    CartProductFragment.price-=productListArrayList.get(position).getPrice();
                    CartProductFragment.getInstance().setPrice();
                    System.out.println("total price: "+CartProductFragment.price);
                   if(count==1)
                   {
                       Boolean isDelete = shoppingMartdb.deleteCartProduct(productListArrayList.get(position).getProductname());
                       if (isDelete) {
                           System.out.println("record deleted success : " + position);
                           productListArrayList.remove(position);
                           notifyItemRemoved(position);
                           notifyDataSetChanged();
                       } else
                           System.out.println("record delete failure");

                   }else
                   {
                       Cursor curser=shoppingMartdb.getCartProductList();
                       if(curser.getCount()!=0)
                       {

                           while(curser.moveToNext())
                           {
                               if(curser.getString(0).equals(productListArrayList.get(position).getProductname()))
                               {
                                   count=count-1;
                                   shoppingMartdb.updateCount(curser.getString(0),count);
                                   break;
                               }

                           }
                       }
                       productListArrayList.get(position).setCount(count);
                       notifyDataSetChanged();
                   }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return productListArrayList.size();
    }

    public static class MyViewHolderCart extends RecyclerView.ViewHolder
    {
        ImageView productImg;
        TextView productName;
        TextView productPrice;
        TextView vendorName;
        TextView vendorAddress;
        Button callToVendor;
        Button removeFromCart;
        TextView count;

        public MyViewHolderCart(View itemView)
        {
            super(itemView);
            productImg=itemView.findViewById(R.id.cartproductimg);
            productName=itemView.findViewById(R.id.cartproductname);
            productPrice=itemView.findViewById(R.id.cartproductprice);
            vendorName=itemView.findViewById(R.id.cartproductvendorname);
            vendorAddress=itemView.findViewById(R.id.cartproductvendoraddress);
            callToVendor=itemView.findViewById(R.id.callvendorB);
            removeFromCart=itemView.findViewById(R.id.removefromcartB);
            count=itemView.findViewById(R.id.cartproductcount);
        }
    }
}

