package com.example.cursach;

import android.os.StrictMode;
import android.util.Log;

import com.example.cursach.utils.UserWishListParser;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.List;

public class SteamAPIConnector {

    private static final String API_KEY = ""; //Steam API KEY
    private static final String TAG = "myTag";

    private static SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();;

/*    public SteamAPIConnector() {
        client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
    }*/

    public static List<Game> getUserGames(String steamID) throws SteamApiException {

        GetOwnedGamesRequest gamesRequest = SteamWebApiRequestFactory.createGetOwnedGamesRequest(steamID);
        GetOwnedGames getOwnedGames = client.<GetOwnedGames> processRequest(gamesRequest);
        List<Game> games = getOwnedGames.getResponse().getGames();

        return games;
    }

    public static List getWishList(String steamID) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            UserWishListParser.parse(steamID);
        }
        catch(Exception e) {
            Log.d(TAG, "error: "+ e);
        }
        return null;
    }

/*    private static class UserWishListParser {
        private static void parce(String steamID) throws IOException {
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
                    Log.d(TAG, "parce: " + game);
                }
            }
        }

    }*/
}