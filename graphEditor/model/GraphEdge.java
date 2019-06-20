package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents an edge in the graph.
 *
 *
 */
public class GraphEdge implements Serializable {

    private static final long serialVersionUID = 42L;
    private final ArrayList<GraphVertex> endpoints;
    private int pathCost = 0;

    /**
     * Constructs GraphEdge from left to right, storing the element.
     *
     * @param left:  A node you wish to connect.
     * @param right: Another node you wish to connect.
     */
    public GraphEdge(GraphVertex left, GraphVertex right) {
        endpoints = new ArrayList<>();
        endpoints.add(left);
        endpoints.add(right);
    }

    /*Getters*/
    public int getPathCost() { return pathCost; }

    /**
     * @return A REFERENCE to the endpoint array.
     */
    public ArrayList<GraphVertex> getEndpoints() {
        return endpoints;
    }

    // Setters //
    public void setPathCost(int cost) {
        pathCost = cost;
    }

    /*Functionality*/
    public String toString() {
        return "Edge between " + this.getEndpoints().get(0).toString()
                + " and " + this.getEndpoints().get(1).toString();
    }
}