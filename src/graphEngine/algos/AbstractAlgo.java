package graphEngine.algos;

import graphEngine.graph.TreeMapGraph;
import sample.EdgeGraph;
import java.util.List;

public abstract class AbstractAlgo implements Runnable{
    protected TreeMapGraph graph;
    protected List<EdgeGraph> edgefx;

    public void setGraph(TreeMapGraph graph) {
        this.graph = graph;
    }

    public void setEdgefx(List<EdgeGraph> edgefx) {
        this.edgefx = edgefx;
    }

    //void run(TreeMapGraph graph, List<EdgeGraph> edgefx);
    // Abstract class implements Interface --> DONT'T NEED OVERRIDE run() method

}
