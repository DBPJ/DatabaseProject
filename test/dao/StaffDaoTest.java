package dao;

import dao.impl.StaffDaoImpl;
import entity.Gender;
import entity.Staff;
import org.junit.Test;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class StaffDaoTest {

    @Test
    public void testAddStaff() {
        StaffDaoImpl staffDao = new StaffDaoImpl();
        Staff staff = new Staff();
        staff.setNumber("RS02001");
        staff.setName("周宏");
        staff.setGender(Gender.MALE);
        staff.setWorkAge(3);
        staff.setLocation("北京");
        staff.setSalary(4000);
        staff.setAdditionRate(0.1);
        staff.setDepartmentName("人事");
        // TODO: 2016/12/13 null need to modify
        staffDao.addStaff(null, staff);

    }
}
