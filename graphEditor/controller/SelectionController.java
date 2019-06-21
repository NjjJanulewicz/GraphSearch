package controller;

import model.GraphModel;
import model.GraphVertex;
import operation.addEdgeOperation;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Remembers if there is a vertex selected and which vertex it is.
 */
public class SelectionController extends MouseInputAdapter implements Observer{

    private GraphModel model;

    private GraphVertex selected = null;
    private boolean addingEdge = false;

    private transient int currentX;
    private transient int currentY;

    /*Constructor*/
    public SelectionController(GraphModel model) {
        this.model = model;
    }

    /*Getters*/
    public GraphVertex getSelected() {
        return selected;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    /*Setters*/
    public void setAddingEdge(boolean addingEdge) {
        this.addingEdge = addingEdge;
    }

    public void setSelected(GraphVertex vertex) {
        this.selected = vertex;
    }

    public void setModel(GraphModel model) {
        this.model = model;
    }

    /*Functionality */
    public boolean isAddingEdge() {
        return addingEdge;
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    /*Mouse Functionality*/
    @Override
    public void mouseClicked(MouseEvent e) {
        currentX = e.getX();
        currentY = e.getY();
        ArrayList<GraphVertex> vertices = model.getVertices();
        if (!addingEdge) {
            for (GraphVertex vertex : vertices) {
                //check for x location
                if ((currentX >= vertex.getRectangle().x && currentX <= vertex.getRectangle().x + vertex.getRectangle().width) &&
                        //check for y location
                        (currentY >= vertex.getRectangle().y && currentY <= vertex.getRectangle().y + vertex.getRectangle().height)) {
                    vertex.setPreviouslySelected(true);
                    this.selected = vertex;
                }
            }
        }
        model.update(model, null);    // This updates the add edge button when its selected.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (addingEdge) {
            ArrayList<GraphVertex> vertices = model.getVertices();
            currentX = e.getX();
            currentY = e.getY();
            for (GraphVertex vertex : vertices) {
                if ((currentX >= vertex.getRectangle().x && currentX <= vertex.getRectangle().x + vertex.getRectangle().width) &&
                        //check for y location
                        (currentY >= vertex.getRectangle().y && currentY <= vertex.getRectangle().y + vertex.getRectangle().height)) {
                    model.getUndoManager().addEdit(new addEdgeOperation(model,selected,vertex));
                }
            }
            addingEdge = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (!addingEdge) {
            ArrayList<GraphVertex> vertices = model.getVertices();
            for (GraphVertex vertex : vertices) {
                if (selected != null) {
                    if (vertex.equals(selected)) {
                        //selected.setXPosition(event.getX());
                        //selected.setYPosition(event.getY());
                        selected.getRectangle().x = event.getX();//selected.getXPosition();
                        selected.getRectangle().y = event.getY();//selected.getYPosition();
                        //vertex.setXPosition(selected.getXPosition());
                        //vertex.setYPosition(selected.getYPosition());
                        vertex.getRectangle().x = event.getX();//selected.getXPosition();
                        vertex.getRectangle().y = event.getY();//selected.getYPosition();
                    }
                }
            }
        }
        if (addingEdge) {
            currentX = event.getX();
            currentY = event.getY();

        }
        model.update(model,null);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
