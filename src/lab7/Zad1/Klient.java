package lab7.Zad1;

import java.util.Random;

public class Klient {

    private int czasSprawy;
    private final Random rand = new Random();

    public Klient(){
        czasSprawy = rand.nextInt(1,6);
    }

    public int getCzasSprawy() {
        return czasSprawy;
    }

    public void setCzasSprawy(int czasSprawy) {
        this.czasSprawy = czasSprawy;
    }

    public String toString(){
        return "klient o czasie sprawy: " + czasSprawy;
    }
}
