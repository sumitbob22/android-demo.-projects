package com.example.sumitbobade.shoppingmart.model;

/**
 * Created by sumitbobade on 21/04/18.
 */

public class ProductList
{
    private String productname;
    private Double price;
    private String vendorname;
    private String vendoraddress;
    private String productImg;
    private String [] productGallery;
    private String phoneNumber;
    private  int count=1;
    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public void setProductGallery(String[] productGallery) {
        this.productGallery = productGallery;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProductname() {
        return productname;
    }

    public Double getPrice() {
        return price;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
    public int getCount()
    {
        return count;
    }

    public String getVendorname() {
        return vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public String getProductImg() {
        return productImg;
    }

    public String[] getProductGallery() {
        return productGallery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
