package lab4.Zad5;

import lab4.Zad2.UnlimitedStack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Zad5 {

    public static void main(String[] args) {
        Zad5 test = new Zad5();
        Scanner sc = new Scanner(System.in);
        UnlimitedStack<Double> stack = new UnlimitedStack<>();

        ArrayList<String> list = test.writeOperationFromFile("operations.txt");           //odczyt z pliku
        for (String s : list){
            System.out.println("Your result: " + test.calculate(s, stack));
        }

//        System.out.print("Write your operation using RPN and spaces between elements: ");   //odczyt z klawiatury
//        String input = sc.nextLine();
//        System.out.println("Your result: " + test.calculate(input, stack));

    }

    public ArrayList<String> writeOperationFromFile(String fileName){

        ArrayList<String> listOfOperations = new ArrayList<>();
        String input;

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            while((input = br.readLine()) != null) {
                listOfOperations.add(input);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return listOfOperations;
    }

    public double calculate(String input, UnlimitedStack<Double> stack){
        String[] inputArray = input.trim().split("\\s+");

        double a, b;
        for (String element : inputArray) {
            switch (element) {
                case "+" -> {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b + a);
                }
                case "-" -> {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b - a);
                }
                case "*" -> {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b * a);
                }
                case "/" -> {
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b / a);
                }
                default -> stack.push(Double.parseDouble(element));
            }
        }
        return stack.pop();
    }
}
