package dao;

import entity.Course;
import entity.Director;
import entity.Staff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface IStaffDao {
    /**
     * @param staff staff
     * @return add result
     */
    public boolean addStaff(Director director,Staff staff);

    /**
     * delete staff
     * @param number staff number
     * @return delete result
     */
    public boolean deleteStaff(Director director,String number);

    /**
     * @param number staff number
     * @param staff staff
     * @return update info
     */
    public boolean updateStaff(Director director,String number, Staff staff);

    /**
     * @param director director
     * @param number staff number
     * @return staff
     */
    public Staff queryStaff(Director director,String number);


    /**
     * @param number staff number
     * @return staff
     */
    public Staff queryStaff(String number);


    /**
     * @param director director
     * @return staff list
     */
    public List<Staff> queryStaffByDepartment(Director director);

    /**
     *
     * @return all staffs
     */
    public List<Staff> queryStaffs();
    /**
     * @param director director
     * @param staff staff
     * @return course list the staff take
     */

    public List<Course> queryStaffCourse(Director director,Staff staff);

    /**
     * @param director director
     * @param staff staff
     * @param courseID course id
     * @return grade
     */
    public List<Enum> queryStaffGrade(Director director,Staff staff, String courseID);


    /**
     *
     * @param director director
     * @return a list contain Staff's name, ID, course name, course grade
     */
    public ArrayList<String[]> queryStaffsCourses(Director director);

    /**
     *
     * @return all staff and course and grade
     */
    public ArrayList<String[]> queryStaffsCourses();
}
