package dao.impl;

import dao.ICourseDao;
import entity.Course;
import entity.Staff;
import entity.Teacher;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public boolean addCourseGrade(String courseID, String staffNumber, String grade) {
        Connection conn = util.getConnection();
        String sql = "UPDATE Staff_take_Course set grade = ? where Course_ID = ? and Staff_number = ?";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,grade);
            pst.setString(2,courseID);
            pst.setString(3,staffNumber);
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
    public boolean updateCourseGrade(String courseID, String staffNumber, String grade) {
        Connection conn = util.getConnection();
        String sql = "UPDATE Staff_take_Course set grade = ? where Course_ID = ? and Staff_number = ?";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,grade);
            pst.setString(2,courseID);
            pst.setString(3,staffNumber);
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
    public Enum queryGrade(String courseID, String staffNumber) {
        return null;
    }

    @Override
    public Map<String, String> queryGrades(String courseID) {
        return null;
    }

    @Override
    public List<Staff> queryResits(Teacher teacher, String courseID) {
        boolean validate = validateAuthority(teacher,courseID);
        if (!validate){
            //todo: 需要通知客户端没有权限访问
            return null;
        }

        List<Staff>staffs = new ArrayList<>();
        Connection conn = util.getConnection();
        String sql1 = "SELECT T1.Staff_number, T2.name from Staff_take_Course as T1 inner join Staff as T2 where T1.Course_ID = ? and T1.resit = 'need'";
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            pst = conn.prepareStatement(sql1);
            pst.setString(1,courseID);
            rs = pst.executeQuery();
            while (rs.next()){
                Staff staff = new Staff();
                staff.setNumber(rs.getString(1));
                staff.setName(rs.getString(2));
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return staffs;
    }

    @Override
    public String queryResit(String courseID, String number) {
        return null;
    }

    @Override
    public boolean deleteCourse(String courseID) {
        return false;
    }

    @Override
    public boolean updateResit(String courseID, String staffNumber, String resit) {
        Connection conn = util.getConnection();
        String sql = "Update Staff_take_Course set resit = ? where Course_ID = ? and Staff_number = ?";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,resit);
            pst.setString(2,courseID);
            pst.setString(3,staffNumber);
            pst.executeUpdate();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(null,pst,conn);
        }
        return res;
    }

    private boolean validateAuthority(Teacher teacher, String courseID){
        Connection conn = util.getConnection();
        String sql = "select Teacher_number from Course where ID = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,courseID);
            rs = pst.executeQuery();
            if (rs.next()){
                String teacherNumber = rs.getString("Teacher_number");
                if (teacher.getNumber().equals(teacherNumber)){
                    res = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return res;
    }
}
