package controller;

import model.GraphEditor;

import javax.swing.*;

public class SearchBar extends JMenu {
    public SearchBar(GraphEditor editor) {
        super("Search algorithms...");
        add(new JMenuItem(new BreadthFirstAction(editor)));
    }
}
