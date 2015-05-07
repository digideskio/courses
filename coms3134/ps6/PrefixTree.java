import java.util.LinkedList;

public class PrefixTree {

    public Node root;

    public PrefixTree() {
        root = new Node('0');
    }

    public PrefixTree(Node n) {
        root = n;
    }

    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null) {
                cur.children[word.charAt(i) - 'a'] = new Node(word.charAt(i));
                cur.children[word.charAt(i) - 'a'].endOfWord = false;
            }
            cur = cur.children[word.charAt(i) - 'a'];
        }
        cur.endOfWord = true;
    }

    public PrefixTree findPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (cur.children[prefix.charAt(i) - 'a'] == null)
                return null;
            cur = cur.children[prefix.charAt(i) - 'a'];
        }
        PrefixTree subtree = new PrefixTree(cur);
        return subtree;
    }

    public String getPrefix(String word) {
        Node cur = root;
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null)
                return prefix.toString();
            cur = cur.children[word.charAt(i) - 'a'];
            prefix.append(cur.letter);
        }
        return prefix.toString();
    }

    public LinkedList<String> buildList(String prefix) {
        PrefixTree subtree = findPrefix(prefix);
        LinkedList<String> words = new LinkedList<String>();
        if (subtree.root.endOfWord == true)
            words.add(prefix);
        for (Node next : subtree.root.children) {
            if (next != null)
                traverse(words, prefix, next);
        }
        return words;
    }

    public void traverse(LinkedList<String> words, String prefix, Node cur) {
        prefix += cur.letter;
        if (cur.endOfWord == true)
            words.add(prefix);
        for (Node next : cur.children) {
            if (next != null)
                traverse(words, prefix, next);
        }
    }

    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            if (cur.children[word.charAt(i) - 'a'] == null)
                return false;
            cur = cur.children[word.charAt(i) - 'a'];
        }
        return cur.endOfWord;
    }

    public static void main(String[] args) {
        PrefixTree test = new PrefixTree();
        test.addWord("blink");
        test.addWord("blue");
        test.addWord("black");
        test.addWord("bill");
        Node cur = test.root;
        for (int i = 0; i < cur.children.length; i++) {
            if (cur.children[i] != null) {
                System.out.println(cur.children[i].letter);
                for (int j = 0; j < cur.children[i].children.length; j++) {
                    if (cur.children[i].children[j] != null)
                        System.out.println(cur.children[i].children[j].letter);
                }
            }
        }
        LinkedList<String> list = test.buildList("b");
        System.out.println(list.toString());
    }
}