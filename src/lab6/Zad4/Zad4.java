package lab6.Zad4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Zad4 {

    Comparator<Integer> comp;
    ArrayList<Integer> randomData = new ArrayList<>();
    ArrayList<Integer> almostSortedData = new ArrayList<>();
    ArrayList<Integer> reverseSortedData = new ArrayList<>();

    public Zad4(int howMuchData) {
        Random rand = new Random();
        comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        for (int i = 0; i < howMuchData; i++) {
            randomData.add(rand.nextInt(-10,11));
        }

        for (int i = 0; i < howMuchData - 1; i++) {
            almostSortedData.add(i+1);
        }
        almostSortedData.add(howMuchData - 2);

        for (int i = howMuchData; i > 1; i--) {
            reverseSortedData.add(i);
        }
        reverseSortedData.add(3);
    }



    public static void main(String[] args) {
        Zad4 test = new Zad4(10000);
        System.out.println("\nDla zasugerowanej metody doboru ciagow: ");
        test.shellSortData(new GapSuggestedMethod());
        System.out.println("\nDla zaproponowanej przeze mnie metody doboru ciagow: ");
        test.shellSortData(new GapMyMethod());

    }

    public void shellSortData(Gap gapChoice){
        ArrayList<Integer> randomList = new ArrayList<>(randomData);
        ArrayList<Integer> almostSortedList = new ArrayList<>(almostSortedData);
        ArrayList<Integer> reverseSortedList = new ArrayList<>(reverseSortedData);
        Shellsort<Integer> sorter = new Shellsort<>(gapChoice, comp);
        System.out.println("\nShellsort stats for random data:\n");
        viewList(randomList);
        sorter.sort(randomList);
        viewList(randomList);
        System.out.println("\nShellsort stats for almost sorted data:\n");
        viewList(almostSortedList);
        sorter.sort(almostSortedList);
        viewList(almostSortedList);
        System.out.println("\nShellsort stats for reverse sorted data:\n");
        viewList(reverseSortedList);
        sorter.sort(reverseSortedList);
        viewList(reverseSortedList);
    }

    public void viewList(ArrayList<Integer> list){
        for (Integer i : list) {
            System.out.printf("%4d\t",i);
        }
        System.out.print("\n");
    }
}
