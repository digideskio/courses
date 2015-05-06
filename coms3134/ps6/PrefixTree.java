public class PrefixTree {

    Node root;

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
        Node root = new Node('0');
        PrefixTree test = new PrefixTree(root);
        test.addWord("do");
        test.addWord("doctor");
        test.addWord("dance");
        test.addWord("doll");
        test.addWord("dog");
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
        if (test.contains("dance"))
            System.out.println("That word is here!");
    }

}