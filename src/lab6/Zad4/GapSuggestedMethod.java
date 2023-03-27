package lab6.Zad4;

import java.util.Stack;

public class GapSuggestedMethod implements Gap{

    @Override
    public Stack<Integer> createGaps(int listSize) {
        Stack<Integer> gaps = new Stack<>();
        int i = 1;
        gaps.push(i);
        int gap;
        while((gap = 3*i + 1) <= listSize/3){
            gaps.push(gap);
            i = gap;
        }
        return gaps;
    }
}
