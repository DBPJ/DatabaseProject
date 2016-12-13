package dao.impl;

import dao.ICourseDao;
import entity.Course;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 14/12/2016.
 */
public class CourseDaoImpl implements ICourseDao {
    JDBCUtil util = new JDBCUtil();

    @Override
    public boolean addCourse(Course course) {
        Connection conn = util.getConnection();
        String sql = "INSERT INTO Course(ID,name,class_hour,teacher_number) VALUES(?,?,?,?)";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,course.getId());
            pst.setString(2,course.getName());
            pst.setInt(3,course.getClassHour());
            pst.setString(4,course.getTeacherNumber());
            pst.executeLargeUpdate();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(null,pst,conn);
        }
        return res;
    }

    @Override
    public boolean addCourseGrade(String courseID, String staffNumber, Enum grade) {
        return false;
    }

    @Override
    public boolean addCourseGrades(String courseID, List<String> staffs, List<Enum> grades) {
        return false;
    }

    @Override
    public Course queryCourse(String courseID) {
        return null;
    }

    @Override
    public List<Course> queryCourses() {
        return null;
    }

    @Override
    public boolean updateCourseGrade(String couseID, String staffNumber, Enum grade) {
        return false;
    }

    @Override
    public Enum queryGrade(String courseID, String staffNumber) {
        return null;
    }

    @Override
    public Map<String, Enum> queryGrades(String courseID) {
        return null;
    }

    @Override
    public Map<String, Enum> queryResits(String courseID) {
        return null;
    }

    @Override
    public Enum queryResit(String courseID, String number) {
        return null;
    }

    @Override
    public boolean deleteCourse(String courseID) {
        return false;
    }
}
