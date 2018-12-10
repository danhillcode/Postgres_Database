

import java.sql.*;

/**
 *
 * @author postgresqltutorial.com
 */
public class App{

    private final String url = "jdbc:postgresql://localhost/livedata";
    private final String user = "danielhill";
    private final String password = "";

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        App app = new App();
        app.connect();
    }
}
