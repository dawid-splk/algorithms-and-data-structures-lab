package lab6;

import lab5.Sort.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Zad1 {
    Sort<Integer> wayOfSorting;
    Comparator<Integer> comp;
    ArrayList<Integer> randomData = new ArrayList<>();
    ArrayList<Integer> almostSortedData = new ArrayList<>();
    ArrayList<Integer> reverseSortedData = new ArrayList<>();


    public Zad1(Sort<Integer> howToSort, int howMuchData) {
        Random rand = new Random();
        wayOfSorting = howToSort;
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
        Zad1 test = new Zad1(new HeapSort<>(), 50);
        test.sortData();
        test.setWayOfSorting(new QuickSort<>());
        test.sortData();
        test.setWayOfSorting(new MergeSort<>());
        test.sortData();

    }

    public void sortData(){
        ArrayList<Integer> randomList = new ArrayList<>(randomData);
        ArrayList<Integer> almostSortedList = new ArrayList<>(almostSortedData);
        ArrayList<Integer> reverseSortedList = new ArrayList<>(reverseSortedData);
        System.out.println("\n" + wayOfSorting.getClass().getName() + " stats for random data:\n");
        viewList(randomList);
        viewList( wayOfSorting.sort(randomList, comp));
        System.out.println("\n" + wayOfSorting.getClass().getName() + " stats for almost sorted data:\n");
        viewList(almostSortedList);
        viewList(wayOfSorting.sort(almostSortedList, comp));
        System.out.println("\n" + wayOfSorting.getClass().getName() + " stats for reverse sorted data:\n");
        viewList(reverseSortedList);
        viewList(wayOfSorting.sort(reverseSortedList, comp));
    }

    public void setWayOfSorting(Sort<Integer> wayOfSorting) {
        this.wayOfSorting = wayOfSorting;
    }

    public void viewList(ArrayList<Integer> list){
        for (Integer i : list) {
            System.out.printf("%3d\t",i);
        }
        System.out.print("\n");
    }


}
