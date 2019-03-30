package A06_Tiefensuche;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

	@Override
	/**
	 * Sortierkriterium im Baum: Länge des Films
	 */
	protected int compare(Film a, Film b) {
		//return ((Double)a.getLänge()).compareTo(b.getLänge());
		return Double.compare(a.getLänge(), b.getLänge());
	}

	/**
	 * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
	 * @param node Wurzelknoten des Teilbaums
	 * @return Liste der Titel in symmetrischer Reihenfolge
	 */
	public List<String> getNodesInOrder(Node<Film> node) {
		List<String> films = new LinkedList<>();
		if (node.getLeft() != null) {
			films.addAll(getNodesInOrder(node.getLeft()));
		}
		films.add(node.getValue().getTitel());
		if(node.getRight() != null) {
			films.addAll(getNodesInOrder(node.getRight()));
		}
		return films;
	}
	
	/**
	 * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
	 * @param min Minimale Länge des Spielfilms
	 * @param max Maximale Länge des Spielfilms
	 * @return Liste der Filmtitel in Hauptreihenfolge
	 */
	public List<String> getMinMaxPreOrder(double min, double max) {
		List<String> films = new LinkedList<>();
		Stack<Node> st = new Stack<>();
		st.push(root);
		Node n;
		while(!st.empty()) {
			n = st.pop();
			Film f = (Film) n.getValue();
			if(f.getLänge() < max && f.getLänge() > min) {
				films.add(f.getTitel());
			}
			if(n.getRight() != null) {
				st.push(n.getRight());
			}
			if(n.getLeft() != null) {
				st.push(n.getLeft());
			}

		}
		return films;
	}

}
