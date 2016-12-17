package dao.impl;

import dao.IDirectorDao;
import entity.Director;
import entity.Gender;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class DirectorDaoImpl implements IDirectorDao {
    private JDBCUtil util;


    public DirectorDaoImpl() {
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
    public ArrayList<Director> queryDirectors() {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Director;";
        Statement stmt = null;
        ArrayList<Director> directors = new ArrayList<Director>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                Director director = new Director();
                director.setNumber(rs.getString("number"));
                director.setName(rs.getString("name"));
                director.setDepartmentName(rs.getString("Department_name"));
                director.setWorkspace(rs.getString("workplace"));
                director.setPhoneNumber(rs.getInt("phone_number"));
                director.setEmail(rs.getString("email"));
                director.setGender(getGender(rs.getString("gender")));
                directors.add(director);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(null,stmt,conn);
        }
        if(directors.size() > 0){
         return directors;
        }
        else {
            return null;
        }
    }
    private Enum getGender(String gender){
        if(gender.equals("male")){
            return Gender.MALE;
        }
        else{
            return Gender.FEMALE;
        }
    }
}
