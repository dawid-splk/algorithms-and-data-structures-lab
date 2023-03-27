package lab10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

public class GraphWithMaxFlow<E> {

    private class Edge{
        E from;
        E to;
        int weight;

        public Edge(E from, E to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Edge(Edge e){
            this.from = e.from;
            this.to = e.to;
            this.weight = e.weight;
        }
    }

    int [][] matrix;
    ArrayList<E> vertexes;
    ArrayList<Edge> allEdges;

    @SuppressWarnings("unchecked")
    public GraphWithMaxFlow(int size){
        matrix = new int [size][size];
        vertexes = new ArrayList<>();
        allEdges = new ArrayList<>();
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

        allEdges.add(new Edge(source, destination, 1));

        if(bidirectional){
            matrix[y][x] = 1;
            allEdges.add(new Edge(destination, source, 1));
        }
    }

    public void addEdge(E source, E destination, int distance, boolean bidirectional){
        if(!vertexes.contains(source))
            addVertex(source);
        if(!vertexes.contains(destination))
            addVertex(destination);

        int x = vertexes.indexOf(source);
        int y = vertexes.indexOf(destination);

        matrix[x][y] = distance;

        allEdges.add(new Edge(source, destination, distance));

        if(bidirectional){
            matrix[y][x] = distance;
            allEdges.add(new Edge(destination, source, distance));
        }
    }

