package graphEngine.algos;

import graphEngine.graph.TreeMapGraph;
import graphEngine.utils.VertexDistRecord;
import sample.EdgeGraph;

import java.util.*;

public class Dijsktra extends AbstractAlgo {
    private VertexDistRecord[] distTable;
    public int start_vertex;

    /** Methods **/
    public int getDistance(int id){
        return this.distTable[id].weight;
    }

    public VertexDistRecord[] getDistTable(){
        return this.distTable;
    }

    @Override
    public void run(){
        //TreeMapGraph graph, List<EdgeGraph> edgefx
        /* prompt for start_vertex here */
        this.start_vertex = 0;

        // adapting
        this.distTable = new VertexDistRecord[graph.getAdjacentMap().size()];

        // initializing
        boolean[] visited = new boolean[graph.getAdjacentMap().size()];
        Arrays.fill(visited, false);

        Arrays.fill(distTable, new VertexDistRecord(-1, Integer.MAX_VALUE));
        this.distTable[this.start_vertex] = new VertexDistRecord(start_vertex, 0);

        PriorityQueue<VertexDistRecord> queue = new PriorityQueue<>();
        queue.add(new VertexDistRecord(start_vertex, 0));

        // start the algorithm
        int tempTotalDistance;
        while(!queue.isEmpty()){
            VertexDistRecord record = queue.poll();
            visited[record.vertex_id] = true;
            if(this.distTable[record.vertex_id].weight < record.weight) continue;
            for(Map.Entry<Integer, Integer> entry : graph.getAdjacentVertices(record.vertex_id).entrySet()){
                if(visited[entry.getKey()]) continue;
                tempTotalDistance = this.distTable[record.vertex_id].weight + entry.getValue();
                if(tempTotalDistance < this.distTable[entry.getKey()].weight){
                    this.distTable[entry.getKey()] = new VertexDistRecord(record.vertex_id, tempTotalDistance);
                    queue.add(new VertexDistRecord(entry.getKey(), tempTotalDistance));
                }
            }

            /* Visualization code here */
            System.out.println(this.distTable[record.vertex_id].vertex_id + "--->" + record.vertex_id + " = " + record.weight);
        }
    }

    public ArrayList<Integer> getPath(int end_vertex){
        if(this.distTable[end_vertex].vertex_id == -1)
            return null;
        ArrayList<Integer> path = new ArrayList<>();
        int temp = end_vertex;
        do{
            path.add(temp);
            temp = this.distTable[temp].vertex_id;
        }while(temp != start_vertex);
        path.add(start_vertex);
        return path;
    }

    public void printPaths(){
        System.out.printf("From %d to", this.start_vertex);
        for(int end_vertex = 0; end_vertex < this.distTable.length; end_vertex++)
            if(end_vertex != this.start_vertex) {
                ArrayList<Integer> path = getPath(end_vertex);
                if (path == null)
                    System.out.println(end_vertex + ": Unreachable");
                else {
                    StringBuilder path_to_string = new StringBuilder(end_vertex + ": " + path.get(path.size() - 1));
                    for (int i = path.size() - 2; i >= 0; i--)
                        path_to_string.append("--->").append(path.get(i));
                    path_to_string.append(" = ").append(this.distTable[end_vertex].weight);
                    System.out.println(path_to_string);
                }
            }
    }
}
