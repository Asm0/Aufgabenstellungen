package A10_DijkstraPQShortestPath;

import java.util.ArrayList;
import java.util.List;

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
		//Ihre Implementierung
		VertexHeap vertexheap = new VertexHeap(graph.numVertices());
		for (int i = 0; i < graph.numVertices(); i++) {
			vertexheap.insert(new Vertex(i, dist[i]));
			pred[i] = -1;
		}
		pred[from] = -1;
		dist[from] = 0;
		vertexheap.setCost(from, 0);

		while(!vertexheap.isEmpty()){
			Vertex v = vertexheap.remove();
			ArrayList<WeightedEdge> weightedEdges = (ArrayList<WeightedEdge>) graph.getEdges(v.vertex);
			for (WeightedEdge weightedEdge : weightedEdges) {
				if(pred[weightedEdge.to_vertex] == -1) {
					pred[weightedEdge.to_vertex] = v.vertex;
					dist[weightedEdge.to_vertex] = weightedEdge.weight + v.cost;
					vertexheap.setCost(weightedEdge.to_vertex, dist[weightedEdge.to_vertex]);
				}
			}
		}
		return true;
	}
}
