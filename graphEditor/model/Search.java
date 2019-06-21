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

    /**
     * TODO: Stress testing.
     * Graph search implamentation of BFS
     * @param searchValue
     */
    public void breadthFirst(int searchValue) {

        // Variables //
        int index = 0;

        GraphVertex state = graph.getVertices().get(index);

        if (state.getElement() == searchValue) {
            this.setPath(state);
        }

        LinkedList<GraphVertex> frontier = new LinkedList();    // FIFO queue
        LinkedList<GraphVertex> explored = new LinkedList();    // Represetnts an explored set

        frontier.push(state);

        while (!frontier.isEmpty()) {
            index++;
            state = frontier.pop();
            explored.add(state);

            if (state.getElement() == searchValue) {
                this.setPath(state);
                return;
            }

            if (this.graph.getVertices().size() - 1 < index ) {
                System.out.println("No solution found");
                return;
            }

            GraphVertex newState = graph.getVertices().get(index);

            if (!explored.contains(newState)) {
                frontier.push(newState);
            }
        }
    }

    /**
     * Graph search implamentation of BFS
     * @param searchValue: The value being searched for.
     */
    public void depthFirst(int searchValue) {
        int index = 0;

        LinkedList<GraphVertex> frontier = new LinkedList();    // FIFO queue
        LinkedList<GraphVertex> explored = new LinkedList();    // Represetnts an explored set

        frontier.push(graph.getVertices().get(index));

        while (!frontier.isEmpty()) {
            GraphVertex state = frontier.pop();

            if (state.getElement() == searchValue) {
                this.setPath(state);
                return;
            }

            explored.add(state);

            for (GraphVertex child : state.getChildren()) {
                if (!explored.contains(child) && !frontier.contains(child)) {
                    frontier.push(child);
                }
            }
        }
    }

//    def depth_first_graph_search(problem):
//    frontier = [(Node(problem.initial))]  # Stack
//            explored = set()
//    while frontier:
//    node = frontier.pop()
//            if problem.goal_test(node.state):
//            return node
//        explored.add(node.state)
//            frontier.extend(child for child in node.expand(problem)
//            if child.state not in explored and
//    child not in frontier)
//            return None
//
//
//    def breadth_first_graph_search(problem):
//    node = Node(problem.initial)
//    if problem.goal_test(node.state):
//            return node
//            frontier = deque([node])
//    explored = set()
//    while frontier:
//    node = frontier.popleft()
//            explored.add(node.state)
//            for child in node.expand(problem):
//            if child.state not in explored and child not in frontier:
//            if problem.goal_test(child.state):
//            return child
//                frontier.append(child)
//            return None

}