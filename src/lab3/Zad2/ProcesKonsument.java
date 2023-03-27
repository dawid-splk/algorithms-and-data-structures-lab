package lab3.Zad2;

import java.util.Random;

public class ProcesKonsument {

    private Random rand = new Random();

    public void operujNaBuforze(Bufor bufor) {
        if (bufor.getBegin() != bufor.getEnd() && rand.nextDouble() < 0.5) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nProces zostal obsluzony. Zwolniono miejsce w buforze.");
            bufor.getBufor()[bufor.getBegin()] = 0;
            bufor.setBegin((bufor.getBegin() + 1) % bufor.getBufor().length);
        }

        bufor.setBegin((bufor.getBegin() + 1) % bufor.getBufor().length);   //przejscie na nastepny element w buforze
        bufor.setEnd((bufor.getEnd() + 1) % bufor.getBufor().length);       //aby czynnosci nie byly wykonywane na jednym
    }
}
