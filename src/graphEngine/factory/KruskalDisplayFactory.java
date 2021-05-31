package graphEngine.factory;

import graphEngine.algos.Kruskal;
import graphEngine.display.Displayer;
import graphEngine.display.KruskalDisplayer;
import javafx.scene.paint.Color;

public class KruskalDisplayFactory implements DisplayerFactory{
    @Override
    public Displayer makeDisplayer() {
        KruskalDisplayer displayer = new KruskalDisplayer();
        displayer.setAlgo(new Kruskal());
        displayer.setColor(Color.LIGHTSKYBLUE);
        displayer.setCount(-1);
        return displayer;
    }
}
