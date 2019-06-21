package controller.actions;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class GenerateNewFrameAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public GenerateNewFrameAction(GraphEditor draw) {
        super("Generate New Frame");
        this.editor = draw;
        this.editor.getGraphModel().addObserver(this);
        this.setEnabled(true);
    }

    /*Functionality*/
    @Override
    public void actionPerformed(ActionEvent e) {
        editor.newFrame();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}


