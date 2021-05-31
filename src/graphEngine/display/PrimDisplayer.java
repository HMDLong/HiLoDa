package graphEngine.display;

public class PrimDisplayer extends Displayer{
    @Override
    public void run(){
        for (count = 0; count < resultEdges.size(); count++) {
            try {
                drawStep();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void drawStep(){
        edgeColoring(resultEdges.get(count));
    }
}
