import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.TreeMap;

public class ReverseIndex {

    public static Map<String, LinkedList<Integer>> readFile(File f) {
        Scanner fileInput = null;
        Map<String, LinkedList<Integer>> index = new HashMap<String, LinkedList<Integer>>();
        try {
            fileInput = new Scanner(new FileReader(f));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        int lineNumber = 0;
        while(fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            String[] words = line.split(" ");
            for(String tmp : words) {
                if(index.get(tmp) == null) {
                    LinkedList<Integer> lines = new LinkedList<Integer>();
                    lines.add(lineNumber);
                    index.put(tmp, lines);
                }
                else {
                    LinkedList<Integer> lines = index.get(tmp);
                    lines.add(lineNumber);
                    index.put(tmp, lines);
                }
            }
            lineNumber++;
        }
        fileInput.close();
        return index;
    }
    
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
    
    public static void main(String[] args) {
        String fileName = args[0];
        try {
            File f = new File(fileName);
            Map<String, LinkedList<Integer>> index = readFile(f);
            Map<Integer, LinkedList<String>> reversedIndex = reverse(index);
            Iterator<Entry<Integer, LinkedList<String>>> it = reversedIndex.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, LinkedList<String>> pair = (Map.Entry<Integer, LinkedList<String>>)it.next();
                LinkedList<String> words = pair.getValue();
                ListIterator<String> listIterator = words.listIterator();
                String content = "";
                while (listIterator.hasNext()) {
                    content += listIterator.next() + " ";
                }
                System.out.println(Integer.toString(pair.getKey()) + " " + content);
                it.remove();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}