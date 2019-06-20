package graphController;

import model.GraphEditor;
import operation.newNodeOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the New Node button is pressed.
 * The action taken is to create a new node in the graph, should be represented in the GUI.
 * Default access modifier used.
 */
class NewNodeAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public NewNodeAction(GraphEditor draw) {
        super("New Node");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
    }

    /*Functionality*/
    private void toggleButton() {
        setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int inputValue = Integer.parseInt(JOptionPane.showInputDialog("Please input a value"));
        editor.getGraphModel().getUndoManager().addEdit(new newNodeOperation(editor, inputValue));

        editor.getController().setSelected(null);
        editor.getGraphModel().update(editor.getGraphModel(), null);    // This updates the add edge button when its selected.
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}