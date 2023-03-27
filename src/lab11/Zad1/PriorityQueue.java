package lab11.Zad1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class PriorityQueue {

    private class Element{
        char c;
        int occurances;

        public Element(char c, int occurances){
            this.c = c;
            this.occurances = occurances;
        }
    }

    private class Node{

        Element content;
        Node left;
        Node right;

        public Node(Element cont, Node l, Node r){
            content = cont;
            left = l;
            right = r;
        }

        public Node(Node l, Node r){
            content = null;
            left = l;
            right = r;
        }
    }

    private ArrayList<Node> list;
    private Comparator<Node> comp = (o1, o2) -> Integer.compare(o1.content.occurances, o2.content.occurances);
    private Node root;

    public PriorityQueue(HashMap<Character, Integer> map){
        list = new ArrayList<>();
        for (Character c : map.keySet()){
            enqueue(new Node(new Element(c,map.get(c)), null, null));
        }
    }

    public void enqueue(Node value){
        list.add(value);
        swim(list.size() - 1);
    }

    public Node dequeue() throws NoSuchElementException {

        if(list.isEmpty())
            throw new NoSuchElementException();

        Node retValue = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);
        sink();

        return retValue;
    }

    private void swim(int index){
        int parent = (index - 1) / 2;
        while(index != 0 && comp.compare(list.get(parent), list.get(index)) > 0){
            swap(parent, index);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void sink(){
        int sinkerIndex = 0;
        int child;

        while((child = 2*sinkerIndex + 1) < list.size()){           //size czy size -1 ?? nullpointerexception
            if(list.size() > child + 1) {
                if (comp.compare(list.get(child), list.get(child + 1)) > 0) {
                    child++;
                }
            }

            if(comp.compare(list.get(sinkerIndex), list.get(child)) > 0){
                swap(sinkerIndex, child);
                sinkerIndex = child;
            } else {
                return;
            }
        }
    }

    private void swap(int left, int right){
        Node temp = list.get(left);
        list.set(left, list.get(right));
        list.set(right, temp);
    }



    public HashMap<String, Character> createHuffmansCodes(){
        int len = list.size();
        Node n = null;
        for (int i = 0; i < len - 1; i++) {
            Element newElem = new Element('*', 0);
            Node left = dequeue();
            Node right = dequeue();
            n = new Node(newElem, left, right);
            n.content.occurances = left.content.occurances + right.content.occurances;
            enqueue(n);
        }
        root = n;
        HashMap<String, Character> codes = new HashMap<>();

        goTroughHeap(root, "", codes);

        return codes;
    }

    public void goTroughHeap(Node begin, String code, HashMap<String, Character> map){
        if(begin.left != null){
            String newCode = code + "0";
            goTroughHeap(begin.left, newCode, map);
        }
        if(begin.right != null){
            String newCode = code + "1";
            goTroughHeap(begin.right, newCode, map);
        }

        if(begin.left == null && begin.right == null){
            map.put(code, begin.content.c);
        }
    }
}
