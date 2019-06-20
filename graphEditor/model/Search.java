package model;

import java.util.LinkedList;

public class Search {

    // Global Variables //
    private final GraphModel graph;    // Compositiion
    private LinkedList path = new LinkedList<GraphVertex>();    // The path we will return.

    // Constructor //
    public Search(GraphModel g) {
        graph = g;
    }

    // Getters //
    public LinkedList<GraphVertex> getPath() {
        return path;
    }

    // Setters //
    public void setPath(GraphVertex node) {

        while (node != null) {
            path.add(node);
            node = node.getParent();
        }
    }

    // Helper functions //

    // Search algorithms //
    public void breadthFirst(int searchValue) {

        // Variables //
        int index = 0;

        GraphVertex state = graph.getVertices().get(index);
        if (state.getElement() == searchValue) {
            this.setPath(state);
        }

        LinkedList<GraphVertex> fringe = new LinkedList();    // FIFO queue
        LinkedList<GraphVertex> explored = new LinkedList();    // Represetnts an explored set

        fringe.push(state);

        while (!fringe.isEmpty()) {
            index++;
            state = fringe.pop();
            explored.add(state);

            if (state.getElement() == searchValue) {
                this.setPath(state);
                return;
            }

//            if (this.graph) {    TODO:  Important check if index is greater than index in the graph,
            //                            If so stop the search and report no solution found

            GraphVertex newState = graph.getVertices().get(index);

            if (!explored.contains(newState)) {
                fringe.push(newState);
            }
        }
    }
}