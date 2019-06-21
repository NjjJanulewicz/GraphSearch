package controller.actions;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the Delete Node button is pressed.
 * The action taken is to create a new node in the graph, should be represented in the GUI.
 * Default access modifier used.
 */
public class GreedyAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    public GreedyAction(GraphEditor draw) {
        super("Breadth First Search");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
        this.setEnabled(false);
    }

    private void toggleButton() {
        if (editor.getGraphModel().getVertices() == null) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }
    }

    @Override
    /**
     * TODO: add exception handling for when a user tries to enter null
     *       It should use a do while, and loop the user through the decision until they get it right.
     */
    public void actionPerformed(ActionEvent e) {
        int inputValue = Integer.parseInt(JOptionPane.showInputDialog("Please input a value"));
        editor.getSearch().breadthFirst(inputValue);

        editor.getGraphModel().update(editor.getGraphModel(), null);    // This updates the add edge button when its selected.
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}