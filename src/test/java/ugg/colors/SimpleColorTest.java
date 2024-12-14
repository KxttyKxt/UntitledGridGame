package ugg.colors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimpleColorTest {
    @Test
    public void test_toColorID_batchTest() {
        int[] correctIDs = {
                30,  31,  32,  33,  34,  35,  36,  37,
                90,  91,  92,  93,  94,  95,  96,  97,
                40,  41,  42,  43,  44,  45,  46,  47,
                100, 101, 102, 103, 104, 105, 106, 107,

                39,  49
        };

        int arrayIndex = 0;
        for (SimpleColor simpleColor : SimpleColor.values())
            Assertions.assertEquals(correctIDs[arrayIndex++], simpleColor.toColorID());
    }
}
