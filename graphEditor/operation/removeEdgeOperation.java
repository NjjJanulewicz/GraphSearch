package operation;

import model.GraphEdge;
import model.GraphEditor;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;


public class removeEdgeOperation extends AbstractUndoableEdit {
    private final GraphEditor editor;
    private final GraphEdge edge;

    /*Constructor*/
    public removeEdgeOperation(GraphEditor editor, GraphEdge edge) {
        this.editor = editor;
        this.edge = edge;
        if (editor.getGraphModel().getEdges().size() >= 1) {
            if(this.edge!=null) {
                this.editor.getGraphModel().removeEdge(this.edge);
            }
            editor.getController().setSelected(null);
        }
    }

    /*Functionality*/
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        this.editor.getGraphModel().removeEdge(edge);

    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        editor.getGraphModel().getEdges().add(edge);
    }
}
