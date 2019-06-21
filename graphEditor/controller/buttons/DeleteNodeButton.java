package controller.buttons;

import controller.actions.DeleteNodeAction;
import model.GraphEditor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Button that deletes a node. It uses the Action API to perform its action
 * which means that this is merely a default configuration for this button.
 * Default access modifier used.
 */
public class DeleteNodeButton extends JButton {

    /*Constructor*/
    public DeleteNodeButton(GraphEditor draw) {
        super(new DeleteNodeAction(draw));
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
        setToolTipText("Delete a node");
    }
}