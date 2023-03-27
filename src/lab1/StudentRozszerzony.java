package lab1;

import java.util.ArrayList;

public class StudentRozszerzony extends Student {

    private ArrayList<Double> oceny;

    public StudentRozszerzony(int nr, String nazw, String imie){
        super(nr,nazw,imie);
        this.oceny = new ArrayList<>();
    }

    public double liczSrednia(){
        double suma = 0.0;
        int ile = 0;
        for(double ocena : oceny){
            suma += ocena;
            ile++;
        }
        return suma/ile;
    }

    @Override
    public String toString(){
        StringBuilder oceny = new StringBuilder("Oceny: ");
        for(double s : this.oceny){
            oceny.append(s).append(" ");
        }
        return String.format("%-5d%-15s%-15s%-30s", this.getNrIndeksu(), this.getNazwisko(), this.getImie(), oceny);
    }

    public ArrayList<Double> getOceny() {
        return oceny;
    }

}
