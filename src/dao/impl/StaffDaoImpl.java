package dao.impl;

import dao.IStaffDao;
import entity.Course;
import entity.Director;
import entity.Staff;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class StaffDaoImpl implements IStaffDao {
    private JDBCUtil util;


    public StaffDaoImpl() {
        util = new JDBCUtil();
    }

    @Override
    public boolean addStaff(Director director,Staff staff) {
        Connection conn = util.getConnection();
        String sql = "INSERT INTO Staff(number,name,gender,work_age,location,salary,additionrate,Department_name) VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,staff.getNumber());
            pst.setString(2,staff.getName());
            pst.setString(3, staff.getGender().name());
            pst.setInt(4, staff.getWorkAge());
            pst.setString(5, staff.getLocation());
            pst.setDouble(6, staff.getSalary());
            pst.setDouble(7, staff.getAdditionRate());
            pst.setString(8, staff.getDepartmentName());
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
    public boolean deleteStaff(Director director,String number) {
        return false;
    }

    @Override
    public boolean updateStaff(Director director,String number, Staff staff) {
        return false;
    }

    @Override
    public Staff queryStaff(Director director, String number) {
        return null;
    }

    @Override
    public List<Staff> queryStaffByDepartment(Director director) {
        return null;
    }

    @Override
    public List<Course> queryStaffCourse(Director director, Staff staff) {
        return null;
    }

    @Override
    public List<Enum> queryStaffGrade(Director director, Staff staff, String courseID) {
        return null;
    }
}
