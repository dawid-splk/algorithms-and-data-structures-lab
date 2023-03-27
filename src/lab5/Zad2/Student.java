package lab5.Zad2;

public class Student implements Comparable<Student> {

    private int index;
    private String name;
    private String surname;

    public Student(String name, String surname, int index) {
        this.index = index;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int compareTo(Student o) {           //komparator naturalny
        int result;
        result = Integer.compare(index, o.index);
        if(result != 0){
            return result;
        } else {
            result = Integer.compare(surname.compareTo(o.surname), 0);
        }
        if(result != 0){
            return result;
        } else {
            result = Integer.compare(name.compareTo(o.name), 0);
        }
        return result;
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
}


//        if(this.index < o.index){
//            return -1;
//        } else {
//            if(this.index > o.index){
//                return 1;
//            } else {
//                if(this.surname.compareTo(o.surname) < 0){
//                    return -1;
//                } else {
//                    if(this.surname.compareTo(o.surname) > 0){
//                        return 1;
//                    } else {
//                        if(name.compareTo(o.name) < 0){
//                            return -1;
//                        } else {
//                            if(name.compareTo(o.name) > 0){
//                                return 1;
//                            } else {
//                                return 0;
//                            }
//                        }
//                    }
//                }
//            }
//        }