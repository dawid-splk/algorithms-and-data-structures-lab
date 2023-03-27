package lab8.Zad4;

import lab8.Zad1_2_3.BST_Tree;

import java.util.ArrayList;
import java.util.Comparator;

public class RB_Tree<E>{

    private class Node{
        Node parent;
        Node left;
        Node right;
        E key;
        boolean isRed;

        Node(E key, Node p){
            this.key = key;
            parent = p;
        }

        public String toString(){
            return isRed ? key + "R" : key + "B";
        }
    }

    Node root;
    Comparator<E> comp;

    public RB_Tree(Comparator<E> comp){
        root = null;
        this.comp = comp;
    }

    public void colorInsert(E key) {
        Node y;
        Node x = insert(key);
        x.isRed = true;
        while (x != root && x.parent.isRed) {
            if (x.parent == x.parent.parent.left) {
                y = x.parent.parent.right;
                if (y != null && y.isRed) {
                    x.parent.isRed = false;
                    y.isRed = false;
                    x.parent.parent.isRed = true;
                    x = x.parent.parent;
                } else {
                    if (x == x.parent.right) {
                        x = x.parent;
                        leftRotate(x);
                    }
                    x.parent.isRed = false;
                    x.parent.parent.isRed = true;
                    rightRotate(x.parent.parent);
                }
            } else {
                y = x.parent.parent.left;
                if (y != null && y.isRed) {
                    x.parent.isRed = false;
                    y.isRed = false;
                    x.parent.parent.isRed = true;
                    x = x.parent.parent;
                } else {
                    if (x == x.parent.left) {
                        x = x.parent;
                        rightRotate(x);
                    }
                    x.parent.isRed = false;
                    x.parent.parent.isRed = true;
                    leftRotate(x.parent.parent);
                }
            }
        }
        root.isRed = false;
    }

    public Node insert(E key){
        if(root == null){
            root = new Node(key, null);
            return root;
        }
        Node y = null;
        Node x = root;
        while(x != null){
            y = x;
            if(comp.compare(key, x.key) < 0){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        Node leaf = new Node(key, y);
        if(comp.compare(leaf.key, y.key) < 0){
            y.left = leaf;
        } else {
            y.right = leaf;
        }
        return leaf;
    }

    public void leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        if(y.left != null){
                y.left.parent = x;
        }
        y.parent = x.parent;
        if(y.parent == null){
            root = y;
        } else {
            if(x == x.parent.left){
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(Node x){
        Node y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if(y.parent == null){
            root = y;
        } else {
            if(x == x.parent.left){
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.right = x;
        x.parent = y;
    }

    public void preOrder(Node begin)  {
        System.out.print(begin + "\t");
        if(begin.left != null){
            preOrder(begin.left);
        }
        if(begin.right != null){
            preOrder(begin.right);
        }
    }

    public void print(){
        int i = 1;
        int space;
        boolean shouldContinue = true;
        ArrayList<Node> list = new ArrayList<>();
        ArrayList<Node> nextGen = new ArrayList<>();

        if(root != null){
            space = 160/(i+1);
            String s = "%" + space + "s";
            System.out.printf(s,root);
            System.out.print("\n");
            list.add(root.left);
            list.add(root.right);
        } else {
            return;
        }

        while(shouldContinue){
            shouldContinue = false;
            i = i * 2;
            space = 160/(i+1);
            String s = "%" + space + "s";
            for (int j = 0; j < list.size(); j++) {
                if(list.get(j) != null){
                    System.out.printf(s, list.get(j));
                    nextGen.add(list.get(j).left);
                    nextGen.add(list.get(j).right);
                    shouldContinue = true;
                } else {
                    System.out.printf(s, "  ");
                    nextGen.add(null);
                    nextGen.add(null);
                }
            }
            System.out.print("\n\n");
            list.clear();
            list.addAll(nextGen);
            nextGen.clear();
        }
    }
}
