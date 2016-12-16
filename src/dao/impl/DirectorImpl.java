package dao.impl;

import dao.IDirectorDao;
import entity.Director;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class DirectorImpl implements IDirectorDao {
    private JDBCUtil util;


    public DirectorImpl() {
        util = new JDBCUtil();
    }

    @Override
    public boolean addDirector(Director director) {
        return false;
    }

    @Override
    public boolean deleteDirector(String number) {
        return false;
    }

    @Override
    public boolean updateDirector(String number, Director director) {
        return false;
    }

    @Override
    public Director queryDirector(String number) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Director where number = \""+ number +"\";";
        Statement stmt = null;
        Director director = new Director();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                director.setNumber(number);
                director.setName(rs.getString("name"));
                director.setDepartmentName(rs.getString("Department_name"));
                return director;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(null,stmt,conn);
        }
        return null;
    }

    @Override
    public List<Director> queryDirectors() {
        return null;
    }
}
