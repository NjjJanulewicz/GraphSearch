package model;

import javax.swing.undo.UndoManager;
import java.io.Serializable;
import java.util.*;

/**
 * Class to represent a graph.
 *
 * //@param <V>
 */
public class GraphModel extends Observable implements Serializable, Observer {

    private static final long serialVersionUID = 42L;
    private transient UndoManager undoManager;
    private  ArrayList<GraphVertex> vertices = new ArrayList<>();
    private  ArrayList<GraphEdge> edges = new ArrayList<>();

    //constructor//
    GraphModel() {
        this.undoManager = new UndoManager();
    }

    /*Getters*/
    public ArrayList<GraphVertex> getVertices() {
        return vertices;
    }

    public ArrayList<GraphEdge> getEdges() {
        return edges;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }

    public void setUndoManager(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    /*Shared Functionalities*/
    /**
     *
     * @param v
     */
    public void removeVertex(GraphVertex v) {
        for (GraphVertex vertex : vertices) {
            for (Map.Entry<GraphVertex, GraphEdge> graphEdgeGraphVertexEntry : vertex.getConnections().entrySet()) {
                if (((Map.Entry) graphEdgeGraphVertexEntry).getKey().equals(v)) {
                    GraphEdge e = (GraphEdge) ((Map.Entry) graphEdgeGraphVertexEntry).getValue();
                    edges.remove(e);
                }
            }
        }
        vertices.remove(v);
    }

    /**
     * TODO: using keys of graph edges where it needs to be a vertex.
     * @param e
     */
    public void removeEdge(GraphEdge e) {
        ArrayList<GraphVertex> f = e.getEndpoints();
        f.get(0).getConnections().remove(e);
        f.get(1).getConnections().remove(e);
        edges.remove(e);
    }

    public void addVertices(GraphVertex v){
        vertices.add(v);
    }

    public void addEdges(GraphEdge e){
        edges.add(e);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}