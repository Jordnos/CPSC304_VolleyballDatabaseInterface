package ca.ubc.cs304.database;

import ca.ubc.cs304.model.Country;
import ca.ubc.cs304.model.GameSet;
import ca.ubc.cs304.model.Ref;
import ca.ubc.cs304.model.Relation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class handles all events related to the volleyball database
 */
public class DatabaseConnectionHandler {
    private static final String ORACLE_URL = "jdbc:oracle:thin:@localhost:1522:stu";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";
    private static final String WARNING_TAG = "[WARNING]";

    private Connection connection = null;

    public DatabaseConnectionHandler() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    public boolean login(String username, String password) {
        try {
            if (connection != null) {
                connection.close();
            }

            connection = DriverManager.getConnection(ORACLE_URL, username, password);
            connection.setAutoCommit(false);

            System.out.println("\nConnected to Oracle!");
            return true;
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            return false;
        }
    }

    private void rollbackConnection() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    // TODO: databaseSetup() and dropCountryTableIfExists() function: check if correct?
    public void databaseSetup() {
        dropCountryTableIfExists();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE Country (Cname varchar(20) PRIMARY KEY, " +
                    "Population integer)");
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        Country country1 = new Country("Canada", 38010000);
        insertCountry(country1);

        Country country2 = new Country("USA", 329500000);
        insertCountry(country2);

        Country country3 = new Country("Italy", 59550000);
        insertCountry(country3);

        Country country4 = new Country("Brazil", 212600000);
        insertCountry(country4);

        Country country5 = new Country("France", 67390000);
        insertCountry(country5);

    }

    private void dropCountryTableIfExists() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");

