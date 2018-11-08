package dbConnection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private static class Database {

        private String dbUrl;
        private String dbUsername;
        private String dbPassword;

        /**
         * Create instance of database by reading the details from db.properties
         */
        public Database() {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            InputStream is = cl.getResourceAsStream("resource/db.properties");
            Properties properties = new Properties();

            try {
                if (is == null) {
                    throw new FileNotFoundException();
                }
                properties.load(is);

                dbUrl = properties.getProperty("database");
                dbUsername = properties.getProperty("dbuser");

                String osName = System.getProperty("os.name");
                if (osName.equals("Linux")) {
                    // in production environment, use aws.db.password
                    dbPassword = properties.getProperty("aws.db.password");
                } else {
                    // in local environment, use db.password
                    dbPassword = properties.getProperty("dbpassword");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getDbUrl() {
            return dbUrl;
        }

        public String getDbUsername() {
            return dbUsername;
        }

        public String getDbPassword() {
            return dbPassword;
        }

    }

    /**
     * Static Initialization
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class can be called to access database connection
     *
     * @return Connection the database connection
     * @throws SQLException This will be thrown if database is not found
     * @throws ClassNotFoundException If db not found
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Database db = new Database();
        return DriverManager.getConnection(db.getDbUrl(), db.getDbUsername(), db.getDbPassword());
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {

        }

    }

}
