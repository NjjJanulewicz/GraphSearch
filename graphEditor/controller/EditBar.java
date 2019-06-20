package controller;

import model.GraphEditor;

import javax.swing.*;

public class EditBar extends JMenu {
    public EditBar(GraphEditor editor) {
        super("Edit...");
        add(new JMenuItem(new RedoAction(editor)));
        add(new JMenuItem(new UndoAction(editor)));
    }
}
