package lab7.Zad1;

public class Urzednik {
    private boolean zajety;

    private String imie;
    private Klient obecnyKlient;

    public Urzednik(String imie){
        zajety = false;
        this.imie = imie;
        obecnyKlient = null;
    }

    public boolean getZajety() {
        return obecnyKlient != null;
    }

    public void setZajety(boolean zajety) {
        this.zajety = zajety;
    }

    public Klient getObecnyKlient() {
        return obecnyKlient;
    }

    public void setObecnyKlient(Klient obecnyKlient) {
        this.obecnyKlient = obecnyKlient;
    }

    public String getImie() {
        return imie;
    }
}
