package manager.impl;

import dao.impl.StaffDaoImpl;
import entity.Course;
import entity.Director;
import entity.Gender;
import entity.Staff;
import manager.IStaffManager;

import java.util.ArrayList;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public class StaffManagerImpl implements IStaffManager {
    StaffDaoImpl staffDao = new StaffDaoImpl();

    @Override
    public boolean addStaffInfo(String number, String name, String gender, String department_name, int work_age, String location, double salary, double additionrate) {
        Staff staff = new Staff();
        staff.setNumber(number);
        staff.setName(name);
        staff.setGender(getGender(gender));
        staff.setDepartmentName(department_name);
        staff.setWorkAge(work_age);
        staff.setLocation(location);
        staff.setSalary(salary);
        staff.setAdditionRate(additionrate);
        // TODO: 2016/12/13 null need to modify
        return staffDao.addStaff(null,staff);

    }

    @Override
    public boolean deleteStaffInfo(String department_name, String number) {
        Director director = new Director();
        director.setDepartmentName(department_name);
        boolean rt = staffDao.deleteStaff(director, number);
        return rt;
    }

    @Override
    public Staff getStaffInfo(String department_name, String number) {
        Director director = new Director();
        director.setDepartmentName(department_name);
        Staff staff = staffDao.queryStaff(director, number);
        return staff;

    }

    @Override
    public boolean updateStaffInfo(String department_name, String number, Staff staff) {
        Director director = new Director();
        director.setDepartmentName(department_name);
        boolean rt = staffDao.updateStaff(director,number, staff);
        return rt;
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

    private Enum getGender(String gender){
        if(gender.equals("male")){
            return Gender.MALE;
        }
        else{
            return Gender.FEMALE;
        }
    }
}
