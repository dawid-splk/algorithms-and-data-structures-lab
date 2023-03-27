package lab11.Zad1;

import java.util.HashMap;

public class Huffman {

    String text;

    public Huffman(String string){
        text = string;
    }

    public HashMap<Character, Integer> countOccurances(){

        HashMap<Character, Integer> occurances = new HashMap<>();
        char temp;
        boolean shouldAdd;

        for (int i = 0; i < text.length(); i++) {
            shouldAdd = true;
            temp = text.charAt(i);
            for(Character c : occurances.keySet()){
                if(temp == c){
                    occurances.replace(c, occurances.get(c) + 1);
                    shouldAdd = false;
                }
            }
            if(shouldAdd){
                occurances.put(temp, 1);
            }
        }
        return occurances;
    }

    public void printOccurances(){
        HashMap<Character, Integer> occurances = countOccurances();
        System.out.println("Occurances: ");
        for(Character c : occurances.keySet()){
            System.out.println(c + ": " + occurances.get(c));
        }
    }

    public HashMap<String, Character> getHuffmansCodes(){
        PriorityQueue kol = new PriorityQueue(countOccurances());
        HashMap<String, Character> codes = kol.createHuffmansCodes();

//        System.out.println("\n\nHuffman's codes for our text: ");
//        for (String code : codes.keySet()){
//            System.out.println(codes.get(code) + ": " + code);
//        }
        return codes;
    }

    public void printHuffmansCodes(){
        HashMap<String, Character> codes = getHuffmansCodes();
        System.out.println("\nHuffman's codes for our text: ");
        for(String code : codes.keySet()){
            System.out.println(codes.get(code) + ": " + code);
        }
    }

    public String printEncryptedText(){
        HashMap<String, Character> codes = getHuffmansCodes();
        char temp;
        String encrypted = "";

        System.out.println("\nEncrypted text: ");
        for (int i = 0; i < text.length(); i++) {
            temp = text.charAt(i);
            for( String s : codes.keySet()){
                if(temp == codes.get(s)){
                    System.out.print(s);
                    encrypted += s;
                }
            }
        }
        return encrypted;
    }

    public void decryptText(String encryptedTest){
        String temp = "";
        HashMap<String, Character> codes = getHuffmansCodes();

        System.out.println("\n\nDecrypted text: ");
        for (int i = 0; i < encryptedTest.length(); i++) {
            temp += encryptedTest.charAt(i);
            for(String code : codes.keySet()){
                if(temp.equals(code)){
                    System.out.print(codes.get(code));
                    temp = "";
                }
            }
        }
    }
}
