package dao;

import entity.Course;
import manager.impl.CourseManagerImpl;
import org.junit.Test;

/**
 * Created by alex on 14/12/2016.
 */
public class CourseDaoTest {
    @Test
    public void testAddCourse(){
        CourseManagerImpl courseManager = new CourseManagerImpl();
        courseManager.addCourse("YY","DBD",2,"test");
    }
}
