package graphEngine.context;
import graphEngine.algos.AbstractAlgo;
import graphEngine.display.Displayer;
import graphEngine.graph.TreeMapGraph;
import javafx.scene.control.Alert;
import sample.EdgeGraph;
import graphEngine.factory.DisplayerFactory;

import java.util.List;

public class Context {
    public TreeMapGraph graph;
    public AbstractAlgo algo;
    public List<EdgeGraph> edgefx;
    public Displayer displayer;

    public void setGraph(TreeMapGraph graph){
        this.graph = graph;
    }
    public void setAlgo(AbstractAlgo algo) {
        this.algo = algo;
    }
    public void setCount(int count) { this.displayer.setCount(count);}
    public int getCount() { return this.displayer.getCount(); }

    public List<EdgeGraph> getResultEdges(){
        return this.displayer.getResultEdges();
    }
    /*
    public void setup(AbstractAlgo algo){
        this.algo = algo;
        this.algo.setGraph(this.graph);
        this.algo.setEdgefx(this.edgefx);
        this.algo.init();
    }

     */

    public void setup(DisplayerFactory factory){
        this.displayer = factory.makeDisplayer();
        this.displayer.algo.setGraph(this.graph);
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
        /*
        if (this.displayer.getCount() == 0 ){
            //EdgeGraph eg = this.algo.getResultEdges().get(count);
            //AbstractAlgo.edgeColoring(eg,this.algo.getColor());
            displayer.drawStep();
        }
        else if (this.displayer.getCount() == displayer.getResultEdges().size()){
            /*
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("RESULT");
            alert.setHeaderText("");
            alert.setContentText(algo.toString());
            alert.show();

            this.displayer.showResult();
        }
        else if (this.displayer.getCount() > displayer.getResultEdges().size()){
            System.out.println("Number of step(s) SURPASSED Edges' size! ");
        }
        else{
            //EdgeGraph eg = this.algo.getResultEdges().get(count);
            //AbstractAlgo.edgeColoring(eg,this.algo.getColor());
            displayer.drawStep();
        }
        */

        try{
            //displayer.drawStep();
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
