package graphEngine.factory;

import graphEngine.algos.Dijsktra;
import graphEngine.display.DijsktraDisplayer;
import graphEngine.display.Displayer;
import javafx.scene.paint.Color;

public class DijsktraDisplayFactory implements DisplayerFactory{
    @Override
    public Displayer makeDisplayer(){
        DijsktraDisplayer displayer = new DijsktraDisplayer();
        displayer.setAlgo(new Dijsktra());
        displayer.setColor(Color.LIGHTGREEN);
        displayer.setCount(-1);
        return displayer;
    }
}
