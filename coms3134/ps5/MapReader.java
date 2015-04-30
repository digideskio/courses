import java.io.BufferedReader;
import java.io.FileReader;

public class MapReader {

    public static Graph readGraph(String vertexfile, String edgefile) {
        Graph g = new Graph();
        addVertexFile(g, vertexfile);
        addEdgeFile(g, edgefile);
        return g;
    }

    private static void addVertexFile(Graph g, String vertexfile) {
        String line;
        try {
            BufferedReader b = new BufferedReader(new FileReader(vertexfile));
            while ((line = b.readLine()) != null) {
                String[] city = line.split(",");
                Vertex v = new Vertex(city[0], Integer.parseInt(city[1]),
                    Integer.parseInt(city[2]));
                g.addVertex(v);
            }
            b.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void addEdgeFile(Graph g, String edgefile) {
        String line;
        try {
            BufferedReader b = new BufferedReader(new FileReader(edgefile));
            while ((line = b.readLine()) != null) {
                String[] connection = line.split(",");		        
                g.addUndirectedEdge(connection[0], connection[1], 1.0);
            }
            b.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Graph graph = null;
        try {
            graph = readGraph(args[0], args[1]);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        graph.computeEuclideanCosts();
        graph.printAdjacencyList();
        DisplayGraph display = new DisplayGraph(graph);
        display.setVisible(true);
    }
}