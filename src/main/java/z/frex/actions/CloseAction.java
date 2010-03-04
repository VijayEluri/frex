package z.frex.actions;

import z.ui.application.ApplicationWindow;

import javax.swing.JOptionPane;
import java.text.MessageFormat;

public class CloseAction extends PlaneViewAction {
    public static final String ID = "z.frex.actions.close"; //$NON-NLS-1$

    private boolean canceledByUser;

    public CloseAction(ApplicationWindow window) {
        super(window, ID);
        setText("&Schlie�en");
        setToolTipText("Schlie�en");
    }

    public boolean isCanceledByUser() {
        return canceledByUser;
    }

    public void run() {

        canceledByUser = false;
        if (getPlaneView().isDirty()) {
            final int answer = promptForSave();
            if (answer == JOptionPane.YES_OPTION) { // Yes, save
                final SaveAction action = new SaveAction(getWindow());
                action.run();
                canceledByUser = action.isCanceledByUser();
            } else if (answer == JOptionPane.NO_OPTION) { // No, not save
                // OK
            } else if (answer == JOptionPane.CANCEL_OPTION) { // Cancel close
                canceledByUser = true;
            }
        }

        if (canceledByUser) {
            return;
        }
        getWindow().getPage().closePageComponent(getPlaneView(), true);
    }

    private int promptForSave() {
        return JOptionPane.showConfirmDialog(getWindow().getShell(),
                                             MessageFormat.format("{0} speichern?",
                                                                  getPlaneView().getDisplayName()),
                                             "Schlie�en",
                                             JOptionPane.YES_NO_CANCEL_OPTION,
                                             JOptionPane.QUESTION_MESSAGE);
    }
}
