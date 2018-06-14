package com.example.sumitbobade.shoppingmart.activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;

import com.example.sumitbobade.shoppingmart.R;


public class MainActivity extends AppCompatActivity
{


    private BottomNavigationView navigationView;
    private Fragment selectedfragment=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initionliseViews();

        if(savedInstanceState!=null)
        {
            String fragment=savedInstanceState.getString("fragment");
            if(fragment.equals("productListFragment"))
            {
                selectedfragment=ProductListFragment.getInstance();
                setFragment(selectedfragment);
            }
            else
            {
                selectedfragment=CartProductFragment.getInstance();
                setFragment(selectedfragment);
            }
        }else
        {
            selectedfragment=ProductListFragment.getInstance();
            setFragment(selectedfragment);
        }
        registerFragment();
    }

    public void registerFragment()
    {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {


                switch(item.getItemId())
                {
                    case R.id.productlist:
                        selectedfragment=ProductListFragment.getInstance();
                        setFragment(selectedfragment);
                        return  true;
                    case R.id.cartproduct:
                        selectedfragment=CartProductFragment.getInstance();
                        setFragment(selectedfragment);
                        return  true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if(selectedfragment==ProductListFragment.getInstance())
        outState.putString("fragment","productListFragment");
        else
        outState.putString("fragment","cartListFragment");
    }

    public void initionliseViews()
    {
        navigationView=findViewById(R.id.navigationView);
    }

    public void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearlayout,fragment);
        fragmentTransaction.commit();
    }
}
