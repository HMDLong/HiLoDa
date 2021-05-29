package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineJoin;

public class ArrowGraph extends Path {
    private static final double defaultArrowHeadSize = 7;
    private double startX, startY, endX, endY;
    public ArrowGraph(double startX, double startY, double endX, double endY, double arrowHeadSize){
        super();

        double angle = Math.atan2((endY - startY), (endX - startX));
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        double dist = Math.sqrt(Math.pow((endY - startY), 2) + Math.pow((endX - startX), 2));

        double angle1 = angle - Math.PI / 2.0;
        double sin1 = Math.sin(angle1);
        double cos1 = Math.cos(angle1);

        double newStartX = startX + 15.0 * cos;
        double newStartY = startY + 15.0 * sin;
        double newEndY = startY + (dist-15.0) * sin;
        double newEndX = startX + (dist-15.0) * cos;

        this.startX = newStartX;
        this.startY = newStartY;
        this.endX = newEndX;
        this.endY = newEndY;

        //Line
        getElements().add(new MoveTo(newStartX, newStartY));
        getElements().add(new LineTo(newEndX, newEndY));

        //ArrowHead
        //point1
        double x1 = (- 1.0 / 2.0 * cos1 + Math.sqrt(3) / 2 * sin1) * arrowHeadSize + newEndX;
        double y1 = (- 1.0 / 2.0 * sin1 - Math.sqrt(3) / 2 * cos1) * arrowHeadSize + newEndY;
        //point2
        double x2 = (1.0 / 2.0 * cos1 + Math.sqrt(3) / 2 * sin1) * arrowHeadSize + newEndX;
        double y2 = (1.0 / 2.0 * sin1 - Math.sqrt(3) / 2 * cos1) * arrowHeadSize + newEndY;

        getElements().add(new LineTo(x1, y1));
        getElements().add(new LineTo(x2, y2));
        getElements().add(new LineTo(newEndX, newEndY));

        setStrokeWidth(5.0);
        setStrokeLineJoin(StrokeLineJoin.ROUND);
        setFill(Color.BLACK);
    }
    public ArrowGraph(double startX, double startY, double endX, double endY){
        this(startX, startY, endX, endY, defaultArrowHeadSize);
    }
}
