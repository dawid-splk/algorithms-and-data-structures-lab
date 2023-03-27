package lab1;

import java.util.Arrays;
import java.util.Iterator;

public class Zad1_3 {

    StudentPodstawowy[] lista;

    public Zad1_3(int iloscStudentow){
        lista = new StudentPodstawowy[iloscStudentow];
    }

    public static void main(String[] args) {
        Zad1_3 test = new Zad1_3(10);

        test.tworzWstepnychStudentow();
        test.wyswietlStudentow();
        test.sredniaOcen();
        test.wpiszOcene(2,2.0);
        test.wyswietlNiezaliczajacych();

        System.out.println("\nZad3\n\n//Dodawanie studenta");
        StudentPodstawowy s11 = new StudentPodstawowy(6,"Kwiatkowski", "Jerzy", 2.0);
        test.dodajStudenta(new IteratorTablicowy<>(test.lista), s11);
        test.wyswietlStudentow();

        System.out.println("\n//Usuwanie nieistniejacego studenta");
        test.usunStudenta(new IteratorTablicowy<>(test.lista), 15);
        System.out.println("\n//Usuwanie istniejacych studentow");
        test.usunStudenta(new IteratorTablicowy<>(test.lista), 3);
        test.usunStudenta(new IteratorTablicowy<>(test.lista), 6);
        test.wyswietlStudentow();

        test.uporzadkujOcenami(new IteratorTablicowy<>(test.lista));

    }

    public void wpiszNaListe(StudentPodstawowy s){
        for (int i = 0; i < lista.length; i++) {
            if(lista[i] == null) {
                lista[i] = s;
                return;
            }
        }
    }

    public void wyswietlStudentow(){

        System.out.println("\nLista wszystkich studentow: ");
        Arrays.sort(lista, new IndexComp());
        IteratorTablicowy<StudentPodstawowy> it = new IteratorTablicowy<>(lista);
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
    }

    public void wpiszOcene(int nrIndeksu, double ocena){

        Iterator<StudentPodstawowy> it = new IteratorTablicowy<>(lista);
        while(it.hasNext()){
            StudentPodstawowy st = it.next();
            if(st.getNrIndeksu() == nrIndeksu) {
                st.setOcena(ocena);
                System.out.println("\nZmieniono ocene studenta " + st.getImie() + " " + st.getNazwisko() + " na " + ocena);
            }
        }
    }

    public double sredniaOcen(){

        Predicate<StudentPodstawowy> predykat = new Predicate<>() {
            @Override
            public boolean accept(StudentPodstawowy s){
                return s.getOcena() >= 3;
            }
        };
        FilterIterator<StudentPodstawowy> it = new FilterIterator<>(new IteratorTablicowy<>(lista), predykat);

        double suma = 0;
        int ile = 0;

        while(it.hasNext()){
            StudentPodstawowy s = it.next();
            suma += s.getOcena();
            ile++;
        }

        System.out.printf("\n%s%5.3f\n","Srednia ocen osob, ktore zaliczyly wynosi: ", suma/ile);
        return suma/ile;
    }


