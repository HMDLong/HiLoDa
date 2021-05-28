package sample;

import javafx.scene.control.Label;
import javafx.scene.shape.Shape;

public class EdgeGraph {
    public NodeGraph s1,s2;
    public double weight;
    public Shape line;
    public Label weightLabel;

    public EdgeGraph(NodeGraph s1, NodeGraph s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.weight = 0;
    }

    public EdgeGraph(NodeGraph s1, NodeGraph s2, double weight, Shape line, Label weightLabel) {
        this.s1 = s1;
        this.s2 = s2;
        this.weight = weight;
        this.line = line;
        this.weightLabel = weightLabel;
    }
}
