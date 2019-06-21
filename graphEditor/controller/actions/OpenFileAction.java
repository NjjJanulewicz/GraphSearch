package controller.actions;

import model.GraphEditor;
import model.GraphModel;
import view.GraphFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class OpenFileAction extends AbstractAction implements Observer {

    private final GraphEditor editor;

    /*Constructor*/
    public OpenFileAction(GraphEditor editor) {
        super("Open File");
        // this.model = model;
        this.editor = editor;
        this.editor.addObserver(this);
    }

    /*Functionality*/
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Object Files", "ser");
            chooser.setFileFilter(filter);
            chooser.setBounds(75, 75, 500, 300);
            int returnValue = chooser.showOpenDialog(chooser);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File loadFile = chooser.getSelectedFile();
                FileInputStream fileChosen = new FileInputStream(loadFile);
                ObjectInputStream toLoad = new ObjectInputStream(fileChosen);
                editor.getDraw().dispose();
                editor.setGraphModel(((GraphModel) toLoad.readObject()));
                editor.getGraphModel().setUndoManager(new UndoManager());
                editor.getController().setModel(editor.getGraphModel());
                editor.setDraw(new GraphFrame(editor));
                editor.getGraphModel().update(this.editor.getGraphModel(), null);
            }
        } catch (FileNotFoundException | ClassNotFoundException FIF) {
            System.err.println("User Friendly message: File was not found.");
            FIF.printStackTrace(System.err);
        } catch (IOException IO) {
            System.err.println("User Friendly message: Object was not found.");
            IO.printStackTrace(System.err);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
