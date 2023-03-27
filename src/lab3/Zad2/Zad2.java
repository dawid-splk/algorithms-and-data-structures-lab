package lab3.Zad2;

public class Zad2 {

    public static void main(String[] args) {
        Zad2 test = new Zad2();
        Bufor bufor = new Bufor();
        ProcesProducent producent = new ProcesProducent(15);
        ProcesKonsument konsument = new ProcesKonsument();

        while(test.warunekProducent(producent) || test.warunekObsluzoneProcesy(bufor)){
            producent.zapelnijBufor(bufor);
            producent.dodajDoKolejki();
            konsument.operujNaBuforze(bufor);
        }
        System.out.println("Pomyslnie obsluzono wszystkie procesy z kolejki!");
    }

    public boolean warunekProducent(ProcesProducent prod){
        return prod.getKolejkaOczekujacych().getElement(0) != null || prod.getIleProcesowDodac() != 0;
    }

    public boolean warunekObsluzoneProcesy(Bufor bufor){
        return bufor.getBegin() != bufor.getEnd();
    }
}
