package Repositories;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository implements IUserRepository {
    String tableName = "\"User\"";

    DataSource _datasource;

    public UserRepository(DataSource ds) {
        _datasource = ds;
    }

    public boolean validateUser(String l, String p) throws SQLException {
        Connection connection = null;
        boolean odp = true;
        try {
            connection = _datasource.getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT count(1) as liczba FROM " + tableName + " where u_login=? and u_password=?;");
            st.setString(1, l);
            st.setString(2, p);
            ResultSet rs = st.executeQuery();
            int licz=-1;
            while (rs.next()) {
                licz = rs.getInt("liczba");
            }
            if (licz==0){odp=false;}


        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection != null) connection.close();
        return odp;
    }

}
