import java.sql.*;

public class SQLHelper {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/mydatabase";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public static void executeUpdate(String sql) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } finally {
            close(conn, stmt, null);
        }
    }

    public static ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            close(conn, stmt, rs);
            throw e;
        }
    }
}


/*
 * getConnection() 메서드는 데이터베이스에 연결하는 메서드이고, close() 메서드는 사용한 리소스를 닫는 메서드입니다. executeUpdate() 메서드는 INSERT, UPDATE, DELETE 쿼리를 실행하고, executeQuery() 메서드는 SELECT 쿼리를 실행합니다.
 * 이러한 JDBCUtil 클래스를 활용하여 데이터베이스 연결 및 쿼리 실행을 수행할 수 있습니다. 예를 들어, 아래와 같은 코드를 사용하여 SELECT 쿼리를 실행할 수 있습니다.
 try {
    ResultSet rs = JDBCUtil.executeQuery("SELECT * FROM mytable");
    while (rs.next()) {
        // 데이터 처리
    }
} catch (SQLException | ClassNotFoundException e) {
    e.printStackTrace();
}
 */