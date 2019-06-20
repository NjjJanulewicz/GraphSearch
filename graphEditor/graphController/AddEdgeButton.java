package graphController;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that creates a new edge. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 * Default access modifier used.
 */
class AddEdgeButton extends JButton {

    /*Constructor*/
    AddEdgeButton(GraphEditor draw) {
        super(new AddEdgeAction(draw));
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
        setToolTipText("Select an edge first");
    }
}