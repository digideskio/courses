public class TestPrim {

    public static void main(String[] args) {
        Graph graph = null;
        try {
            graph = MapReader.readGraph(args[0], args[1]);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        graph.computeEuclideanCosts();
        Graph minSpanningTree = graph.getMinimumSpanningTree(args[2]);
        minSpanningTree.printAdjacencyList();
        DisplayGraph display = new DisplayGraph(minSpanningTree);
        display.setVisible(true);
    }

}