package controller.actions;

import model.GraphEditor;
import operation.newNodeOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the action taken when the New Node button is pressed.
 * The action taken is to create a new node in the graph, should be represented in the GUI.
 * Default access modifier used.
 */
public class NewNodeAction extends AbstractAction implements Observer {

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
    /**
     * TODO: fix input boxes.
     */
    public void actionPerformed(ActionEvent e) {

        JLabel heuristic = new JLabel("Heuristic value ");
        JLabel value = new JLabel("Node value ");

        JTextField heuristicField = new JTextField(5);
        JTextField valueField = new JTextField(5);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(value, BorderLayout.NORTH);
        panel.add(valueField, BorderLayout.AFTER_LINE_ENDS);
        panel.add(heuristic, BorderLayout.CENTER);
        panel.add(heuristicField, BorderLayout.AFTER_LAST_LINE);

        int inputValue = JOptionPane.showConfirmDialog(null, panel, "Do a thing",
                JOptionPane.PLAIN_MESSAGE);

        if (inputValue == JOptionPane.OK_OPTION) {
            System.out.println("x value: " + valueField.getText());
            System.out.println("y value: " + heuristicField.getText());
            editor.getGraphModel().getUndoManager().addEdit(new newNodeOperation(editor,
                    Integer.parseInt(valueField.getText()), Integer.parseInt(heuristicField.getText())));
        }

        editor.getController().setSelected(null);
        editor.getGraphModel().update(editor.getGraphModel(), null);    // This updates the add edge button when its selected.
    }

    @Override
    public void update(Observable o, Object arg) { toggleButton(); }
}