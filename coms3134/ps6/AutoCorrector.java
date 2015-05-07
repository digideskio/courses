import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class AutoCorrector {

    private PrefixTree dictionary;

    AutoCorrector(String dictionaryFile) {
        dictionary = buildTree(dictionaryFile);
    }

    private PrefixTree buildTree(String dictionaryFile) {
        PrefixTree dictionary = new PrefixTree();
        String word;
        try {
            BufferedReader b = new BufferedReader(new FileReader(dictionaryFile));
            while ((word = b.readLine()) != null) {
                dictionary.addWord(word);
            }
            b.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public String correct(String word) {
        if (dictionary.contains(word))
            return word;
        String prefix = dictionary.getPrefix(word);
        LinkedList<String> words = dictionary.buildList(prefix);
        ListIterator<String> it = words.listIterator();
        String correctWord = null;
        int minDistance = Integer.MAX_VALUE;
        while (it.hasNext()) {
            String suggestion = it.next();
            int diff = EditDistance.editDistance(word, suggestion);
            if (diff < minDistance) {
                minDistance = diff;
                correctWord = suggestion;
            }
        }
        return correctWord;
    }

    public static void main(String[] args) {
        AutoCorrector ac = null;
        try {
            ac = new AutoCorrector(args[0]);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Scanner s = new Scanner(System.in);
        String word = null;
        while (s.hasNext()) {
            word = s.nextLine();
            String correction = ac.correct(word);
            if (correction != null)
                System.out.println(correction);
            else
                System.out.println("Unknown word.");
        }
        s.close();
    }
}