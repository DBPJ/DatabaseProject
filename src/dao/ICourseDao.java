package dao;

import entity.Course;

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
    public boolean addCourseGrade(String courseID, String staffNumber, Enum grade);

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
     * @param couseID
     * @param staffNumber
     * @param grade
     * @return
     */
    public boolean updateCourseGrade(String couseID, String staffNumber, Enum grade);

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
    public Map<String,Enum> queryGrades(String courseID);

    /**
     * get all staff resits
     * @param courseID
     * @return
     */
    public Map<String,Enum> queryResits(String courseID);

    /**
     * @param courseID
     * @param number
     * @return resit status of the staff in this course
     */
    public Enum queryResit(String courseID, String number);

    /**
     * delete a course
     * @param courseID
     * @return
     */
    public boolean deleteCourse(String courseID);
}
