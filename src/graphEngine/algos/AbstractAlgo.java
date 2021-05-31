package graphEngine.algos;

import graphEngine.graph.TreeMapGraph;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.EdgeGraph;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAlgo {
    protected TreeMapGraph graph;
    protected List<EdgeGraph> edgefx;

    public void setGraph(TreeMapGraph graph) {
        this.graph = graph;
    }

    public void setEdgefx(List<EdgeGraph> edgefx) {
        this.edgefx = edgefx;
    }

    public abstract List<EdgeGraph> init();

    public abstract String resultToString();

    // Abstract class implements Interface --> DONT'T NEED OVERRIDE run() method

}
