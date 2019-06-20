package graphController;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.KeyEvent;

class RemoveEdgeButton extends JButton {

    /*Constructor*/
    public RemoveEdgeButton(GraphEditor draw) {
        super(new RemoveEdgeAction(draw));
        ButtonProperties();
    }

    /*Functionality*/
    /**
     * Defines the visible properties the button will have.
     */
    private void ButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_C);
        setToolTipText("Add an edge");
    }
}