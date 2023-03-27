package lab0;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Czas implements Serializable, Comparable<Czas> {

    private int rok;
    private int miesiac;
    private int dzien;
    private int godzina;
    private int minuta;
    private Random rand = new Random();

    public Czas() {
        rok = rand.nextInt(2000, 2023);
        miesiac = rand.nextInt(1, 13);
        if (miesiac == 2) {
            if (rok % 4 == 0) {
                if (rok % 100 == 0)
                    dzien = rok % 400 == 0 ? rand.nextInt(1, 30) : rand.nextInt(1, 29);
                else
                    dzien = rand.nextInt(1, 30);
            } else {
                dzien = rand.nextInt(1, 29);
            }
        } else {
            dzien = rand.nextInt(1, 32);
        }
        godzina = rand.nextInt(0, 24);
        minuta = rand.nextInt(0, 61);
    }

    public Czas(int r, int m, int d, int g, int min) {
        rok = r;
        miesiac = m;
        dzien = d;
        godzina = g;
        minuta = min;
    }

    public int getRok() {
        return rok;
    }

    public int getMiesiac() {
        return miesiac;
    }

    @Override
    public String toString() {
        return "rok: " + rok +
                ", miesiac: " + miesiac +
                ", dzien: " + dzien +
                ", godzina: " + godzina +
                ", minuta: " + minuta;
    }

    @Override
    public int compareTo(Czas o) {
        Date data1 = new Date(rok - 1900, miesiac, dzien, godzina, minuta);
        Date data2 = new Date(o.rok - 1900, o.miesiac, o.dzien, o.godzina, o.minuta);

        if (data1.before(data2)) {
            return -1;
        } else {
            return data1.after(data2) ? 1 : 0;
        }
    }
//        if(rok >o.rok)
//
//    {
//        return 1;
//    } else
//
//    {
//        if (rok < o.rok) {
//            return -1;
//        } else {
//            if (miesiac > o.miesiac) {
//                return 1;
//            } else {
//                if (miesiac < o.miesiac) {
//                    return -1;
//                } else {
}
