package sample.visual.elements;

public class Vertex extends VirtualVertex {
    private static int idCount = 0;
    private int id;
    private boolean isSelected = false;

    public Vertex(Double x, Double y){
        // call super to set x,y layout
        super(x, y);
        // centralize the node around the cursor
        translateXProperty().bind(widthProperty().divide(-2));
        translateYProperty().bind(heightProperty().divide(-2));
        // set style for vertex
        getStyleClass().add("normalVertex");
        this.id = idCount++;
        setText(this.id + "");
    }

    public int getVertexId(){
        return this.id;
    }

    public boolean isSelected(){
        return this.isSelected;
    }

    public void setSelected(boolean selected){
        this.isSelected = selected;
    }
}
