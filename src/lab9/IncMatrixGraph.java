package lab9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class IncMatrixGraph<E> {

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

    int[][] matrix;
    ArrayList<E> vertexes;
    ArrayList<Integer> edgesIdx;    //do reprezentacji i wypisywania
    ArrayList<Edge> edges;          //do praktycznego zastosowania: MST

    public IncMatrixGraph(int edges, int vertexes){
        matrix = new int[vertexes][edges];
        this.edgesIdx = new ArrayList<>();
        this.vertexes = new ArrayList<>();
        this.edges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    public void addEdge(E source, E destination, boolean bidirectional) {
        if(!vertexes.contains(source))
            addVertex(source);
        if(!vertexes.contains(destination))
            addVertex(destination);

        int edgeNum = edgesIdx.size();
        int x = vertexes.indexOf(source);
        int y = vertexes.indexOf(destination);

        edgesIdx.add(edgeNum);

        matrix[x][edgeNum] = 1;
        matrix[y][edgeNum] = -1;

        if(bidirectional){
            edgesIdx.add(edgeNum + 1);
            matrix[x][edgeNum + 1] = -1;
            matrix[y][edgeNum + 1] = 1;
        }

        edges.add(new Edge(source, destination, 1));
    }

    public void addEdge(E source, E destination, int weight, boolean bidirectional) {
        if(!vertexes.contains(source))
            addVertex(source);
        if(!vertexes.contains(destination))
            addVertex(destination);

        int edgeNum = edgesIdx.size();
        int x = vertexes.indexOf(source);
        int y = vertexes.indexOf(destination);

        edgesIdx.add(edgeNum);      //do tabeli i wypisywania

        matrix[x][edgeNum] = weight;
        matrix[y][edgeNum] = -weight;

        if(bidirectional){
            edgesIdx.add(edgeNum + 1);
            matrix[x][edgeNum + 1] = -weight;
            matrix[y][edgeNum + 1] = weight;
        }

        edges.add(new Edge(source, destination, weight));   //do praktycznego zastosowania: MST

    }

    public void addVertex(E vertex){
        vertexes.add(vertex);
    }

    public void printIncidenceMatrix(){
        System.out.println("\nIncidence matrix: ");
        for (int i = 0; i < edgesIdx.size(); i++) {
            System.out.print("\te" + edgesIdx.get(i));
        }
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.print("\n" + vertexes.get(i) + ": \t");
            for (int j = 0; j < edgesIdx.size(); j++) {
                System.out.printf("%2d\t", matrix[i][j]);
            }
        }
    }

    public ArrayList<Edge> minimumSpanningTree(){   //założenie: nie ma odosobnionych wierzchołków
        Comparator<Edge> comp = (o1, o2) -> Integer.compare(o1.weight, o2.weight);
        edges.sort(comp);
        ArrayList<ArrayList<E>> trees = new ArrayList<>();
        ArrayList<Edge> minTree = new ArrayList<>();
        boolean createNewTree;
        Iterator<Edge> iter = edges.iterator();

        while(minTree.size() < vertexes.size() - 1){
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
}
