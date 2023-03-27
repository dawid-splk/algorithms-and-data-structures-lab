package lab2;

import java.util.Scanner;

public class Zad4 {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {        //ze wzgledu na wczytywanie wartosci z klawiatury przypadki testowe sa
        Zad4 test = new Zad4();                     //umieszczone w dokumencie tekstowym "postac wynikowa konsoli 2.4"
        System.out.print("Wpisz liczbe N osob w kole: ");
        OneWayCycledList<Integer> circle = test.createCircle(sc.nextInt());
        System.out.print("Wpisz liczbe K - co ktora osoba popelnia samobojstwo: ");
        circle.avoidDeath(sc.nextInt());
        System.out.print("Nalezalo ustawic sie na pozycjach:\t");
        for (int i = 0; i < circle.size(); i++) {
            System.out.print(circle.get(i).toString());
            System.out.print("\t");
        }
    }

    public OneWayCycledList<Integer> createCircle(int n){

        OneWayCycledList<Integer> circle = new OneWayCycledList<>();
        for (int i = 0; i < n; i++) {
            circle.add(i+1);
        }
        return circle;
    }

}
