

import javax.transaction.Transaction;
import java.sql.*;
//CRUD - create, retrieve, update, delete


public class App{

    private final String url = "jdbc:postgresql://localhost/livedata";
    private final String user = "danielhill";
    private final String password = "";

    /*
TODO CREATE A TRANSACTION AND EXECUTE IT
 */
//    private final Transaction transaction;

//    public App() {
//        transaction = new Transaction();
//    }

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */


    public Connection connect() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to the PostgreSQL server successfully.");

        // our SQL SELECT query.
        // if you only need a few columns, specify them by name instead of using "*"
        String query = "SELECT * FROM cities";

        // create the java statement
        Statement st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);

        // iterate through the java resultset
        while (rs.next()) {
            int id = rs.getInt("name");
            /*String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            Date dateCreated = rs.getDate("date_created");
            boolean isAdmin = rs.getBoolean("is_admin");
            int numPoints = rs.getInt("num_points");
*/
            // print the results
            System.out.format("%s, \n", id);
        }
        st.close();

        return null;
    }


    public static void viewTable(Connection con) throws SQLException {

        String query = "select COF_NAME, SUP_ID, PRICE, " +
                "SALES, TOTAL " +
                "from COFFEES";

        try (Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + ", " + supplierID +
                        ", " + price + ", " + sales +
                        ", " + total);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static void create(Connection con) throws SQLException {

        String query = "CREATE TABLE Persons (\n" +
                "    PersonID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Address varchar(255),\n" +
                "    City varchar(255) \n" +
                ");";
        try (Statement stmt = con.createStatement()) {

            stmt.executeQuery(query);

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void retrieve(Connection con) throws SQLException {

        String query = "Select * from company";
        try (Statement stmt = con.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String salary = rs.getString("salary");
               /* int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");*/
                System.out.println(salary);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
//    put in valid data for column in database

//    public static void update(Connection con) throws SQLException {
//
//        String query = "UPDATE Customers" +
//        "SET ContactName = 'Alfred Schmidt'";
//        try (Statement stmt = con.createStatement()) {
//
//            ResultSet rs = stmt.executeQuery(query);
//
//            while (rs.next()) {
//                String salary = rs.getString("salary");
//               /* int supplierID = rs.getInt("SUP_ID");
//                float price = rs.getFloat("PRICE");
//                int sales = rs.getInt("SALES");
//                int total = rs.getInt("TOTAL");*/
//                System.out.println(salary);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//
//    }

    public static void delete(Connection con) throws SQLException {


        String query = "DROP TABLE Persons";
        try (Statement stmt = con.createStatement()) {

            stmt.executeQuery(query);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        String url = "jdbc:postgresql://localhost:5435/";
        String user = "danielhill";
        String password = "";

        Connection conn = null;
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(url, user, password);

        App app = new App();

        app.create(conn);
        app.retrieve(conn);
        //app.update(conn);
        app.delete(conn);

    }
}
