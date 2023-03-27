package lab6.Zad3;

import java.util.Objects;

public class Student {

    private int index;
    private double grade;

    public Student(int index, double grade) {
        this.index = index;
        this.grade = grade;
    }

    public Student(Student st){
        this.index = st.index;
        this.grade = st.grade;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString(){
        return grade + " - " + index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return index == student.index;
    }
}
