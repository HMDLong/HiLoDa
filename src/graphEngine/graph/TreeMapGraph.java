package graphEngine.graph;

import java.util.Map;
import java.util.TreeMap;

public abstract class TreeMapGraph implements AbstractGraph{
    protected TreeMap<Integer, TreeMap<Integer, Integer>> adjacentMap;

    public TreeMapGraph(){
        this.adjacentMap = new TreeMap<>();
    }

    public TreeMap<Integer, TreeMap<Integer, Integer>> getAdjacentMap(){
        return this.adjacentMap;
    }

    public void addVertex(int id){
        this.adjacentMap.putIfAbsent(id, new TreeMap<>());
    }

    public void removeVertex(int id){
        this.adjacentMap.remove(id);
        for(Map.Entry<Integer, TreeMap<Integer, Integer>> entry : this.adjacentMap.entrySet())
            entry.getValue().remove(id);
    }

    public TreeMap<Integer, Integer> getAdjacentVertices(int id){
        return this.adjacentMap.get(id);
    }

    public int getVerticesSize(){
        return this.adjacentMap.size();
    }

    public abstract void print();
}
