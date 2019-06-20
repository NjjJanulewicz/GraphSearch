package operation;

import model.GraphEditor;
import model.GraphVertex;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import java.util.Iterator;

public class newNodeOperation extends AbstractUndoableEdit {
    private final GraphEditor editor;
    private GraphVertex node;

    /*Constructor*/
    public newNodeOperation(GraphEditor editor, int inputValue) {
        this.editor = editor;
        newNode(inputValue);
    }

    /*Functionality*/
    private void newNode(int ele) {
        node = new GraphVertex(ele);
        node.setIndex(editor.getGraphModel().getVertices().size());
        editor.getGraphModel().addVertices(node);
    }

    @Override
    public void redo() throws CannotRedoException {
        super.redo();
        editor.getGraphModel().addVertices(node);
    }

    @Override
    public void undo() throws CannotUndoException {
        super.undo();
        Iterator vertexIterator = editor.getGraphModel().getVertices().iterator();
            GraphVertex vertex = (GraphVertex) vertexIterator.next();
            while(!vertex.equals(node)&&vertexIterator.hasNext()){
                vertex = (GraphVertex) vertexIterator.next();
            }
         editor.getGraphModel().removeVertex(vertex);
    }
}

