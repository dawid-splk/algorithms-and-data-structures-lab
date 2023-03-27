package lab9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class ListGraph <E> {

    private class Edge{
        E from;
        E to;
        int weight;

        public Edge(E from, E to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private class Element{
        E vertex;
        int weight;

        public Element(E vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString(){
            return "(" + vertex.toString() + "," + weight + ")";
        }
    }

    private HashMap<E, OneWayLinkedList<Element>> map;
    ArrayList<Edge> edges;

    public ListGraph(){
        map = new HashMap<>();
        edges = new ArrayList<>();
    }

    public void addVertex(E vertex){
        map.put(vertex, new OneWayLinkedList<>());
    }

    public void addEdge(E source, E destination, boolean bidirectional) {
        if (!map.containsKey(source))
            addVertex(source);
        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(new Element(destination,1));
        if (bidirectional)
            map.get(destination).add(new Element(source,1));

        edges.add(new Edge(source, destination, 1));
    }

    public void addEdge(E source, E destination, int weight, boolean bidirectional) {
        if (!map.containsKey(source))
            addVertex(source);
        if (!map.containsKey(destination))
            addVertex(destination);

        map.get(source).add(new Element(destination,weight));
        if (bidirectional)
            map.get(destination).add(new Element(source,weight));

        edges.add(new Edge(source, destination, weight));
    }

    public void printAdjacencyList(){
        System.out.println("\n\nAdjacency list:");
        for(E key : map.keySet()){
            System.out.print(key + ":\t");
            printSingularVertexList(map.get(key));
            System.out.print("\n");
        }
    }

    private void printSingularVertexList(OneWayLinkedList<Element> list){
        for(Element elem : list){
            System.out.print(elem + "\t");
        }
    }

    public ArrayList<Edge> minimumSpanningTree(){   //założenie: nie ma odosobnionych wierzchołków
        Comparator<Edge> comp = (o1, o2) -> Integer.compare(o1.weight, o2.weight);
        edges.sort(comp);
        ArrayList<ArrayList<E>> trees = new ArrayList<>();
        ArrayList<Edge> minTree = new ArrayList<>();
        boolean createNewTree;
        Iterator<Edge> iter = edges.iterator();

        while(minTree.size() < map.keySet().size() - 1){
            createNewTree = true;
            Edge e = iter.next();
            for(ArrayList<E> list : trees){
                if(list.contains(e.from) || list.contains(e.to)){
                    for (int j = trees.indexOf(list) + 1; j < trees.size(); j++) {
                        if (trees.get(j).contains(e.from) || trees.get(j).contains(e.to)) {
                            list.addAll(trees.get(j));
                            trees.remove(j);
                            j--;
                            minTree.add(e);
                        }
                    }
                    if(!(list.contains(e.from) && list.contains(e.to))){
                        minTree.add(e);
                        if(list.contains(e.from))
                            list.add(e.to);
                        else
                            list.add(e.from);
                    }
                    createNewTree = false;
                }
            }
            if(createNewTree){
                ArrayList<E> newTree = new ArrayList<>();
                newTree.add(e.from);
                newTree.add(e.to);
                minTree.add(e);
                trees.add(newTree);
            }

        }
        int sum = 0;
        System.out.println("\n\nMinimum spanning tree: ");
        for(Edge e : minTree){
            System.out.print(e.from + " - " + e.to + ": " + e.weight + "\t");
            sum += e.weight;
        }
        System.out.println("\nWeight of MST: " + sum);
        return minTree;
    }

    public void breadthFirstSearch(E start){
        MyQueue<E> queue = new MyQueue<>();
        ArrayList<E> visited = new ArrayList<>();

        visited.add(start);
        queue.enqueue(start);

        System.out.println("\nBreadth-first search: ");

        while(queue.size() != 0){
            E vertex = queue.dequeue();
            System.out.print(vertex + "\t");
            for(Element e : map.get(vertex)){
                if(!visited.contains(e.vertex)){
                    visited.add(e.vertex);
                    queue.enqueue(e.vertex);
                }
            }
         }
        System.out.print("\n");
    }

    public void depthFirstSearch(E start){
        ArrayList<E> visited = new ArrayList<>();

        System.out.println("\nDepth-first search: ");
        exploreVertex(start, visited);
        System.out.print("\n");
    }

    private void exploreVertex(E vertex, ArrayList<E> visited){
        visited.add(vertex);
        System.out.print(vertex + "\t");

        for(Element e : map.get(vertex)){
            if(!visited.contains(e.vertex)){
                exploreVertex(e.vertex, visited);
            }
        }
    }
}
