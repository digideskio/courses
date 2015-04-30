import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Graph {

  // Keep a fast index to nodes in the map
  protected Map<String, Vertex> vertices;

  /**
   * Construct an empty Graph.
   */
  public Graph() {
    vertices = new HashMap<String, Vertex>();
  }

  public void addVertex(String name) {
    Vertex v = new Vertex(name);
    addVertex(v);
  }

  public void addVertex(Vertex v) {
    if (vertices.containsKey(v.name))
      throw new IllegalArgumentException(
          "Cannot create new vertex with existing name.");
    vertices.put(v.name, v);
  }

  public Collection<Vertex> getVertices() {
    return vertices.values();
  }

  public Vertex getVertex(String s) {
    return vertices.get(s);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          the source vertex.
   * @param w
   *          the target vertex.
   */
  public void addEdge(String nameU, String nameV, Double cost) {
    if (!vertices.containsKey(nameU))
      addVertex(nameU);
    if (!vertices.containsKey(nameV))
      addVertex(nameV);
    Vertex sourceVertex = vertices.get(nameU);
    Vertex targetVertex = vertices.get(nameV);
    Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
    sourceVertex.addEdge(newEdge);
  }

  /**
   * Add a new edge from u to v. Create new nodes if these nodes don't exist
   * yet. This method permits adding multiple edges between the same nodes.
   * 
   * @param u
   *          unique name of the first vertex.
   * @param w
   *          unique name of the second vertex.
   */
  public void addEdge(String nameU, String nameV) {
    addEdge(nameU, nameV, 1.0);
  }
  
  public void addUndirectedEdge(String s, String t, Double cost) {
    if (!vertices.containsKey(s))
      addVertex(s);
    if (!vertices.containsKey(t))
      addVertex(t);
    Vertex firstVertex = vertices.get(s);
    Vertex secondVertex = vertices.get(t);
    Edge firstEdge = new Edge(firstVertex, secondVertex, cost);
    Edge secondEdge = new Edge(secondVertex, firstVertex, cost);
    firstVertex.addEdge(firstEdge);
	secondVertex.addEdge(secondEdge);
  }
  
  public void computeEuclideanCosts() {
    for (String u : vertices.keySet()) {
      for (Edge e : vertices.get(u).getEdges()) {
        int startX = vertices.get(u).posX;
        int startY = vertices.get(u).posY;
        int endX = vertices.get(e.targetVertex.name).posX;
        int endY = vertices.get(e.targetVertex.name).posY;
        e.cost = Math.sqrt(Math.pow((startX - endX), 2) +
            Math.pow((startY - endY), 2));
      }
    }
  }
  
  public void doBfs(String s) {
    Queue<Vertex> q = new LinkedList<>();
    Vertex start = vertices.get(s);
    q.add(start);
    start.distance = 0;
    start.flag = true;
    while (q.peek() != null) {
      Vertex u = q.poll();
      for (Edge e : u.getEdges()) {
        Vertex v = e.targetVertex;
        if (v.distance == Double.POSITIVE_INFINITY) {
          v.distance = u.distance + 1;
          v.flag = true;
          v.prev = u;
          q.add(v);
        }
      }
    }
  }

  public Graph getUnweightedShortestPath(String s, String t) {
    doBfs(s);
    Graph graph = new Graph();
    for (String u : vertices.keySet()) {
      Vertex copy = new Vertex(u, getVertex(u).posX, getVertex(u).posY);
      graph.addVertex(copy);
    }
    Vertex source = getVertex(s);
    Vertex target = getVertex(t);
    while (target != source) {
      graph.addEdge(target.name, target.prev.name);
      target = target.prev;
    }
    return graph;
  }

  /**
     * 
     */
  public void printAdjacencyList() {
    for (String u : vertices.keySet()) {
      StringBuilder sb = new StringBuilder();
      sb.append(u);
      sb.append(" -> [ ");
      for (Edge e : vertices.get(u).getEdges()) {
        sb.append(e.targetVertex.name);
        sb.append("(");
        sb.append(e.cost);
        sb.append(") ");
      }
      sb.append("]");
      System.out.println(sb.toString());
    }
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    g.addVertex(new Vertex("v0", 0, 0));
    g.addVertex(new Vertex("v1", 0, 1));
    g.addVertex(new Vertex("v2", 1, 0));
    g.addVertex(new Vertex("v3", 1, 1));

    g.addEdge("v0", "v1");
    g.addEdge("v1", "v2");
    g.addEdge("v2", "v3");
    g.addEdge("v3", "v0");
    g.addEdge("v0", "v2");
    g.addEdge("v1", "v3");

    g.printAdjacencyList();

    DisplayGraph display = new DisplayGraph(g);
    display.setVisible(true);
  }

}