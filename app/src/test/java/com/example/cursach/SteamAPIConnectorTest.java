package com.example.cursach;

import static org.junit.Assert.*;

import com.lukaspradel.steamapi.data.json.playerachievements.Achievement;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SteamAPIConnectorTest {
    private SteamWebApiClient client;

    @Before
    public void setUp() throws Exception {
        String API_KEY = ""; //Steam API KEY
        SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(API_KEY).build();
    }

    @Test
    public void getUserGames() {

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
        int gameID = 286320;
        List<Achievement> expected = new ArrayList<Achievement>() {{
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_1");
                    setAdditionalProperty("unlocktime", 1465919046);
                    setAdditionalProperty("name", "Awakening");
                    setAdditionalProperty("description", "Finish chapter 1");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_2");
                    setAdditionalProperty("unlocktime", 1471447854);
                    setAdditionalProperty("name", "An extended hand");
                    setAdditionalProperty("description", "Finish chapter 2");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_2_boss");
                    setAdditionalProperty("unlocktime", 1471448721);
                    setAdditionalProperty("name", "Into the darkness");
                    setAdditionalProperty("description", "Escape the eyrie");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_3");
                    setAdditionalProperty("unlocktime", 1584568831);
                    setAdditionalProperty("name", "Beneath the surface");
                    setAdditionalProperty("description", "Finish chapter 3");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_4");
                    setAdditionalProperty("unlocktime", 1584572566);
                    setAdditionalProperty("name", "A winding path");
                    setAdditionalProperty("description", "Finish chapter 4");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_5");
                    setAdditionalProperty("unlocktime", 1584575792);
                    setAdditionalProperty("name", "Gate of promises");
                    setAdditionalProperty("description", "Finish chapter 5");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_chapter_5_boss");
                    setAdditionalProperty("unlocktime", 1584578761);
                    setAdditionalProperty("name", "Dawn");
                    setAdditionalProperty("description", "Escape the cavern");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_lore_library");
                    setAdditionalProperty("unlocktime", 1584578306);
                    setAdditionalProperty("name", "Folklorist");
                    setAdditionalProperty("description", "Complete the lore library");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(1);
                    setApiname("ach_grave");
                    setAdditionalProperty("unlocktime", 1584572714);
                    setAdditionalProperty("name", "A final resting place");
                    setAdditionalProperty("description", "Find the nattramn's grave");
                }
            });
            add(new Achievement() {
                {
                    setAchieved(0);
                    setApiname("ach_mystery_carving");
                    setAdditionalProperty("unlocktime", 0);
                    setAdditionalProperty("name", "Mystery carving");
                    setAdditionalProperty("description", "It's a secret to everybody");
                }
            });
        }};
        List<Achievement> result = SteamAPIConnector.getUserGameAchievements(gameID, steamID);
        assertArrayEquals(expected.toArray(), result.toArray());
        assertEquals(expected.size(), result.size());
    }
}