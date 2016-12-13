package manager.impl;

import dao.impl.CourseDaoImpl;
import entity.Course;
import manager.ICourseManager;

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
}
