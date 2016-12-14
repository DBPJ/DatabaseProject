package dao;

import entity.Course;
import entity.Staff;
import entity.Teacher;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;
import java.util.Map;

/**
 * Created by alex on 10/12/2016.
 */
public interface ICourseDao {
    /**
     * @param course new course to add
     * @return true for add success, false for add fail
     */
    public boolean addCourse(Course course);

    /**
     * @param courseID
     * @param staffNumber
     * @param grade
     * @return add success or fail
     */
    public boolean addCourseGrade(String courseID, String staffNumber, String grade);

    /**
     * add a batch of grades
     * @param courseID
     * @param staffs a list of staff who select this course
     * @param grades a list of grades
     * @return add result
     */
    public boolean addCourseGrades(String courseID, List<String> staffs, List<Enum> grades);

    /**
     * query a course info without grade
     * @param courseID
     * @return
     */
    public Course queryCourse(String courseID);


    /**
     * @return all course
     */
    public List<Course> queryCourses();

    /**
     * update grade for staff in the course
     * @param courseID
     * @param staffNumber
     * @param grade
     * @return
     */
    public boolean updateCourseGrade(String courseID, String staffNumber, String grade);

    /**
     * @param courseID
     * @param staffNumber
     * @return the grade for the staff at this course
     */
    public Enum queryGrade(String courseID, String staffNumber);


    /**
     * get all staff grades
     * @param courseID
     * @return a map that the key is staff number and the value is the grade of the staff
     */
    public Map<String,String> queryGrades(String courseID);

    /**
     * get all staff resits
     * @param teacher querying teacher
     * @param courseID
     * @return a list of the array of staff number and staff name
     */
    public List<Staff> queryResits(Teacher teacher, String courseID);

    /**
     * @param courseID
     * @param number
     * @return resit status of the staff in this course
     */
    public String queryResit(String courseID, String number);


    /**
     * delete a course
     * @param courseID
     * @return
     */
    public boolean deleteCourse(String courseID);

    public boolean updateResit(String courseID, String staffNumber, String resit);
}
