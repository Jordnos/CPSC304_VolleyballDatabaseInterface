package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.VolleyballWindow;

/**
 * This is the main controller class that will orchestrate the Volleyball Database project.
 * Reference: Bank.java class in the CPSC304-JavaDemo Project.
 */
public class Volleyball implements LoginWindowDelegate {

    private DatabaseConnectionHandler dbHandler = null;
    private LoginWindow loginWindow = null;

    public Volleyball() {
        dbHandler = new DatabaseConnectionHandler();
    }

    private void start() {
        loginWindow = new LoginWindow();
        loginWindow.showFrame(this);
    }

    public void login(String username, String password) {
        boolean didConnect = dbHandler.login(username, password);

        if (didConnect) {
            loginWindow.dispose();

            VolleyballWindow volleyballWindow = new VolleyballWindow();
            volleyballWindow.setupDatabase((VolleyballWindowDelegate) this);
            volleyballWindow.showFrame((VolleyballWindowDelegate) this);
        } else {
            loginWindow.handleLoginFailed();

            if (loginWindow.hasReachedMaxLoginAttempts()) {
                loginWindow.dispose();
                System.out.println("You have exceeded your number of allowed attempts to log in :(");
                System.exit(-1);
            }
        }
    }

    /**
     * VolleyBallWindowDelegate Implementation
     *
     * VolleyballWindow instance tells us that it is done with what it's doing so we are cleaning up the connection
     * since its no longer needed.
     */
    public void volleyBallWindowFinished() {
        dbHandler.close();
        dbHandler = null;
        System.exit(0);
    }

    /**
     * VolleyballWindowDelegate Implementation
     *
     * The VolleyballWindow instance tells us that the user is fine with dropping any existing tables and creating new
     * ones for this project to use.
     */
    public void databaseSetup() {
        dbHandler.databaseSetup();
    }

    /**
     * Main method called at launch
     */
    public static void main(String[] args) {
        Volleyball volleyball = new Volleyball();
        volleyball.start();
    }

    //TODO methods for queries!!!


}
