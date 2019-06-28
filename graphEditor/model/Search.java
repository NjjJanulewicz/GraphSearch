package model;

import java.util.LinkedList;

public class Search {

    // Global Variables //
    int MAXSIZE = 32;
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
    public int minimum(int limit, int nodeValue) {
        if (limit < nodeValue) {
            return limit;
        } else {
            return nodeValue;
        }
    }

    public GraphVertex lowest(LinkedList<GraphVertex> successors) {
        GraphVertex lowest = null;

        for (GraphVertex node : successors) {
            if (node.getHeuristic() < lowest.getHeuristic()) {
                lowest = node;
            }
        }
        return lowest;
    }

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
     * TODO: Stress testing.
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

    /**
     * Used w/ greedy, astar, and ucs.
     * @param searchValue
     * @param initalState
     * @param limit
     */
    public GraphVertex bestFirstSearch(int searchValue, GraphVertex initalState, int limit) {
        int fLimit = limit;
        int index = 0;
        GraphVertex state = graph.getVertices().get(index);
        if (state.getElement() == searchValue) {
            this.setPath(state);
        }

        LinkedList<GraphVertex> successors = new LinkedList();

        for (GraphVertex child : state.getChildren()) {
            successors.push(child);
        }

        if (successors.isEmpty()) {
            System.out.println("Failure, no solution found");
            return null;
        }

        for (GraphVertex successor : successors) {
            // Update f value
        }

        while (!successors.isEmpty()) {
            GraphVertex best = this.lowest(successors);

            if (best.getHeuristic() > fLimit) {
                System.out.println("Failure, no solution found");
                return null;
            }
            successors.remove(best);
            GraphVertex alternative = this.lowest(successors);
            GraphVertex result = bestFirstSearch(searchValue, best, minimum(fLimit, alternative.getHeuristic()));
            if (result != null) {
                return result;
            }
        }
        return null;
    }

//                function RBFS(problem, node,f limit) returns a solution, or failure and a new f-cost limit
//            if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
//            successors ←[]
//            for each action in problem.ACTIONS(node.STATE) do
//                add CHILD-NODE(problem, node, action) into successors
//            if successors is empty then return failure, ∞
//            for each s in successors do /* update f with value from previous search, if any */
//                s.f ← max(s.g + s.h, node.f))
//            loop do
//                best ← the lowest f-value node in successors
//                if best.f > f limit then return failure, best.f
//                alternative ←the second-lowest f-value among successors
//                result, best.f ← RBFS(problem, best, min(f limit, alternative))
//                if result = failure then return result

    /**
     * TODO: Finish Implementation
     * @param searchValue
     */
    public void RecursiveBestFirst(int searchValue) {
        bestFirstSearch(searchValue, graph.getVertices().get(0), Integer.MAX_VALUE);
    }

//        function RECURSIVE-BEST-FIRST-SEARCH(problem) returns a solution, or failure
//            return RBFS(problem, MAKE-NODE(problem.INITIAL-STATE),∞)
//


//        def best_first_graph_search(problem, f):
//
//        """Search the nodes with the lowest f scores first.
//        You specify the function f(node) that you want to minimize; for example,
//        if f is a heuristic estimate to the goal, then we have greedy best
//        first search; if f is node.depth then we have breadth-first search.
//                There is a subtlety: the line "f = memoize(f, 'f')" means that the f
//        values will be cached on the nodes as they are computed. So after doing
//        a best first search you can examine the f values of the path returned."""

//        f = memoize(f, 'f')
//        node = Node(problem.initial)
//        frontier = PriorityQueue('min', f)
//        frontier.append(node)
//        explored = set()
//        while frontier:
//        node = frontier.pop()
//        if problem.goal_test(node.state):
//        return node
//        explored.add(node.state)
//        for child in node.expand(problem):
//        if child.state not in explored and child not in frontier:
//        frontier.append(child)
//        elif child in frontier:
//        if f(child) < frontier[child]:
//        del frontier[child]
//        frontier.append(child)
//        return None

    /**
     * TODO: Finish Implementation
     * @param searchValue
     */
    public void greedy(int searchValue) {
        // Greedy best-first search is accomplished by specifying f(n) = h(n).

    }
}