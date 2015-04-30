public class TestBfs {

    public static void main(String[] args) {
        Graph graph = null;
        try {
            graph = MapReader.readGraph(args[0], args[1]);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        Graph shortestPathGraph = graph.getUnweightedShortestPath(args[2], args[3]);
        shortestPathGraph.printAdjacencyList();
        DisplayGraph display = new DisplayGraph(shortestPathGraph);
        display.setVisible(true);
    }

}