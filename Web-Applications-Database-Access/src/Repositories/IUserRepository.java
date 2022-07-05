package Repositories;

import java.sql.SQLException;
public interface IUserRepository {
boolean validateUser(String x, String y) throws SQLException;
}