    public void wyswietlNiezaliczajacych(){

        System.out.println("\nOsoby, ktore nie zaliczyly:");
        Predicate<StudentPodstawowy> predykat = new Predicate<StudentPodstawowy>() {
            @Override
            public boolean accept(StudentPodstawowy s) {
                return s.getOcena() < 3 || s.getOcena() == 0;
            }
        };

        FilterIterator<StudentPodstawowy> it = new FilterIterator<>(new IteratorTablicowy<>(lista),predykat);
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    public Iterator<StudentPodstawowy> dodajStudenta(Iterator<StudentPodstawowy> iterator, StudentPodstawowy st){
        int index = 0;

        while(iterator.hasNext() && st.getNrIndeksu() > iterator.next().getNrIndeksu()){
                index++;
            }
        StudentPodstawowy[] copy = new StudentPodstawowy[lista.length + 1];
        System.arraycopy(lista, 0, copy, 0, index);
        copy[index] = st;
        System.arraycopy(lista, index, copy, index + 1, lista.length - index);
        lista = copy;
        System.out.println("\nDodano studenta " + st.getImie() + " " + st.getNazwisko() + " do listy studentow.");
        return new IteratorTablicowy<>(lista);
    }


    public Iterator<StudentPodstawowy> usunStudenta(Iterator<StudentPodstawowy> iterator, int numerIndeksu){
        int index = 0;
        while(iterator.hasNext()){
            StudentPodstawowy st2 = iterator.next();
            if(numerIndeksu == st2.getNrIndeksu()){
                StudentPodstawowy[] copy = new StudentPodstawowy[lista.length - 1];
                System.arraycopy(lista, 0, copy, 0, index);
                System.arraycopy(lista, index + 1, copy, index, copy.length - index);
                lista = copy;
                System.out.println("\nPomyślnie usunieto studenta " + st2.getImie() + " " + st2.getNazwisko() + " z listy studentow.");
                return new IteratorTablicowy<>(lista);
            }
            index++;
        }
        System.out.println("\nNie znaleziono studenta o indeksie: " + numerIndeksu + " w bazie danych.");
        return iterator;
    }

    public Iterator<StudentPodstawowy> uporzadkujOcenami(IteratorTablicowy<StudentPodstawowy> iterator){
        boolean notSorted = true;
        StudentPodstawowy a;
        StudentPodstawowy b;
        while(notSorted){
            notSorted = false;
            a = iterator.first();
            while(iterator.hasNext()){
                b = iterator.next();
                if(a.getOcena() < b.getOcena()) {
                    StudentPodstawowy copyA = new StudentPodstawowy(a.getNrIndeksu(),a.getNazwisko(),a.getImie(),a.getOcena());
                    a.setOcena(b.getOcena());
                    a.setImie(b.getImie());
                    a.setNazwisko(b.getNazwisko());
                    a.setNrIndeksu(b.getNrIndeksu());
                    b.setOcena(copyA.getOcena());
                    b.setImie(copyA.getImie());
                    b.setNazwisko(copyA.getNazwisko());
                    b.setNrIndeksu(copyA.getNrIndeksu());
                    notSorted = true;
                }
                a = b;
            }
        }
        System.out.println("\nLista uporzadkowana ocenami malejaco: ");
        IteratorTablicowy<StudentPodstawowy> it = new IteratorTablicowy<>(lista);
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }
        return new IteratorTablicowy<>(lista);
    }                                     // do while(boolean notSorted)

    public void tworzWstepnychStudentow(){
        StudentPodstawowy s1 = new StudentPodstawowy(3,"Nowak", "Jan", 3.5);
        StudentPodstawowy s2 = new StudentPodstawowy(7,"Kowalska", "Jolanta", 4);
        StudentPodstawowy s3 = new StudentPodstawowy(4,"Zielinski", "Robert", 3.5);
        StudentPodstawowy s4 = new StudentPodstawowy(2,"Nowak", "Katarzyna", 5.5);
        StudentPodstawowy s5 = new StudentPodstawowy(1,"Wojcik", "Joanna", 3.0);
        StudentPodstawowy s6 = new StudentPodstawowy(5,"Lewandowski", "Piotr", 2.0);
        StudentPodstawowy s7 = new StudentPodstawowy(8,"Kubiak", "Katarzyna", 4);
        StudentPodstawowy s8 = new StudentPodstawowy(9,"Spalek", "Dawid", 3.5);
        StudentPodstawowy s9 = new StudentPodstawowy(10,"Wisniewski", "Adam", 4.0);
        StudentPodstawowy s10 = new StudentPodstawowy(11,"Zyzik", "Julia", 3.0);


        wpiszNaListe(s1);
        wpiszNaListe(s2);
        wpiszNaListe(s3);
        wpiszNaListe(s4);
        wpiszNaListe(s5);
        wpiszNaListe(s6);
        wpiszNaListe(s7);
        wpiszNaListe(s8);
        wpiszNaListe(s9);
        wpiszNaListe(s10);
    }
}
