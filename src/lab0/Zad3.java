package lab0;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Zad3 {

    private String nazwaPliku;
    private int iloscRekordow;

    public Zad3(String nazwa, int ilosc){
        nazwaPliku = nazwa;
        iloscRekordow = ilosc;
    }

    public static void main(String[] args) {
        Zad3 test = new Zad3("pomiary.dat", 100);

        test.zapisWstepnychObiektow();
        test.odczytajPomiary();
        test.pomiaryWskazanegoMiesiaca(2022,1);
        test.dopiszPomiar("nowePomiary.dat",35.25,2022,1,31,15,30);
        test.pomiaryWskazanegoMiesiaca(2022,1);
        test.kiedyOcieplenie(2022,1);

    }


    public void zapisWstepnychObiektow() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwaPliku));
            ArrayList<Pomiar> obiekty = tworzObiekty(iloscRekordow);

            for (int i = 0; i < obiekty.size(); i++) {
                oos.writeObject(obiekty.get(i));
            }
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pomiar> tworzObiekty(int ilosc) {
        ArrayList<Pomiar> obiekty = new ArrayList<>();
        for (int i = 0; i < ilosc; i++) {
            obiekty.add(new Pomiar());
        }
        Collections.sort(obiekty);
        return obiekty;
    }

    public ArrayList<Pomiar> deserializacjaDoListy() {

        ArrayList<Pomiar> listaPomiarow = new ArrayList<>();

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nazwaPliku));

            for (int i = 0; i < iloscRekordow; i++) {
                Pomiar p = (Pomiar) ois.readObject();
                listaPomiarow.add(p);
            }

            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPomiarow;
    }

    public void odczytajPomiary(){

        ArrayList<Pomiar> listaPomiarow = deserializacjaDoListy();
        for (int i = 0; i < listaPomiarow.size(); i++) {
            System.out.println(listaPomiarow.get(i));
        }
    }

    public void dopiszPomiar(String nazwaNowegoPliku, double temperatura, int rok, int miesiac, int dzien, int godzina, int minuta){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nazwaNowegoPliku));

            Pomiar p = new Pomiar(temperatura,rok,miesiac,dzien,godzina,minuta);
            ArrayList<Pomiar> pomiary = deserializacjaDoListy();
            pomiary.add(p);
            Collections.sort(pomiary);

            for (int i = 0; i < pomiary.size(); i++) {
                oos.writeObject(pomiary.get(i));
            }
            oos.close();

            nazwaPliku = nazwaNowegoPliku;
            iloscRekordow++;

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pomiaryWskazanegoMiesiaca(int rok, int miesiac){

        ArrayList<Pomiar> lista = deserializacjaDoListy();
        System.out.println("\n\nRekordy z wybranego roku i miesiaca: ");
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getCzas().getRok() == rok && lista.get(i).getCzas().getMiesiac() == miesiac)
                System.out.println(lista.get(i));
        }
    }

    public void kiedyOcieplenie(int rok, int miesiac){
        ArrayList<Pomiar> pomiary = deserializacjaDoListy();
        ArrayList<Pomiar> pomiaryDanegoMiesiaca = new ArrayList<>();
        Pomiar porownanie;
        for (int i = 0; i < pomiary.size(); i++) {
            if(pomiary.get(i).getCzas().getRok() == rok && pomiary.get(i).getCzas().getMiesiac() == miesiac)
                pomiaryDanegoMiesiaca.add(pomiary.get(i));
        }

        System.out.println("\n\nOcieplenie nastapilo w ponizszych dniach: ");
        if(!pomiaryDanegoMiesiaca.isEmpty()){
            porownanie = pomiaryDanegoMiesiaca.get(0);
            for (int i = 1; i < pomiaryDanegoMiesiaca.size(); i++) {
                if(pomiaryDanegoMiesiaca.get(i).getTemperatura() > porownanie.getTemperatura()){
                    System.out.println(pomiaryDanegoMiesiaca.get(i));
                }
                porownanie = pomiaryDanegoMiesiaca.get(i);
            }
        }
    }


}
