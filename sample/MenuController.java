package sample;

import com.jfoenix.controls.JFXToggleButton;
import graphEngine.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import sample.visual.elements.Arrow;
import sample.visual.elements.Vertex;
import sample.visual.elements.VirtualVertex;

public class MenuController implements Initializable {
    @FXML
    private Pane canvas;
    @FXML
    private JFXToggleButton editToggle, addNodeToggle, addEdgeToggle;
    private boolean editing = false, addingNode = false, addingEdge = false;

    private Context context;

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    private void initContext(){

    }

    private Vertex fromVertex = null, tempVertex = null;
    private VirtualVertex toVertex = null;

    private List<Vertex> vertexList = new ArrayList<>();

    /** canvas handling **/
    // toggle add vertex, click canvas to make vertex
    public void clickCanvas(MouseEvent mouseEvent) {
        if(addingNode) {
            tempVertex = makeVertex(mouseEvent.getX(), mouseEvent.getY());
            canvas.getChildren().add(tempVertex);
            vertexList.add(tempVertex);
            tempVertex = null;
        }
    }

    private Vertex makeVertex(double x, double y) {
        Vertex v = new Vertex(x, y);
        // set action for vertex
        v.setOnDragDetected(e -> onVertexDragDetected(e, v));
        v.setOnMouseDragged(e -> onVertexDragged(e, v));
        v.setOnMouseEntered(e -> onMouseEnterVertex(e, v));
        v.setOnMouseExited(e -> onMouseExitVertex(e, v));
        v.setOnMouseReleased(e -> onVertexDragReleased(e, v));
        return v;
    }

    private void onVertexDragReleased(MouseEvent e, Vertex v) {
        toVertex = null;
        fromVertex = null;
    }

    private void makeArrow(VirtualVertex vertex1, VirtualVertex vertex2){
        Arrow arrow = new Arrow(vertex1.getLayoutX(), vertex1.getLayoutY(), vertex2.getLayoutX(), vertex2.getLayoutY());
        // bind the start end vertex coordinate to the arrow so that when the vertices move, the arrow follow
        arrow.x1Property().bind(vertex1.layoutXProperty());
        arrow.y1Property().bind(vertex1.layoutYProperty());
        arrow.x2Property().bind(vertex2.layoutXProperty());
        arrow.y2Property().bind(vertex2.layoutYProperty());

        canvas.getChildren().add(arrow);
        //return arrow;
    }

    private void onMouseExitVertex(MouseEvent e, Vertex v) {
        v.getStyleClass().removeAll("vertexEntered");
        v.setSelected(false);
    }

    private void onMouseEnterVertex(MouseEvent e, Vertex v) {
        v.getStyleClass().add("vertexEntered");
        v.setSelected(true);
    }

    // toggle edit, drag vertex to move vertex
    private void onVertexDragDetected(MouseEvent e, Vertex v) {
        if(editing)
            v.toFront();
        if(addingEdge){
            // anchor the start vertex to this vertex
            fromVertex = v;
            // anchor the end the text to a virtual vertex at the cursor
            toVertex = makeVertex(v.getLayoutX() + e.getX() - 15, v.getLayoutY() + e.getY() - 15);
            // make the arrow fromVertex->toVertex
            //arrow =
            makeArrow(fromVertex, toVertex);
        }
    }

    private void onVertexDragged(MouseEvent e, Vertex v) {
        if(editing){
            v.setLayoutX(v.getLayoutX() + e.getX() + v.getTranslateX());
            v.setLayoutY(v.getLayoutY() + e.getY() + v.getTranslateY());
        }
        if(addingEdge && toVertex != null && fromVertex != null){
            toVertex.setLayoutX(v.getLayoutX() + e.getX() - 15);
            toVertex.setLayoutY(v.getLayoutY() + e.getY() - 15);
        }
    }

    /** other functional buttons, toggles handling **/
    public void reset(ActionEvent actionEvent) {
        canvas.getChildren().clear();
        initContext();
    }

    public void back(ActionEvent actionEvent) {
    }

    public void addNodeHandle(ActionEvent actionEvent) {
        if(!addingNode){
            addingNode = true;
            addingEdge = false;
            editing = false;
            addEdgeToggle.setSelected(false);
            editToggle.setSelected(false);
        }
        else addingNode = false;
    }

    public void addEdgeHandle(ActionEvent actionEvent) {
        if(!addingEdge){
            addingNode = false;
            addingEdge = true;
            editing = false;
            addNodeToggle.setSelected(false);
            editToggle.setSelected(false);
        }
        else addingEdge = false;
    }

    public void editHandle(ActionEvent actionEvent) {
        if(!editing){
            addingNode = false;
            addingEdge = false;
            editing = true;
            addEdgeToggle.setSelected(false);
            addNodeToggle.setSelected(false);
        }
        else editing = false;
    }
}
