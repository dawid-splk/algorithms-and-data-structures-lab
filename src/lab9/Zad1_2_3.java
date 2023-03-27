package lab9;

public class Zad1_2_3 {

    public static void main(String[] args) {

        System.out.println("\nZad1. Graph representations, adding vertexes/edges, MST: ");

        AdjMatrixGraph<String> adj = new AdjMatrixGraph<>(10);     //ustalony odgornie rozmiar
        adj.addVertex("A");
        adj.addVertex("B");
        adj.addVertex("C");
        adj.addVertex("D");
        adj.addVertex("E");
        adj.addVertex("F");                                             //2 rodzaje metod addEdge:
        adj.addEdge("A","C",true);                 //domyślna - waga 1
        adj.addEdge("B","A",2,false);       //waga jako argument
        adj.addEdge("A","D",true);
        adj.addEdge("A","F", 7, false);
        adj.addEdge("E", "A",4, false);
        adj.addEdge("E", "C", 3, true);
        adj.addEdge("E", "F", true);
        adj.addEdge("F", "B",2, false);

        adj.addEdge("B", "G", 4, true);
        adj.addEdge("D", "G", false);
//        adj.addVertex("Z");


        adj.printAdjacencyMatrix();
        adj.minimumSpanningTree();  //założenie: drzewo rozpinające łączy wszystkie wierzchołki grafu
                                    // = nie ma odosobnionych wierzchołków


        ListGraph<String> list = new ListGraph<>();
        list.addVertex("A");
        list.addVertex("B");
        list.addVertex("C");
        list.addVertex("D");
        list.addVertex("E");
        list.addVertex("F");
        list.addEdge("A","C",true);
        list.addEdge("B","A",2,false);
        list.addEdge("A","D",true);
        list.addEdge("A","F", 7, false);
        list.addEdge("E", "A",4, false);
        list.addEdge("E", "C", 3, true);
        list.addEdge("E", "F", true);
        list.addEdge("F", "B",2, false);

        list.addEdge("B", "G", 4, true);
        list.addEdge("D", "G", false);
//        list.addVertex("Z");

        list.printAdjacencyList();
        list.minimumSpanningTree();

        IncMatrixGraph<String> inc = new IncMatrixGraph<>(20,20);   //ustalony rozmiar
        inc.addVertex("A");
        inc.addVertex("B");
        inc.addVertex("C");
        inc.addVertex("D");
        inc.addVertex("E");
        inc.addVertex("F");
        inc.addEdge("A","C",true);
        inc.addEdge("B","A",2,false);
        inc.addEdge("A","D",true);
        inc.addEdge("A","F", 7, false);
        inc.addEdge("E", "A",4, false);
        inc.addEdge("E", "C", 3, true);
        inc.addEdge("E", "F", true);
        inc.addEdge("F", "B",2, false);

        inc.addEdge("B", "G", 4, true);
        inc.addEdge("D", "G", false);
//        inc.addVertex("Z");

        inc.printIncidenceMatrix();
        inc.minimumSpanningTree();

        System.out.println("\n\nZad2. BFS and DFS algorithms: ");
        list.breadthFirstSearch("F");
        list.depthFirstSearch("F");

        System.out.println("\n\nZad3. Dijkstra's single source shortest path algorithm: ");
        adj.getShortestPath("A","B");
        adj.getAllPaths("A");
    }
}
