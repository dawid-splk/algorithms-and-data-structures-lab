package lab11.Zad1;

public class Zad1 {

    public static void main(String[] args) {
        String string = "Zaimplementuj metode Grahama wyznaczania otoczki wypuklej zbioru punktow. W ramach\n" +
                "zadania nalezy utworzyc metode sortowania polarnego zbioru punktow.";
        Huffman test = new Huffman(string);
        test.printOccurances();
        test.printHuffmansCodes();
        String encryptedText = test.printEncryptedText();
        String code = "1110011011000010111111100101010111100010010010001111110000011000000011001110010";
        test.decryptText(encryptedText);
        test.decryptText(code);
    }
}
