package dao.impl;

import dao.IStaffDao;
import entity.*;
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
        System.out.println("!!" + director.getDepartmentName() + " " + director.getNumber());
        String sql = "SELECT Department_name FROM mydb.Staff where number = \""+ number +"\";";
        Statement stmt = null;
        boolean res = false;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                System.out.println("1");
                String name = rs.getString(1);
                System.out.println(name + " " + department_name);
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
                    System.out.println("2");
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
    public Staff queryStaff(String number) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Staff where number = \""+ number +"\";";
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
    public ArrayList<Staff> queryStaffs() {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Staff;";
        Statement stmt = null;
        ArrayList<Staff> staffs = new ArrayList<Staff>();
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

    @Override
    public ArrayList<String[]> queryStaffsCourses(Director director) {
        Connection conn = util.getConnection();
        String department_name = director.getDepartmentName();
        String sql = "SELECT * FROM mydb.Staff where Department_name = \""+ department_name +"\";";
        ResultSet rs = null;
        Statement stmt = null;

        String staff_name = null;
        String staff_department = null;
        String staff_number = null;
        String course_name = null;
        String course_number = null;
        String grade;
        ArrayList<Staff> staffs = new ArrayList<>();
        ArrayList<String[]> infos_rt = new ArrayList<>();
        ArrayList<String> course_id = new ArrayList<>();
        ArrayList<String> course_grade = new ArrayList<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Staff staff = new Staff();
                staff_number = rs.getString("number");
                staff_name = rs.getString("name");
                staff.setNumber(staff_number);
                staff.setName(staff_name);
                staff.setDepartmentName(rs.getString("Department_name"));
                staff_department = rs.getString("Department_name");

                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, stmt, conn);
        }

        for (int i = 0; i < staffs.size(); i++) {
            Connection new_con = util.getConnection();
            Statement new_stmt = null;
            ResultSet new_rs = null;
            staff_number = staffs.get(i).getNumber();
            staff_name = staffs.get(i).getName();

            try {
                new_stmt = new_con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                sql = "SELECT * FROM mydb.Staff_take_Course where Staff_number = \"" + staff_number + "\";";
                new_rs = new_stmt.executeQuery(sql);
                while (new_rs.next()) {
                    course_number = new_rs.getString("Course_ID");
                    grade = new_rs.getString("grade");
                    course_id.add(course_number);
                    course_grade.add(grade);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(new_rs, new_stmt, new_con);
            }
        }


        for(int i = 0 ; i < course_id.size() ; i ++){
            Connection new_con = util.getConnection();
            Statement new_stmt = null;
            ResultSet new_rs = null;
            try {
                new_stmt = new_con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                String newSql = "SELECT name FROM mydb.Course where ID = \"" + course_id.get(i) + "\";";
                ResultSet rsNew = new_stmt.executeQuery(newSql);
                if (rsNew.next()) {
                    course_name = rsNew.getString(1);
                }
                String[] infos = new String[6];
                System.out.println(staff_number);
                infos[0] = staff_number;
                System.out.println(staff_department);
                infos[1] = staff_department;
                System.out.println(staff_name);
                infos[2] = staff_name;
                System.out.println(course_id.get(i));
                infos[3] = course_id.get(i);
                System.out.println(course_name);
                infos[4] = course_name;
                System.out.println(course_grade.get(i));
                infos[5] = course_grade.get(i);
                infos_rt.add(infos);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(new_rs, new_stmt, new_con);
            }
        }
        return infos_rt;
    }

    public ArrayList<String[]> queryStaffsCourses() {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM mydb.Staff;";
        ResultSet rs = null;
        Statement stmt = null;

        String staff_name = null;
        String staff_department = null;
        String staff_number = null;
        String course_name = null;
        String course_number = null;
        String grade;
        ArrayList<Staff> staffs = new ArrayList<>();
        ArrayList<String[]> infos_rt = new ArrayList<>();
        ArrayList<String> course_id = new ArrayList<>();
        ArrayList<String> course_grade = new ArrayList<>();
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Staff staff = new Staff();
                staff_number = rs.getString("number");
                staff_name = rs.getString("name");
                staff.setNumber(staff_number);
                staff.setName(staff_name);
                staff.setDepartmentName(rs.getString("Department_name"));
                staff_department = rs.getString("Department_name");
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs, stmt, conn);
        }

        for (int i = 0; i < staffs.size(); i++) {
            Connection new_con = util.getConnection();
            Statement new_stmt = null;
            ResultSet new_rs = null;
            staff_number = staffs.get(i).getNumber();
            staff_name = staffs.get(i).getName();

            try {
                new_stmt = new_con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                sql = "SELECT * FROM mydb.Staff_take_Course where Staff_number = \"" + staff_number + "\";";
                new_rs = new_stmt.executeQuery(sql);
                while (new_rs.next()) {
                    course_number = new_rs.getString("Course_ID");
                    grade = new_rs.getString("grade");
                    course_id.add(course_number);
                    course_grade.add(grade);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(new_rs, new_stmt, new_con);
            }
        }


        for(int i = 0 ; i < course_id.size() ; i ++){
            Connection new_con = util.getConnection();
            Statement new_stmt = null;
            ResultSet new_rs = null;
            try {
                new_stmt = new_con.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                String newSql = "SELECT name FROM mydb.Course where ID = \"" + course_id.get(i) + "\";";
                ResultSet rsNew = new_stmt.executeQuery(newSql);
                if (rsNew.next()) {
                    course_name = rsNew.getString(1);
                }
                String[] infos = new String[6];
                System.out.println(staff_number);
                infos[0] = staff_number;
                System.out.println(staff_department);
                infos[1] = staff_department;
                System.out.println(staff_name);
                infos[2] = staff_name;
                System.out.println(course_id.get(i));
                infos[3] = course_id.get(i);
                System.out.println(course_name);
                infos[4] = course_name;
                System.out.println(course_grade.get(i));
                infos[5] = course_grade.get(i);
                infos_rt.add(infos);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                util.close(new_rs, new_stmt, new_con);
            }
        }
        return infos_rt;
    }

    @Override
    public List<StaffTakeCourseRecord> queryCourseRecords(Staff staff) {
        Connection conn = util.getConnection();
        String sql = "select * from Course as T1 inner join Staff_take_Course as T2" +
                " where T1.ID = T2.Course_ID and Staff_number = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<StaffTakeCourseRecord> records = new ArrayList<>();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,staff.getNumber());
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


    @Override
    public List<StaffTakeCourseRecord> queryCourseSelection(String courseID, Teacher teacher) {
        Connection conn = util.getConnection();
        String sql = "select c.name, a.Staff_number, b.name, b.Department_name from mydb.Staff_take_Course a, Staff b, Course c where a.Course_ID = ?" +
                "and a.Staff_number = b.number and  a.Course_ID = c.ID and c.Teacher_number = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<StaffTakeCourseRecord> records = new ArrayList<>();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, courseID);
            pst.setString(2, teacher.getNumber());
            rs = pst.executeQuery();
            while (rs.next()){
                StaffTakeCourseRecord record = new StaffTakeCourseRecord();
                record.setCourseName(rs.getString(1));
                record.setStaffNumber(rs.getString(2));
                record.setStaffName(rs.getString(3));
                record.setStaffDepart(rs.getString(4));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return records;
    }

    @Override
    public StaffTakeCourseRecord queryCourseRecord(String courseID, String staffNumber) {
        Connection conn = util.getConnection();
        String sql = "select * from Course as T1 inner join Staff_take_Course as T2" +
                " where T1.ID = T2.Course_ID and Staff_number = ? and T2.Course_ID = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
//        List<StaffTakeCourseRecord> records = new ArrayList<>();
        StaffTakeCourseRecord record = new StaffTakeCourseRecord();

        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,staffNumber);
            pst.setString(2,courseID);
            rs = pst.executeQuery();
            if (rs.next()){
                record.setCourseID(rs.getString("Course_ID"));
                record.setCourseName(rs.getString("name"));
                record.setGrade(rs.getString("grade"));
                record.setResit(rs.getString("resit"));
                record.setStaffNumber(rs.getString("Staff_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return record;
    }

    @Override
    public boolean applyResit(String staffNumber, String courseID) {
        boolean setfee = setFee(staffNumber,courseID);
        if (!setfee){
            return false;
        }
        Connection conn = util.getConnection();
        String sql = "update Staff_take_Course set resit = 'applying'" +
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
    public List<StaffTakeCourseRecord> queryStaffCourseGrades() {
        Connection conn = util.getConnection();
        String sql = "select * from Course as T1 inner join Staff_take_Course as T2 inner join Staff as T3" +
                " where T1.ID = T2.Course_ID and T2.Staff_number = T3.number";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<StaffTakeCourseRecord> records = new ArrayList<>();
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                StaffTakeCourseRecord record = new StaffTakeCourseRecord();
                record.setCourseID(rs.getString("ID"));
                record.setCourseName(rs.getString(1));
                record.setStaffNumber(rs.getString("Staff_number"));
                record.setCourseName(rs.getString(11));
                record.setGrade(rs.getString("grade"));
                record.setStaffDepart(rs.getString("Department_name"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }

        return records;
    }


    private boolean setFee(String staffNumber, String courseID){
        Connection conn = util.getConnection();
        String sql = "select grade_upload_time from Course where ID = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean res = false;
        boolean timeout = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,courseID);
            rs = pst.executeQuery();
            if (rs.next()){
                Date date = rs.getDate("grade_upload_time");
                java.util.Date date1 = new java.util.Date(date.getTime());
                java.util.Date date2 = new java.util.Date(new java.util.Date().getTime());
                long betweenDays = (date2.getTime() - date1.getTime())/(1000*60*60*24);
                String sql2 = "update Staff_take_Course set fee = ? where Course_ID = ? and Staff_number  = ?";
                pst = conn.prepareStatement(sql2);
                if (betweenDays<=7){
                    pst.setDouble(1,50);
                }else if (betweenDays<14){
                    pst.setDouble(1,100);
                }else{
                    pst.setDouble(1,-1);
                    timeout = true;
                }
                pst.setString(2,courseID);
                pst.setString(3,staffNumber);
                pst.executeUpdate();
            }
            res =true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return res&&(!timeout);
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
