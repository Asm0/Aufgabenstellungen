package A08_GraphZusammenhängend;

import basisAlgorithmen.Graph;
import basisAlgorithmen.ListGraph;
import basisAlgorithmen.Vertex;
import basisAlgorithmen.WeightedEdge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ConnectedComponents {
	
	/**
	 * Retourniert die Anzahl der zusammenhängenden Komponenten eines Graphen
	 * @param g zu prüfender Graph
	 * @return Anzahl der Komponenten
	 */
	public int getNumberOfComponents(Graph g) {
		return getNumberOfComponents(g, 0);
	}

	public int getNumberOfComponents(Graph g, int startVertex){
		Stack<Integer> vertexStack = new Stack<>();
		Set<Integer> vertexSet = new HashSet<>();
		int numOfComponents = 1;
		vertexStack.add(startVertex);
		vertexSet.add(startVertex);
		while (!vertexStack.empty()){
			List<WeightedEdge> weightedEdges = g.getEdges(vertexStack.pop());
			for (WeightedEdge weightedEdge : weightedEdges) {
				if(vertexSet.add(weightedEdge.to_vertex)) {
					vertexStack.add(weightedEdge.to_vertex);
				}
			}
		}
		for (int i = startVertex; i < g.numVertices(); i++) {
			if(!vertexSet.contains(i)){
				numOfComponents += getNumberOfComponents(g, i);
				break;
			}
		}
		return numOfComponents;
	}

}
