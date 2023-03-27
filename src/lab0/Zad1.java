package lab0;

import java.io.*;
import java.util.Random;

public class Zad1 {
    Random rand = new Random();
    int w;
    int k;

    public static void main(String[] args) {
        Zad1 test = new Zad1();
        test.write("testFile.txt");
        test.read("testFile.txt");
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
        return array;
    }

    public void write(String fileName){
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {

            double [][] table = createArray();

            pw.printf("%s%n", "Macierz");
            pw.printf("%d%n", w);
            pw.printf("%d%n", k);

            String line = "";

            for (int i = 0; i < w; i++) {
                for (int j = 0; j < k; j++) {
                    line += String.format("%3.2f\t", table[i][j]);
                }
                pw.printf("%s%n", line);
                line = "";
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void read(String fileName){
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            br.readLine();
            double [][] copy = new double[Integer.parseInt(br.readLine())][Integer.parseInt(br.readLine())];

            String line;
            for (int i = 0; i < copy.length; i++) {
                line = br.readLine();
                String [] row = line.trim().split("\\s+");
                for (int j = 0; j < copy[0].length; j++) {
                    copy[i][j] = Double.parseDouble(row[j]);
                }
            }

            for (int i = 0; i < copy.length; i++) {
                for (int j = 0; j < copy[0].length; j++) {
                    System.out.printf("%3.2f\t", copy[i][j]);
                }
                System.out.println("\n");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
