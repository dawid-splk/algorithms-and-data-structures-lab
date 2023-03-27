package lab1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Zad2 {

    StudentRozszerzony [] lista;

    public Zad2(int iloscStudentow){
        lista = new StudentRozszerzony[iloscStudentow];
    }

    public static void main(String[] args) {
        Zad2 testRoz = new Zad2(5);
        StudentRozszerzony s1 = new StudentRozszerzony(3,"Nowak", "Jan");
        StudentRozszerzony s2 = new StudentRozszerzony(7,"Kowalska", "Jolanta");
        StudentRozszerzony s3 = new StudentRozszerzony(4,"Zielinski", "Jerzy");
        StudentRozszerzony s4 = new StudentRozszerzony(2,"Nowak", "Katarzyna");
        StudentRozszerzony s5 = new StudentRozszerzony(1,"Nowakowska", "Joanna");

        testRoz.wpiszNaListeRoz(s1);
        testRoz.wpiszNaListeRoz(s2);
        testRoz.wpiszNaListeRoz(s3);
        testRoz.wpiszNaListeRoz(s4);
        testRoz.wpiszNaListeRoz(s5);

        testRoz.dopiszOcene(2, 4.0, 4.5);
        testRoz.dopiszOcene(3, 2.0, 3.0, 2.0, 2.0);
        testRoz.dopiszOcene(4, 5.5, 4.5, 3.5, 4.0);
        testRoz.dopiszOcene(7, 3.5, 5.0, 3.0);

        testRoz.wyswietlStudentow();
        testRoz.liczSredniaGrupy();
        testRoz.wyswietlListeNiezaliczajacych();

    }

    public void wyswietlStudentow(){
        System.out.println("\nLista wszystkich studentow: ");
        Arrays.sort(lista);
        for(StudentRozszerzony st : lista){
            System.out.println(st);
        }
    }

    public void dopiszOcene(int nrIndeksu, Double... ocenyDoWpisania){
        for(StudentRozszerzony s : lista){
            if(s.getNrIndeksu() == nrIndeksu)
                s.getOceny().addAll(List.of(ocenyDoWpisania));
        }
    }

    public void wpiszNaListeRoz(StudentRozszerzony st){
        for (int i = 0; i < lista.length; i++) {
            if(lista[i] == null) {
                lista[i] = st;
                return;
            }
        }
    }


    public void liczSredniaGrupy(){
        double suma = 0;
        int ile = 0;

        Predicate<Student> predykat = new Predicate<>() {
            @Override
            public boolean accept(Student s) {
                StudentRozszerzony sr = (StudentRozszerzony) s;
                return sr.liczSrednia() >= 3;
            }
        };
        FilterIterator<Student> it = new FilterIterator<>(new IteratorTablicowy<>(lista),predykat);
        while(it.hasNext()){
            StudentRozszerzony st = (StudentRozszerzony) it.next();
            suma += st.liczSrednia();
            ile++;
        }
        System.out.printf("\n%s\n%5.3f\n","Srednia ocen osob, ktore zdaly wynosi: ", suma/ile);
    }

    public void wyswietlListeNiezaliczajacych(){
        System.out.println("\nLista osob, ktore nie zdaly: ");
        Predicate<Student> predykat = new Predicate<>() {
            @Override
            public boolean accept(Student s) {
                StudentRozszerzony sr = (StudentRozszerzony) s;
                return sr.liczSrednia() < 3 || sr.getOceny().isEmpty();
            }
        };
        FilterIterator<Student> it = new FilterIterator<>(new IteratorTablicowy<>(lista),predykat);

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
