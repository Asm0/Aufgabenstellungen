package A01_Stack;


public class Stack<T>
{
	 private Node<T> first;
    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException 
     */
    public T pop() throws StackEmptyException {
        if(first==null) {
            throw new StackEmptyException();
        }
        T t = first.getData();
        Node<T> node = first.getNext();
        if(node == null) {
            first = null;
        } else {
            first.setNext(null);
            first = node;
        }
    	return t;
    }
    
    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {
        if(first == null) {
            first = new Node<>(i);
        } else {
            Node<T> node = new Node<>(i);
            node.setNext(first);
            first = node;
        }

    }
    
    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {
        int count = 1;
        if(first == null) {
            return 0;
        }
        Node<T> node = first;
        while ((node = node.getNext()) != null) {
            count++;
        }
    	return count;
    }
}
