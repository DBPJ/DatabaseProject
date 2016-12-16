package dao.impl;

import dao.ITeacherDao;
import entity.Teacher;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class TeacherDaoImpl implements ITeacherDao {
    JDBCUtil util=  new JDBCUtil();
    @Override
    public Teacher queryTeacher(String teacherNumber) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Teacher where number = \""+ teacherNumber +"\";";
        Statement stmt = null;
        Teacher teacher = new Teacher();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                teacher.setNumber(teacherNumber);
                teacher.setName(rs.getString("name"));
                return teacher;
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
    public ArrayList<Teacher> queryTeacher() {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Director;";
        Statement stmt = null;
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setNumber(rs.getString("number"));
                teacher.setName(rs.getString("name"));
                teacher.setPhoneNumber(rs.getLong("phone_number"));
                teacher.setEmail(rs.getString("email"));
                teacher.setGender(rs.getString("gender"));
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(null,stmt,conn);
        }
        if(teachers.size() > 0){
            return teachers;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean addTeaher(Teacher teacher) {
        Connection conn = util.getConnection();
        String sql = "insert into Teacher(number, name, gender, phone_number, email) values(?,?,?,?,?)";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,teacher.getNumber());
            pst.setString(2,teacher.getName());
            pst.setString(3,teacher.getGender());
            pst.setLong(4,teacher.getPhoneNumber());
            pst.setString(5,teacher.getEmail());
            pst.executeUpdate();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(null,pst,conn);
        }
        return res;
    }
}
