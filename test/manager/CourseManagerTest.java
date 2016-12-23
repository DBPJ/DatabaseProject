package manager;

import entity.Teacher;
import manager.impl.CourseManagerImpl;
import org.junit.Test;

/**
 * Created by alex on 14/12/2016.
 */
public class CourseManagerTest {

    @Test
    public void testUploadGrade(){
        CourseManagerImpl courseManager = new CourseManagerImpl();
        courseManager.uploadGrade("test_staff","test_number","fail");
    }

    @Test
    public void testAddCourses(){
        Teacher teacher = new Teacher();
        teacher.setNumber("TR01005");
        teacher.setName("李敏");
        teacher.setGender("male");
        teacher.setPhoneNumber((long) 12345);
        teacher.setEmail("haha@x.com");
        CourseManagerImpl courseManager = new CourseManagerImpl();
        courseManager.addCourses(teacher,"test/data/初始课程信息.xls");
    }
}
