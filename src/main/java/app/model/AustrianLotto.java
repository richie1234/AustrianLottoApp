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

        if (drawing ==null || picks == null){
            throw new IllegalArgumentException("Input strings and picks cannot be null");
        }
        else if (!(MIN_PICKS <picks.length && picks.length <= MAX_PICKS)) {
            throw new IllegalArgumentException("Picks should be between 0 and 50");
        }

        int numberOfMatches =0;
        //used store final results
        int[] result = new int[ARRAY_SIZE];
        //split drawing string by spaces
        String[] drawingTokens = drawing.split(" ");
        if (drawingTokens.length != DRAWING_SIZE) {
            throw new IllegalArgumentException("Each drawing should contain six numbers");
        }

        //Find each pick, split, store, and sorted then
        for (int i = 0; i < picks.length; i++) {
            String[] pick = picks[i].split(" ");
            if (pick.length != DRAWING_SIZE) {
                throw new IllegalArgumentException("Each pick should contain six numbers");
            }

            List<Integer> pickList = new ArrayList<Integer>();
            for (int k = 0; k < pick.length; k++) {
                int pickedNumber = Integer.parseInt(pick[k].trim());
                if((MIN_LOTTO_NUMBER <= pickedNumber) &&  (pickedNumber <= MAX_LOTTO_NUMBER)){
                    pickList.add(pickedNumber);
                }else {
                    throw new IllegalArgumentException("Lotto numbers should be between 1 and 45 ");
                }
            }
            Collections.sort(pickList);
            numberOfMatches = 0;
            for (int k = 0; k < drawingTokens.length; k++) {

                int searchKey = Integer.parseInt(drawingTokens[k]);
                if((searchKey < MIN_LOTTO_NUMBER  ) ||  ( MAX_LOTTO_NUMBER < searchKey )){
                     throw new IllegalArgumentException("Lotto drawing numbers should be between 1 and 45 ");
                }
                // Use binary search algorithm to count the matching numbers
                if (binarySearch(pickList, searchKey)) {
                    numberOfMatches++;
                }
            }
            //increment the results
            result[numberOfMatches]++;
        }
        return result;
    }


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
