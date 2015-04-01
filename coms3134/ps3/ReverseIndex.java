import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ReverseIndex {
 
    public static Map<Integer,LinkedList<String>> reverse(Map<String,LinkedList<Integer>> map) {
        Map<Integer,LinkedList<String>> reversedMap = new TreeMap<Integer,LinkedList<String>>();
        Iterator<Entry<String, LinkedList<Integer>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, LinkedList<Integer>> pair = (Map.Entry<String, LinkedList<Integer>>)it.next();
            LinkedList<Integer> lines = pair.getValue();
            ListIterator<Integer> listIterator = lines.listIterator();
            while (listIterator.hasNext()) {
                Integer tmp = listIterator.next();
                if(reversedMap.get(tmp) == null) {
                    LinkedList<String> words = new LinkedList<String>();
                    words.add(pair.getKey());
                    reversedMap.put(tmp, words);
                }
                else {
                    LinkedList<String> words = reversedMap.get(tmp);
                    words.add(pair.getKey());
                    reversedMap.put(tmp, words);
                }
            }
        }
        return reversedMap;
    }
    
    public static void printIndex(Map<Integer, LinkedList<String>> map) {
        Iterator<Entry<Integer, LinkedList<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, LinkedList<String>> pair = (Map.Entry<Integer, LinkedList<String>>)it.next();
            LinkedList<String> words = pair.getValue();
            ListIterator<String> listIterator = words.listIterator();
            String content = "";
            while (listIterator.hasNext()) {
                content += listIterator.next() + " ";
            }
            System.out.println(Integer.toString(pair.getKey()) + " " + content);
        }
    }
 
    public static void main(String[] args) {
        String fileName = args[0];
        File f = null;
        try {
            f = new File(fileName);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        CreateIndex create = new CreateIndex();
        create.readFile(f);
        Map<String, LinkedList<Integer>> index = create.getCreateIndex();
        Map<Integer, LinkedList<String>> reversedIndex = reverse(index);
        printIndex(reversedIndex);
    }
}