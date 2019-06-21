package controller.actions;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the Undo button is pressed.
 * Default access modifier used.
 */
public class UndoAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public UndoAction(GraphEditor draw) {
        super("Undo action");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
    }

    /*Functionality*/
    @Override
    public void actionPerformed(ActionEvent e) {
        editor.getGraphModel().getUndoManager().undo();
        editor.getGraphModel().update(editor.getGraphModel(),editor);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}