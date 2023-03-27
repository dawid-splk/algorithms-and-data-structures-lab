package lab8.Zad1_2_3;

import java.util.ArrayList;
import java.util.Comparator;

public class BST_Tree<E> {

    private class Node {
        Node parent;
        Node left;
        Node right;
        E key;
        int leftHeight, rightHeight;
        int overLoad;
        int leftNodes, rightNodes;
        int leftMaxOL, rightMaxOL;
        int leftLeaves, rightLeaves;

        Node(E key, Node p){
            this.key = key;
            parent = p;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public String toString(){
            return key.toString();
        }
    }

    Node root;
    Comparator<E> comp;

    public BST_Tree(Comparator<E> comp){
        root = null;
        this.comp = comp;
    }

    public Node search(E key, Node beg){
        if(beg == null){
            return null;
        }
        E val = beg.key;
        if(root == null || comp.compare(key, val) == 0)
            return beg;
        if(comp.compare(key, val) < 0){
            return search(key, beg.left);
        } else {
            return search(key, beg.right);
        }
    }

    public void insert(E key){
        if(root == null){
            root = new Node(key, null);
            return;
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
    }

    public Node treeMinimum(Node begin){        //rekurencyjnie:    if(begin.left == null) -> return begin
        while(begin.left != null){                                //else -> return treeMinimum(begin.left)
            begin = begin.left;
        }
        return begin;
    }

    public Node treeMaximum(Node begin){
        while(begin.right != null){
            begin = begin.right;
        }
        return begin;
    }

    public Node treeSuccessor(E key){
        Node node = search(key, root);
        if(node.right != null){
            return treeMinimum(node.right);
        }
        Node temp = node.parent;
        while(temp != null && node == temp.right) {
            node = temp;
            temp = temp.parent;
        }
        return temp;
    }

    public Node treePredecessor(E key){
        Node node = search(key, root);
        if(node.left != null){
            return treeMaximum(node);
        }
        Node temp = node.parent;
        while(temp != null && node == temp.left){
            node = temp;
            temp = temp.parent;
        }
        return temp;
    }

    public void preOrder(Node begin) throws InterruptedException {
        Thread.sleep(200);
        System.out.print(begin.key + "\t");
        if(begin.left != null){
            preOrder(begin.left);
        }
        if(begin.right != null){
            preOrder(begin.right);
        }
    }

    public void inOrder(Node begin) throws InterruptedException {
        if(begin.left != null){
            inOrder(begin.left);
        }
        Thread.sleep(200);
        System.out.print(begin.key + "\t");
        if(begin.right != null){
            inOrder(begin.right);
        }
    }

    public void postOrder(Node begin) throws InterruptedException {
        if(begin.left != null){
            postOrder(begin.left);
        }
        if(begin.right != null){
            postOrder(begin.right);
        }
        Thread.sleep(200);
        System.out.print(begin.key + "\t");
    }

    public E delete(E key, Node begin){
        Node y;
        Node x;
        Node deletee = search(key, begin);
        if(deletee.left == null || deletee.right == null){
            y = deletee;
        } else {
            y = treeSuccessor(deletee.key);
        }
        if(y.left != null){
            x = y.left;
        } else {
            x = y.right;
        }

        if(x != null)
            x.parent = y.parent;
        if(y.parent == null){
            root = x;
        } else {
            if(y == y.parent.left){
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        if(y != deletee){
            E temp = deletee.key;
            deletee.key = y.key;
            y.key = temp;
        }

        return y.key;
    }

    public void getCharacteristics(E key, Node begin){
        Node n = search(key, begin);

        n.leftHeight = getHeight(n.left);
        n.rightHeight = getHeight(n.right);
        n.overLoad = n.rightHeight - n.leftHeight;
        n.leftNodes = getNodes(n.left);
        n.rightNodes = getNodes(n.right);
        n.leftMaxOL = maxOverload(n.left);
        n.rightMaxOL = maxOverload(n.right);
        n.leftLeaves = getLeaves(n.left);
        n.rightLeaves = getLeaves(n.right);

        System.out.println("\nWysokosc lewego poddrzewa: " + n.leftHeight +
                        "\nWysokosc prawego poddrzewa: " + n.rightHeight +
                        "\nPrzeciazenie wierzcholka: " + n.overLoad +
                        "\nLiczba wezlow w lewym poddrzewie: " + n.leftNodes +
                        "\nLiczba wezlow w prawym poddrzewie: " + n.rightNodes +
                        "\nMaksymalne przeciazenie w lewym poddrzewie: " + n.leftMaxOL +
                        "\nMaksymalne przeciazenie w prawym poddrzewie: " + n.rightMaxOL +
                        "\nLiczba lisci w lewym poddrzewie: " + n.leftLeaves +
                        "\nLiczba lisci w prawym poddrzewie: " + n.rightLeaves);
    }

    private int getHeight(Node begin){
        if(begin == null)
            return 0;
        int height = 1;
        int left = 0, right = 0;
        if(begin.left != null){
            left = getHeight(begin.left);
        }
        if(begin.right != null){
            right = getHeight(begin.right);
        }
        return height + Math.max(left, right);
    }

    private int getNodes(Node begin){
        int nodes = 1;
        int left = 0, right = 0;
        if(begin.left != null){
            left = getNodes(begin.left);
        }
        if(begin.right != null){
            right = getNodes(begin.right);
        }
        return nodes + left + right;
    }

    private int maxOverload(Node begin){
        int ol = getHeight(begin.right) - getHeight(begin.left);
        int left = 0, right = 0;
        if(begin.left != null){
            left = maxOverload(begin.left);
        }
        if(begin.right != null){
            right = maxOverload(begin.right);
        }
        if(Math.abs(left) > Math.abs(right)){
            if(Math.abs(left) > Math.abs(ol))
                ol = left;
        } else {
            if(Math.abs(right) > Math.abs(ol))
                ol = right;
        }
        return ol;
    }

    private int getLeaves(Node begin){
        int leaves = 0;
        int left = 0, right = 0;
        if(begin.left == null && begin.right == null){
            leaves++;
        }
        if(begin.left != null){
            left = getLeaves(begin.left);
        }
        if(begin.right != null){
            right = getLeaves(begin.right);
        }
        return leaves + left + right;
    }

    public Node getLeft(Node node){
        return node.left;
    }

    public Node getRight(Node node){
        return node.right;
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
//            System.out.print(StringUtils.center(root.toString(), space));
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
//                    System.out.print(StringUtils.center(list.get(j).toString(), space));
                    nextGen.add(list.get(j).left);
                    nextGen.add(list.get(j).right);
                    shouldContinue = true;
                } else {
                    System.out.printf(s, " ");
//                    System.out.print(StringUtils.center(" ", space));
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
