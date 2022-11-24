package ca.ubc.cs304.delegates;

import ca.ubc.cs304.model.Country;
import ca.ubc.cs304.model.Ref;
import ca.ubc.cs304.model.Relation;

import java.util.ArrayList;

/**
 * This interface uses the delegation design pattern where instead of having
 * the VolleyballWindow class try to do everything, it will only
 * focus on handling the UI. The actual logic/operation will be delegated to the
 * controller class (in this case Bank).
 *
 * VolleyballWindow calls the methods that we have listed below but
 * Volleyball is the actual class that will implement the methods.
 *
 * Reference: LoginWindowDelegate and TerminalTransationDelegate from CPSC304-Sample Project
 */

public interface VolleyballWindowDelegate {
    public void databaseSetup();
    public void volleyballWindowFinished();

    /**
     * Query methods for Country class.
     */
    public void deleteCountry(String countryName);
    public void insertCountry(Country model);
    public void showCountry();
    public void updateCountry(String countryName, int population);

    public Relation[] getCountryTableData();
    public Object[][] getTableData(Relation relation, String[] conditions);
    public Ref[] getRefTableData();
    public int[][] getPopMax();
    public Object[][] getProjectionInfo(Relation relation, String[] attributes);
    public Object[][] getJoinInfo(Relation firstRelation, Relation secondRelation,String[] attributes);
    public Object[][] getAggregationHaving();
}
