package com.example.lendahand;

public class Listing {
    private String title;
    private String description;
    private int imageResId;
    private String price;
    private String priceUnit;
    private String postalCode;

    public Listing(String title, String description, int imageResId, String price, String priceUnit, String postalCode) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.price = price;
        this.priceUnit = priceUnit;
        this.postalCode = postalCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getPrice() {
        return price;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public String getPostalCode() {
        return postalCode;
    }
}