package app.model;

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
    public void testLottoAppGivenExampleOne() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"4 7 14 30 33 35", "1 11 12 25 37 38", "11 18 19 20 21 22"};
        int[] expectedResult = { 1,  0,  2,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test
    public void testLottoAppAllSixNumbersAreCorrect() {
        String drawing = "3 18 23 11 37 45";
        String[] picks = {"3 11 18 23 37 45"};
        int[] expectedResult = { 0,  0,  0,  0,  0,  0,  1 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test
    public void testLottoAppThreeNumbersAreCorrect() {
        String drawing = "42 26 33 2 13 14";
        String[] picks = {"13 4 36 42 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }


    @Test
    public void testLottoAppDrawingDoesNotMatchAnyPicks() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"4 7 14 30 33 35", "1 14 12 25 35 38", "19 13 19 20 21 22"};
        int[] expectedResult = { 3,  0,  0,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test
    public void testLottoAppDrawingMatchOnlyOneNumberInSecondPick() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"4 7 14 30 33 35", "1 11 12 25 35 38", "19 13 19 20 21 22"};
        int[] expectedResult = { 2,  1,  0,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

     @Test
    public void testLottoAppThreeNumbersCorrectAndAllSixNumbersCorrect() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"4 7 14 30 33 35", "1 14 12 25 35 38","11 3 45 23 37 18",
                "19 13 19 20 21 22", "45 11 19 20 3 22"};
        int[] expectedResult = { 3,  0,  0,  1,  0,  0,  1 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingMoreThanSixNumbers() {
        String drawing = "3 11 18 23 37 45 34";
        String[] picks = {"13 4 36 42 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingLessThanSixNumbers() {
        String drawing = "42 26 33 2 13";
        String[] picks = {"13 4 36 42 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppPickLessThanSixNumbers() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"13 4 36 42 26"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppPickMoreThanSixNumbers() {
        String drawing = "42 26 33 2 13 14";
        String[] picks = {"13 4 36 42 26 12 20"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppLottoPickNumbersShouldBetweenOneAndFortyFive() {
        String drawing = "42 26 33 2 13 14";
        String[] picks = {"13 4 36 42 26 -12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingNumbersShouldBetweenOneAndFortyFive() {
        String drawing = "42 26 33 2 -13 14";
        String[] picks = {"13 4 36 42 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

// Boundary value testing
    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingTestForZero() {
        String drawing = "42 26 33 2 13 0";
        String[] picks = {"13 4 36 42 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    // Boundary value testing
    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingTestForFortySIX() {
        String drawing = "42 26 33 2 46 12";
        String[] picks = {"13 4 36 42 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppPicksTestForZero() {
        String drawing = "42 26 33 2 13 12";
        String[] picks = {"13 4 36 0 26 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    // Boundary value testing
    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppPicksTestForFortySIX() {
        String drawing = "42 26 33 2 45 12";
        String[] picks = {"13 4 36 42 46 12"};
        int[] expectedResult = { 0,  0,  0,  1,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    // Boundary value testing
    @Test
    public void testLottoAppCheckForMaxPicksFifty() {
        String drawing = "42 26 33 2 45 12";
        String[] picks = new String[50];
        for (int i =0; i<50;i++ ){
            picks[i] = "13 4 36 42 8 12";
        }
        int[] expectedResult = { 0,  0,  50,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    // Boundary value testing
    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppCheckForMaxPicksFiftyOne() {
        String drawing = "42 26 33 2 45 12";
        String[] picks = new String[51];
        for (int i =0; i<51;i++ ){
            picks[i] = "13 4 36 42 8 12";
        }
        int[] expectedResult = { 0,  0,  51,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    // Boundary value testing
    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingIsEmpty() {
        String drawing = "";
        String[] picks = new String[51];
        for (int i =0; i<51;i++ ){
            picks[i] = "13 4 36 42 8 12";
        }
        int[] expectedResult = { 0,  0,  51,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppOnePickIsEmpty() {
        String drawing = "3 11 18 23 37 45";
        String[] picks = {"", "1 14 12 25 35 38", "19 13 19 20 21 22"};
        int[] expectedResult = { 3,  0,  0,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppDrawingIsNull() {
        String drawing = null;
        String[] picks = {"1 14 12 25 35 38", "19 13 19 20 21 22"};
        int[] expectedResult = { 3,  0,  0,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLottoAppPicksAreEmpty() {
        String drawing = null;
        String[] picks = {};
        int[] expectedResult = { 3,  0,  0,  0,  0,  0,  0 };
        int[] result = austrianLotto.evaluate(drawing ,picks);
        assertArrayEquals(result,expectedResult);
    }

}
