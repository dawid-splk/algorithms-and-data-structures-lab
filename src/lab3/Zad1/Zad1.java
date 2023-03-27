package lab3.Zad1;

public class Zad1 {

    public static void main(String[] args) {
        KolejkaNieograniczona<Integer> kolejka = new KolejkaNieograniczona<>();

        System.out.print("Czy dodano element?\t");
        System.out.println(kolejka.enqueue(1));
        kolejka.enqueue(2);
        kolejka.enqueue(3);
        kolejka.enqueue(4);

        System.out.println(kolejka.dequeue());
        System.out.println(kolejka.dequeue());
        System.out.println(kolejka.dequeue());
        kolejka.enqueue(-1);
        System.out.println(kolejka.dequeue());
        System.out.println(kolejka.dequeue());



    }
}
