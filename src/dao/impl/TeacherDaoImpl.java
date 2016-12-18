package dao.impl;

import dao.ITeacherDao;
import entity.StaffTakeCourseRecord;
import entity.Teacher;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "SELECT * FROM  Teacher;";
        Statement stmt = null;
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
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

    @Override
    public boolean acceptResit(String staffNumber, String courseID) {
        Connection conn = util.getConnection();
        String sql = "update Staff_take_Course set resit = 'accept'" +
                " where Staff_number = ? and Course_ID  = ?";
        PreparedStatement pst =null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,staffNumber);
            pst.setString(2,courseID);
            pst.executeUpdate();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(null,pst,conn);
        }
        return res;
    }

    @Override
    public List<StaffTakeCourseRecord> queryCourseGrades(String teacherNumber) {
        Connection conn = util.getConnection();
        String sql = "select * from Course as T1 inner join Staff_take_Course as T2" +
                " where T1.ID = T2.Course_ID and Teacher_number = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<StaffTakeCourseRecord> records = new ArrayList<>();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,teacherNumber);
            rs = pst.executeQuery();
            while (rs.next()){
                StaffTakeCourseRecord record = new StaffTakeCourseRecord();
                record.setCourseID(rs.getString("Course_ID"));
                record.setCourseName(rs.getString("name"));
                record.setGrade(rs.getString("grade"));
                record.setResit(rs.getString("resit"));
                record.setStaffNumber(rs.getString("Staff_number"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return records;
    }
}
