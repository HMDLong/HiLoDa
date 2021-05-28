package graphEngine.graph;

import graphEngine.utils.EdgeRecord;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class UndirectedGraph extends TreeMapGraph{
    @Override
    public void addEdge(int start, int end, int weight) {
        this.adjacentMap.get(start).putIfAbsent(end, weight);
        this.adjacentMap.get(end).putIfAbsent(start, weight);
    }

    @Override
    public void removeEdge(int start, int end) {
        this.adjacentMap.get(start).remove(end);
        this.adjacentMap.get(end).remove(start);
    }

    @Override
    public void print(){
        System.out.println("-----------------------------------------------");
        ArrayList<EdgeRecord> edgelist = new ArrayList<>();
        for(Map.Entry<Integer, TreeMap<Integer, Integer>> src : adjacentMap.entrySet())
            for(Map.Entry<Integer, Integer> dest : src.getValue().entrySet()) {
                EdgeRecord edge = new EdgeRecord(src.getKey(), dest.getKey(), dest.getValue());
                if(edgelist.contains(edge)) edge = null;
                else {
                    System.out.println(edge.toString());
                    edgelist.add(edge);
                }

            }
    }
}
