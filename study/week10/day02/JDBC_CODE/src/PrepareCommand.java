import java.sql.*;
import java.util.Hashtable;

public class PrepareCommand {
    public static void PrepareCommand(Connection connection, PreparedStatement command, CommandType commandType, String commandText, IDataParameter[] parameters) throws SQLException {
        commandText = commandText.trim();
        commandType = (commandText.startsWith("{") && commandText.endsWith("}")) ? CommandType.StoredProcedure : CommandType.Text;

        command.clearParameters();
        command.setFetchSize(1000);
        command.setFetchDirection(ResultSet.FETCH_FORWARD);
        command.setQueryTimeout(60);

        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                IDataParameter parameter = parameters[i];
                if (parameter != null) {
                    if (parameter.getDirection() == ParameterDirection.Input) {
                        command.setObject(i + 1, parameter.getValue());
                    } else if (parameter.getDirection() == ParameterDirection.Output || parameter.getDirection() == ParameterDirection.InputOutput) {
                        // command.registerOutParameter(i + 1, parameter.getDbType());
                    }
                }
            }
        }
    }
}
