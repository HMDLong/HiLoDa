package graphEngine.context;
import graphEngine.algos.AbstractAlgo;
import graphEngine.graph.TreeMapGraph;
import sample.EdgeGraph;

import java.util.List;

public class Context {
    public TreeMapGraph graph;
    public AbstractAlgo algo;
    public List<EdgeGraph> edgefx;

    public void setGraph(TreeMapGraph graph){
        this.graph = graph;
    }

    public void setAlgo(AbstractAlgo algo) {
        this.algo = algo;
    }

    public void execute(){
        algo.setGraph(this.graph);
        algo.setEdgefx(this.edgefx);
        Thread t = new Thread(algo);
        t.start();
    }
}
