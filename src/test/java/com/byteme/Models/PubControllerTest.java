
import com.byteme.Controllers.PubController;
import org.junit.Before;
import org.junit.Test;

import com.byteme.Models.ConfigRepository;
import com.byteme.Models.GameStartStore;

import java.lang.Integer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class PubControllerTest {
    private ConfigRepository configRepository;
    private GameStartStore gameStartStore;
    private PubController pubController = new PubController();
    public static final int TIMEOUT = 2000000;


    @Before
    public void setUp() {
        ConfigRepository configRepository = new ConfigRepository();
        GameStartStore gameStartStore = new GameStartStore(configRepository);
    }

    @Test(timeout = TIMEOUT)
    public void testTimeBonus() {
        int temp = pubController.getTimeBonus(Integer.MAX_VALUE);
        assertTrue("The bonus was: " + temp + ". Should be 200.",
                temp == 200);

        temp = pubController.getTimeBonus(38);
        assertTrue("The bonus was: " + temp + ". Should be 200.",
                temp == 200);

        temp = pubController.getTimeBonus(37);
        assertTrue("The bonus was: " + temp + ". Should be 200.",
                temp == 200);

        temp = pubController.getTimeBonus(30);
        assertTrue("The bonus was: " + temp + ". Should be 150.",
                temp == 150);

        temp = pubController.getTimeBonus(25);
        assertTrue("The bonus was: " + temp + ". Should be 150.",
                temp == 150);

        temp = pubController.getTimeBonus(38);
        assertTrue("The bonus was: " + temp + ". Should be 200.",
                temp == 200);
    }
}