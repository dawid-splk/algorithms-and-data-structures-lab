package lab6.Zad4;

import java.util.ArrayList;
import java.util.Stack;

public class GapMyMethod implements Gap{


    @Override
    public Stack<Integer> createGaps(int listSize) {
        Stack<Integer> gaps = new Stack<>();
        ArrayList<Integer> conversion = new ArrayList<>();
        int i = 1;
        int gap;
        while((gap = 2 * (listSize / (int) Math.pow(2,i+1)) + 1) != 1){
            conversion.add(gap);
            i++;
        }
        conversion.add(1);
        for (int j = conversion.size() - 1; j >= 0; j--) {
            gaps.push(conversion.get(j));
        }
        return gaps;
    }
}
