package util;

import java.sql.*;

/**
 * Created by alex on 10/12/2016.
 */
public class JDBCUtil {

    /**
     * The url to get connection to database.
     */
    private String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false&characterEncoding=utf-8";

    /**
     * User name for database, maybe you need to fix it.
     */
    private String user = "root";

    /**
     * password, fix it if necessary
     */
    private String password = "root";


    public JDBCUtil(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the connection to DB
     * @return Connection
     */
    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * @param rs ResultSet
     * @param st Statement
     * @param conn Connection
     */
    public void close(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) { rs.close(); }
            if (st != null) { st.close(); }
            if (conn != null) { conn.close(); }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
