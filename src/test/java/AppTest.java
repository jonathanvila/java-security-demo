import demo.security.util.Utils;
import demo.security.util.DBUtils;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testGenerateKey() {
        assertNotNull(Utils.generateKey());
    }

    @Test
    public void testSumNumbersMethods() {
        for (int max = 10; max <= 200; max += 10) {
            int legacy = DBUtils.getSumNumbers(max);
            int stream = DBUtils.sumNumbersStream(max);
            assertEquals("Mismatch for max=" + max, legacy, stream);
        }
    }
}
