package lab7;

import java.util.Objects;

public class Student {

    private int index;
    private String name;
    private String surname;

    public Student(String name, String surname, int index) {
        this.index = index;
        this.name = name;
        this.surname = surname;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "index: " + index + ", name: " + name + ", surname: " + surname;
    }

    @Override
    public int hashCode(){
        return Math.abs(index * (name.hashCode() ^ surname.hashCode()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return index == student.index && Objects.equals(name, student.name) && Objects.equals(surname, student.surname);
    }
}