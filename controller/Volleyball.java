package ca.ubc.cs304.controller;

import ca.ubc.cs304.database.DatabaseConnectionHandler;
import ca.ubc.cs304.delegates.LoginWindowDelegate;
import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Country;
import ca.ubc.cs304.model.Ref;
import ca.ubc.cs304.model.Relation;
import ca.ubc.cs304.ui.LoginWindow;
import ca.ubc.cs304.ui.VolleyballWindow;

/**
 * This is the main controller class that will orchestrate the Volleyball Database project.
 * Reference: Bank.java class in the CPSC304-JavaDemo Project.
 */
public class Volleyball implements LoginWindowDelegate, VolleyballWindowDelegate {

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

            //UI IMPLEMENTATION
            VolleyballWindow volleyballWindow = new VolleyballWindow();
            volleyballWindow.setupDatabase(this);
            volleyballWindow.showFrame(this);

            //CONSOLE IMPLEMENTATION
            //TerminalVolleyball terminalVolleyball = new TerminalVolleyball();
            //terminalVolleyball.setupDatabase(this);
            //terminalVolleyball.showMainMenu(this);
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
     * VolleyballWindow instance tells us that it is done with what it's doing, so we are cleaning up the connection
     * since its no longer needed.
     */
    public void volleyballWindowFinished() {
        dbHandler.close();
        dbHandler = null;
        System.exit(0);
    }

    /**
     * VolleyballWindowDelegate Implementation
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

    //Query Methods for Country class.
    /**
     * VolleyballWindowDelegate Implementation
     * Delete country with given country name
     */
    public void deleteCountry(String countryName) {
        dbHandler.deleteCountry(countryName);
    }

    /**
     * VolleyballWindowDelegate Implementation
     * Insert a country with the given info
     */
    public void insertCountry(Country model) {
        dbHandler.insertCountry(model);
    }

    /**
     * VolleyballWindowDelegate Implementation
     * Update a country's population for a specific country name
     */
    public void updateCountry(String countryName, int population) {
        dbHandler.updateCountry(countryName, population);
    }

    /**
     * VolleyballWindowDelegate Implementation
     * Displays information about countries
     */
    public void showCountry() {

        //TERMINAL IMPLEMENTATION
        Country[] models = dbHandler.getCountryInfo();

        for (int i = 0; i < models.length; i++) {
            Country model = models[i];

            System.out.printf("%-10.10s", model.getCName());
            System.out.printf("%-20.20s", model.getPopulation());
            System.out.println();
        }
    }

    public Relation[] getCountryTableData() {
        return dbHandler.getCountryInfo();
    }

    public Object[][] getTableData(Relation relation, String[] conditions) {
        return dbHandler.getRelationInfo(relation, conditions);
    }


    public Ref[] getRefTableData() {
        return dbHandler.getDivisionRef();
    }

    public int[][] getPopMax() {
        return dbHandler.getAggregationGroup();
    }

    public Object[][] getProjectionInfo(Relation relation, String[] attributes) {
        return dbHandler.getProjectionInfo(relation, attributes);
    }

    public Object[][] getJoinInfo(Relation firstRelation, Relation secondRelation, String[] attributes) {
        return dbHandler.getJoinInfo(firstRelation, secondRelation, attributes);
    }


}
