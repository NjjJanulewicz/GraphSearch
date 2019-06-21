package controller;

import controller.actions.GenerateNewFrameAction;
import controller.actions.OpenFileAction;
import controller.actions.SaveFileAction;
import model.GraphEditor;

import javax.swing.*;

public class FileBar extends JMenu {
    public FileBar(GraphEditor editor) {
        super("File...");
        add(new JMenuItem(new OpenFileAction(editor)));
        add(new JMenuItem(new SaveFileAction(editor)));
        add(new JMenuItem(new GenerateNewFrameAction(editor)));
    }
}
