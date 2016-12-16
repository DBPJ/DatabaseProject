package dao.impl;

import dao.ITeacherDao;
import entity.Teacher;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class TeacherDaoImpl implements ITeacherDao {
    JDBCUtil util=  new JDBCUtil();
    @Override
    public Teacher queryTeacher(String teacherNumber) {
        return null;
    }

    @Override
    public List<Teacher> queryTeacher() {
        return null;
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
