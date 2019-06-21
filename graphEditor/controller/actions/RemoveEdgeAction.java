package controller.actions;

import model.GraphEdge;
import model.GraphEditor;
import operation.removeEdgeOperation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RemoveEdgeAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public RemoveEdgeAction(GraphEditor draw) {
        super("Remove Edge");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
        this.setEnabled(false);
    }

    /*Functionality*/
    private void toggleButton() {
        if (editor.getGraphModel().getEdges() != null && editor.getGraphModel().getEdges().size() >= 1) {
            this.setEnabled(true);
        } else {
            this.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor.getGraphModel().getEdges().size() >= 1) {
            ArrayList edgesList = editor.getGraphModel().getEdges();
            GraphEdge edge = (GraphEdge) JOptionPane.showInputDialog(null,
                    "Choose one", "Edge to remove",
                    JOptionPane.INFORMATION_MESSAGE, null,
                    edgesList.toArray(), edgesList.toArray()[0].toString());
            editor.getGraphModel().getUndoManager().addEdit(new removeEdgeOperation(editor,edge));
            editor.getGraphModel().update(editor.getGraphModel(), null);    // This updates the add edge button when its selected.
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        toggleButton();
    }
}
