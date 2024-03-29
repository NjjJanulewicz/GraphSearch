package controller;

import controller.buttons.*;
import model.GraphEditor;

import javax.swing.*;

/**
 * Panel with the buttons for Draw controllers
 */
public class ButtonBar extends JMenuBar {

    public ButtonBar(GraphEditor draw) {
        add(new AddEdgeButton(draw));
        add(new DeleteNodeButton(draw));
        add(new NewNodeButton(draw));
        add(new EditButton(draw));
        add(new RemoveEdgeButton(draw));
    }
}
