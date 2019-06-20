package view;

import graphController.ButtonBar;
import graphController.SelectionController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * This is where all the vertices and edges will be drawn.
 */
class GraphPanel extends JPanel implements Observer{

    private final GraphModel model;
    private final SelectionController controller;
    private final Search search;

    /*Constructor*/
    GraphPanel(GraphEditor editor) {
        this.controller = editor.getController();
        this.model = editor.getGraphModel();
        this.search = editor.getSearch();
        model.addObserver(this);
        this.addMouseListener(editor.getController());
        this.addMouseMotionListener(editor.getController());
        this.add(new ButtonBar(editor));
        setBackground(new Color(85, 152, 230, 255));
        setVisible(true);
        setOpaque(true);
    }

    /*Functionality*/
    /**
     * Helper method for yCalc.
     *
     * @param x     value
     * @param lower bound
     * @param upper bound
     * @return boolean
     */
    private static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    /**
     * Helper function for printVertex.
     *
     * @param index number
     * @return ylocation
     */
    private int yCalc(int index) {
        double y;
        if (isBetween(index, 1, 3)) {
            y = (1.0 / 6) * this.getHeight();
        } else if (isBetween(index, 4, 5)) {
            y = (1.0 / 2) * this.getHeight();
        } else if (isBetween(index, 6, 8)) {
            y = (5.0 / 6) * this.getHeight();
        } else {
            y = (1.0 / 2) * this.getHeight();
        }
        return (int) y;    // This type of down casting cuts off everything after the decimal.
    }

    /**
     * Helper function for printVertex.
     *
     * @param index of vertex
     * @return x
     */
    private int xCalc(int index) {
        double x = (1.0 / 2) * this.getWidth() - 35;
        switch (index) {
            case 1:
            case 6:
                x = (1.0 / 6) * this.getWidth() - 35;
                break;
            case 2:
            case 7:
                x = (1.0 / 2) * this.getWidth() - 35;
                break;
            case 3:
            case 8:
                x = (5.0 / 6) * this.getWidth() - 35;
                break;
            case 4:
                x = (1.0 / 3) * this.getWidth() - 35;
                break;
            case 5:
                x = (2.0 / 3) * this.getWidth() - 35;
                break;
        }
        return (int) x;    // This type of down casting cuts off everything after the decimal.
    }

    /*Painting Functionality*/
    private void paintVertices(Graphics g) {
        ArrayList<GraphVertex> vertices = model.getVertices();
        for (GraphVertex v : vertices) {
            if (v.equals(controller.getSelected())) {
                g.setColor(new Color(145, 110, 53, 255));
                g.drawRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
                g.setColor(new Color(230, 190, 154, 255));
                g.fillRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
                g.setColor(Color.BLACK);
                g.drawString(String.valueOf(v.getElement()), v.getRectangle().x + v.getRectangle().width / 3, v.getRectangle().y + (v.getRectangle().height / 2));
            } else {
                if (v.isPreviouslySelected()) {
                    g.setColor(Color.BLACK);
                    g.drawRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
                    g.setColor(new Color(207, 219, 230, 255));
                    g.fillRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(v.getElement()), v.getRectangle().x + v.getRectangle().width / 3, v.getRectangle().y + (v.getRectangle().height / 2));
                } else {
                    int xCord = xCalc(v.getIndex());
                    int yCord = yCalc(v.getIndex());
                    v.getRectangle().x = xCord;
                    v.getRectangle().y = yCord;
                    g.setColor(Color.BLACK);
                    g.drawRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
                    g.setColor(new Color(207, 219, 230, 255));
                    g.fillRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
                    g.setColor(Color.BLACK);
                    g.drawString(String.valueOf(v.getElement()), xCord + v.getRectangle().width / 3, yCord + (v.getRectangle().height / 2));
                }
            }
        }
    }

    private void paintCursor(Graphics g) {
        if (controller.isAddingEdge()) {
            g.drawLine(controller.getSelected().getRectangle().x, controller.getSelected().getRectangle().y,
                    controller.getCurrentX(), controller.getCurrentY());
        }
    }

    private void paintEdges(Graphics g) {
        ArrayList<GraphEdge> edges = model.getEdges();
        for (GraphEdge e : edges) {
            int xCord = e.getEndpoints().get(0).getRectangle().x + e.getEndpoints().get(0).getRectangle().width/2;
            int yCord = e.getEndpoints().get(0).getRectangle().y + e.getEndpoints().get(0).getRectangle().height/2;
            int xCordDest = e.getEndpoints().get(1).getRectangle().x + e.getEndpoints().get(1).getRectangle().width/2;
            int yCordDest = e.getEndpoints().get(1).getRectangle().y + e.getEndpoints().get(1).getRectangle().height/2;
            g.drawLine(xCord, yCord, xCordDest, yCordDest);
        }
    }

    /**
     * Paints the path of the most recent search.
     */
    private void paintPath(Graphics g) {
        for (GraphVertex v : this.search.getPath()) {
            g.setColor(new Color(45, 41, 145, 255));
            g.drawRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
            g.setColor(new Color(75, 53, 230, 255));
            g.fillRect(v.getRectangle().x, v.getRectangle().y, v.getRectangle().width, v.getRectangle().height);
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(v.getElement()), v.getRectangle().x + v.getRectangle().width / 3, v.getRectangle().y
                    + (v.getRectangle().height / 2));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.paintEdges(g);
        this.paintVertices(g);
        this.paintCursor(g);
        this.paintPath(g);
    }
}