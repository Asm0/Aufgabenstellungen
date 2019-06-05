package A10_DijkstraPQShortestPath;

import java.util.ArrayList;

public class DijkstraPQShortestPath extends FindWay {
	private int[] dist;

	public DijkstraPQShortestPath(Graph graph) {
		super(graph);
	}

	/**
	 * Startentfernung initialisieren
	 *
	 */
	protected void initPathSearch() {
		int numv = graph.numVertices();
		dist = new int[numv];
		for (int i = 0; i < numv; i++) {
			dist[i] = 9999; // Summen im Graph dürfen nie mehr ergeben
		}

	}

	/**
	 * Berechnet *alle* kürzesten Wege ausgehend vom Startknoten Setzt dist[]-
	 * und pred[]-Arrays, kein Rückgabewert
	 * 
	 * @param from
	 *            Startknoten
	 */
	protected boolean calculatePath(int from, int to) {
		VertexHeap vertexheap = new VertexHeap(graph.numVertices());
		for (int i = 0; i < graph.numVertices(); i++) {	//Forschleife fügt alle Knoten mit der initialen Distanz (=9999) in den Heap ein und setzt den Vorgänger auf -1
			vertexheap.insert(new Vertex(i, dist[i]));
			pred[i] = -1;
		}
		dist[from] = 0;							//Die Distanz zum Startknoten ist 0
		vertexheap.setCost(from, 0);

		while(!vertexheap.isEmpty()){
			Vertex v = vertexheap.remove();																	//Knoten mit kürzester Distanz wird aus dem Heap genommen
			ArrayList<WeightedEdge> weightedEdges = (ArrayList<WeightedEdge>) graph.getEdges(v.vertex);	//Alle Kanten von diesem Knoten weg werden in eine Liste gespeichert
			for (WeightedEdge weightedEdge : weightedEdges) {
				if(weightedEdge.weight + v.cost < dist[weightedEdge.to_vertex]) {	//Überprüfe ob der Weg zum Knoten besser ist als der bereits existierende.
					pred[weightedEdge.to_vertex] = v.vertex;												//Der Vorgänger des Zielknotens wird zum aktuellen Knoten
					dist[weightedEdge.to_vertex] = weightedEdge.weight + v.cost;							//Die Distanz zum Zielknotens wird berechnet durch die Distanz zum aktuellen Knoten + das Kantengewicht.
					vertexheap.setCost(weightedEdge.to_vertex, dist[weightedEdge.to_vertex]);				//Setze die Distanz auch im Heap.
				}
			}
		}
		return true;
	}
}
