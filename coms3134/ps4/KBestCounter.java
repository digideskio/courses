import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<T>> {
 
    private int k;
    private PriorityQueue<T> kBest;
 
    public KBestCounter(int chosenK) {
        k = chosenK;
        kBest = new PriorityQueue<T>(k);
    }
 
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