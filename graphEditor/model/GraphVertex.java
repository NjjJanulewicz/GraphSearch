package model;

import java.awt.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Observable;

/**
 * Represents an edge in the graph.
 * Graph Vertex: Contains an index, an element, a rectangle and its default x-y coordinates. Contains a
 * TODO: connections changes, make sure to change all other methods that used it.
 */
public class GraphVertex extends Observable implements Serializable { //Vertex<V>,

    private static final long serialVersionUID = 42L;
    private int index;
    private int element;
    private boolean previouslySelected;
    private GraphVertex parent;
    private LinkedList children = new LinkedList<GraphVertex>();
    private final LinkedHashMap<GraphVertex, GraphEdge> connections;    // used to be <GraphEdge, GraphVertex>
    private Rectangle rectangle;

    // Constructor //
    public GraphVertex(int ele) {
        element = ele;
        connections = new LinkedHashMap<>();
        previouslySelected = false;
        generateRectangle();
    }

    /*Getters*/
    public LinkedList<GraphVertex> getChildren() { return children; }

    public GraphVertex getParent() { return parent; }

    public int getElement() {
        return element;
    }

    /**
     * @return The position of this Vertex in the graphs Vertex list.
     */
    public int getIndex() {
        return index;
    }

    /**
     * @return The reference to the Map of outgoing Edges.
     */
    public LinkedHashMap<GraphVertex, GraphEdge> getConnections() {
        return connections;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * TODO: needs tested
     * @return
     */
    public GraphEdge getEdgeBetween(GraphVertex left, GraphVertex right) {

        if (left.getConnections().get(left) == right.getConnections().get(right) ) {
            return left.getConnections().get(left);
        } else {
            return null;
        }
    }

    /*Setters*/
    public void setHierarchy(GraphVertex parent, GraphVertex child) {
        child.parent = parent;
        parent.children.push(child);
    }

    /**
     * Stores the position of this Vertex within the graphs Vertex list.
     *
     * @param p: The position in the graph represented by an integer.
     */
    public void setIndex(int p) {
        index = p;
    }

    public void setPreviouslySelected(boolean previouslySelected) {
        this.previouslySelected = previouslySelected;
    }

    public void setElement(int title) {
        this.element = title;
    }

    /*Functionality*/


    public void addToTable(GraphEdge Edge, GraphVertex Destination) {
        connections.put(Destination, Edge);
    }

    private void generateRectangle() {
        Integer elementObj = element;
        rectangle = new Rectangle();
        rectangle.height = 50;
        rectangle.width = 80;
        if(elementObj.toString ().length() >3){
            rectangle.width+=(elementObj.toString().length() - 3) * 20;
        }
    }

    public void generateRectangle2(int x, int y) {
        Integer elementObj = element;
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.height = 50;
        rectangle.width = 80;
        if(elementObj.toString().length() > 3){
            rectangle.width+=(elementObj.toString().length() - 3) * 20;
        }
    }

    public boolean isPreviouslySelected() {
        return previouslySelected;
    }

    public String toString() {
        return "Node " + element;
    }
}