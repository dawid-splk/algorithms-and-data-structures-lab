package lab6.Zad2;

public class CountingSort {

    int moveCounter;
    int compCounter;

    public int[] sort(int[] array, int bound){

        moveCounter = 0;
        compCounter = 0;    //nie wiem, w którym miejscu miałyby następować porównania, więc u mnie compCounter = 0

        int[] temp = new int[++bound];
        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            temp[array[i]]++;
        }

        temp[0]--;

        for (int i = 1; i < temp.length; i++) {
            temp[i] += temp[i-1];
        }

        System.out.println("\n\nPrimary position of each number: ");
        for (int i = 0; i < temp.length; i++) {
            System.out.print(temp[i] + "\t");
        }

        for (int i = array.length-1; i >= 0; i--) {
            result[temp[array[i]]] = array[i];
            moveCounter++;
            temp[array[i]]--;
        }

        for (int i = 0; i < result.length; i++) {
            array[i] = result[i];
        }

        System.out.println("\n\nNumber of actions (comparisons/reallocations): " + (compCounter + moveCounter));

        return array;
    }
}
