package controller;

//import controller.actions.BestFirstAction;
import controller.actions.BreadthFirstAction;
import controller.actions.DepthFirstAction;
import controller.actions.GreedyAction;
import model.GraphEditor;

import javax.swing.*;

public class SearchBar extends JMenu {
    public SearchBar(GraphEditor editor) {
        super("Search algorithms...");
        add(new JMenuItem(new BreadthFirstAction(editor)));
        add(new JMenuItem(new DepthFirstAction(editor)));
//        add(new JMenuItem(new BestFirstAction(editor)));
        add(new JMenuItem(new GreedyAction(editor)));
    }
}
