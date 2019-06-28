package controller.actions;

import model.GraphEditor;
import operation.changeTitleOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class EditAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public EditAction(GraphEditor draw) {
        super("Edit Node");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
        this.setEnabled(false);
    }

    /*Functionality*/
    private void toggleButton() {
        if (editor.getController().getSelected() == null) {
            this.setEnabled(false);
        } else {
            this.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getGraphModel().getUndoManager().addEdit(new changeTitleOperation(editor));
        editor.getGraphModel().update(editor.getGraphModel(), null);    // This updates the add edge button when its selected.
        //editor.getDraw().getPanel().repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}