package operation;

import model.GraphEditor;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class changeTitleOperation extends AbstractUndoableEdit {
    private final GraphEditor editor;
    private final int element;
    private int newElement;

    /*Constructor*/
    public changeTitleOperation(GraphEditor editor){
        this.editor = editor;
        element = editor.getController().getSelected().getElement();

        newElement = Integer.parseInt(JOptionPane.showInputDialog(null,
                "Choose one"));
        this.editor.getController().getSelected().setElement(newElement);
        this.editor.getController().getSelected().generateRectangle2(this.editor.getController().getSelected().getRectangle().x,
                this.editor.getController().getSelected().getRectangle().y);
        this.editor.getController().setSelected(null);
    }

    /*Functionality*/
    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        editor.getController().getSelected().setElement(newElement);
        this.editor.getController().getSelected().generateRectangle2(this.editor.getController().getSelected().getRectangle().x,
                this.editor.getController().getSelected().getRectangle().y);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        editor.getController().getSelected().setElement(element);
        this.editor.getController().getSelected().generateRectangle2(this.editor.getController().getSelected().getRectangle().x,
                this.editor.getController().getSelected().getRectangle().y);
    }
}
