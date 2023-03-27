package lab5.Zad1_3;

import lab5.Sort.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Zad1_3 {

    Sort<Integer> wayOfSorting;
    Comparator<Integer> comp;
    ArrayList<Integer> randomData = new ArrayList<>();
    ArrayList<Integer> almostSortedData = new ArrayList<>();
    ArrayList<Integer> reverseSortedData = new ArrayList<>();


    public Zad1_3(Sort<Integer> howToSort, int howMuchData) {
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
        Zad1_3 test = new Zad1_3(new ShakerSort<>(), 100);
        test.sortData();
        test.setWayOfSorting(new BubbleSort<>());
        test.sortData();
        test.setWayOfSorting(new SelectSort<>());
        test.sortData();
        test.setWayOfSorting(new InsertSort<>());
        test.sortData();

    }

    public void sortData(){
        ArrayList<Integer> randomList = new ArrayList<>(randomData);
        ArrayList<Integer> almostSortedList = new ArrayList<>(almostSortedData);
        ArrayList<Integer> reverseSortedList = new ArrayList<>(reverseSortedData);
        System.out.println("\n" + wayOfSorting.getClass().getName() + " stats for random data:\n");
        viewList(randomList);
        wayOfSorting.sort(randomList, comp);
        viewList(randomList);
        System.out.println("\n" + wayOfSorting.getClass().getName() + " stats for almost sorted data:\n");
        viewList(almostSortedList);
        wayOfSorting.sort(almostSortedList, comp);
        viewList(almostSortedList);
        System.out.println("\n" + wayOfSorting.getClass().getName() + " stats for reverse sorted data:\n");
        viewList(reverseSortedList);
        wayOfSorting.sort(reverseSortedList, comp);
        viewList(reverseSortedList);
    }

    public void setWayOfSorting(Sort<Integer> wayOfSorting) {
        this.wayOfSorting = wayOfSorting;
    }

    public void viewList(ArrayList<Integer> list){
        for (Integer i : list) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");
    }


}
