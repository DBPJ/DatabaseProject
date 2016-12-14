package dao.impl;

import dao.IStaffDao;
import entity.Course;
import entity.Director;
import entity.Gender;
import entity.Staff;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
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
        Connection conn = util.getConnection();
        String department_name = director.getDepartmentName();
        String sql = "SELECT Department_name FROM mydb.Staff where number = \""+ number +"\";";
        Statement stmt = null;
        boolean res = false;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                String name = rs.getString(1);
                if(name.equals(department_name)){
                    System.out.println("RRRR");
                    sql = "DELETE FROM mydb.Staff where number = \"" + number + "\";";
                    try {
                        stmt.execute(sql);
                        res = true;
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(null,stmt,conn);
        }
        return res;
    }

    @Override
    public boolean updateStaff(Director director,String number, Staff staff) {
        Connection conn = util.getConnection();
        System.out.println(number);
        String sql = "UPDATE `mydb`.`Staff` SET `name`='"+ staff.getName() +"', `gender`='"+ staff.getGender().name() +"', `work_age`='"+ staff.getWorkAge() +"', `location`='"+ staff.getLocation() +"', `salary`='"+ staff.getSalary() +"', `additionrate`='"+ staff.getAdditionRate() +"' WHERE `number`='"+ number +"';";
        Statement pst = null;
        boolean res = false;
        try {
            pst = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int rs = pst.executeUpdate(sql);
            if(rs == 1) {
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(null,pst,conn);
        }
        System.out.println(res);
        return res;
    }

    @Override
    public Staff queryStaff(Director director, String number) {
        Connection conn = util.getConnection();
        String department_name = director.getDepartmentName();
        String sql = "SELECT * FROM mydb.Staff where number = \""+ number +"\" and Department_name = \""+ department_name +"\";";
        Statement stmt = null;
        Staff staff = new Staff();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()) {
                staff.setNumber(rs.getString("number"));
                staff.setName(rs.getString("name"));
                staff.setGender(getGender(rs.getString("gender")));
                staff.setWorkAge(rs.getInt("work_age"));
                staff.setLocation(rs.getString("location"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setAdditionRate(rs.getDouble("additionrate"));
                staff.setDepartmentName(rs.getString("Department_name"));
                return staff;
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
    public List<Staff> queryStaffByDepartment(Director director) {
        Connection conn = util.getConnection();
        String department_name = director.getDepartmentName();
        String sql = "SELECT * FROM mydb.Staff where Department_name = \""+ department_name +"\";";
        Statement stmt = null;
        List<Staff> staffs = new ArrayList<Staff>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Staff staff = new Staff();
                staff.setNumber(rs.getString("number"));
                staff.setName(rs.getString("name"));
                staff.setGender(getGender(rs.getString("gender")));
                staff.setWorkAge(rs.getInt("work_age"));
                staff.setLocation(rs.getString("location"));
                staff.setSalary(rs.getDouble("salary"));
                staff.setAdditionRate(rs.getDouble("additionrate"));
                staff.setDepartmentName(rs.getString("Department_name"));
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            util.close(null,stmt,conn);
        }
        if(staffs.isEmpty()){
            return null;
        }
        else{
            return staffs;
        }
    }

    @Override
    public List<Course> queryStaffCourse(Director director, Staff staff) {
        return null;
    }

    @Override
    public List<Enum> queryStaffGrade(Director director, Staff staff, String courseID) {
        return null;
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