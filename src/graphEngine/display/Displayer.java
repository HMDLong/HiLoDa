package graphEngine.display;

import graphEngine.algos.AbstractAlgo;
import javafx.animation.FillTransition;
import javafx.animation.StrokeTransition;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.EdgeGraph;
import java.util.List;

public abstract class Displayer implements Runnable{
    protected List<EdgeGraph> resultEdges;
    //protected List<EdgeGraph> edgefx;
    protected int count;
    protected Color color;
    public AbstractAlgo algo;

    public void setCount(int new_count){
        this.count = new_count;
    }

    public int getCount(){
        return this.count;
    }

    public void setAlgo(AbstractAlgo algo){
        this.algo = algo;
    }

    public AbstractAlgo getAlgo(){
        return this.algo;
    }

    public List<EdgeGraph> getResultEdges(){
        return this.resultEdges;
    }

    public void setEdgefx(List<EdgeGraph> edgefx) {
        this.algo.setEdgefx(edgefx);
    }

    /*
    public List<EdgeGraph> getEdgefx() {
        return this.edgefx;
    }
     */

    public void setColor(Color color){
        this.color = color;
    }

    public void showResult(){
        Alert result_dialog = new Alert(Alert.AlertType.INFORMATION);
        result_dialog.setTitle("RESULT");
        result_dialog.setHeaderText("");
        result_dialog.setContentText(algo.resultToString());
        result_dialog.show();
    }

    public void edgeColoring(EdgeGraph eg){
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

    public static void resetEdgeColor(EdgeGraph eg){
        FillTransition ft1 = new FillTransition(Duration.millis(100), eg.s1.circle);
        ft1.setToValue(Color.BLACK);
        ft1.play();

        FillTransition ft2 = new FillTransition(Duration.millis(100), eg.s2.circle);
        ft2.setToValue(Color.BLACK);
        ft2.play();

        StrokeTransition ftEdge = new StrokeTransition(Duration.millis(100), eg.line);
        ftEdge.setToValue(Color.BLACK);
        ftEdge.play();
    }

    public void calculate(){
        this.resultEdges = this.algo.init();
    }

    public abstract void drawStep();
}
