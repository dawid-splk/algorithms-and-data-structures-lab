package lab0;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Zad4 {

    private String nazwaPliku;
    private ArrayList<CiagPomiarow> lista;

    public Zad4(String nazwa){
        nazwaPliku = nazwa;
        lista = new ArrayList<>();
    }

    public static void main(String[] args) {
        Zad4 test = new Zad4("tablicePomiarow.dat");
        CiagPomiarow cg1 = test.tworzPomiary(15);
        CiagPomiarow cg2 = test.tworzPomiary(4);
        CiagPomiarow cg3 = test.tworzPomiary(2);
        test.zapisDoPliku();
        test.odczytZPliku();
        test.dopiszCiagPomiarow(3, "noweTablicePomiarow.dat");
        test.odczytZPliku();

    }


    public CiagPomiarow tworzPomiary(int ilosc){

        CiagPomiarow cg = new CiagPomiarow();
        ArrayList<Pomiar> listaTemp = new ArrayList<>();
        for (int i = 0; i < ilosc; i++) {
            listaTemp.add(new Pomiar());
        }
        Collections.sort(listaTemp);
        Pomiar [] tab = new Pomiar[ilosc];
        for (int i = 0; i < ilosc; i++) {
            tab[i] = listaTemp.get(i);
        }
        cg.setPomiary(tab);
        lista.add(cg);
        return cg;
    }

    public void zapisDoPliku(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwaPliku));

            for (int i = 0; i < lista.size(); i++) {
                oos.writeObject(lista.get(i));
            }
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void odczytZPliku(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazwaPliku));

            try {
                while (true) {
                    System.out.println(ois.readObject());
                }
            } catch (EOFException e) {
                System.out.println("\nKoniec pliku\n\n");
            }
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dopiszCiagPomiarow(int iloscElementow, String nazwaNowegoPliku){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazwaPliku));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwaNowegoPliku));

            try {
                while(true){
                    oos.writeObject(ois.readObject());
                }
            } catch (Exception e) {
                oos.writeObject(tworzPomiary(iloscElementow));
            }
            nazwaPliku = nazwaNowegoPliku;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
