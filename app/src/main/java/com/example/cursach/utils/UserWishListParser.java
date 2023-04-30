package com.example.cursach.utils;

import android.os.StrictMode;
import android.util.Log;

import com.example.cursach.model.WishListGame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class UserWishListParser {
    public static List<WishListGame> getWishListGames(String steamID) throws IOException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy); //Разобраться с потоками
        URL url = new URL("https://store.steampowered.com/wishlist/profiles/" + steamID + "/wishlistdata/"); //Список желаемого
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        int status = con.getResponseCode();
        if (status != -1) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return parseWishList(response.toString());
        }
        else
            return null;
    }

    private static List<WishListGame> parseWishList(String response) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<Integer, WishListGame>>(){}.getType();
        Map<Integer, WishListGame> wishList = gson.fromJson(response, type);
        /*for (WishListGame game :
                wishList.values()) {
            Log.d("log", "parce: " + game);
        }*/
        return (List<WishListGame>) wishList.values();
    }
}
