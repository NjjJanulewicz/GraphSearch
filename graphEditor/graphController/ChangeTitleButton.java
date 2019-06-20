package graphController;

import model.GraphEditor;

import javax.swing.*;
import java.awt.event.KeyEvent;

class ChangeTitleButton extends JButton {

    /*Constructor*/
    public ChangeTitleButton(GraphEditor draw) {
        super(new ChangeTitleAction(draw));
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
        setToolTipText("Create a new node");
    }
}