package manager;

import entity.Teacher;

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
}
