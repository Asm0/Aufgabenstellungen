package A02_Queue;

public class Queue<T>
{
    private Node<T> first;
    
    private Node<T> last;
    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws QueueEmptyException 
     */
    public T dequeue() throws QueueEmptyException {
        if(first == null) {
            throw new QueueEmptyException();
        } else {
            T t = first.getData();
            Node node = last;
            if(node.getNext() == null) {
                first = null;
                last = null;
            } else {
                while(node.getNext().hashCode() != first.hashCode()) {
                    node = node.getNext();
                }
                node.setNext(null);
                first = node;
            }
            return t;
        }
    }
    
    
    
    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     * @param i Zahl
     */
    public void enqueue(T i) {
        Node node = new Node(i);
        if(first == null) {
            first = node;
            last = node;
        } else {
           node.setNext(last);
           last = node;
        }

    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
        int count = 1;
        if(last == null) {
            return 0;
        }
        Node n = last;
        while ((n = n.getNext()) != null) {
            count++;
        }
    	return count;
    }
}
