package lab11.Zad2;

public class Zad2 {

    public static void main(String[] args) {
        Graham test = new Graham();

//        test.addPoint(0,0);
//        test.addPoint(3,1);
//        test.addPoint(1,2);
//        test.addPoint(4,0);
//        test.addPoint(0,4);
//        test.addPoint(1,1);     //polozenie katowe wzgledem startu (0,0) takie same jak w (4,4), wiec jest nieistotny
//                                     //bo jego odleglosc od startu jest mniejsza niz w przypadku (4,4)
//        test.addPoint(4,4);

        test.addPoint(0,0);
        test.addPoint(5,1);
        test.addPoint(5,4);
        test.addPoint(4,1);
        test.addPoint(1,4);
        test.addPoint(6,3);
        test.addPoint(2,5);
        test.addPoint(5,3);
        test.addPoint(2,1);
        test.addPoint(2,2);
        test.addPoint(3,3);
        test.addPoint(3,4);
        test.addPoint(1,6);

        test.printPoints();
        test.convexHull();
    }
}

