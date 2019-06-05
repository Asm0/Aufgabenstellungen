package A12_DijkstraLand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

	public static List<Integer> dijkstra(Graph g, int von, int nach) {
		
		int[] pred = new int[g.numVertices()];
		int[] dist = new int[g.numVertices()];
		VertexHeap vertexHeap = new VertexHeap(g.numVertices());

		//Distance und predecessor array initialisieren
		for (int i = 0; i < g.numVertices(); i++) {
			pred[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		}

		//Heap befüllen
		for (int i = 0; i < g.numVertices(); i++) {
			//Weightededge wird als Vertex verwendet
			vertexHeap.insert(new WeightedEdge(i, dist[i]));
		}

		///Entfernung zum Startknoten ist 0
		dist[von] = 0;
		vertexHeap.setPriority(von, 0);

		//Solange der heap nicht leer ist..
		while (!vertexHeap.isEmpty()) {
			//Knoten mit kleinster Distanz aus dem Heap entfernen
			WeightedEdge currentVertex = vertexHeap.remove();
			//Alles mit diesem Knoten verbundenen Kanten holen
			List<WeightedEdge> weightedEdges = g.getEdges(currentVertex.vertex);
			for (WeightedEdge weightedEdge : weightedEdges) {
				int differendCountryCost = 0;
				if(g.getLand(currentVertex.vertex).equals(g.getLand(weightedEdge.vertex)) == false){ //Überprüfe ob Ziel in einem anderen Land
					differendCountryCost = 1;
				}
				if(weightedEdge.weight + currentVertex.weight + differendCountryCost < dist[weightedEdge.vertex]){ //Überprüfe ob weg zum Knoten besser ist als der bereits existierende
					pred[weightedEdge.vertex] = currentVertex.vertex; //predecessor wird angepasst
					dist[weightedEdge.vertex] = weightedEdge.weight + currentVertex.weight + differendCountryCost;
					vertexHeap.setPriority(weightedEdge.vertex, dist[weightedEdge.vertex]);
				}
			}
		}

		// pred ausgeben
		for(int i=0; i<pred.length; i++) {
			System.out.println(i + " über " + pred[i]);
		}
		
		// Way ausgeben
		System.out.println();
		ArrayList<Integer> way = predToWay(pred, von, nach);
		return way;
	}
	
	private static ArrayList<Integer> predToWay(int[] pred, int from, int to) {
		if(pred[to] == -1){
			return null;
		}
		ArrayList<Integer> way = new ArrayList<Integer>();
		int current = to;
		while (current != -1){
			way.add(current);
			current = pred[current];
		}
		Collections.reverse(way);
		return way;
	}
	

}
