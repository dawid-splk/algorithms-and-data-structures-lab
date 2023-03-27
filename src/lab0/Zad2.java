package lab0;

import java.io.*;
import java.util.Random;

public class Zad2 {

    Random rand = new Random();
    int w;
    int k;

    public static void main(String[] args) {
        Zad2 test = new Zad2();
        test.writeArray("testFile.dat");
        test.readArray("testFile.dat");
    }

    public double [][] createArray(){
        w = rand.nextInt(3,10);
        k = rand.nextInt(3,10);
        double [][] array = new double[w][k];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = rand.nextDouble(10);
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.print("\n");
        }
        return array;
    }

    public void writeArray(String nazwaPliku){

        double [][] matrix = createArray();

        try {
            FileOutputStream fos = new FileOutputStream(nazwaPliku);
            ObjectOutputStream dos = new ObjectOutputStream(fos);

            dos.writeInt(matrix.length);
            dos.writeInt(matrix[0].length);
            dos.writeObject(matrix);
            fos.close();
            dos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readArray(String nazwaPliku){

        try {
            FileInputStream fis = new FileInputStream(nazwaPliku);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int w = ois.readInt();
            int k = ois.readInt();
            System.out.println(w);
            System.out.println(k);

            double [][] matrixCopy = (double[][]) ois.readObject();

            fis.close();
            ois.close();

            double max = matrixCopy[0][0];
            int x = 0;
            int y = 0;

            for (int j = 0; j < matrixCopy.length; j++) {
                for (int l = 0; l < matrixCopy[0].length; l++) {
                    System.out.printf("%4.2f\t", matrixCopy[j][l]);
                    if(matrixCopy[j][l] > max) {
                        max = matrixCopy[j][l];
                        x = j;
                        y = l;
                    }
                }
                System.out.print("\n");
            }
            System.out.println("Element maksymalny o indeksach: [" + (x+1) + "][" + (y+1) + "] ma wartość: " + max);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


