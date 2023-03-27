package lab6.Zad3;

import java.util.*;

public class Zad3 {

    ArrayList<Student> list;
    Random rand = new Random();
    ArrayList<Student> two = new ArrayList<>();
    ArrayList<Student> three = new ArrayList<>();
    ArrayList<Student> threePlus = new ArrayList<>();
    ArrayList<Student> four = new ArrayList<>();
    ArrayList<Student> fourPlus = new ArrayList<>();
    ArrayList<Student> five = new ArrayList<>();
    ArrayList<Student> fivePlus = new ArrayList<>();
    ArrayList<ArrayList<Student>> buckets = new ArrayList<>();


    public Zad3(int sizeOfList){
        double[] grades = {2.0,3.0,3.5,4.0,4.5,5.0,5.5};
        buckets.add(two);
        buckets.add(three);
        buckets.add(threePlus);
        buckets.add(four);
        buckets.add(fourPlus);
        buckets.add(five);
        buckets.add(fivePlus);

        list = new ArrayList<>();
        int idxRand;
        boolean shouldContinue;
        for (int i = 0; i < sizeOfList; i++) {
            idxRand = rand.nextInt(9999);
            shouldContinue = true;
            do{
                if(!list.contains(new Student(260000 + idxRand, 0.0))){
                    list.add(new Student(260000 + idxRand, grades[rand.nextInt(grades.length)]));
                    shouldContinue = false;
                 } else {
                    idxRand = rand.nextInt(9999);
                }
            } while(shouldContinue);
        }
    }

    public static void main(String[] args) {
        Zad3 test = new Zad3(50);
        System.out.println("Unsorted list: ");
        test.viewList(test.list);
        test.viewList(test.bucketSort());
        test.viewList(test.bubbleSort());
    }

    public ArrayList<Student> bucketSort(){

        int totalCompCounter = 0;
        int totalMoveCounter = 0;

        ArrayList<Student> tempList = new ArrayList<>();
        for (Student st: list) {
            tempList.add(new Student(st));
        }
        ArrayList<Student> result = new ArrayList<>();
        for (Student st : tempList) {
            totalMoveCounter++;
            switch ((int) (2 * st.getGrade())) {
                case 4 -> two.add(st);
                case 6 -> three.add(st);
                case 7 -> threePlus.add(st);
                case 8 -> four.add(st);
                case 9 -> fourPlus.add(st);
                case 10 -> five.add(st);
                case 11 -> fivePlus.add(st);
                default -> {}
            }
        }
        BubbleSort<Student> sorter = new BubbleSort<>();
        Comparator<Student> cmp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getIndex(), o2.getIndex());
            }
        };

        for (ArrayList<Student> bucket : buckets) {
            sorter.sort(bucket, cmp);
            totalMoveCounter += 2*sorter.getSwapCounter();
            totalCompCounter += sorter.getCompCounter();
            result.addAll(bucket);
        }

        System.out.println("\nBucketsort stats and result: ");
        System.out.println("Number of comparisons: " + totalCompCounter);
        System.out.println("Number of reallocations: " + totalMoveCounter);

        return result;
    }

    public ArrayList<Student> bubbleSort(){
        ArrayList<Student> tempList = new ArrayList<>();
        for (Student st: list) {
            tempList.add(new Student(st));
        }
        BubbleSort<Student> bs = new BubbleSort<>();
        Comparator<Student> cmp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if(o1.getGrade() == o2.getGrade()){
                    return Integer.compare(o1.getIndex(), o2.getIndex());
                } else {
                    return Double.compare(o1.getGrade(), o2.getGrade());
                }
            }
        };
        bs.sort(tempList,cmp);

        System.out.println("\nBubblesort stats and result: ");
        System.out.println("Number of comparisons: " + bs.getCompCounter());
        System.out.println("Number of reallocations: " + 2*bs.getSwapCounter());

        return tempList;
    }

    public void viewList(ArrayList<Student> listToView){
        for (Student st : listToView) {
            System.out.print(st + "\t");
        }
        System.out.print("\n");
    }

}
