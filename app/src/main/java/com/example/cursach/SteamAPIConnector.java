package com.example.cursach;

import android.util.Log;

import com.example.cursach.model.WishListGame;
import com.example.cursach.utils.UserWishListParser;
import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.achievementpercentages.GetGlobalAchievementPercentagesForApp;
import com.lukaspradel.steamapi.data.json.ownedgames.Game;
import com.lukaspradel.steamapi.data.json.ownedgames.GetOwnedGames;
import com.lukaspradel.steamapi.data.json.playerstats.Achievement;
import com.lukaspradel.steamapi.data.json.playerstats.GetUserStatsForGame;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetGlobalAchievementPercentagesForAppRequest;
import com.lukaspradel.steamapi.webapi.request.GetOwnedGamesRequest;
import com.lukaspradel.steamapi.webapi.request.GetUserStatsForGameRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SteamAPIConnector {

    private static final String API_KEY = ""; //Steam API KEY
    private static final String TAG = "myTag";

    private static SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();

/*    public SteamAPIConnector() {
        client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
    }*/

    public static List<Game> getUserGames(String steamID) throws SteamApiException {

        GetOwnedGamesRequest gamesRequest = SteamWebApiRequestFactory.createGetOwnedGamesRequest(steamID);
        GetOwnedGames getOwnedGames = client.<GetOwnedGames>processRequest(gamesRequest);
        List<Game> games = getOwnedGames.getResponse().getGames();

        return games;
    }

    public static List<WishListGame> getUserWishList(String steamID) {
        try {
            return UserWishListParser.getWishListGames(steamID);
        } catch (Exception e) {
            Log.d(TAG, "error: " + e);
        }
        return null;
    }

    public static Map<Game, List<Achievement>> getUserAchievements(String steamID) {
        try {
            Map<Game, List<Achievement>> result = new HashMap<>();
            List<Game> games = getUserGames(steamID);
            for (Game game : games) {
                result.put(game, getUserGameAchievements(game.getAppid(), steamID));
            }
            return result;
        } catch (SteamApiException e) {
            Log.d(TAG, "getUserAchievements: ");
        }
        return null;
    }

    public static List<Achievement> getUserGameAchievements(int gameID, String steamID) {
        GetUserStatsForGameRequest userStatsForGameRequest = SteamWebApiRequestFactory
                .createGetUserStatsForGameRequest(gameID, steamID, "ru");
        try {
            GetUserStatsForGame userStatsForGame = client.processRequest(userStatsForGameRequest);
            return userStatsForGame.getPlayerstats().getAchievements();
        } catch (SteamApiException e) {
            //throw new RuntimeException(e);
        }
        return null;
    }

    public static List<com.lukaspradel.steamapi.data.json.achievementpercentages.Achievement> getGameAchievements(int gameID) {
        GetGlobalAchievementPercentagesForAppRequest achievementsRequest = SteamWebApiRequestFactory
                .createGetGlobalAchievementPercentagesForAppRequest(gameID);
        try {
            GetGlobalAchievementPercentagesForApp requestResult = client
                    .processRequest(achievementsRequest);
            return requestResult.getAchievementpercentages().getAchievements(); //Достижения с процентами (сколько людей получило)
        } catch (SteamApiException e) {
            //throw new RuntimeException(e);
        }
        return null;
    }
}