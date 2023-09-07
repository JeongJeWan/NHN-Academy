import java.sql.*;

public class IDataParameter {
    private Object value;
    private int dbType;
    private int direction;
    private int sqlType;

    public IDataParameter(Object value, int dbType, int direction, int sqlType) {
        this.value = value;
        this.dbType = dbType;
        this.direction = direction;
        this.sqlType = sqlType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getDbType() {
        return dbType;
    }

    public void setDbType(int dbType) {
        this.dbType = dbType;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSqlType() {
        return sqlType;
    }

    public void setSqlType(int sqlType) {
        this.sqlType = sqlType;
    }

    public static IDataParameter inputParam(Object value, int dbType) {
        return new IDataParameter(value, dbType, ParameterDirection.Input, Types.NULL);
    }

    public static IDataParameter outputParam(int dbType, int sqlType) {
        return new IDataParameter(null, dbType, ParameterDirection.Output, sqlType);
    }

    public static IDataParameter inputOutputParam(Object value, int dbType, int sqlType) {
        return new IDataParameter(value, dbType, ParameterDirection.InputOutput, sqlType);
    }

    public static IDataParameter returnValueParam(int dbType) {
        return new IDataParameter(null, dbType, ParameterDirection.ReturnValue, Types.NULL);
    }
}
