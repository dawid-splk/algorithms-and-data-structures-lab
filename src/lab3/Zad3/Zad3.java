package lab3.Zad3;

import lab3.Zad1.KolejkaNieograniczona;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Zad3 {     //przypadki testowe (pusta kolejka, 2 klientow, 10 klientow w pliku "postac wynikowa konsoli 3.3"
                        //ze wzgledu na wartosci wczytywane z klawiatury

    private static final ArrayList<Urzednik> listaUrzednikow = new ArrayList<>();
    private static final KolejkaNieograniczona<Klient> kolejka = new KolejkaNieograniczona<>();
    private static int klienciDoDodania;
    private static final Scanner sc = new Scanner(System.in);
    private static Random rand = new Random();

    public static void main(String[] args) {
        Urzednik a = new Urzednik("A");
        Urzednik b = new Urzednik("B");
        Urzednik c = new Urzednik("C");
        listaUrzednikow.add(a);
        listaUrzednikow.add(b);
        listaUrzednikow.add(c);


        System.out.print("Wpisz liczbę klientow do obsluzenia: ");
        int liczbaKlientow = sc.nextInt();
        klienciDoDodania = liczbaKlientow;
        for (int i = 0; i < liczbaKlientow * 0.6; i++) {
            klienciDoDodania--;
            kolejka.enqueue(new Klient());
        }

        boolean warunek = true;
        while(warunek){
            warunek = false;
            for (Urzednik u : listaUrzednikow) {
                if (!u.getZajety()) {
                    try {
                        u.setObecnyKlient(kolejka.dequeue());
                        System.out.println("Urzednik " + u.getImie() + " zaczal obslugiwac nowego klienta.");
                        warunek = true;
                    } catch (NoSuchElementException ignored) {
                        System.out.println(" Urzednik " + u.getImie() + " musi czekac na kolejnego klienta.");
                    }
                } else {
                    if (u.getObecnyKlient().getCzasSprawy() > 1) {
                        System.out.println("Urzednik " + u.getImie() + " jest w trakcie obslugiwania klienta.");
                        u.getObecnyKlient().setCzasSprawy(u.getObecnyKlient().getCzasSprawy() - 1);
                    } else {
                        System.out.println("Urzednik " + u.getImie() + " zakonczyl obslugiwanie klienta.");
                        u.setObecnyKlient(null);
                    }
                    warunek = true;
                }
            }
            System.out.print("\n");

            if(klienciDoDodania > 0){
                warunek = true;
                if(rand.nextDouble() > 0.6) {
                    System.out.println("\nNowy klient dolaczyl do kolejki!\n");
                    klienciDoDodania--;
                    kolejka.enqueue(new Klient());
                }
            }
        }
        System.out.println("Obsluzono wszystkich klientow na dzis.");
    }

}
