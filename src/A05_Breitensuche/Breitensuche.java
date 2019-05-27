package A05_Breitensuche;

import java.util.*;

public class Breitensuche extends BaseTree<Integer> {

    @Override
    protected int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
     *
     * @param start Startknoten für Teilbaum
     * @return Liste der Knoten in Breitenfolge
     */
    public List<Integer> getBreadthFirstOrder(Node<Integer> start) {
        Queue<Node> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.remove();
            list.add(node.getValue());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return list;
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
     * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
     *
     * @param start Startknoten für Teilbaum
     * @param level Nur Knoten dieser Ebene ausgeben
     * @return Liste aller Knoten
     */
    public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {
        Queue<Node> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        start.setLevel(1);
        queue.add(start);
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.remove();
            if (node.getLevel() == level) {
                list.add(node.getValue());
            }
            if (node.getLeft() != null) {
                Node left = node.getLeft();
                left.setLevel(node.getLevel() + 1);
                queue.add(left);
            }
            if (node.getRight() != null) {
                Node right = node.getRight();
                right.setLevel(node.getLevel() + 1);
                queue.add(right);
            }
        }
        return list;
    }

}
