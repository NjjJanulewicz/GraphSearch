package operation;

import model.GraphEdge;
import model.GraphModel;
import model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.ArrayList;

public class addEdgeOperation extends AbstractUndoableEdit {
    private final GraphModel model;
    private final GraphVertex origin;
    private final GraphVertex dest;
    private GraphEdge edge;

    /*Constructor*/
    public addEdgeOperation(GraphModel old, GraphVertex origin, GraphVertex dest){
        this.model = old;
        this.origin = origin;
        this.dest = dest;
        this.dest.setHierarchy(origin, dest);
        if (model.getVertices().size() >= 2) {
                insertEdge(origin, dest);
            } else {
                System.out.println("TOO FEW");
            }
        model.update(model, null);
    }

    /*Functionality*/
    private void insertEdge(GraphVertex origin, GraphVertex dest) {
        if (getEdge(origin, dest) == null) {
            GraphEdge e = new GraphEdge(origin, dest);
            //e.setIndex(editor.getGraphModel().getEdges().size());
            model.addEdges(e);
            this.edge = e;
            origin.addToTable(e, dest);
            dest.addToTable(e, origin);
        }
    }
    private GraphEdge getEdge(GraphVertex u, GraphVertex v) {
    ArrayList<GraphEdge> edgeList = model.getEdges();
        for (GraphEdge edge : edgeList) {
            ArrayList endpoints = edge.getEndpoints();
            if (endpoints.get(0).equals(u)) {
                if (endpoints.get(1).equals(v)) {
                    return edge;
                }
            }
            if (endpoints.get(0).equals(v)) {
                if (endpoints.get(1).equals(u)) {
                    return edge;
                }
            }
        }
        return null;
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        insertEdge(this.origin,this.dest);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        model.removeEdge(edge);
    }

}
