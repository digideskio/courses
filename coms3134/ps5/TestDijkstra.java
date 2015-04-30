public class TestDijkstra {

    public static void main(String[] args) {
        Graph graph = null;
        try {
            graph = MapReader.readGraph(args[0], args[1]);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        graph.computeEuclideanCosts();
        Graph shortestPathGraph = graph.getWeightedShortestPath(args[2], args[3]);
        shortestPathGraph.printAdjacencyList();
        DisplayGraph display = new DisplayGraph(shortestPathGraph);
        display.setVisible(true);
    }

}

// One example of a city pair for which the path found by BFS is different than
// the path found by Dijkstra's is Oklahoma and Seattle. BFS finds a path from
// Seattle to Helena, then Denver, then Kansas City, then Oklahoma City.
// Dijkstra's, on the other hand, goes from Seattle to Helena to Denver to
// Santa Fe, then Oklahoma City. The unweighted paths are the same length, but
// Dijkstra's finds a path that's 5.5 units shorter when it takes the edge
// weights into account.