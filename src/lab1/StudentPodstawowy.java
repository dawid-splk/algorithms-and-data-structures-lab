package lab1;

public class StudentPodstawowy extends Student {

    private double ocena;

    public StudentPodstawowy(int nr, String nazw, String imie, double ocena){
        super(nr,nazw,imie);
        this.ocena = ocena;
    }

    @Override
    public String toString(){
        return String.format("nrIndeksu:%2d\t%-15s%-15s\t%5.1f", this.getNrIndeksu(), this.getNazwisko(), this.getImie(), ocena);
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }


    @Override
    public boolean equals(Object o){
        StudentPodstawowy s = (StudentPodstawowy) o;
        if(this.getImie().equals(s.getImie()) && this.getNazwisko().equals(s.getNazwisko()) && this.getNrIndeksu() == s.getNrIndeksu())
            return true;
        else
            return false;
    }
}
