package lab10;

public class Zad2 {

    public static void main(String[] args) {
        GraphWithMaxFlow<String> graph = new GraphWithMaxFlow<>(10);
        graph.addEdge("A", "B", true);
        graph.addEdge("A","C", true);
        graph.addEdge("B","D", true);
        graph.addEdge("B","F", true);
        graph.addEdge("C","F", true);
        graph.addEdge("D", "E", true);

        graph.isBipartite();

        graph.addEdge("A", "D", true);
        System.out.println("\n\n(Added an edge (A ->D), which made the graph not bipartite)\n");
        graph.isBipartite();
    }
}
