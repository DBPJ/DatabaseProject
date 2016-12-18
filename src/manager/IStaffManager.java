package manager;

import entity.Course;
import entity.Director;
import entity.Staff;
import entity.StaffTakeCourseRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jindiwei on 2016/12/13.
 */
public interface IStaffManager {
    /**
     * 导入部门员工信息,每次只能导入一个
     *
     * @param number 工号
     * @param name 姓名
     * @param gender 性别
     * @param department_name 部门
     * @param work_age 工龄
     * @param location 工作地点
     * @param salary 基本工资
     * @param additionrate 加成
     * @return true or false
     */
    public boolean addStaffInfo(String number, String name, String gender, String department_name, int work_age, String location, double salary, double additionrate);

    /**
     * 根据员工的工号删除信息
     * @param number 员工的工号
     * @return true or false
     */
    public boolean deleteStaffInfo(String department_name, String number);

    /**
     * 在修改员工信息时调用,先将初始信息反馈给部门主管
     * @param number 员工工号
     * @param department_name 部门主管对象
     * @return return a Staff object include the information of the staff
     */
    public Staff getStaffInfo(String department_name, String number);

    /**
     *
     * @param staff 包含一个修改过的员工对象
     * @return success or fail
     */
    public boolean updateStaffInfo(String department_name, String number, Staff staff);

    /**
     *
     * @param director 部门主管
     * @return 包含员工选课情况的ArrayList 包括:工号;姓名;课程名称;成绩
     */
    public ArrayList<String []> getCourseTakenInfo(Director director);

    /**
     * 获得已开设的课程的信息
     * @return 包含课程信息的arraylist
     */
    public ArrayList<Course> getCourseInfo();

    /**
     * 设置部门培训计划
     *
     * @param course_ids 包含所有选中的课程的课程编号
     * @return true or false
     */
    public boolean setTrainingPlan(String department_name, int[] course_ids);

    public List<Staff> queryStaffs();


    /**
     *
     * @return all staff and course and grade
     */
    public ArrayList<String[]> queryStaffsCourses();


    /**
     *
     * @param staffNumber
     * @return
     */
    public List<StaffTakeCourseRecord> queryCourseRecords(String staffNumber);


    public StaffTakeCourseRecord queryCourseRecord(String courseID, String staffNumber);

    /**
     *
     * @param staffNumber
     * @param courseID
     * @return
     */
    public boolean applyResit(String staffNumber, String courseID);


    public List<StaffTakeCourseRecord> queryStaffCourseGrades();

    public Staff queryStaff(String staffNumber);
}
