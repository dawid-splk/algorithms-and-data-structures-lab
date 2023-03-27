package lab0;

import java.io.Serializable;
import java.util.Random;

public class Pomiar implements Serializable, Comparable<Pomiar> {

    private Czas czas;
    private double temperatura;
    private Random rand = new Random();

    public Pomiar() {
        temperatura = rand.nextDouble(-10,35);
        czas = new Czas();
    }

    public Pomiar(double temp, int rr, int mm, int dd, int godz, int min){
        temperatura = temp;
        czas = new Czas(rr, mm, dd, godz, min);
    }

    public Czas getCzas() {
        return czas;
    }

    public double getTemperatura() {
        return temperatura;
    }

    @Override
    public String toString(){
        return String.format("%5.2f°C  %s", temperatura, getCzas().toString());
    }

    @Override
    public int compareTo(Pomiar o) {
        return czas.compareTo(o.getCzas());
    }
}
