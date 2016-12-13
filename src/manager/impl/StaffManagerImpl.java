package manager.impl;

import dao.impl.StaffDaoImpl;
import entity.Course;
import entity.Staff;
import manager.IStaffManager;

import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class StaffManagerImpl implements IStaffManager {
    StaffDaoImpl staffDao = new StaffDaoImpl();

    @Override
    public boolean addStaffInfo(String number, String name, Enum gender, String department_name, int work_age, String location, double salary, double additionrate) {
        Staff staff = new Staff();
        staff.setNumber(number);
        staff.setName(name);
        staff.setGender(gender);
        staff.setDepartmentName(department_name);
        staff.setWorkAge(work_age);
        staff.setLocation(location);
        staff.setSalary(salary);
        staff.setAdditionRate(additionrate);
        // TODO: 2016/12/13 null need to modify
        return staffDao.addStaff(null,staff);

    }

    @Override
    public boolean deleteStaffInfo(String number) {
        // TODO: 2016/12/13 null need to modify
        staffDao.deleteStaff(null, number);
        return false;
    }

    @Override
    public Staff getStaffInfo(String number) {
//        staffDao.queryStaff()
        return null;
    }

    @Override
    public boolean updateStaffInfo(String number, Staff staff) {
        // TODO: 2016/12/13 null need to modify
        staffDao.updateStaff(null,number, staff);
        return false;
    }

    @Override
    public ArrayList<String[]> getCourseTakenInfo(String department) {
//
        return null;
    }

    @Override
    public ArrayList<Course> getCourseInfo() {
        return null;
    }

    @Override
    public boolean setTrainingPlan(String department_name, int[] course_ids) {
        return false;
    }
}
