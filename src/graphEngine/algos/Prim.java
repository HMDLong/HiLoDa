package graphEngine.algos;

import graphEngine.graph.DirectedGraph;
import graphEngine.utils.VertexDistRecord;
import javafx.scene.paint.Color;
import sample.EdgeGraph;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class Prim extends AbstractAlgo implements Runnable {
    private VertexDistRecord[] distTable;

    @Override
    public void init() {
        // check graph
        if (this.graph instanceof DirectedGraph) return;
        this.color = Color.CORAL;
        // adapting
        this.distTable = new VertexDistRecord[graph.getAdjacentMap().size()];

        // initializing
        boolean[] visited = new boolean[this.graph.getAdjacentMap().size()];
        Arrays.fill(visited, false);

        Arrays.fill(distTable, new VertexDistRecord(-1, Integer.MAX_VALUE));
        this.distTable[0] = new VertexDistRecord(0, 0);

        PriorityQueue<VertexDistRecord> queue = new PriorityQueue<>();
        queue.add(new VertexDistRecord(0, 0));

        System.out.println("-----------------------------------------------");
        // start the algorithm
        while (!queue.isEmpty()) {
            VertexDistRecord record = queue.poll();
            visited[record.vertex_id] = true;
            if (this.distTable[record.vertex_id].weight < record.weight) continue;
            for (Map.Entry<Integer, Integer> entry : graph.getAdjacentVertices(record.vertex_id).entrySet()) {
                if (visited[entry.getKey()]) continue;
                if (entry.getValue() < this.distTable[entry.getKey()].weight) {
                    this.distTable[entry.getKey()] = new VertexDistRecord(record.vertex_id, entry.getValue());
                    queue.add(new VertexDistRecord(entry.getKey(), entry.getValue()));
                }
            }
            this.smallWeight += record.weight;

            String begin = String.valueOf(this.distTable[record.vertex_id].vertex_id);
            String end = String.valueOf(record.vertex_id);
            for (EdgeGraph eg : edgefx) {
                if ((eg.s1.name.equals(begin) && eg.s2.name.equals(end)) ||
                        (eg.s1.name.equals(end) && eg.s2.name.equals(begin))) {
                    this.resultEdges.add(eg);
                }
            }
            //System.out.println(this.distTable[record.vertex_id].vertex_id + "---" + record.vertex_id + " = " + record.weight);
        }
    }

    @Override
    public void run() {
        for (EdgeGraph eg: this.resultEdges) {
            try{
                edgeColoring(eg,this.color);
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Prim MST weight = " + this.smallWeight);
    }
}
