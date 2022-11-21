package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.VolleyballWindowDelegate;

import javax.swing.*;
import java.io.BufferedReader;

/**
 * This class is only response for displaying and handling the volleyball GUI.
 */
public class VolleyballWindow extends JFrame {
    private BufferedReader bufferedReader = null;
    private VolleyballWindowDelegate delegate;

    public VolleyballWindow() {
        super("Volleyball");
    }

    public void setupDatabase(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        delegate.databaseSetup();
    }

    public void showFrame(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;
        //TODO make gui
    }
}
