package com.example.cursach.model;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;

public class WishListGame {
    public String name;
    public String review_desc;
    public String reviews_total;
    public int reviews_percent;
    public String release_string;
    public List<WishListSubs> subs;
    public String[] tags;

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name ").append(name).append("\n");
        sb.append("review_desc ").append(review_desc).append("\n");
        sb.append("reviews_percent ").append(reviews_percent).append("\n");
        sb.append("reviews_total ").append(reviews_total).append("\n");
        sb.append("release_string ").append(release_string).append("\n");
        sb.append("subs ").append(subs).append("\n");
        sb.append("tags: \n");
        for (String tag : tags) {
            sb.append(tag).append(", ");
        }
        sb.replace(sb.length()-2, sb.length(), "");
        return sb.toString();
    }
}
