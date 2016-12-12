package manager.impl;

import dao.impl.TeacherDaoImpl;
import entity.Teacher;
import manager.ITeacherManager;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class TeacherManagerImpl implements ITeacherManager{
    TeacherDaoImpl teacherDao = new TeacherDaoImpl();

    @Override
    public Teacher queryTeacher(String number) {
        Teacher teacher = teacherDao.queryTeacher(number);

        return teacher;
    }
}
