package graphEngine.context;
import graphEngine.algos.AbstractAlgo;
import graphEngine.display.Displayer;
import graphEngine.graph.TreeMapGraph;
import javafx.scene.control.Alert;
import sample.EdgeGraph;
import graphEngine.factory.DisplayerFactory;

import java.util.List;

public class Context {
    private TreeMapGraph graph;
    private List<EdgeGraph> edgefx;
    private Displayer displayer;

    public void setGraph(TreeMapGraph graph){
        this.graph = graph;
    }
    public void setCount(int count) { this.displayer.setCount(count);}
    public int getCount() { return this.displayer.getCount(); }

    public void setEdgefx(List<EdgeGraph> edges){
        this.edgefx = edges;
    }

    public List<EdgeGraph> getEdgefx(){
        return this.edgefx;
    }

    public TreeMapGraph getGraph(){
        return this.graph;
    }

    public Displayer getDisplayer(){
        return this.displayer;
    }

    public List<EdgeGraph> getResultEdges(){
        return this.displayer.getResultEdges();
    }

    public void setup(DisplayerFactory factory){
        this.displayer = factory.makeDisplayer();
        this.displayer.getAlgo().setGraph(this.graph);
        this.displayer.setEdgefx(this.edgefx);
        this.displayer.calculate();
    }

    public void clear(TreeMapGraph graph){
        this.displayer.setCount(-1);
        this.graph = graph;
    }

    public void runAuto(){
        Thread t = new Thread(displayer);
        t.start();
    }

    public void runStepByStep(){
        this.displayer.setCount(this.displayer.getCount() + 1);
        try{
            if(this.displayer.getCount() < displayer.getResultEdges().size())
                displayer.drawStep();
            else
                displayer.showResult();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Out of step!!");
            alert.show();
        }
    }
}
