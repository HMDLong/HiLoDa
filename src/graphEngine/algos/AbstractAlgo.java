package graphEngine.algos;

import graphEngine.graph.TreeMapGraph;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.EdgeGraph;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAlgo implements Runnable{
    protected TreeMapGraph graph;
    protected List<EdgeGraph> edgefx;
    protected List<EdgeGraph> resultEdges = new ArrayList<>();
    protected int smallWeight = 0;
    protected Color color;

    public void setGraph(TreeMapGraph graph) {
        this.graph = graph;
    }

    public void setEdgefx(List<EdgeGraph> edgefx) {
        this.edgefx = edgefx;
    }

    public List<EdgeGraph> getResultEdges() {
        return resultEdges;
    }

    public Color getColor() { return this.color; }

    public static void edgeColoring(EdgeGraph eg, Color color){
        FillTransition ft1 = new FillTransition(Duration.millis(100), eg.s1.circle);
        ft1.setToValue(color);
        ft1.play();

        FillTransition ft2 = new FillTransition(Duration.millis(100), eg.s2.circle);
        ft2.setToValue(color);
        ft2.play();

        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(100), eg.line);
        ftEdge.setToValue(color);
        ftEdge.play();
    }

    public abstract void init();



    // Abstract class implements Interface --> DONT'T NEED OVERRIDE run() method

}
