package lab1;

public class Student implements Comparable<Student> {
    private int nrIndeksu;
    private String nazwisko;
    private String imie;

    public Student(int nr, String nazw, String imie){
        this.nrIndeksu = nr;
        this.nazwisko = nazw;
        this.imie = imie;
    }

    public int getNrIndeksu() {
        return nrIndeksu;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setNrIndeksu(int nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public int compareTo(Student s) {
        if(nrIndeksu < s.nrIndeksu)
            return -1;
        else
            return 1;
    }

}
