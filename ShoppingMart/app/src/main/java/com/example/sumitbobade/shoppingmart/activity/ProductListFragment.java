package com.example.sumitbobade.shoppingmart.activity;


import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sumitbobade.shoppingmart.interfaces.IProductList;
import com.example.sumitbobade.shoppingmart.Provider.ProductListProvider;
import com.example.sumitbobade.shoppingmart.R;
import com.example.sumitbobade.shoppingmart.adapter.RecyclerViewAdapter;
import com.example.sumitbobade.shoppingmart.model.ProductList;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends Fragment implements IProductList, SwipeRefreshLayout.OnRefreshListener {

    private static ProductListFragment productListFragment=null;
    private ProductListProvider productListProvider;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    public ProductListFragment()
    {
        // Required empty public constructor

    }

    public static ProductListFragment getInstance()
    {
        if(productListFragment==null)
        {
            productListFragment=new ProductListFragment();
        }
        return productListFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_product_list, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        productListProvider=ProductListProvider.getInstance();
        productListProvider.getProductList(this,getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=(RecyclerView)getView().findViewById(R.id.recyclerView_id);
        progressBar=getView().findViewById(R.id.progressbar);
        swipeRefreshLayout=getView().findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    public void displayProuductList(ArrayList<ProductList> list)
    {
        productListProvider.productListArrayList=list;
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), list);
        swipeRefreshLayout.setRefreshing(false);
        int cardCount=2;
        if(getActivity()!=null)
        cardCount=calculateNumberOfColumns(2);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), cardCount));
        recyclerView.setAdapter(recyclerViewAdapter);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void DisplayFail(int statuscode)
    {
        Toast.makeText(getContext()," Network not found",Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh()
    {
        productListProvider.getProductList(this,getContext());
    }

    public int calculateNumberOfColumns(int base)
    {
            int columns = base;
            String screenSize = getScreenSizeCategory();

            if(screenSize.equals("small"))
            {
                if(base!=1)
                {
                    columns = columns-1;
                }
            }else if (screenSize.equals("normal"))
            {
                // Do nothing
            }else if(screenSize.equals("large"))
            {
                columns += 2;
            }else if (screenSize.equals("xlarge"))
            {
                columns += 3;
            }

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
                columns = (int) (columns * 1.5);
            }

        return columns;
    }

    public String getScreenSizeCategory()
    {
        int screenLayout = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch(screenLayout)
        {
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                // small screens are at least 426dp x 320dp
                System.out.println("screensize is small");
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                // normal screens are at least 470dp x 320dp
                System.out.println("screensize is normal");
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                // large screens are at least 640dp x 480dp
                System.out.println("screensize is larg");
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                // xlarge screens are at least 960dp x 720dp
                System.out.println("screensize is xlarge");
                return "xlarge";
            default:
                return "undefined";
        }
    }
}
