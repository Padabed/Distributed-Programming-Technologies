package Repositories;

import java.sql.SQLException;
import java.util.List;

public interface IResourceRepository {
    List<String> getAllRecourseNames(String login, String password) throws SQLException;
    String getAllRecourceDetails(String resourceName) throws SQLException;
}
