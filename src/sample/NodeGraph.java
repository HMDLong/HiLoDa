package sample;

import java.util.ArrayList;
import java.util.List;

public class NodeGraph {
    public String name;
    public List<EdgeGraph> adjacents = new ArrayList<>();
    public MenuController.NodeFX circle;

    public NodeGraph(String name, MenuController.NodeFX circle) {
        this.name = name;
        this.circle = circle;
    }
}
