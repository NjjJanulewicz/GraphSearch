package controller.actions;

import model.GraphEditor;
import model.GraphModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

public class SaveFileAction extends AbstractAction implements Observer {
    private final GraphEditor editor;

    public SaveFileAction(GraphEditor draw) {
        super("Save File");
        this.editor = draw;
        this.editor.addObserver(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Object Files", "ser");
            chooser.setFileFilter(filter);
            chooser.setBounds(75, 75, 500, 300);
            int returnValue = chooser.showSaveDialog(chooser);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                FileOutputStream saveFile = new FileOutputStream(chooser.getSelectedFile() + ".ser");
                ObjectOutputStream objectSave = new ObjectOutputStream(saveFile);
                GraphModel model = editor.getGraphModel();
                objectSave.writeObject(model);
                objectSave.close();
                saveFile.close();
                saveFile.flush();
            }
        } catch (IOException IO) {
            System.err.println("User Friendly message: Object was not found.");
            IO.printStackTrace(System.err);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
