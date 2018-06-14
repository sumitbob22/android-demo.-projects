package com.example.sumitbobade.shoppingmart.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sumitbobade on 23/04/18.
 */

public class ShoppingMartdb extends SQLiteOpenHelper
{

    public static String DATABASE_NAME="shoping_mart";
    public static String TABLE_NAME_PRODUCT="product_info";
    public static String COL_PRODUCT_NAME="productname";
    public static String COL_PRODUCT_PRICE="price";
    public static String COL_VENDOR_NAME="vendorname";
    public static String COL_VENDOR_ADDRESS="vendoraddress";
    public static String COL_PRODUCT_IMG="productImg";
    public static String  COL_PRODUCT_GALLERY="productGallery";
    private static String COL_PHONE_NO="phoneNumber";

    private static String TABLE_NAME_CART="cart_product";
    private static String COL_COUNT="count";

    public static ShoppingMartdb shoppingMartdb;

    public ShoppingMartdb(Context context)
    {
        super(context, DATABASE_NAME, null, 1);

    }

    public static ShoppingMartdb getInstance(Context context)
    {
        if(shoppingMartdb==null)
        {
            shoppingMartdb=new ShoppingMartdb(context);
            return shoppingMartdb;
        }else
        {
            return shoppingMartdb;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+TABLE_NAME_PRODUCT+" ( productname TEXT,price REAL,vendorname TEXT,vendoraddress TEXT,productImg TEXT, productGallery TEXT,phoneNumber TEXT)");
        db.execSQL("create table "+TABLE_NAME_CART+" ( productname TEXT,price REAL,vendorname TEXT,vendoraddress TEXT,productImg TEXT,phoneNumber TEXT, count INTEGER)");
        System.out.println("datbase created table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_CART);
        onCreate(db);
        System.out.println("table droped and created again");
    }

    public Boolean insertProductList(String productname, Double price ,String vendorname ,String vendoraddress , String productImg , String productGallery ,String phoneNumber)
    {
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_PRODUCT_NAME,productname);
        contentValues.put(COL_PRODUCT_PRICE,price);
        contentValues.put(COL_VENDOR_NAME,vendorname);
        contentValues.put(COL_VENDOR_ADDRESS,vendoraddress);
        contentValues.put(COL_PRODUCT_IMG,productImg);
        contentValues.putNull(COL_PRODUCT_GALLERY);
        contentValues.put(COL_PHONE_NO,phoneNumber);

        long result=db.insert(TABLE_NAME_PRODUCT,null,contentValues);

            if(result==-1)
            {
                return false;
            }else
            {
                return true;
            }
    }

    public Cursor getAllProductList()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME_PRODUCT,null);
        return res;
    }

    public Boolean insertCartList(String productname,Double price,String vendorname,String vendoraddress,String productImg,String phoneNo,int count )
    {

        SQLiteDatabase db=this.getWritableDatabase();
        //onUpgrade(db,1,2);
       // onCreate(db);

        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_PRODUCT_NAME,productname);
        contentValues.put(COL_PRODUCT_PRICE,price);
        contentValues.put(COL_VENDOR_NAME,vendorname);
        contentValues.put(COL_VENDOR_ADDRESS,vendoraddress);
        contentValues.put(COL_PRODUCT_IMG,productImg);
        contentValues.put(COL_PHONE_NO,phoneNo);
        contentValues.put(COL_COUNT,count);
        long result=db.insert(TABLE_NAME_CART,null,contentValues);

        if(result==-1)
        {
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor getCartProductList()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME_CART,null);
        return res;
    }

    public boolean deleteCartProduct(String productname)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME_CART,COL_PRODUCT_NAME+"=?",new String[]{productname})>0;
    }

    public void updateCount(String name,int count)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("Field1","Bob"); //These Fields should be your String values of actual column names
        cv.put(COL_COUNT,count);
        db.update(TABLE_NAME_CART, cv, COL_PRODUCT_NAME+" = ?", new String[]{name});
    }

}
