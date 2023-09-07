import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExecuteNonQuery {
    public static int executeNonQuery(Connection connection, String commandText, CommandType commandType, IDataParameter[] parameters) throws SQLException {
        try (PreparedStatement command = connection.prepareStatement(commandText)) {
            PrepareCommand.PrepareCommand(connection, command, commandType, commandText, parameters);
            return command.executeUpdate();
        }
    }
}
