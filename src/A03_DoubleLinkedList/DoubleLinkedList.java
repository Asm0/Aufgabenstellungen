package A03_DoubleLinkedList;

public class DoubleLinkedList<T>
{
    private Node<T> current;
    private Node<T> last;
    private Node<T> first;

    /**
     * Einfügen einer neuen <T>
     * @param a <T>
     */

    public void add(T a) {
        Node<T> node = new Node<>(a);
        if(first == null) { //Check if any node exists yet
            first=node;
            last=node;
        } else {
            node.setPrevious(last);
            last.setNext(node);
            last=node;
        }
    }

    /**
     * Internen Zeiger für next() zurücksetzen
     */
    public void reset() {
        current = first;
    }

    /**
     * analog zur Funktion reset()
     */
    public void resetToLast() {
        current = last;
    }

    /**
     * Liefert erste Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getFirst() {
        if(first == null) {
            return null;
        }
        return first;
    }
    
    /**
     * Liefert letzte Node der Liste retour oder null, wenn Liste leer
     * @return Node|null
     */
    public Node<T> getLast() {
        if(last == null) {
            return null;
        }
        return last;
    }
    
    /**
     * Gibt aktuelle <T> zurück und setzt internen Zeiger weiter.
     * Falls current nicht gesetzt, wird null retourniert.
     * @return <T>|null
     */
    public T next() {
        if(current == null) {
            return null;
        }
        T t = current.getData();
        current = current.getNext();
    	return t;
    }

    /**
     * analog zur Funktion next()
     * @return <T>|null
     */
    public T previous() {
        if(current == null) {
            return null;
        }
        T t = current.getData();
        current = current.getPrevious();
        return t;
    }
    
    /**
     * Current-Pointer auf nächste <T> setzen (aber nicht auslesen).
     * Ignoriert still, dass current nicht gesetzt ist.
     */
    public void moveNext() {
        if(current != null) {
            current = current.getNext();
        }
    }
    
    /**
     * Analog zur Funktion moveNext()
     */
    public void movePrevious() {
        if(current != null) {
            current = current.getPrevious();
        }
    }
   
    /**
     * Retourniert aktuelle (current) <T>, ohne Zeiger zu ändern
     * @return <T>
     * @throws CurrentNotSetException
     */
    public T getCurrent() throws CurrentNotSetException {
        if(current == null) {
            throw new CurrentNotSetException();
        }
    	return current.getData();
    }

    /**
     * Gibt <T> an bestimmter Position zurück
     * @param pos Position, Nummerierung startet mit 1
     * @return <T>|null
     */
    public T get(int pos) {
        Node<T> node = first;
        for (int i = 1; i < pos; i++) {
            if(node == null) {
                return null;
            }
            node = node.getNext();
        }
        return (node==null) ?  null : node.getData();
    }

    /**
     * Entfernen des Elements an der angegebenen Position.
     * Falls das entfernte Element das aktuelle Element ist, wird current auf null gesetzt.
     * @param pos
     */
    public void remove(int pos) {
        if(first != null) {
            Node<T> node = first;
            for (int i = 1; i < pos; i++) {
                node = node.getNext();
            }
            if (node != null) {
                if (node.getNext() != null) {
                    node.getNext().setPrevious(node.getPrevious());
                } else {
                    last = last.getPrevious();
                }
                if (node.getPrevious() != null) {
                    node.getPrevious().setNext(node.getNext());
                } else {
                    first = first.getNext();
                }
                if (current != null && node.hashCode() == current.hashCode()) {
                    current = null;
                }
            }
        }
    }
    
    /**
     * Entfernt das aktuelle Element.
     * Als neues aktuelles Element wird der Nachfolger gesetzt oder
     * (falls kein Nachfolger) das vorhergehende Element 
     * @throws CurrentNotSetException
     */
    public void removeCurrent() throws CurrentNotSetException {
        if(current == null) {
            throw new CurrentNotSetException();
        }
        if(current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
        } else {
            first = first.getNext();
        }
        if(current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
            current = current.getNext();
        } else {
            last = last.getPrevious();
            current = current.getPrevious();
        }
    }
    
    /**
     * Die Methode fügt die übergebene <T> nach der aktuellen (current) ein
     * und setzt dann die neu eingefügte <T> als aktuelle (current) <T>.
     * @throws CurrentNotSetException 
     */
    public void insertAfterCurrentAndMove(T a) throws CurrentNotSetException {
        Node<T> node = new Node<>(a);
        if(current == null) {
            throw new CurrentNotSetException();
        }
        if(current.getNext()!=null) {
            current.getNext().setPrevious(node);
        }
        node.setNext(current.getNext());
        node.setPrevious(current);
        current.setNext(node);
        current = node;
    }
}
