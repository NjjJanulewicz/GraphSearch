package operation;

import model.GraphEditor;
import model.GraphVertex;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class deleteNodeOperation extends AbstractUndoableEdit {

    private final GraphEditor editor;
    private final GraphVertex vertex;

    /*Constructor*/
    public deleteNodeOperation(GraphEditor editor) {
        this.editor = editor;
        this.vertex = editor.getController().getSelected();
        if (vertex != null) {
            int choice = JOptionPane.showConfirmDialog(this.editor.getDraw(),
                    "Are you sure you want to delete " + vertex.toString(), "Confirm deletion", JOptionPane.YES_NO_OPTION);
            if (choice != 1) {
                this.editor.getGraphModel().removeVertex(vertex);
                this.editor.getController().setSelected(null);
            }
        }
    }

    /*Functionality*/
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        editor.getGraphModel().removeVertex(vertex);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        editor.getGraphModel().getVertices().add(vertex);
    }
}
