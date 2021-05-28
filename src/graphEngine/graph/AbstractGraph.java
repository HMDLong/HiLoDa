package graphEngine.graph;

public interface AbstractGraph {
    void addVertex(int id);
    void removeVertex(int id);
    void addEdge(int start, int end, int weight);
    void removeEdge(int start, int end);
}
