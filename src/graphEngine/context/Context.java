package graphEngine.context;

import graphEngine.algos.AbstractAlgo;
import graphEngine.algos.Prim;
import graphEngine.graph.TreeMapGraph;
import sample.EdgeGraph;

import java.util.List;

public class Context {
    public TreeMapGraph graph;
    public AbstractAlgo algo;

    public void setGraph(TreeMapGraph graph){
        this.graph = graph;
    }

    public void setAlgo(AbstractAlgo algo) {
        this.algo = algo;
    }

    public void execute(List<EdgeGraph> edgefx){
       this.algo.run(this.graph, edgefx);
    }
}
