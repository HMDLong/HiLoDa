package graphEngine.utils;

/* An internal private class to hold adjacent_vertex_id, edge_weight */
public class VertexDistRecord implements Comparable<VertexDistRecord>{
    public int vertex_id;
    public int weight;

    public VertexDistRecord(int vertex_id, int weight){
        this.vertex_id = vertex_id;
        this.weight = weight;
    }

    public int compareTo(VertexDistRecord rec){
        return this.weight - rec.weight;
    }
}
