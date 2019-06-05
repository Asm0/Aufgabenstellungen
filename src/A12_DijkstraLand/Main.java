package A12_DijkstraLand;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ListGraph g = new ListGraph(8, false);
        String [] lands = {"Andorra", "Austria", "Russia", "Andorra", "Spain", "Andorra", "Andorra", "Andorra"};

        g.addEdge(0, 4,  3);
        g.addEdge(0, 5,  4);
        g.addEdge(1, 3,  1);
        g.addEdge(1, 4,  6);
        g.addEdge(1, 6,  2);
        g.addEdge(2, 3,  3);
        g.addEdge(2, 4,  4);
        g.addEdge(2, 7,  4);
        g.addEdge(3, 6,  2);
        g.addEdge(3, 7,  1);
        g.addEdge(5, 6,  3);
        for (int i = 0; i < g.numVertices(); i++) {
            g.setLand(lands[i], i);
        }

        List<Integer> way = Dijkstra.dijkstra(g, 0, 7);
        printWay(way);

    }

    public static void printWay(List<Integer> way) {
        if (way == null) {
            System.out.println("Kein Weg gefunden.");
            return;
        }
        for (int i=0; i < way.size(); i++) {
            if (i != 0)
                System.out.print(" -> ");
            System.out.print(way.get(i));
        }
    }


}
