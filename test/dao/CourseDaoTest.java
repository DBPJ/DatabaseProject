package dao;

import dao.impl.CourseDaoImpl;
import entity.Course;
import entity.Staff;
import entity.Teacher;
import manager.impl.CourseManagerImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by alex on 14/12/2016.
 */
public class CourseDaoTest {
    @Test
    public void testAddCourse(){
        CourseManagerImpl courseManager = new CourseManagerImpl();
        courseManager.addCourse("YY","DBD",2,"test");
    }

    @Test
    public void testQueryResit(){
        Teacher teacher = new Teacher();
        teacher.setNumber("test");
        teacher.setName("test");
        CourseDaoImpl courseDao = new CourseDaoImpl();
        List<Staff> staffList = courseDao.queryResits(teacher,"test_staff");
        for (Staff staff: staffList){
            System.out.println(staff.getNumber()+" "+staff.getName());
        }
    }

    @Test
    public void testUpdateResit(){
        CourseDaoImpl courseDao = new CourseDaoImpl();
        courseDao.updateResit("test_staff","test_number","accept");
    }
}
