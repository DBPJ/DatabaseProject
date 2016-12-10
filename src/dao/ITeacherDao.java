package dao;

import entity.Teacher;

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
    public List<Teacher> queryTeacher();
}
