public class Node {

    public char letter;
    public Node[] children;
    public boolean endOfWord;

    public Node(char l) {
        letter = l;
        children = new Node[26];
    }

}