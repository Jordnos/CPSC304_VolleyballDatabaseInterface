package ca.ubc.cs304.ui;

//import ca.ubc.cs304.delegates.TerminalTransactionsDelegate;
import ca.ubc.cs304.delegates.VolleyballWindowDelegate;
import ca.ubc.cs304.model.Country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TerminalVolleyball is only responsible for handling terminal text inputs for the volleyball controller.
 */
public class TerminalVolleyball {
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";
    private static final int INVALID_INPUT = Integer.MIN_VALUE;
    private static final int EMPTY_INPUT = 0;

    private BufferedReader bufferedReader = null;
    private VolleyballWindowDelegate delegate = null;

    public TerminalVolleyball() {
    }

    public void setupDatabase(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;

        while (choice != 1 && choice != 2) {
            System.out.println("If you have a table called Country in your database (capitialization of the name does not matter), it will be dropped and a new Country table will be created.\nIf you want to proceed, enter 1; if you want to quit, enter 2.");

            choice = readInteger(false);

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        delegate.databaseSetup();
                        break;
                    case 2:
                        handleQuitOption();
                        break;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.\n");
                        break;
                }
            }
        }
    }

    /**
     * Displays simple text interface
     */
    public void showMainMenu(VolleyballWindowDelegate delegate) {
        this.delegate = delegate;

        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = INVALID_INPUT;

        while (choice != 5) {
            System.out.println();
            System.out.println("1. Insert country");
            System.out.println("2. Delete country");
            System.out.println("3. Update country's population");
            System.out.println("4. Show countries");
            System.out.println("5. Quit");
            System.out.print("Please choose one of the above 5 options: ");

            choice = readInteger(false);

            System.out.println(" ");

            if (choice != INVALID_INPUT) {
                switch (choice) {
                    case 1:
                        handleInsertOption();
                        break;
                    case 2:
                        handleDeleteOption();
                        break;
                    case 3:
                        handleUpdateOption();
                        break;
                    case 4:
                        delegate.showCountry();
                        break;
                    case 5:
                        handleQuitOption();
                        break;
                    default:
                        System.out.println(WARNING_TAG + " The number that you entered was not a valid option.");
                        break;
                }
            }
        }
    }

    private void handleUpdateOption() {
        String Cname = null;
        while (Cname == null || Cname.length() <= 0) {
            System.out.print("Please enter the name of the country you wish to update: ");
            Cname = readLine().trim();
        }

        int Population = INVALID_INPUT;
        while (Population == INVALID_INPUT) {
            System.out.print("Please enter the country's population you wish to update: ");
            Population = readInteger(false);
        }

        delegate.updateCountry(Cname, Population);
    }

    private void handleDeleteOption() {
        String Cname = null;
        while (Cname == null || Cname.length() <= 0) {
            System.out.print("Please enter the name of the country you wish to delete: ");
            Cname = readLine().trim();
            if (Cname != null && Cname.length() > 0) {
                delegate.deleteCountry(Cname);
            }
        }
    }

    private void handleInsertOption() {
        String Cname = null;
        while (Cname == null || Cname.length() <= 0) {
            System.out.println("Please enter the name of the country you wish to insert: ");
            Cname = readLine().trim();
        }

        int Population = INVALID_INPUT;
        while (Population == INVALID_INPUT) {
            System.out.println("Please enter the population of the country you wish to insert: ");
            Population = readInteger(false);
        }

        Country model = new Country(Cname, Population);
        delegate.insertCountry(model);
    }

    private void handleQuitOption() {
        System.out.println("Good Bye!");

        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("IOException!");
            }
        }

        delegate.volleyballWindowFinished();
    }

    private int readInteger(boolean allowEmpty) {
        String line = null;
        int input = INVALID_INPUT;
        try {
            line = bufferedReader.readLine();
            input = Integer.parseInt(line);
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        } catch (NumberFormatException e) {
            if (allowEmpty && line.length() == 0) {
                input = EMPTY_INPUT;
            } else {
                System.out.println(WARNING_TAG + " Your input was not an integer");
            }
        }
        return input;
    }

    private String readLine() {
        String result = null;
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        return result;
    }


}
