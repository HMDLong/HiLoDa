package sample.visual.elements;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;

public class Arrow extends Group {
    private Polyline mainline = new Polyline();
    private Polyline head = new Polyline();
    // start point coordinate
    private SimpleDoubleProperty x1 = new SimpleDoubleProperty();
    private SimpleDoubleProperty y1 = new SimpleDoubleProperty();
    // end point coordinate
    private SimpleDoubleProperty x2 = new SimpleDoubleProperty();
    private SimpleDoubleProperty y2 = new SimpleDoubleProperty();
    // arrow scaling
    private final int ARROW_SCALER = 20;
    private final double ARROWHEAD_ANGLE = Math.toRadians(30);
    private final double ARROWHEAD_LENGTH = 20;

    // make the arrow
    public Arrow(double x1, double y1, double x2, double y2){
        // setup the mainline
        this.x1.set(x1);
        this.y1.set(y1);
        this.x2.set(x2);
        this.y2.set(y2);

        // add to the group
        getChildren().addAll(mainline, head);

        // set listener on the start end of the mainline for it to be updated
        for(SimpleDoubleProperty s : new SimpleDoubleProperty[]{this.x1, this.y1, this.x2, this.y2}){
            s.addListener((l,o,n) -> update());
        }

        // call update() here to make it update itself when created
        update();
    }

    private void update(){
        // re-set start end point
        double[] scaled_start = scale(x1.get(), y1.get(), x2.get(), y2.get());
        double[] scaled_end = scale(x2.get(), y2.get(), x1.get(), y1.get());
        double x1 = scaled_start[0];
        double y1 = scaled_start[1];
        double x2 = scaled_end[0];
        double y2 = scaled_end[1];

        mainline.getPoints().setAll(x1, y1, x2, y2);

        // setup the head of arrow
        double theta = Math.atan2(y2-y1, x2-x1);
        double x = x2 - Math.cos(theta + ARROWHEAD_ANGLE) * ARROWHEAD_LENGTH;
        double y = y2 - Math.sin(theta + ARROWHEAD_ANGLE) * ARROWHEAD_LENGTH;
        head.getPoints().setAll(x,y,x2,y2);
        x = x2 - Math.cos(theta - ARROWHEAD_ANGLE) * ARROWHEAD_LENGTH;
        y = y2 - Math.sin(theta - ARROWHEAD_ANGLE) * ARROWHEAD_LENGTH;
        head.getPoints().addAll(x,y);
    }

    private double[] scale(double x1, double y1, double x2, double y2){
        double theta = Math.atan2(y2-y1, x2-x1);
        return new double[]{
                x1 + Math.cos(theta) * ARROW_SCALER,
                y1 + Math.sin(theta) * ARROW_SCALER,
        };
    }
    // getter and setter
    public Polyline getMainline() { return mainline; }
    public void setMainline(Polyline mainline) { this.mainline = mainline; }

    public double getX1() { return x1.get(); }
    public void setX1(double x1) { this.x1.set(x1); }
    public SimpleDoubleProperty x1Property() { return x1; }

    public double getY1() { return y1.get(); }
    public void setY1(double y1) { this.y1.set(y1); }
    public SimpleDoubleProperty y1Property() { return y1; }

    public double getX2() { return x2.get(); }
    public void setX2(double x2) { this.x2.set(x2); }
    public SimpleDoubleProperty x2Property() { return x2; }

    public double getY2() { return y2.get(); }
    public void setY2(double y2) { this.y2.set(y2); }
    public SimpleDoubleProperty y2Property() { return y2; }
}
