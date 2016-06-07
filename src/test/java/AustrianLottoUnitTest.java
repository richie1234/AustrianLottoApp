import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by Ruchitha on 07-Jun-16.
 */
public class AustrianLottoUnitTest {

    private AustrianLotto austrianLotto;

    @Before
    public void setUp() {
        austrianLotto = new AustrianLotto();

    }


    @Test
    public void testLottoExampleOne() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"4 7 14 30 33 35", "1 11 12 25 37 38", "11 18 19 20 21 22"};
        int[] expectedResult = { 1,  0,  2,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);

        assertArrayEquals(result,expectedResult);
    }

}
