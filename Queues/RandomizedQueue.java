import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    public RandomizedQueue()                 // construct an empty randomized queue
    {

        queue = (Item[]) new Object[2];
        size = 0;
    }

    public boolean isEmpty()                 // is the randomized queue empty?
    {
        return size == 0;
    }

    public int size()                        // return the number of items on the randomized queue
    {
        return size;
    }

    private void resize(int cap) {
        Item[] copy = (Item[]) new Object[cap];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;

    }

    private void checkSize() {
        if (queue.length <= size + 1) {
            resize(queue.length * 2);
        }
        else if (queue.length / 4 >= size) {
            resize(queue.length / 2);
        }
    }

    public void enqueue(Item item)           // add the item
    {
        if (item == null)
            throw new IllegalArgumentException();
        checkSize();
        queue[size] = item;
        size++;
    }

    private int randomIndex() {
        return StdRandom.uniform(0, size);
    }


    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        int rnd = randomIndex();
        Item item = queue[rnd];
        queue[rnd] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        return item;
    }

    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        return queue[randomIndex()];
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        final private Item[] randQueue;
        private int rSize;

        public RandomQueueIterator() {
            randQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                randQueue[i] = queue[i];
            }
            StdRandom.shuffle(randQueue);
        }

        public boolean hasNext() {
            return rSize < size;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();
            return randQueue[rSize++];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

}