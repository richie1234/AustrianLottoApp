package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ruchitha on 07-Jun-16.
 */
public class AustrianLotto {

    public static final int DRAWING_SIZE = 6;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MAX_PICKS = 50;
    public static final int MIN_PICKS = 0;
    public static final int ARRAY_SIZE = 7;

    public int[] evaluate(String drawing, String[] picks) {


        validateInput(drawing, picks);

        int numberOfMatches;
        //used store final results
        int[] result = new int[ARRAY_SIZE];
        //split drawing string by spaces
        String[] drawingTokens = drawing.split(" ");
        if (drawingTokens.length != DRAWING_SIZE) {
            throw new IllegalArgumentException("Each drawing should contain six numbers");
        }

        //Find each pick, split, store, and sorted then
        for (int i = 0; i < picks.length; i++) {
            List<Integer> pick = getPickAsASortedList(picks[i]);
            numberOfMatches = 0;
            for (int k = 0; k < drawingTokens.length; k++) {

                int searchKey = Integer.parseInt(drawingTokens[k]);
                if ((searchKey < MIN_LOTTO_NUMBER) || (MAX_LOTTO_NUMBER < searchKey)) {
                    throw new IllegalArgumentException("Lotto drawing numbers should be between 1 and 45 ");
                }
                // Use binary search algorithm to search count the matching numbers
                if (binarySearch(pick, searchKey)) {
                    numberOfMatches++;
                }
            }
            //increment the results
            result[numberOfMatches]++;
        }
        return result;
    }

    /**
     * Input validation for NUll and Max and Min Picks     *
     * @param drawing
     * @param picks
     */
    private void validateInput(String drawing, String[] picks) {
        if (drawing == null || picks == null) {
            throw new IllegalArgumentException("Input strings and picks cannot be null");
        } else if (!(MIN_PICKS < picks.length && picks.length <= MAX_PICKS)) {
            throw new IllegalArgumentException("Picks should be between 0 and 50");
        }
    }

    /**
     * split, separate and sort each pick     *
     * @param pick
     * @return list of sorted pick
     */

    private List<Integer> getPickAsASortedList(String pick) {
        String[] pickTokens = pick.split(" ");
        if (pickTokens.length != DRAWING_SIZE) {
            throw new IllegalArgumentException("Each pick should contain six numbers");
        }

        List<Integer> pickList = new ArrayList<Integer>();
        for (int k = 0; k < pickTokens.length; k++) {
            int pickedNumber = Integer.parseInt(pickTokens[k].trim());
            if ((MIN_LOTTO_NUMBER <= pickedNumber) && (pickedNumber <= MAX_LOTTO_NUMBER)) {
                pickList.add(pickedNumber);
            } else {
                throw new IllegalArgumentException("Lotto numbers should be between 1 and 45 ");
            }
        }
        Collections.sort(pickList);
        return pickList;
    }

    /**
     * Simple binary search search for given number in a pick if found return true
     * else return false     *
     * @param numbers sorted pick
     * @param key     given number in a pick
     * @return if number found return true else return false
     */

    public static boolean binarySearch(final List<Integer> numbers,
                                       final Integer key) {
        int lo = 0;
        int hi = numbers.size() - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < numbers.get(mid)) hi = mid - 1;
            else if (key > numbers.get(mid)) lo = mid + 1;
            else return true;
        }
        return false;
    }

}