            while(rs.next()) {
                if(rs.getString(1).toLowerCase().equals("country")) {
                    stmt.execute("DROP TABLE Country");
                    break;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
    }

    //Query methods for Country class
    public void deleteCountry(String countryName) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Country WHERE Cname=?");
            ps.setString(1, countryName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Country " + countryName + " does not exist! :(");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void insertCountry(Country model) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Country VALUES (?,?)");
            ps.setString(1, model.getCName());
            ps.setInt(2, model.getPopulation());

            ps.executeUpdate();
            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }

    public void updateCountry(String countryName, int population) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE Country SET Population = ? WHERE Cname = ?");
            ps.setInt(1, population);
            ps.setString(2, countryName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println(WARNING_TAG + " Country " + countryName + " does not exist! :(");
            }

            connection.commit();
            ps.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
            rollbackConnection();
        }
    }



    public Object[][] getRelationInfo(Relation relation, String[] conditions) {
        ArrayList<ArrayList<Object>> a = new ArrayList<>();
        Object[][] dataArray = null;

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM " + relation.getRelationName();
            for(int i = 0; i < conditions.length; i++){
                if(i == 0)
                    query += " WHERE ";
                query += conditions[i];
                if(i < conditions.length-1)
                    query += " AND ";
            }

            ResultSet rs = stmt.executeQuery(query);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            int rowNum = 0;
            while(rs.next()) {
                a.add(new ArrayList<>());
                for(int i = 0; i < relation.getAllAttributeNames().length; i++){
                    a.get(rowNum).add(rs.getObject(rsmd.getColumnName(i+1)));
                }
                rowNum++;
            }
            if(a.isEmpty())
                dataArray = new Object[0][0];
            else{
                dataArray = new Object[a.size()][a.get(0).size()];
                for(int i = 0; i < a.size(); i++){
                    dataArray[i] = a.get(i).toArray();
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return dataArray;
    }

    public Country[] getCountryInfo() {
        ArrayList<Country> result = new ArrayList<Country>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Country");

            // TERMINAL IMPLEMENTATION
            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            System.out.println(" ");

            // display column names;
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // get column name and print it
                System.out.printf("%-20s", rsmd.getColumnName(i + 1));
            }
            System.out.printf("\n");

            while(rs.next()) {
                Country model = new Country(rs.getString("Cname"),
                        rs.getInt("Population"));
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Country[result.size()]);
    }





    public Ref[] getDivisionRef() {
        ArrayList<Ref> result = new ArrayList<Ref>();

        try {
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from Ref R WHERE NOT EXISTS (SELECT L.lid FROM League L " +
                    "WHERE NOT EXISTS (SELECT W.rid FROM WorksFor W " +
                    "WHERE L.lid = W.lid AND W.rid = R.rid))");

           // ResultSet rs = stmt.executeQuery("SELECT * from Ref");



            while(rs.next()) {
                Ref model = new Ref(rs.getInt("Rid"),
                        rs.getString("Name"),
                        rs.getInt("Salary"))
                //Ref model = new Ref(6, "bob", 100)

                {


                };
                result.add(model);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return result.toArray(new Ref[result.size()]);
    }

    public int[][] getAggregationGroup() {
        int[][] result = new int[5][2];
        try{
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT LID, AVG(Salary) as avgSalary FROM REF r, WorksFor w WHERE r.RID = w.RID GROUP BY LID");



//            result[0] = rs.getInt("LID");
//            result[1] = (rs.getInt("avgSalary"));

            int i = 0;

            while(rs.next()) {
                result[i][0] = rs.getInt("LID");
                result[i][1] = (rs.getInt("avgSalary"));
                i +=1;

            }




        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }
        System.out.println(result);
        return result;
    }

    public Object[][] getAggregationHaving() {
        ArrayList<ArrayList<Object>> a = new ArrayList<>();
        Object[][] dataArray = null;
        Relation relation = new GameSet();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT GID " +
                    "FROM GameSet " +
                    "GROUP BY GID HAVING COUNT(*)=3");

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            int rowNum = 0;
            while(rs.next()) {
                a.add(new ArrayList<>());
                for(int i = 0; i < 1; i++){
                    a.get(rowNum).add(rs.getObject(rsmd.getColumnName(i+1)));
                }
                rowNum++;
            }
            if(a.isEmpty())
                dataArray = new Object[0][0];
            else{
                dataArray = new Object[a.size()][a.get(0).size()];
                for(int i = 0; i < a.size(); i++){
                    dataArray[i] = a.get(i).toArray();
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return dataArray;
    }

    public Object[][] getProjectionInfo(Relation relation, String[] attributes) {
        ArrayList<ArrayList<Object>> a = new ArrayList<>();
        Object[][] dataArray = null;

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT ";
            for(int i = 0; i < attributes.length; i++){
                query += attributes[i];
                if(i < attributes.length-1)
                    query += ", ";
            }
            query += " FROM " + relation.getRelationName();

            ResultSet rs = stmt.executeQuery(query);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            int rowNum = 0;
            while(rs.next()) {
                a.add(new ArrayList<>());
                for(int i = 0; i < attributes.length; i++){
                    a.get(rowNum).add(rs.getObject(rsmd.getColumnName(i+1)));
                }
                rowNum++;
            }
            if(a.isEmpty())
                dataArray = new Object[0][0];
            else{
                dataArray = new Object[a.size()][a.get(0).size()];
                for(int i = 0; i < a.size(); i++){
                    dataArray[i] = a.get(i).toArray();
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return dataArray;
    }

    public Object[][] getJoinInfo(Relation firstRelation, Relation secondRelation, String[] conditions) {
        ArrayList<ArrayList<Object>> a = new ArrayList<>();
        Object[][] dataArray = null;

        try {
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM " + firstRelation.getRelationName() + " r1, " + secondRelation.getRelationName() + " r2";
            for(int i = 0; i < conditions.length; i++){
                if(i == 0)
                    query += " WHERE ";
                query += conditions[i];
                if(i < conditions.length-1)
                    query += " AND ";
            }

            ResultSet rs = stmt.executeQuery(query);

            // get info on ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();

            int rowNum = 0;
            while(rs.next()) {
                a.add(new ArrayList<>());
                for(int i = 0; i < rsmd.getColumnCount(); i++){
                    a.get(rowNum).add(rs.getObject(i+1));
                }
                rowNum++;
            }
            if(a.isEmpty())
                dataArray = new Object[0][0];
            else{
                dataArray = new Object[a.size()][a.get(0).size()];
                for(int i = 0; i < a.size(); i++){
                    dataArray[i] = a.get(i).toArray();
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println(EXCEPTION_TAG + " " + e.getMessage());
        }

        return dataArray;
    }


}
