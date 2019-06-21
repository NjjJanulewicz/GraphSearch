package controller;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;


/**
 * {@link javax.swing.JOptionPane} that can be modified for creating resizable
 * dialogs and so on. Default implementation of {@link javax.swing.JOptionPane}
 * creates allways unresizable dialog.
 *
 * @version $Name:  $ - $Revision: 1.1.1.1 $ - $Date: 2005/04/07 18:36:21 $
 */
public class CustomJOptionPane extends JOptionPane {

    private boolean resizable;

    public CustomJOptionPane() {
        super();
    }

    /**
     * @param message
     */
    public CustomJOptionPane(Object message) {
        super(message);
    }

    /**
     * @param message
     * @param messageType
     */
    public CustomJOptionPane(Object message, int messageType) {
        super(message, messageType);
    }

    /**
     * @param message
     * @param messageType
     * @param optionType
     */
    public CustomJOptionPane(Object message, int messageType, int optionType) {
        super(message, messageType, optionType);
    }

    /**
     * @param message
     * @param messageType
     * @param optionType
     * @param icon
     */
    public CustomJOptionPane(Object message, int messageType, int optionType,
                                 Icon icon) {
        super(message, messageType, optionType, icon);
    }

    /**
     * @param message
     * @param messageType
     * @param optionType
     * @param icon
     * @param options
     */
    public CustomJOptionPane(Object message, int messageType, int optionType,
                                 Icon icon, Object[] options) {
        super(message, messageType, optionType, icon, options);
    }

    /**
     * @param message
     * @param messageType
     * @param optionType
     * @param icon
     * @param options
     * @param initialValue
     */
    public CustomJOptionPane(Object message, int messageType, int optionType,
                                 Icon icon, Object[] options, Object initialValue) {
        super(message, messageType, optionType, icon, options, initialValue);
    }


    /**
     * @see javax.swing.JOptionPane#createDialog(java.awt.Component, java.lang.String)
     */
    public JDialog createDialog(Component parentComponent, String title)
            throws HeadlessException {
        JDialog dialog = super.createDialog(parentComponent, title);
        dialog.setResizable(isResizable());
        return dialog;
    }


    /**
     * @see javax.swing.JOptionPane#createInternalFrame(java.awt.Component, java.lang.String)
     */
    public JInternalFrame createInternalFrame(Component parentComponent,
                                              String title) {
        JInternalFrame frame = super.createInternalFrame(parentComponent, title);
        frame.setResizable(isResizable());
        return frame;
    }

    public void setResizable(boolean b) {
        this.resizable = b;
    }

    public boolean isResizable() {
        return resizable;
    }
}