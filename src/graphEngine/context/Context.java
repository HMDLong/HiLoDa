package graphEngine.context;

import graphEngine.algos.AbstractAlgo;
import graphEngine.graph.TreeMapGraph;

public class Context {
    public TreeMapGraph graph;
    public AbstractAlgo algo;

    public void setGraph(TreeMapGraph graph){
        this.graph = graph;
    }

    public void setAlgo(AbstractAlgo algo) {
        this.algo = algo;
    }

    public void execute(){
        this.algo.run(this.graph);
    }
}
