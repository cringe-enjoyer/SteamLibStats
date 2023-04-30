package com.example.cursach;

import static org.junit.Assert.*;

import com.lukaspradel.steamapi.data.json.playerstats.Achievement;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SteamAPIConnectorTest {
    private SteamWebApiClient client;

    @Before
    public void setUp() throws Exception {
        String API_KEY = "6F6A8C94C3E52C64BD799432680ED053"; //Steam API KEY
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
    }

    @Test
    public void getUserGames() {
        String steamID = "76561198113659898";

    }

    @Test
    public void getUserWishList() {
    }

    @Test
    public void getUserAchievements() {
    }

    @Test
    public void getGameAchievements() {
        String steamID = "76561198113659898";
        int gameID = 632470;
        List<Achievement> expected = new ArrayList<Achievement>() {{
            add(new Achievement() {
                {
                    setAchieved(1);
                    setName("ACH_COP_ART");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setName("ACH_COP_GOOD");
                }
            });
        }};
        List<Achievement> result = SteamAPIConnector.getUserGameAchievements(gameID, steamID);
        assertArrayEquals(expected.toArray(), result.toArray());
        assertEquals(expected.size(), result.size());
    }
}