    public void printAdjacencyMatrix(){
        System.out.println("\nAdjacency matrix:");

        System.out.printf("%14s", " ");
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.printf("%14s", vertexes.get(i));
        }
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.printf("%n%12s", vertexes.get(i));
            for (int j = 0; j < vertexes.size(); j++) {
                if(matrix[i][j] == 2147483647){
                    System.out.printf("%14s", "-  ");
                } else {
                    System.out.printf("%12s%2s", matrix[i][j], "km");
                }
            }
        }
    }

    public ArrayList<Edge> minimumSpanningTree(){   //założenie: nie ma odosobnionych wierzchołków
        Comparator<Edge> comp = (o1, o2) -> Integer.compare(o1.weight, o2.weight);
        allEdges.sort(comp);
        ArrayList<ArrayList<E>> trees = new ArrayList<>();
        ArrayList<Edge> minTree = new ArrayList<>();
        boolean createNewTree;
        Iterator<Edge> iter = allEdges.iterator();

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

    private HashMap<E, Integer> shortestWay(E start){
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

    public void getAlternateWays(E from, E to){
        ArrayList<E> visited = new ArrayList<>();
        visited.add(from);
        System.out.println("\n\nAlternate ways from " + from + " to " + to + ":\n");
        explore(from, visited, to, 0);
    }

    private void explore(E key, ArrayList<E> vis, E destination, int weight){
        for (int i = 0; i < matrix[0].length; i++) {
            if(i != vertexes.indexOf(key) && matrix[vertexes.indexOf(key)][i] != 2147483647){
                if(vertexes.get(i) == destination) {
                    for (int j = 0; j < vis.size(); j++) {
                        System.out.print(vis.get(j) + " -> ");
                    }
                    int distance = weight + matrix[vertexes.indexOf(key)][i];
                    System.out.println(destination + "\t(distance: " + distance + "km)");
                } else {
                    if (!vis.contains(vertexes.get(i))) {
                        ArrayList<E> visited = new ArrayList<>(vis);
                        int distance = weight;
                        visited.add(vertexes.get(i));
                        distance += matrix[vertexes.indexOf(key)][i];
                        explore(vertexes.get(i), visited, destination, distance);
                    }
                }
            }
        }
    }

    public void getMaximumFlow(E from, E to){
        ArrayList<E> visited = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        ArrayList<Edge> allEdgesCopy = new ArrayList<>();
        ArrayList<ArrayList<Edge>> allWays = new ArrayList<>();

        for (int i = 0; i < allEdges.size(); i++) {
            allEdgesCopy.add(new Edge(allEdges.get(i)));
        }

        visited.add(from);
        System.out.println("\n\nAlternate ways from " + from + " to " + to + ":\n");
        search(from, visited, edges, allWays, to, 0, allEdgesCopy);

        int maxFlow = 0;
        for (ArrayList<Edge> way : allWays) {
            int min = way.get(0).weight;
            for (Edge edge : way) {
                if (edge.weight < min) {
                    min = edge.weight;     //min capacity
                }
            }
            for (Edge edge : way) {
                edge.weight -= min;
            }
            maxFlow += min;
        }
        System.out.println("\n\nMaximum flow between " + from + " and " + to + ": " + maxFlow);
    }

    private void search(E key, ArrayList<E> vis, ArrayList<Edge> currentWay,ArrayList<ArrayList<Edge>> allWays, E destination, int weight, ArrayList<Edge> copy){
        for (int i = 0; i < matrix[0].length; i++) {
            if(i != vertexes.indexOf(key) && matrix[vertexes.indexOf(key)][i] != 2147483647){
                if(vertexes.get(i) == destination) {
                    for (int j = 0; j < vis.size(); j++) {
                        System.out.print(vis.get(j) + " -> ");
                    }
                    ArrayList<Edge> finalWay = new ArrayList<>(currentWay);
                    finalWay.add(findEdge(key, vertexes.get(i), copy));
                    allWays.add(finalWay);
                    int distance = weight + matrix[vertexes.indexOf(key)][i];
                    System.out.println(destination + "\t(distance: " + distance + "km)");
                } else {
                    if (!vis.contains(vertexes.get(i))) {
                        ArrayList<E> visited = new ArrayList<>(vis);
                        ArrayList<Edge> newWay = new ArrayList<>(currentWay);
                        newWay.add(findEdge(key, vertexes.get(i), copy));
                        int distance = weight;
                        visited.add(vertexes.get(i));
                        distance += matrix[vertexes.indexOf(key)][i];      //czy weight robi sie na nowo czy dziala na jednej pamieci
                        search(vertexes.get(i), visited, newWay, allWays, destination, distance, copy);
                    }
                }
            }
        }
    }

    private Edge findEdge(E from, E to, ArrayList<Edge> copy){
        for (int i = 0; i < allEdges.size(); i++) {
            if(copy.get(i).from == from && copy.get(i).to == to)
                return copy.get(i);
        }
        return null;
    }

    public int getShortestWay(E from, E to){
        int shortestWay = shortestWay(from).get(to);
        System.out.print("\n\n\nThe shortest way from " + from + " to " + to + " is " + shortestWay + "km.");

        return shortestWay;
    }

    public void getAllWays(E from){
        System.out.println("\n\nShortest ways from " + from + ":");
        HashMap<E, Integer> map = shortestWay(from);
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


    public boolean isBipartite(){
        ArrayList<E> bracketA = new ArrayList<>();
        ArrayList<E> bracketB = new ArrayList<>();

        bracketA.add(vertexes.get(0));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i != j && matrix[i][j] != 2147483647){

                    if(checkIfNotBipartite(bracketA, i, j) || checkIfNotBipartite(bracketB, i, j)){
                        System.out.println("Graph is not bipartite.");
                        return false;
                    }

                    if(checkIfInBracket(bracketA, bracketB, i)){
                        if(!checkIfInBracket(bracketA, bracketB, j)){
                            if(bracketA.contains(vertexes.get(i))){
                                bracketB.add(vertexes.get(j));
                            } else {
                                bracketA.add(vertexes.get(j));
                            }
                            fixLinkedVertexes(j, bracketA, bracketB);
                        }
                    } else {
                        if(checkIfInBracket(bracketA, bracketB, j)){
                            if(bracketA.contains(vertexes.get(j))){
                                bracketB.add(vertexes.get(i));
                            } else {
                                bracketA.add(vertexes.get(i));
                            }
                            fixLinkedVertexes(i, bracketA, bracketB);
                        }
                    }
                }
            }
        }
        System.out.println("Graph is bipartite.");

        System.out.print("\nSet A:\t ");    //reprezentacja z grupowaniem wierzchołków w zbiory
        for (E e : bracketA) {
            System.out.print(e + "   ");
        }
        System.out.print("\nSet B:\t ");
        for (E e : bracketB) {
            System.out.print(e + "   ");
        }

        System.out.print("\n");             //reprezentacja poprzez krawędzie
        for (int i = 0; i < vertexes.size(); i++) {
            System.out.print("\n" + vertexes.get(i) + " -> ");
            for (int j = 0; j < vertexes.size(); j++) {
                if(i != j && matrix[i][j] != 2147483647){
                    System.out.print(vertexes.get(j) + "  ");
                }
            }
        }
        return true;
    }

    private boolean checkIfNotBipartite(ArrayList<E> bracket, int first, int second){
        return (bracket.contains(vertexes.get(first)) && bracket.contains(vertexes.get(second)));
    }

    private boolean checkIfInBracket(ArrayList<E> bracketA, ArrayList<E> bracketB, int i){
        return (bracketA.contains(vertexes.get(i)) || bracketB.contains(vertexes.get(i)));
    }

    private void fixLinkedVertexes(int idx, ArrayList<E> a, ArrayList<E> b){
        for (int i = 0; i < matrix[idx].length; i++) {
            if(matrix[idx][i] != 2147483647){
                if(!checkIfInBracket(a, b, i)){
                    if(a.contains(vertexes.get(idx))){
                        b.add(vertexes.get(i));
                    } else {
                        a.add(vertexes.get(i));
                    }
                }
            }
        }
    }
}
