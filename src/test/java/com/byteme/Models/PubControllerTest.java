
import com.byteme.Controllers.PlaceMuleHandler;
import com.byteme.Controllers.PubController;
import com.byteme.Schema.*;
import com.byteme.Schema.Difficulty;
import com.byteme.Schema.GameConfigParams;
import com.byteme.Schema.MapControllerStates;
import com.byteme.Schema.MapType;
import com.byteme.Schema.PlayerConfigParams;
import com.byteme.Schema.Race;
import com.byteme.Models.MapStateStore;
import com.byteme.Util.RandomWrapper;
import com.byteme.Util.TestableRandomWrapper;
import com.byteme.Util.MockedRandomWrapper;

import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;


import com.byteme.Models.ConfigRepository;
import com.byteme.Models.GameStartStore;

import java.lang.Integer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class PubControllerTest {
    private ConfigRepository configRepository;
    private GameStartStore gameStartStore;
    private MapStateStore mapStateStore;
    private PubController pubController = new PubController();
    public static final int TIMEOUT = 2000000;

    private MockedRandomWrapper mockRandom200 = new MockedRandomWrapper(200);
    private MockedRandomWrapper mockRandom150 = new MockedRandomWrapper(150);
    private MockedRandomWrapper mockRandom100 = new MockedRandomWrapper(100);
    private MockedRandomWrapper mockRandom50 = new MockedRandomWrapper(50);
    private MockedRandomWrapper mockRandom1 = new MockedRandomWrapper(1);
    private MockedRandomWrapper mockRandom0 = new MockedRandomWrapper(0);

    private PlayerConfigParams p1;
    private PlayerConfigParams p2;


    @Before
    public void setUp() {
        GameConfigParams gameConfigParams = new GameConfigParams(Difficulty.BEGINNER, MapType.STANDARD, 2);
        PlayerConfigParams p1 = new PlayerConfigParams("Jim", Race.FLAPPER, "Red", 100, null, 0);
        PlayerConfigParams p2 = new PlayerConfigParams("Bob", Race.FLAPPER, "Yellow", 100, null, 1);

        Map<Integer, PlayerConfigParams> playerConfigList = new HashMap<Integer, PlayerConfigParams>(2);
        playerConfigList.put(0, p1);
        playerConfigList.put(1, p2);
        //Make Config Repository
        configRepository = new ConfigRepository(gameConfigParams, playerConfigList);

        ArrayList<PlayerConfigParams> players = new ArrayList<PlayerConfigParams>(2);
        players.add(p1);
        players.add(p2);

        mapStateStore = new MapStateStore(configRepository, 1, players);

        gameStartStore = new GameStartStore(configRepository);
    }

    @Test(timeout = TIMEOUT)
    public void testGetMoney() {
        //t = MAX Rand = 200 Round = 1
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(Integer.MAX_VALUE);
        pubController = new PubController(mockRandom200, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 350);

        //t = 37 Rand = 200 Round = 1
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(37);
        pubController = new PubController(mockRandom200, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 600);

        //t = 25 Rand = 150 Round = 2
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(25);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom150, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 850);

        //t = 12 Rand = 100 Round = 3
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(12);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom100, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1100);

        //t = 1 Rand = 50 Round = 4
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom50, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1350);

        //t = 0 Rand = 0 Round = 5
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(0);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom0, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1350);

        //t = 37 Rand = 1 Round = 6
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(37);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1450);

        //t = 1 Rand = 1 Round = 7
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1550);

        //t = 1 Rand = 1 Round = 8
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1700);

        //t = 1 Rand = 1 Round = 9
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 1850);

        //t = 1 Rand = 1 Round = 10
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 2000);

        //t = 1 Rand = 1 Round = 11
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 2150);

        //t = 1 Rand = 1 Round = 12
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(1);
        mapStateStore.incRound();
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        assertTrue(pubController.getPlayer().getMoney() == 2350);

        //t = minInt Rand = 1 Round = 12
        mapStateStore.getPlayerAt(gameStartStore.getCurrentPlayer()).setTimeLeft(Integer.MIN_VALUE);
        pubController = new PubController(mockRandom1, gameStartStore, mapStateStore, configRepository);
        try {
            pubController.getPlayer().getMoney();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().equals("Time cannot be negative!"));
        }

        //Breaks Code
        //assertTrue(false);


    }
}