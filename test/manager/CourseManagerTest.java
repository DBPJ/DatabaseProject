package manager;

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
}
