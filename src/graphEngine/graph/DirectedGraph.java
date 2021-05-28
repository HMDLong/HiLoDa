package graphEngine.graph;

import java.util.Map;
import java.util.TreeMap;

public class DirectedGraph extends TreeMapGraph{
    @Override
    public void addEdge(int start, int end, int weight){
        this.adjacentMap.get(start).putIfAbsent(end, weight);
    }

    @Override
    public void removeEdge(int start, int end){
        this.adjacentMap.get(start).remove(end);
    }

    @Override
    public void print(){
        System.out.println("-----------------------------------------------");
        for(Map.Entry<Integer, TreeMap<Integer, Integer>> src : adjacentMap.entrySet())
            for(Map.Entry<Integer, Integer> dest : src.getValue().entrySet())
                System.out.println(src.getKey() + "--->" + dest.getKey() + "=" + dest.getValue());
    }
}
