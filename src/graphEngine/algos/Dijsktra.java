package graphEngine.algos;

import graphEngine.utils.VertexDistRecord;
import sample.EdgeGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import java.util.PriorityQueue;
import graphEngine.utils.Prompter;

public class Dijsktra extends AbstractAlgo {
    private VertexDistRecord[] distTable;
    private int start_vertex;
    private int end_vertex;

    /** Methods **/
    public int getDistance(int id){
        return this.distTable[id].weight;
    }

    public VertexDistRecord[] getDistTable(){
        return this.distTable;
    }

    public void setStart(int start_id){
        this.start_vertex = start_id;
    }

    public void setEnd(int end_id){
        this.end_vertex = end_id;
    }

    @Override
    public List<EdgeGraph> init(){
        List<EdgeGraph> resultEdges = new ArrayList<>();
        // prompt for start_vertex
        int start_id;
        while(true){
            start_id = Prompter.askInt("Enter start id", "Dijsktra", null);
            if(start_id < 0)
                break;
            if(this.graph.getAdjacentMap().containsKey(start_id))
                break;
        }
        if(start_id < 0) return null;
        this.start_vertex = start_id;
        // prompt for end_vertex
        int end_id = Prompter.askInt("Enter end id", "Dijsktra", "-1");
        this.end_vertex = end_id;

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

            String begin = String.valueOf(record.vertex_id);
            String end = String.valueOf(this.distTable[record.vertex_id].vertex_id);
            for(EdgeGraph eg: this.edgefx) {
                if ((eg.s1.name.equals(begin) && eg.s2.name.equals(end)) ||
                        (eg.s1.name.equals(end) && eg.s2.name.equals(begin))) {
                    resultEdges.add(eg);
                }
            }

            if(record.vertex_id == this.end_vertex)
                return resultEdges;
            /* Visualization code here */
            //System.out.println(this.distTable[record.vertex_id].vertex_id + "--->" + record.vertex_id + " = " + record.weight);
        }
        return resultEdges;
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

    // Get the final result string here
    @Override
    public String resultToString(){
        StringBuilder path_to_string = new StringBuilder(String.format("From %d to\n", this.start_vertex));
        for(int end_vertex = 0; end_vertex < this.distTable.length; end_vertex++)
            if(end_vertex != this.start_vertex) {
                ArrayList<Integer> path = getPath(end_vertex);
                if (path == null)
                    System.out.println(end_vertex + ": Unreachable");
                else {
                    path_to_string.append(end_vertex + ": " + path.get(path.size() - 1));
                    for (int i = path.size() - 2; i >= 0; i--)
                        path_to_string.append("--->").append(path.get(i));
                    path_to_string.append(" = ").append(this.distTable[end_vertex].weight).append("\n");
                }
            }
        return path_to_string.toString();
    }
}
