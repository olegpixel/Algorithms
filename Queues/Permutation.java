import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();


        // Deque<Integer> deque = new Deque<Integer>();
        // deque.addFirst(1);
        // deque.addLast(2);
        // deque.addLast(3);
        // deque.removeLast();
        //
        // for (d: deque) {
        //
        // }
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        int count = Integer.parseInt(args[0]);
        while (count > 0) {
            StdOut.println(rq.dequeue());
            count--;
        }
    }
}
