import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;

    public Deque()                           // construct an empty deque
    {
        first = null;
        last = null;
        size = 0;
    }

    private class Node {
        private Item item;
        private Node next = null;
        private Node prev = null;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return size == 0;
    }

    public int size()                        // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if (oldFirst != null) {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        first.prev = null;
        size++;
        if (last == null) {
            last = first;
        }
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null)
            throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast != null) {
            last.prev = oldLast;
            oldLast.next = last;
        }
        last.next = null;
        size++;
        if (first == null) {
            first = last;
        }
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        // first.prev = null;
        size--;
        if (size == 0) {
            last = null;
            first = null;
        }
        return item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        // last.next = null;
        size--;
        if (size == 0) {
            last = null;
            first = null;
        }
        return item;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node currect = first;

        public boolean hasNext() {
            return currect != null;
        }

        public Item next() {
            if (currect == null)
                throw new java.util.NoSuchElementException();
            Item item = currect.item;
            currect = currect.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }


    public static void main(String[] args)   // unit testing (optional)
    {
        // StdOut.println("mean = ");
        // Deque d = new Deque();
        // d.addFirst(1);
        // StdOut.println(d.size());
    }
}
