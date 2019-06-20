package view;

import model.GraphEditor;
import controller.EditBar;
import controller.FileBar;
import controller.SearchBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * main frame of the editor, holding buttons.
 */
public class GraphFrame extends JFrame implements Observer, ActionListener {

    /*Constructor*/
    public GraphFrame(GraphEditor editor) {

        super();
        GraphPanel Panel = new GraphPanel(editor);
        this.setTitle("Graph Editor");

        /*Add the menu bars*/
        JMenuBar optionBar = new JMenuBar();
        optionBar.add(new FileBar(editor));
        optionBar.add(new EditBar(editor));
        optionBar.add(new SearchBar(editor));
        this.setJMenuBar(optionBar);

        Panel.setVisible(true);
        this.add(Panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    /*Functionality*/
    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}