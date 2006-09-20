package webdsl.users;

import java.sql.*;

public interface ProcessResultSet
{
    Object process(ResultSet rs);
}
