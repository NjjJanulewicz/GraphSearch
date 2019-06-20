package graphController;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the Add Edge button is pressed.
 * Default access modifier used.
 */
class AddEdgeAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    AddEdgeAction(GraphEditor draw) {
        super("Add Edge");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
        this.setEnabled(false);
    }

    /*Functionality*/
    private void toggleButton() {
        if (editor.getController().getSelected() == null || editor.getGraphModel().getVertices().size() < 2) {
            this.setEnabled(false);
        } else {
            this.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor.getGraphModel().getVertices().size() >= 2) {
            editor.getController().setAddingEdge(true);
            if (editor.getController().getSelected() != null) {
                editor.getController().setAddingEdge(true);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}