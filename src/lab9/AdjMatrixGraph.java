package lab9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class AdjMatrixGraph<E> {

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

    int [][] matrix;
    ArrayList<E> vertexes;
    ArrayList<Edge> edges;

    @SuppressWarnings("unchecked")
    public AdjMatrixGraph(int size){
        matrix = new int [size][size];
        vertexes = new ArrayList<>();
        edges = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i == j){
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = (int) Double.POSITIVE_INFINITY;
                }
            }
        }
    }

    public void addVertex(E node){
        vertexes.add(node);
    }

    public void addEdge(E source, E destination, boolean bidirectional){
        if(!vertexes.contains(source))
            addVertex(source);
        if(!vertexes.contains(destination))
            addVertex(destination);

        int x = vertexes.indexOf(source);
        int y = vertexes.indexOf(destination);

        matrix[x][y] = 1;

        if(bidirectional){
            matrix[y][x] = 1;
        }

        edges.add(new Edge(source, destination, 1));
    }

    public void addEdge(E source, E destination, int weight, boolean bidirectional){
        if(!vertexes.contains(source))
            addVertex(source);
        if(!vertexes.contains(destination))
            addVertex(destination);

        int x = vertexes.indexOf(source);
        int y = vertexes.indexOf(destination);

        matrix[x][y] = weight;

        if(bidirectional){
            matrix[y][x] = weight;
        }

        edges.add(new Edge(source, destination, weight));
    }

    public void printAdjacencyMatrix(){
        System.out.println("\nAdjacency matrix:");

        for (int i = 0; i < vertexes.size(); i++) {
            System.out.print("\t" + vertexes.get(i));
        }
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.print("\n" + vertexes.get(i) + "\t");
            for (int j = 0; j < vertexes.size(); j++) {
                if(matrix[i][j] == 2147483647){
                    System.out.print("∞\t");
                } else {
                    System.out.print(matrix[i][j] + "\t");
                }
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

    private HashMap<E, Integer> dijkstraSingleSourceSP(E start){
        ArrayList<E> toVisit = new ArrayList<>(vertexes);
        toVisit.remove(start);
        HashMap<E, Integer> shortestPaths = new HashMap<>();
        shortestPaths.put(start, 0);

        for (int i = 0; i < toVisit.size(); i++) {
            shortestPaths.put(toVisit.get(i), matrix[vertexes.indexOf(start)][vertexes.indexOf(toVisit.get(i))]);
        }

        while(!toVisit.isEmpty()){
            int min = (int) Double.POSITIVE_INFINITY;
            E arg = null;
            for(E key : toVisit){
                if(shortestPaths.get(key) <= min){
                    arg = key;
                    min = shortestPaths.get(key);
                }
            }
            toVisit.remove(arg);

            for (int i = 0; i < toVisit.size(); i++) {
                E subject = toVisit.get(i);
                int newPath = getEdgeWeight(arg, toVisit.get(i));
                if(newPath != 2147483647) {
                    newPath = shortestPaths.get(arg) + getEdgeWeight(arg, toVisit.get(i));

                    if (newPath < shortestPaths.get(subject)) {
                        shortestPaths.replace(subject, newPath);
                    }
                }
            }
        }
        return shortestPaths;
    }

    public int getShortestPath(E from, E to){
        int shortestPath = dijkstraSingleSourceSP(from).get(to);
        System.out.print("\nThe shortest path from " + from + " to " + to + " weighs: " + shortestPath);

        return shortestPath;
    }

    public void getAllPaths(E from){
        System.out.println("\n\nShortest paths from " + from + ":");
        HashMap<E, Integer> map = dijkstraSingleSourceSP(from);
        for(E key : map.keySet()){
            if(map.get(key) == 2147483647)
                System.out.println(key + ": " + "∞");
            else
                System.out.println(key + ": " + map.get(key));
        }
    }

    private int getEdgeWeight(E from, E to){
        return matrix[vertexes.indexOf(from)][vertexes.indexOf(to)];
    }
}
