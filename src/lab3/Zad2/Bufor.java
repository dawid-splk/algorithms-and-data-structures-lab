package lab3.Zad2;

public class Bufor {

    private int[] bufor = new int[6];
    private int begin;
    private int end;
    private int freeIndex;

    public Bufor(){
        freeIndex = 0;
        begin = 2;
        end = 2;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void clearBufor(){
        bufor = new int[5];
    }

    public int[] getBufor() {
        return bufor;
    }

    public int getFreeIndex() {
        return freeIndex;
    }

    public void setFreeIndex(int freeIndex) {
        this.freeIndex = freeIndex;
    }
}
