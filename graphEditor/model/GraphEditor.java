package model;

import controller.SelectionController;
import view.GraphFrame;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Contains the method that starts the program and the interface.
 */
public class GraphEditor extends Observable implements Observer {

    private GraphModel graphModel;
    private GraphFrame frame;
    private Search search;
    private final ArrayList<GraphFrame> extraFrames;
    private final SelectionController controller;

    public GraphEditor() {
        graphModel = new GraphModel();
        extraFrames = new ArrayList<>();
        controller = new SelectionController(graphModel);
        search = new Search(graphModel);
    }

    /*Getters*/
    public GraphModel getGraphModel() {
        return graphModel;
    }

    public SelectionController getController() {
        return controller;
    }

    public GraphFrame getDraw() {
        return frame;
    }

    public Search getSearch() { return search; }

    /*Setters*/

    public void setDraw(GraphFrame frame) {
        this.frame = frame;
    }

    public void setGraphModel(GraphModel graphModel) {
        this.graphModel = graphModel;
    }

    /*Functionality*/
    public void start() {
        frame = new GraphFrame(this);
    }

    public void newFrame(){
        extraFrames.add(new GraphFrame(this));
    }

    @Override
    public void update(Observable o, Object arg) {
        notifyObservers();
        setChanged();
    }
}
