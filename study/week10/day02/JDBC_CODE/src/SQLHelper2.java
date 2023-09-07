import java.sql.*;
import java.util.Hashtable;

public abstract class SQLHelper2 {
    public static final String connString = "Server=localhost;Database=DataMotionMovieDatabase;User ID=sa;Password=kogpsd1!;";
    private static Hashtable parameterCache = new Hashtable();

    public static ResultSet ExecuteReader(String sqlQuery) throws SQLException {
        Connection myConnection = DriverManager.getConnection(connString);
        PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);

        try {
            PrepareCommand(myConnection, myCommand, CommandType.Text, null, sqlQuery, null);
            ResultSet myReader = myCommand.executeQuery();
            return myReader;
        } catch (Exception e) {
            myConnection.close();
            throw new DataAccessHelperException(e.toString());
        }
    }

    public static ResultSet ExecuteReader(String sqlQuery, CommandType commandType) throws SQLException {
        Connection myConnection = DriverManager.getConnection(connString);
        PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);

        try {
            PrepareCommand(myConnection, myCommand, commandType, null, sqlQuery, null);
            ResultSet myReader = myCommand.executeQuery();
            return myReader;
        } catch (Exception e) {
            myConnection.close();
            throw new DataAccessHelperException(e.toString());
        }
    }

    public static ResultSet ExecuteReader(String sqlQuery, CommandType commandType, IDataParameter... parameters) throws SQLException {
        Connection myConnection = DriverManager.getConnection(connString);
        PreparedStatement myCommand = myConnection.prepareStatement(sqlQuery);

        try {
            PrepareCommand(myConnection, myCommand, commandType, null, sqlQuery, parameters);
            ResultSet myReader = myCommand.executeQuery();
            return myReader;
        } catch (Exception e) {
            myConnection.close();
            throw new DataAccessHelperException(e.toString());
        }
    }

    public static Object ExecuteScalar(String sqlQuery) throws SQLException {
        PreparedStatement myCommand = null;
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PrepareCommand(myConnection, myCommand, CommandType.Text, null, sqlQuery, null);
            Object result = myCommand.executeQuery();
            return result;
        }
    }

    public static Object ExecuteScalar(String sqlQuery, CommandType commandType) throws SQLException {
        PreparedStatement myCommand = null;
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PrepareCommand(myConnection, myCommand, commandType, null, sqlQuery, null);
            Object result = myCommand.executeQuery();
            return result;
        }
    }

    public static Object ExecuteScalar(String sqlQuery, CommandType commandType, IDataParameter... parameters) throws SQLException {
        PreparedStatement myCommand = null;
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PrepareCommand(myConnection, myCommand, commandType, null, sqlQuery, parameters);
            Object result = myCommand.executeQuery();
            myCommand.clearParameters();
            return result;
        }
    }

    public static int ExecuteNonQuery(String sqlQuery) throws SQLException {
        PreparedStatement myCommand = null;
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PrepareCommand(myConnection, myCommand, CommandType.Text, null, sqlQuery, null);
            int result = myCommand.executeUpdate();
            return result;
        }
    }

    public static int ExecuteNonQuery(String sqlQuery, CommandType commandType) throws SQLException {
        PreparedStatement myCommand = null;
        try (Connection myConnection = DriverManager.getConnection(connString)) {
            PrepareCommand(myConnection, myCommand, commandType, null, sqlQuery, null);
            int result = myCommand.executeUpdate();
            return result;
        }
    }

    public static int ExecuteNonQuery(String sqlQuery, CommandType commandType) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, commandType, null);
            int result = stmt.executeUpdate();
            return result;
        }
    }

    public static int ExecuteNonQuery(String sqlQuery, CommandType commandType, IDataParameter... parameters) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, commandType, null, parameters);
            int result = stmt.executeUpdate();
            return result;
        }
    }

    public static int ExecuteNonQuery(String sqlQuery, IDbTransaction transaction) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, CommandType.Text, (SqlTransaction)transaction, null);
            int result = stmt.executeUpdate();
            return result;
        }
    }

    public static int ExecuteNonQuery(String sqlQuery, IDbTransaction transaction, IDataParameter... parameters) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, CommandType.Text, (SqlTransaction)transaction, parameters);
            int result = stmt.executeUpdate();
            return result;
        }
    }

    public static DataTable ExecuteTable(String sqlQuery) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            DataTable table = new DataTable();
            table.Load(rs);
            return table;
        }
    }

    public static DataTable ExecuteTable(String sqlQuery, CommandType commandType) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, commandType, null);
            ResultSet rs = stmt.executeQuery();
            DataTable table = new DataTable();
            table.Load(rs);
            return table;
        }
    }

    public static DataTable ExecuteTable(String sqlQuery, CommandType commandType, IDataParameter... parameters) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, commandType, null, parameters);
            ResultSet rs = stmt.executeQuery();
            DataTable table = new DataTable();
            table.Load(rs);
            return table;
        }
    }

    public static DataSet ExecuteDataSet(String sqlQuery) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            DataSet ds = new DataSet();
            ds.Load(rs, "Table");
            return ds;
        }
    }

    public static DataSet ExecuteDataSet(String sqlQuery, CommandType commandType, IDataParameter... parameters) throws SQLException {
        try (Connection conn = DriverManager.getConnection(connString)) {
            PreparedStatement stmt = PrepareCommand(conn, sqlQuery, commandType, null, parameters);
            ResultSet rs = stmt.executeQuery();
            DataSet ds = new DataSet();
            ds.Load(rs, "Table");
            return ds;
        }
    }

    private static void PrepareCommand(Connection myConnection, PreparedStatement myCommand, CommandType commandType,
        SqlTransaction myTransaction, String sqlQuery, IDataParameter... parameters) throws SQLException {

        if (myConnection.isClosed())
            myConnection.open();

        myCommand.setConnection(myConnection);
        myCommand.setCommandText(sqlQuery);
        myCommand.setCommandType(commandType);

        if (myTransaction != null)
            myCommand.setTransaction(myTransaction);

        if (parameters != null) {
            for (IDataParameter p : parameters) {
                myCommand.addParameter(p);
            }
        }
    }

    public static void CacheParameters(String cacheKey, IDataParameter... parameters) {
        parameterCache.put(cacheKey, parameters);
    }

    public static SqlParameter[] GetCachedParameters(String cacheKey) throws CloneNotSupportedException {
        SqlParameter[] cachedParameters = (SqlParameter[]) parameterCache.get(cacheKey);

        if (cachedParameters == null) {
            return null;
        }

        SqlParameter[] cloneParameters = new SqlParameter[cachedParameters.length];

        for (int i = 0, j = cloneParameters.length; i < j; i++) {
            cloneParameters[i] = (SqlParameter) ((ICloneable) cachedParameters[i]).clone();
        }

        return cloneParameters;
    }



}
