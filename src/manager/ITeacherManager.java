package manager;

import entity.Teacher;

import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public interface ITeacherManager {

    /**
     * 根据教室的工号查询教师信息
     * @param number teacher number
     * @return query result
     */
    public Teacher queryTeacher(String number);


    /**
     * @return all teacher
     */
    public ArrayList<Teacher> queryTeacher();

    /**
     *
     * @param number teacher number
     * @param name teacher name
     * @param gender gender
     * @param phone phone
     * @param email email
     * @return result
     */
    public boolean addTeacher(String number, String name, String gender, String phone, String email);
}
