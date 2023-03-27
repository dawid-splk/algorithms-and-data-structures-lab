package lab2;

public class Zad3 {

    public static void main(String[] args) {
        Zad3 test = new Zad3();

        TwoWayCycledList<Student> list1 = new TwoWayCycledList<>();
        TwoWayCycledList<Student> list2 = new TwoWayCycledList<>();

        list1.add(new Student("Dawid", "Spalek", 1));
        list1.add(new Student("Jakub", "Dziarmaga", 3));
        list1.add(new Student("Michal", "Chudy", 5));
        list1.add(new Student("Kacper", "Mikluszka", 7));
        list1.add(new Student("Mateusz", "Spalek", 9));
        list2.add(new Student("Lukasz", "Nowak", 2));
        list2.add(new Student("Karolina", "Michalska", 4));
        list2.add(new Student("Natalia", "Grobek", 6));
        list2.add(new Student("Rafal", "Kozlik", 8));
        list2.add(new Student("Julia", "Kowalska", 10));

        System.out.println("Zawartosc listy 1: ");
        list1.viewList();
        System.out.println("\nZawartosc listy 2: ");
        list2.viewList();
        System.out.println("\n");

//        System.out.println("Wstawianie listy przed elementem o indeksie 0:\n");
//        list1.insertListIndex(list2,0).viewList();
//        System.out.println("Wstawianie listy przed elementem o indeksie 4 (ostatnim):\n");
//        list1.insertListIndex(list2,4).viewList();
//        System.out.println("Wstawianie listy przed elementem o indeksie -1 (nieprawidlowa wartosc, nie ma takiego elementu):");
//        list1.insertListIndex(list2,-1).viewList();
//        System.out.println("Wstawianie listy przed elementem o indeksie X w liscie pustej (nie znajduje elementu, rzuca wyjatkiem):");
//        list1.clear();
//        list1.insertListIndex(list2,1).viewList();
//        System.out.println("Wstawianie pustej listy przed elementem o indeksie X w liscie (lista pozostaje bez zmian):");
//        list2.clear();
//        list1.insertListIndex(list2,4).viewList();

        System.out.println("Wstawianie listy przed elementem pierwszym z uzyciem metody 'equals':\n");
        list1.insertListEquals(list2, new Student("Dawid", "Spalek", 1)).viewList();
//        System.out.println("Wstawianie listy przed elementem z uzyciem metody 'equals' (literowka w nazwisku, nie znajdzie elementu):\n");
//        list1.insertListEquals(list2, new Student("Dawid", "Spalak", 1)).viewList();

//        System.out.println("Wstawianie listy na koniec listy pierwszej: \n");
//        list1.insertListEnd(list2).viewList();

//        System.out.println("Wstawianie listy na koniec listy pierwszej (lista pierwsza jest pusta): \n");
//        list1.clear();
//        list1.insertListEnd(list2).viewList();

    }

}
