package A10_DijkstraPQShortestPath;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class FindWay {
	protected Graph graph;
	protected int[] pred;
	
	public FindWay(Graph graph) {
		this.graph = graph;
		this.pred = new int[graph.numVertices()];
	}

	/**
	 * Liefert den Weg von (from) nach (to) als Liste zurück
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg von Start nach Ziel oder null
	 */
	public List<Integer> findWay(int from, int to) {
		initPathSearch();
		if (!calculatePath(from, to)) {
			return null;
		}
		return createWay(from, to);
	}

	/**
	 * Initialisierungsfunktion
	 */
	abstract protected void initPathSearch();

	/**
	 * Berechnungsfunktion für Weg von (from) nach (to)
	 */
	abstract protected boolean calculatePath(int from, int to);
	
	/**
	 * Weg von (to) nach (from) aus Vorgängerknoten rekonstruieren
	 * @param from Startknoten
	 * @param to Zielknoten
	 * @return Weg als Liste
	 */
	protected ArrayList<Integer> createWay(int from, int to) {
		if(pred[to] == -1) {
			return null;	//Wenn Vorgänger von Ziel auf -1 dann konnte kein Weg dahin gefunden werden -> return null
		}
		ArrayList<Integer> way = new ArrayList<>();
		int current = to;
		way.add(to);
		while (current != from) {	//Gehe solange vom Zielknoten über den jeweiligen Vorgänger bis du am Startknoten angekommen bist.
			way.add(pred[current]);
			current = pred[current];
		}
		Collections.reverse(way);	//Drehe das Array damit die Ausgabe schöner ist
		return way;
	}
}
