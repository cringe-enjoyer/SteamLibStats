package com.example.cursach.utils;

import android.util.Log;

import com.example.cursach.model.WishListGame;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class UserWishListParser {
    public static void parse(String steamID) throws IOException {
        URL url = new URL("https://store.steampowered.com/wishlist/id/" + steamID + "/wishlistdata/"); //Список желаемого
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
            Gson gson = new Gson();
            Type type = new TypeToken<Map<Integer, WishListGame>>(){}.getType();
            Map<Integer, WishListGame> listGame = gson.fromJson(response.toString(), type);
            for (WishListGame game :
                    listGame.values()) {
                Log.d("log", "parce: " + game);
            }
        }
    }

}
