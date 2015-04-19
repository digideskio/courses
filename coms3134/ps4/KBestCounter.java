import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<T>> {
 
    private int k;
    private PriorityQueue<T> kBest;
 
    // The constructor takes the user's chosen value for k and sets the
    // static variable k to that value, then initializes the PriorityQueue
    // (min heap) with initial quantity k.
    public KBestCounter(int chosenK) {
        k = chosenK;
        kBest = new PriorityQueue<T>(k);
    }
 
    // The count() method takes an object x and first checks if kBest has
    // been filled. If so, it compares x to the head (the minimum value
    // in kBest). If x is larger, then the head is removed and x is added.
    // If kBest has fewer than k values, then x is added directly.
    public void count(T x) {
        if(kBest.size() == k) {
            T head = kBest.peek();
            if(x.compareTo(head) > 0) {
                kBest.poll();
                kBest.add(x);
            }
        }
        else
            kBest.add(x);
    }
 
    // The kbest() method converts kBest at the time it is called to a List
    // (in this case, a LinkedList). A copy of kBest is created so that the
    // original is unmodified. Then, the LinkedList is initialized. The
    // copy of kBest (tmp) is used to fill the LinkedList with values from
    // greatest to least by calling poll(), which removes the head (min
    // value), and adding that value to the front of the LinkedList.
    public List<T> kbest() {
        PriorityQueue<T> tmp = new PriorityQueue<T>(k);
        Iterator<T> it = kBest.iterator();
        while(it.hasNext())
            tmp.add(it.next());
        List<T> kBestList = new LinkedList<T>();
        for(int i = 0; i < kBest.size(); i++) {
            T min = tmp.poll();
            kBestList.add(0, min);
        }
        return kBestList;
    }
 
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        for(int i = 1000; i > 0; i--) {
            list.add(i);
        }
        Iterator<Integer> s = list.iterator();
        int k = 10;
        KBestCounter<Integer> counter = new KBestCounter<>(k);
        for(int i = 0; i < 100; i++) {
            counter.count(s.next());
        }
        List<Integer> kbest = counter.kbest();
        System.out.println(kbest);
        for (int i = 0; i < 100; i++) {
            counter.count(s.next());
        }
        kbest = counter.kbest();
        System.out.println(kbest);
    }
}