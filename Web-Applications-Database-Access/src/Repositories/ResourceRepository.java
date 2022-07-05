package Repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResourceRepository implements IResourceRepository {
    String userTableName = "\"User\"";
    String resourceTableName = "\"resource\"";
    String u_r_tableName = "\"user_resource\"";
    DataSource _datasource;
    public ResourceRepository(DataSource ds)
    {
        _datasource=ds;
    }
    @Override
    public List<String> getAllRecourseNames(String login, String password) throws SQLException {
        PreparedStatement st;
        Connection connection=null;
        List<String> resNames=new ArrayList<>();
        try {
            connection=_datasource.getConnection();
            st = connection
                    .prepareStatement("select r_name from " + resourceTableName + " r join "+u_r_tableName+" ur on r.r_id=ur.r_id join "+userTableName+" u on u.u_id=ur.u_id where u_login=? and u_password=?");
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            while ( rs.next() )
            {
                String res = rs.getString("r_name");
                resNames.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection!=null) connection.close();
        return resNames;

    }

    @Override
    public String getAllRecourceDetails(String resourceName) throws SQLException {
        PreparedStatement st;
        Connection connection=null;
        String allRes="";
        try {
            connection=_datasource.getConnection();
            st = connection
                    .prepareStatement("select r_name, r_content from " + resourceTableName + " where r_name=?");
            st.setString(1, resourceName);
            ResultSet rs = st.executeQuery();
            while ( rs.next() )
            {
                String res1 = rs.getString("r_name");
                String res = rs.getString("r_content");
                allRes+="Resource name: "+res1+"<br> Resource description: "+res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection!=null) connection.close();
        return  allRes;
    }
}
