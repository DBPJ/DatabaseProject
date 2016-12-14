package dao;

import dao.impl.StaffDaoImpl;
import entity.Director;
import entity.Gender;
import entity.Staff;
import org.junit.Test;

import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class StaffDaoTest {
    StaffDaoImpl staffDao = new StaffDaoImpl();
    @Test
    public void testAddStaff() {
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
    @Test
    public void testDeleteStaff(){
        Director director = new Director();
        director.setDepartmentName("人事");
        staffDao.deleteStaff(director, "123");
    }

    @Test
    public void testQUeryStaff(){
        Director director = new Director();
        director.setDepartmentName("人事");
        Staff rt = staffDao.queryStaff(director, "123");
        if(rt == null){
            System.out.println("null");
        }
        else {
            System.out.println(rt.getName());
        }
    }

    @Test
    public void testQUeryStaffs(){
        Director director = new Director();
        director.setDepartmentName("人事");
        List<Staff> rt = staffDao.queryStaffByDepartment(director);
        if(rt == null){
            System.out.println("null");
        }
        else {
            for(int i = 0 ; i < rt.size() ; i++){
                System.out.println(rt.get(i).getName());
            }
        }
    }

    @Test
    public void testUpdateStaff(){
        Director director = new Director();
        director.setDepartmentName("人事");
        Staff staff = new Staff();
        staff.setNumber("123");
        staff.setName("周宏");
        staff.setGender(Gender.MALE);
        staff.setWorkAge(3);
        staff.setLocation("绍兴");
        staff.setSalary(40);
        staff.setAdditionRate(0.1);
        staff.setDepartmentName("人事");
        staffDao.updateStaff(director, "123", staff);
    }





}
