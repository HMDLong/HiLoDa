package graphEngine.algos;

import graphEngine.graph.DirectedGraph;
import graphEngine.graph.TreeMapGraph;
import graphEngine.utils.VertexDistRecord;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class Prim implements AbstractAlgo {
    private VertexDistRecord[] distTable;
    public int MSTweight = 0;

    @Override
    public void run(TreeMapGraph graph){
        // check graph
        if(graph instanceof DirectedGraph) return;
        // adapting
        this.distTable = new VertexDistRecord[graph.getAdjacentMap().size()];

        // initializing
        boolean[] visited = new boolean[graph.getAdjacentMap().size()];
        Arrays.fill(visited, false);

        Arrays.fill(distTable, new VertexDistRecord(-1, Integer.MAX_VALUE));
        this.distTable[0] = new VertexDistRecord(0, 0);

        PriorityQueue<VertexDistRecord> queue = new PriorityQueue<>();
        queue.add(new VertexDistRecord(0, 0));

        // start the algorithm
        while(!queue.isEmpty()){
            VertexDistRecord record = queue.poll();
            visited[record.vertex_id] = true;
            if(this.distTable[record.vertex_id].weight < record.weight) continue;
            for(Map.Entry<Integer, Integer> entry : graph.getAdjacentVertices(record.vertex_id).entrySet()){
                if(visited[entry.getKey()]) continue;
                if(entry.getValue() < this.distTable[entry.getKey()].weight){
                    this.distTable[entry.getKey()] = new VertexDistRecord(record.vertex_id, entry.getValue());
                    queue.add(new VertexDistRecord(entry.getKey(), entry.getValue()));
                }
            }
            MSTweight += record.weight;

            /* Visualization code here */
            System.out.println(this.distTable[record.vertex_id].vertex_id + "---" + record.vertex_id + " = " + record.weight);
        }
        System.out.println("Prim MST weight = " + this.MSTweight);
    }
}
