package graphController;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that creates a new node. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 * Default access modifier used.
 */
class NewNodeButton extends JButton {

    /*Constructor*/
    NewNodeButton(GraphEditor draw) {
        super(new NewNodeAction(draw));
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
        setToolTipText("Create a new node");
    }
}