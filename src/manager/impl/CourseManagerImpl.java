package manager.impl;

import dao.impl.CourseDaoImpl;
import entity.Course;
import entity.Staff;
import entity.Teacher;
import manager.ICourseManager;

import java.util.List;

/**
 * Created by alex on 14/12/2016.
 */
public class CourseManagerImpl implements ICourseManager {
    CourseDaoImpl courseDao = new CourseDaoImpl();
    @Override
    public boolean addCourse(String ID, String name, int classHour, String teacherNumber) {
        Course course = new Course();
        course.setId(ID);
        course.setName(name);
        course.setClassHour(classHour);
        course.setTeacherNumber(teacherNumber);
        return courseDao.addCourse(course);
    }

    @Override
    public boolean uploadGrade(String ID, String staffNumber, String grade) {
        return courseDao.addCourseGrade(ID, staffNumber, grade);
    }

    @Override
    public boolean updateGrade(String ID, String staffNumber, String grade) {
        return courseDao.updateCourseGrade(ID,staffNumber,grade);
    }

    @Override
    public List<Staff> queryResits(Teacher teacher, String courseID) {
        return courseDao.queryResits(teacher,courseID);
    }

    @Override
    public boolean acceptResit(String courseID, String staffNumber) {
        return false;
    }


}
