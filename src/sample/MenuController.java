package sample;

import com.jfoenix.controls.JFXToggleButton;
import graphEngine.algos.AbstractAlgo;
import graphEngine.algos.Dijsktra;
import graphEngine.algos.Kruskal;
import graphEngine.algos.Prim;
import graphEngine.context.Context;
import graphEngine.factory.DijsktraDisplayFactory;
import graphEngine.factory.KruskalDisplayFactory;
import graphEngine.factory.PrimDisplayFactory;
import graphEngine.graph.DirectedGraph;
import graphEngine.graph.TreeMapGraph;
import graphEngine.graph.UndirectedGraph;
import javafx.animation.FillTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Group paneGroup;
    @FXML
    private Button backButton, clearButton, runButton, stepButton, primButton, kruButton, dijkButton;
    @FXML
    private JFXToggleButton addEdgeButton, addNodeButton;
    @FXML
    private Line edgeLine;
    @FXML
    private ArrowGraph arrow;
    @FXML
    private Pane viewer;
    @FXML
    private Label weight;

    boolean addNode = true, addEdge = false;

    // List node graph
    List<NodeFX> nodes = new ArrayList<>();
    // Context
    Context context;

    private final boolean direct = MainController.directed;
    private final boolean undirect = MainController.undirected;

    public TreeMapGraph makeGraph(){
        if(direct) return new DirectedGraph();
        return new UndirectedGraph();
    }

    public void initContext(){
        context = new Context();
        context.setGraph(makeGraph());
        context.edgefx = new ArrayList<>();
    }

    int nNode = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        primButton.setDisable(true);
        kruButton.setDisable(true);
        dijkButton.setDisable(true);
        addEdgeButton.setDisable(true);
        addNodeButton.setSelected(true);
        addNodeButton.setDisable(false);
        clearButton.setDisable(true);
        runButton.setDisable(true);
        stepButton.setDisable(true);
        initContext();
        System.out.println("Init success");
    }

    /**
     * Handle events for mouse clicks.
     * @param ev
     */
    @FXML
    public void handle(MouseEvent ev){
        if (ev.getEventType() == MouseEvent.MOUSE_RELEASED && ev.getButton() == MouseButton.PRIMARY) {
            NodeFX circle = new NodeFX(ev.getX(), ev.getY(), 1.5, String.valueOf(nNode));
            if(addNode) {
                System.out.print("Add Node: ");
                paneGroup.getChildren().add(circle);
                paneGroup.getChildren().add(circle.id);
                nodes.add(circle);
                System.out.print("X=" + circle.getCenterX() + ", ");
                System.out.print("Y=" + circle.getCenterY() + "\n");
                circle.setOnMousePressed(mouseEventEventHandler);
                circle.setOnMouseReleased(mouseEventEventHandler);
                //Change size circle
                ScaleTransition tr = new ScaleTransition(Duration.millis(100), circle);
                tr.setByX(7f);
                tr.setByY(7f);
                tr.setInterpolator(Interpolator.EASE_OUT);
                tr.play();
                //Add node on tree graph
                context.graph.addVertex(nNode);
                //Handle button
                nNode++;
                if(nNode >= 1){
                    clearButton.setDisable(false);
                }
                if (nNode >= 2) {
                    addEdgeButton.setDisable(false);
                    dijkButton.setDisable(false);
                    if (undirect) {
                        kruButton.setDisable(false);
                        primButton.setDisable(false);
                    }
                }
            }
        }
    }

    //Check exits edge
    boolean checkEdge(NodeFX source, NodeFX target){
        return context.graph.getAdjacentMap().get(Integer.valueOf(source.node.name)).containsKey(Integer.valueOf(target.node.name));
    }

    //Add edge
    NodeFX selectedNode = null;
    EdgeGraph temp = null;
    EventHandler<MouseEvent> mouseEventEventHandler = new EventHandler<>() {
        @Override
        public void handle(MouseEvent event) {
            NodeFX circle = (NodeFX) event.getSource();
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED && event.getButton() == MouseButton.PRIMARY) {
                if(!circle.isSelected) {
                    if (selectedNode != null) {
                        if(addEdge && !checkEdge(selectedNode,circle)){
                            weight = new Label();
                            System.out.println("Adding Edge");
                            if(undirect){
                                //Add weight
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setTitle(null);
                                dialog.setHeaderText("Add weight :");
                                dialog.setContentText(null);
                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {
                                    // Set up visual edge
                                    weight.setText(result.get());
                                    weight.setStyle("-fx-font-family: Courier; -fx-font-size: 14pt");
                                    weight.setTextFill(Color.ROYALBLUE);
                                    weight.setLayoutX(((selectedNode.point.x) + (circle.point.x)) / 2);
                                    weight.setLayoutY(((selectedNode.point.y) + (circle.point.y)) / 2);
                                    paneGroup.getChildren().add(weight);

                                    edgeLine = new Line(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                                    edgeLine.setId("Line");
                                    edgeLine.setStyle("-fx-stroke-width: 4; -fx-opacity: 0.6;");
                                    paneGroup.getChildren().add(edgeLine);

                                    temp = new EdgeGraph(selectedNode.node, circle.node, Integer.valueOf(weight.getText()), edgeLine, weight);
                                    context.edgefx.add(temp);
                                    context.graph.addEdge(Integer.valueOf(selectedNode.node.name), Integer.valueOf(circle.node.name), Integer.valueOf(weight.getText()));
                                    // to check treemap on console
                                    context.graph.print();
                                }
                            }else if(direct){
                                // Prompt weight
                                TextInputDialog dialog = new TextInputDialog();
                                dialog.setTitle(null);
                                dialog.setHeaderText("Add weight :");
                                dialog.setContentText(null);
                                Optional<String> result = dialog.showAndWait();
                                if (result.isPresent()) {
                                    // Set up visual edge
                                    weight.setText(result.get());
                                    weight.toFront();
                                    weight.setLayoutX(((selectedNode.point.x) + (circle.point.x)) / 2);
                                    weight.setLayoutY(((selectedNode.point.y) + (circle.point.y)) / 2);
                                    paneGroup.getChildren().add(weight);

                                    arrow = new ArrowGraph(selectedNode.point.x, selectedNode.point.y, circle.point.x, circle.point.y);
                                    arrow.setId("arrow");
                                    arrow.setStyle("-fx-stroke-width: 4; -fx-opacity: 0.6;");
                                    paneGroup.getChildren().add(arrow);

                                    //Add edge to arraylist
                                    context.graph.addEdge(Integer.valueOf(selectedNode.node.name), Integer.valueOf(circle.node.name), Integer.valueOf(weight.getText()));
                                    temp = new EdgeGraph(selectedNode.node, circle.node, Double.valueOf(weight.getText()), arrow, weight);
                                    //edges.add(temp);
                                    context.edgefx.add(temp);
                                    //To check treemap on console
                                    context.graph.print();
                                }
                            }
                            if (addNode || addEdge) {
                                selectedNode.isSelected = false;
                                FillTransition ft1 = new FillTransition(Duration.millis(300), selectedNode, Color.RED, Color.GRAY);
                                ft1.play();
                            }
                            selectedNode = null;
                            return;
                        }
                    }
                    circle.isSelected = true;
                    selectedNode = circle;
                    for (NodeFX n : nodes) {
                        n.isSelected = false;
                        FillTransition ft1 = new FillTransition(Duration.millis(300), n);
                        ft1.setToValue(Color.GRAY);
                        ft1.play();
                    }
                }else{
                    circle.isSelected = false;
                    FillTransition ft1 = new FillTransition(Duration.millis(300), circle, Color.RED, Color.GRAY);
                    ft1.play();
                    selectedNode = null;
                }
            }
            FillTransition ft = new FillTransition(Duration.millis(300), circle, Color.GRAY, Color.RED);
            ft.play();

        }
    };

    //Create NodeGraph(id)
    public class NodeFX extends Circle {
        NodeGraph node;
        Label id;
        Point point;
        Boolean isSelected = false;
        public NodeFX(double x, double y, double rad, String name) {
            super(x, y, rad);
            point = new Point((int) x, (int) y);
            node = new NodeGraph(name,this);
            setFill(Color.GRAY);
            id = new Label(name);
            id.setTextFill(Color.BLACK);
            id.setLayoutX(x - 5);
            id.setLayoutY(y - 35);
            id.toFront();
            id.setStyle("-fx-font-family: Courier; -fx-font-size: 14pt");
        }
    }

    public void setButtonOnError(){
        kruButton.setDisable(true);
        dijkButton.setDisable(true);
        primButton.setDisable(true);
    }

    //Clear current visualized edges when switch mode
    public void ClearColor(){
        try {
            for (EdgeGraph eg : context.getResultEdges()) {
                context.displayer.resetEdgeColor(eg);
            }
        } catch(NullPointerException e) {
            System.out.println("Nothing to clear :/");
            return;
        }
    }

    //Handle clear button
    @FXML
    public void clearClick(ActionEvent event){
        System.out.println("Clear");
        paneGroup.getChildren().clear();
        paneGroup.getChildren().addAll(viewer);
        addNode = true;
        addEdge = false;
        selectedNode = null;
        addEdgeButton.setDisable(true);
        addEdgeButton.setSelected(false);
        addNodeButton.setDisable(false);
        addNodeButton.setSelected(true);
        primButton.setDisable(true);
        kruButton.setDisable(true);
        dijkButton.setDisable(true);
        runButton.setDisable(true);
        stepButton.setDisable(true);
        nNode = 0;
        context.clear(makeGraph());
    }

    //Handle back button
    @FXML
    public void backClick(ActionEvent actionEvent){
        MainController.menu.hide();
        Main.home.show();
    }

    //Handle addEdge button
    @FXML
    public void addEdgeHandle() {
        addNode = false;
        addEdge = true;
        addNodeButton.setSelected(false);
        addEdgeButton.setSelected(true);
    }

    //Handle addNode button
    @FXML
    public void addNodeHandle() {
        selectedNode = null;
        addNode = true;
        addEdge = false;
        addNodeButton.setSelected(true);
        addEdgeButton.setSelected(false);
    }

    //Handle prim button
    @FXML
    public void primHandle(){
        try {
            ClearColor();
            addNode = false;
            addEdge = false;
            addNodeButton.setDisable(true);
            addEdgeButton.setDisable(true);
            kruButton.setDisable(true);
            dijkButton.setDisable(true);
            runButton.setDisable(false);
            stepButton.setDisable(false);
            addNodeButton.setSelected(false);
            addEdgeButton.setSelected(false);
            context.setup(new PrimDisplayFactory());
            //context.setup(new Prim());
            //context.setAlgo(new Prim());
            //context.setCount(-1);
        } catch (Exception e){
            System.out.println("Error occurred");
            setButtonOnError();
        }
    }

    //Handle kru button
    @FXML
    public void kruHandle(){
        try {
            ClearColor();
            addNode = false;
            addEdge = false;
            addNodeButton.setDisable(true);
            addNodeButton.setSelected(false);
            addEdgeButton.setDisable(true);
            addEdgeButton.setSelected(false);
            primButton.setDisable(true);
            dijkButton.setDisable(true);
            runButton.setDisable(false);
            stepButton.setDisable(false);
            context.setup(new KruskalDisplayFactory());
        } catch (Exception e){
            System.out.println("Error occurred");
            setButtonOnError();
        }
    }

    //Handle dijk button
    @FXML
    public void dijkHandle(){
        try {
            ClearColor();
            addNode = false;
            addEdge = false;
            addNodeButton.setDisable(true);
            addEdgeButton.setDisable(true);
            kruButton.setDisable(true);
            primButton.setDisable(true);
            runButton.setDisable(false);
            stepButton.setDisable(false);
            context.setup(new DijsktraDisplayFactory());
        } catch (Exception e){
            System.out.println("Error occurred");
            setButtonOnError();
        }
    }

    //Handle run button
    @FXML
    public void runClick(){
        System.out.println("Run Automatically");
        try {
            context.runAuto();
        } catch (Exception e){
            System.out.println("Error occurred");
            setButtonOnError();
        }
    }

    //Handle step button
    @FXML
    public void stepClick(){
        System.out.println("Run Step By Step");
        try {
            context.runStepByStep();
        } catch (Exception e){
            System.out.println("Error occurred");
            setButtonOnError();
        }
    }
}
