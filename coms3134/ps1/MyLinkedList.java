/**
 *  * LinkedList class implements a doubly-linked list.
 *   * Adapted from Weiss, Data sSructures and Algorithm Analysis in Java. 3rd ed.
 *    * http://users.cis.fiu.edu/~weiss/dsaajava3/code/MyLinkedList.java
 *     */
import java.util.HashSet;

public class MyLinkedList<AnyType> implements Iterable<AnyType>{

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    /**
     *      * This is the doubly-linked list node.
     *           */
    private static class Node<AnyType> {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n  ) {
            data = d; prev = p; next = n;

        }
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }

    public MyLinkedList() {
        doClear();
    }

    /**
     *      * Change the size of this collection to zero by
     *           *  initializing the beginning and end marker.
     *                */
    public void doClear() {
        beginMarker = new Node<>( null, null, null  );
        endMarker = new Node<>( null, beginMarker, null  );
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private Node<AnyType> getNode( int idx, int lower, int upper  ) {
        Node<AnyType> p;

        if( idx < lower || idx > upper  )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size(  )  );

        if( idx < size() / 2  ) { // Search through list from the beginning
            p = beginMarker.next;
            for( int i = 0; i < idx; i++  )
                p = p.next;
        } else { // serch through the list from the end
            p = endMarker;
            for( int i = size(); i > idx; i--  )
                p = p.prev;
        }

        return p;
    }

    private Node<AnyType> getNode( int idx  )
    {
        return getNode( idx, 0, size(  ) - 1  );
    }


    public AnyType get( int idx  ) {
        return getNode( idx  ).data;
    }

    public AnyType set( int idx, AnyType newVal  ) {
        Node<AnyType> p = getNode( idx  );
        AnyType oldVal = p.data;

        p.data = newVal;
        return oldVal;
    }

    private void addBefore( Node<AnyType> p, AnyType x  ) {
        Node<AnyType> newNode = new Node<>( x, p.prev, p  );
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    public void add(int idx, AnyType x) {
        addBefore( getNode(idx, 0, size()), x  );
    }

    public boolean add( AnyType x  ) {
        add( size(  ), x  );
        return true;
    }

    private AnyType remove( Node<AnyType> p  ) {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;

        return p.data;
    }

    public AnyType remove( int idx  ) {
        return remove( getNode( idx  )  );
    }

    /**
     *      * Returns a String representation of this collection.
     *           */
    public String toString() {
        StringBuilder sb = new StringBuilder( "[ " );
        for( AnyType x : this  )
            sb.append( x + " "  );
        sb.append( "]"  );
        return new String( sb  );
    }

    /**
     *      * Obtains an Iterator object used to traverse the collection.
     *           * @return an iterator positioned prior to the first element.
     *                */
    public java.util.Iterator<AnyType> iterator(  ) {
        return new LinkedListIterator(  );
    }


    /********* ADD YOUR SOLUTIONS HERE *****************/
    public int indexOf(Object o) {
        // create new instance of iterator
        LinkedListIterator it = (LinkedListIterator) this.iterator();
        int index = 0; // set index count at zero
        AnyType current = it.next(); // set current to next node 
        while(it.hasNext()) { // while loop moves through all nodes in linked list
            if(current.equals(o)) { // if statement checks for element o
                return index; // returns index number of element o in linked list
            }
            current = it.next();  // if element isn't found, set current to next node
            index++; // increment index by one and continue while loop
        }
        return -1; // returns -1 if element o isn't found
    }

    public void reverse() {
        Node<AnyType> temp = beginMarker; // set temp to head
        beginMarker = endMarker; // set head to tail
        endMarker = temp; // set tail to initial head, completing head/tail flip
        Node<AnyType> current = beginMarker; // set current to new head
        while(current != null) { // while loop moves through all nodes
            temp = current.next; // reset temp to next pointer of current
            current.next = current.prev; // set next pointer to prev pointer
            current.prev = temp; // set prev to initial next, completing directional swap
            current = current.next; // move current to next node
        }
    }
    
    // You could reverse the doubly linked list in O(1) time by switching the
    // head (beginMarker) and tail (endMarker) and then just moving through the
    // linked list backwards (i.e., keeping the pointers as they are and using
    // prev for next and vice versa). However, to have a truly reversed linked
    // list, reverse() would have to operate in O(N) time because you would have
    // to iterate through every node in the list to change the pointers.

    public void removeDuplicates() {
        // create Hashset to store visited nodes
        HashSet<AnyType> visited = new HashSet<AnyType>();
        // create new instance of iterator
        LinkedListIterator it = (LinkedListIterator) this.iterator();
        AnyType current = it.next(); // set current to next node
        while(it != null) { //while loop moves through nodes
            if(visited.contains(current)) { // check if HashSet already holds node
                it.remove(); // remove duplicate node from linked list
            }
            else { // else statement applies if HashSet doesn't hold node yet
                visited.add(current); // place node into HashSet
            }

            if(it.hasNext()) { // checks if there are more nodes to visit
                current = it.next(); // moves current to next node
            }
            else { // applies if iterator has reached end of linked list
                it = null; // sets iterator to null to end while loop
            }
        }
    }
    
    // The running time of the algorithm is O(N) because I used a HashSet
    // to store the unique nodes, so the program runs through each node in
    // the linked list once, checks it against the HashSet, and then removes
    // it if it is already present. If the list were sorted, it wouldn't be
    // faster because it would run in O(N) as well. You could implement that
    // algorithm by storing the current node, moving the iterator to the next
    // node, checking to see if they're equal, and storing that node to current,
    // and then continuing once throughout the linked list.

    public void interleave(MyLinkedList<AnyType> other) {
        // creates instance of linked list iterator
        LinkedListIterator it1 = (LinkedListIterator) this.iterator();
        // creates instance of other linked list iterator
        LinkedListIterator it2 = (LinkedListIterator) other.iterator();
        Node<AnyType> p = it1.current; // sets node to current node in it1
        Node<AnyType> n = it1.current; // sets node to current node in it1

        while(it2.hasNext()) { // while loop moves through nodes of other linked list
            if(it1.hasNext()) { // checks if primary linked list has more nodes
                it1.next(); // moves it1 to next node
                n = it1.current; // sets node n to current node
                // creates temp node with data from node in other linked list
                Node<AnyType> temp = new Node<AnyType>(it2.current.data, p, n);
                p.next = temp; // sets next pointer for node p to temp
                it2.next(); // moves it2 to next node
                p = n; // sets node p to node n
            }
            else { // runs if there are no more nodes in primary linked list
                // adds current node in other linked list to primary
                this.add(it2.current.data);
                it2.next(); // moves it2 to next node
            }
        }
    }
    
    // The Big-Oh running time of interleave() is O(N) because it goes through
    // each list only once, inserting the nodes from "other" to the primary
    // linked list as it passes through them both.

    /**
     *      * This is the implementation of the LinkedListIterator.
     *           * It maintains a notion of a current position and of
     *                * course the implicit reference to the MyLinkedList.o
     *                     */
    private class LinkedListIterator implements java.util.Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext(  ) {
            return current != endMarker;
        }

        public AnyType next(  ) {
            if(modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!hasNext())
                throw new java.util.NoSuchElementException();

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if(modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if(!okToRemove)
                throw new IllegalStateException();

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    /**
     * Test the linked list. 
     */
    public static void main( String [ ] args ) {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ )
                lst.add( i );
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

        System.out.println( lst );

    }
}