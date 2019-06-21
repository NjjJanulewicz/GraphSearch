package controller.actions;

import model.GraphEditor;
import operation.deleteNodeOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the Delete Node button is pressed.
 * The action taken is to create a new node in the graph, should be represented in the GUI.
 * Default access modifier used.
 */
public class DeleteNodeAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    public DeleteNodeAction(GraphEditor draw) {
        super("Delete Node");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
        this.setEnabled(false);
    }

    private void toggleButton() {
        if (editor.getController().getSelected() == null) {
            this.setEnabled(false);
        } else {
            this.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(editor.getController().getSelected()!=null) {
            editor.getGraphModel().getUndoManager().addEdit(new deleteNodeOperation(editor));
            editor.getGraphModel().update(editor.getGraphModel(), null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}