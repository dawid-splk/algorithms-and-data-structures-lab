package lab10;

public class Zad1_3 {

    public static void main(String[] args) {
        GraphWithMaxFlow<String> trasyMiast = new GraphWithMaxFlow<>(15);
        trasyMiast.addEdge("Warszawa", "Krakow", 300, true);
        trasyMiast.addEdge("Warszawa", "Bialystok", 200, true);
        trasyMiast.addEdge("Opole", "Krakow", 200, true);
        trasyMiast.addEdge("Opole", "Wroclaw", 100, true);
        trasyMiast.addEdge("Poznan", "Lodz", 200, true);
        trasyMiast.addEdge("Poznan", "Wroclaw", 200, true);
        trasyMiast.addEdge("Poznan", "Gdansk", 300, true);
        trasyMiast.addEdge("Poznan", "Zielona Gora", 150, true);
        trasyMiast.addEdge("Wroclaw", "Zielona Gora", 200, true);
        trasyMiast.addEdge("Wroclaw", "Lodz", 250, true);
        trasyMiast.addEdge("Warszawa", "Lodz", 250, true);
        trasyMiast.addEdge("Lodz", "Kalisz", 70, true);
        trasyMiast.addEdge("Kalisz", "Zielona Gora", 300, true);


        trasyMiast.printAdjacencyMatrix();  //reprezentacja graficzna grafu
        trasyMiast.getShortestWay("Lodz", "Zielona Gora");  //najkrótsza droga
//        trasyMiast.getAlternateWays("Lodz", "Zielona Gora");  //wszystkie drogi wraz z odlegloscia
        trasyMiast.getMaximumFlow("Lodz", "Zielona Gora");  //hobbystycznie: maksymalny przeplyw pomiedzy miastami
    }                                                       //(printuje rowniez wszystkie drogi z odlegloscia - 1c)
}
