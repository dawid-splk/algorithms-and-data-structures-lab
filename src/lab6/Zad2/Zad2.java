package lab6.Zad2;

import java.util.Random;

public class Zad2 {

    public static void main(String[] args) {
        Random rand = new Random();
        CountingSort test = new CountingSort();

        int[] arrayToSort = new int[50];
        int bound = 9;
        System.out.println("Unsorted list: ");
        for (int i = 0; i < arrayToSort.length; i++) {
            arrayToSort[i] = rand.nextInt(0,bound+1);   //bound's exclusive
            System.out.print(arrayToSort[i] + "\t");
        }

        test.sort(arrayToSort, bound);

        System.out.println("\n\nResult:");
        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.print(arrayToSort[i] + "\t");
        }
    }
}
