package controller.actions;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the Redo button is pressed.
 * Default access modifier used.
 */
public class RedoAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public RedoAction(GraphEditor draw) {
        super("Redo action");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
    }

    /*Functionality*/
    private void toggleButton() {
        setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getGraphModel().getUndoManager().redo();
        editor.getGraphModel().update(editor.getGraphModel(),editor);
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}