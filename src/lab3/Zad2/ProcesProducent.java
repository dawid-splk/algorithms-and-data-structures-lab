package lab3.Zad2;

import lab3.Zad1.KolejkaNieograniczona;

import java.util.NoSuchElementException;
import java.util.Random;

public class ProcesProducent {

    Random rand = new Random();
    private KolejkaNieograniczona<Integer> kolejkaOczekujacych = new KolejkaNieograniczona<>();
    private int ileProcesowDodac;

    public ProcesProducent(int liczbaProcesow){
        ileProcesowDodac = liczbaProcesow;
        for (int i = 0; i < liczbaProcesow * 0.5; i++) {
            ileProcesowDodac--;
            kolejkaOczekujacych.enqueue(rand.nextInt(1,11));
        }
    }

    public void zapelnijBufor(Bufor bufor) {
        while(bufor.getBegin() != (bufor.getEnd() + 1) % bufor.getBufor().length){
            try {
                bufor.getBufor()[bufor.getEnd()] = kolejkaOczekujacych.dequeue();
            } catch (NoSuchElementException e){
                System.out.println(" - Nie mozna zapelnic bufora.");
                return;
            }
            bufor.setEnd((bufor.getEnd() + 1) % bufor.getBufor().length);
            System.out.print("Dodano nowy proces.\n");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dodajDoKolejki(){
        if(rand.nextDouble() > 0.2 && ileProcesowDodac > 0){
            ileProcesowDodac--;
            kolejkaOczekujacych.enqueue(rand.nextInt(1,11));
        }
    }

    public int getIleProcesowDodac() {
        return ileProcesowDodac;
    }

    public KolejkaNieograniczona<Integer> getKolejkaOczekujacych() {
        return kolejkaOczekujacych;
    }
}
