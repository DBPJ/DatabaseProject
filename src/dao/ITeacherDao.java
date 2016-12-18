package dao;

import entity.StaffTakeCourseRecord;
import entity.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface ITeacherDao {
    /**
     * @param teacherNumber number of teacher
     * @return teacher
     */
    public Teacher queryTeacher(String teacherNumber);


    /**
     * @return all teacher
     */
    public ArrayList<Teacher> queryTeacher();

    /**
     * add teacher to DB
     * @param teacher  tacher
     * @return result
     */
    public boolean addTeaher(Teacher teacher);

    /**
     *
     * @param staffNumber
     * @param courseID
     * @return
     */
    public boolean acceptResit(String staffNumber, String courseID);

    public List<StaffTakeCourseRecord> queryCourseGrades(String teacherNumber);

}
