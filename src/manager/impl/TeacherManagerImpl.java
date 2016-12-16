package manager.impl;

import dao.impl.TeacherDaoImpl;
import entity.Teacher;
import manager.ITeacherManager;

import java.util.ArrayList;

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

    @Override
    public ArrayList<Teacher> queryTeacher() {
        return teacherDao.queryTeacher();
    }

    @Override
    public boolean addTeacher(String number, String name, String gender, String phone, String email) {
        Teacher teacher = new Teacher();
        teacher.setNumber(number);
        teacher.setName(name);
        teacher.setGender(gender);
        teacher.setPhoneNumber(Long.parseLong(phone));
        teacher.setEmail(email);
        return teacherDao.addTeaher(teacher);
    }
}
