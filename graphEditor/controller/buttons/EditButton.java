package controller.buttons;

import controller.actions.EditAction;
import model.GraphEditor;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditButton extends JButton {

    /*Constructor*/
    public EditButton(GraphEditor draw) {
        super(new EditAction(draw));
        ButtonProperties();
    }

    /*Functionality */
    /**
     * Defines the visible properties the button will have.
     */
    private void ButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_C);
        setToolTipText("Edit Node properties");
    }
}