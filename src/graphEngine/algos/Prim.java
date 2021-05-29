package graphEngine.algos;

import graphEngine.graph.DirectedGraph;
import graphEngine.utils.VertexDistRecord;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.EdgeGraph;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class Prim extends AbstractAlgo implements Runnable {
    private VertexDistRecord[] distTable;
    public int MSTweight = 0;

    @Override
    public void run(){
        // check graph
        if(this.graph instanceof DirectedGraph) return;
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

            String begin = String.valueOf(this.distTable[record.vertex_id].vertex_id);
            String end = String.valueOf(record.vertex_id);
            for(EdgeGraph eg: edgefx){
                if ((eg.s1.name.equals(begin) && eg.s2.name.equals(end)) ||
                        (eg.s1.name.equals(end) && eg.s2.name.equals(begin))){
                    try{
                        FillTransition ft1 = new FillTransition(Duration.millis(100), eg.s1.circle);
                        ft1.setToValue(Color.FORESTGREEN);
                        ft1.play();

                        FillTransition ft2 = new FillTransition(Duration.millis(100), eg.s2.circle);
                        ft2.setToValue(Color.FORESTGREEN);
                        ft2.play();

                        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(100), eg.line);
                        ftEdge.setToValue(Color.ORANGERED);
                        ftEdge.play();
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

            /* Visualization code here */
            System.out.println(this.distTable[record.vertex_id].vertex_id + "---" + record.vertex_id + " = " + record.weight);
        }
        System.out.println("Prim MST weight = " + this.MSTweight);
    }
}
