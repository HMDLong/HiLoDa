package graphEngine.algos;

import graphEngine.graph.DirectedGraph;
import graphEngine.graph.TreeMapGraph;
import graphEngine.utils.EdgeRecord;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Kruskal implements AbstractAlgo{

    class Subsets{
        int parent,rank;
    }

    int find(Subsets[] subsets, int i) {
        // find root and make root as parent of i
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets,subsets[i].parent);
        }
        return subsets[i].parent;
    }

    // A function that does union of two sets
    void Union(Subsets[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            (subsets[xroot].rank)++;
            //same rank --> take 1 as root --> increase rank.
        }
    }

    @Override
    public void run(TreeMapGraph graph){
        // graph check
        if(graph instanceof DirectedGraph) return;
        // initialize the parent tables: using Disjoint-set / Set-Union algo to detect cycle
        Subsets[] subsets = new Subsets[graph.getVerticesSize()];
        // initialize the edges priority queue
        PriorityQueue<EdgeRecord> queue = new PriorityQueue<>();
        for(Map.Entry<Integer, TreeMap<Integer, Integer>> src : graph.getAdjacentMap().entrySet())
            for(Map.Entry<Integer, Integer> dest : src.getValue().entrySet()){
                EdgeRecord edge = new EdgeRecord(src.getKey(), dest.getKey(), dest.getValue());
                if(queue.contains(edge)) edge = null;
                else queue.add(edge);
            }
        // Create V subsets with single elements
        for (int v = 0; v < graph.getVerticesSize(); ++v) {
            subsets[v] = new Subsets(); //need to be init
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        // start algorithm
        int MSTweight = 0;
        for (int i=0; i < graph.getVerticesSize();i++) {
            EdgeRecord next_edge = queue.poll();
            int x = find(subsets, next_edge.v1);
            int y = find(subsets, next_edge.v2);

            //No cycle --> add
            if (x != y) {
                Union(subsets, x, y);
                MSTweight += next_edge.weight;

                /* Visualization code here */
                System.out.printf("Edge %d:(%d, %d) cost:%d \n", i, next_edge.v1, next_edge.v2, next_edge.weight);
            }
        }
        // print MSTweight
        System.out.printf("Kruskal MSTweight = %d", MSTweight);
    }
}

