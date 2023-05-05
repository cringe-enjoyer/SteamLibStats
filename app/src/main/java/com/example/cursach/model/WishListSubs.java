package com.example.cursach.model;

import androidx.annotation.NonNull;

public class WishListSubs {
    public int id;
    public int price;

    @NonNull
    @Override
    public String toString() {
        return "price " + price;
    }
}
