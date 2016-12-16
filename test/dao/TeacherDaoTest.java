package dao;

import dao.impl.TeacherDaoImpl;
import entity.Teacher;
import org.junit.Test;

/**
 * Created by alex on 16/12/2016.
 */
public class TeacherDaoTest {
    @Test
    public void testAddTeacher(){
        Teacher teacher = new Teacher();
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();

        teacher.setNumber("TR01001");
        teacher.setName("李敏");
        teacher.setGender("male");
        teacher.setPhoneNumber((long) 65640323);
        teacher.setEmail("limin@xx.com");
        teacherDao.addTeaher(teacher);

        teacher.setNumber("TR01002");
        teacher.setName("高隽");
        teacher.setGender("female");
        teacher.setPhoneNumber((long) 65643356);
        teacher.setEmail("gaojun@xx.com");
        teacherDao.addTeaher(teacher);

        teacher.setNumber("TR01003");
        teacher.setName("夏勇");
        teacher.setGender("male");
        teacher.setPhoneNumber((long) 65642219);
        teacher.setEmail("xiayong@xx.com");
        teacherDao.addTeaher(teacher);

        teacher.setNumber("TR01004");
        teacher.setName("周杰");
        teacher.setGender("male");
        teacher.setPhoneNumber((long) 65640358);
        teacher.setEmail("zhoujie@xx.com");
        teacherDao.addTeaher(teacher);

        teacher.setNumber("TR01005");
        teacher.setName("何晔");
        teacher.setGender("female");
        teacher.setPhoneNumber((long) 65642018);
        teacher.setEmail("heye@xx.com");
        teacherDao.addTeaher(teacher);

        teacher.setNumber("TR01006");
        teacher.setName("朱峰");
        teacher.setGender("male");
        teacher.setPhoneNumber((long) 65649488);
        teacher.setEmail("zhufeng@xx.com");
        teacherDao.addTeaher(teacher);


    }
}
