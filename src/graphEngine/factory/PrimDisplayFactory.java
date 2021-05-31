package graphEngine.factory;

import graphEngine.algos.Prim;
import graphEngine.display.Displayer;
import graphEngine.display.PrimDisplayer;
import javafx.scene.paint.Color;

public class PrimDisplayFactory implements DisplayerFactory{
    @Override
    public Displayer makeDisplayer(){
        PrimDisplayer displayer = new PrimDisplayer();
        displayer.setAlgo(new Prim());
        displayer.setColor(Color.CORAL);
        displayer.setCount(-1);
        return displayer;
    }
}
