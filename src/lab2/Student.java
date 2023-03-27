package lab2;

import java.util.Objects;

public class Student {

    private String imie;
    private String nazwisko;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return nrIndeksu == student.nrIndeksu && Objects.equals(imie, student.imie) && Objects.equals(nazwisko, student.nazwisko);
    }

    public void setNrIndeksu(int nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    private int nrIndeksu;

    public Student(String imie, String nazwisko, int nrIndeksu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
    }

    public int getNrIndeksu() {
        return nrIndeksu;
    }

    @Override
    public String toString() {
        return "Student{" +
                imie + ' ' +
                nazwisko +
                ", nrIndeksu=" + nrIndeksu +
                '}';
    }
}
