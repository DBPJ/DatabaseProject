package manager;

import entity.Staff;
import entity.Teacher;

import java.util.List;

/**
 * Created by alex on 14/12/2016.
 */
public interface ICourseManager {
    /**
     * 用于添加一门课到数据库
     * 成绩上传时间此时不需要传，当教师上传成绩时更新
     * @param ID 课程ID
     * @param name 课程名称
     * @param classHour 学时
     * @param teacherNumber 授课老师编号
     * @return 操作结果是否成功
     */
    public boolean addCourse(String ID, String name, int classHour, String teacherNumber);

    /**
     * 上传用户的成绩，数据库根据触发器自动将resit设为合适的状态
     * @param ID 课程名
     * @param staffNumber 员工号
     * @param grade 成绩：pass 或者 fail
     * @return 更新结果
     */
    public boolean uploadGrade(String ID,String staffNumber, String grade);

    /**
     * 更新用户的成绩，数据库根据触发器自动将resit设为合适的状态
     * @param teacherNumber teachernumber
     * @param ID 课程名
     * @param staffNumber 员工号
     * @param grade 成绩：pass 或者 fail
     * @return 更新结果
     */
    public boolean updateGrade(String teacherNumber, String ID, String staffNumber, String grade);

    /**
     *
     * @param teacher 教师对象，在view里应该维护一个教师的对象
     * @param courseID 课程ID
     * @return 查询是否正确
     */
    public List<Staff> queryResits(Teacher teacher, String courseID);

    /**
     * 接受补考申请
     * @param courseID 课程编号
     * @param staffNumber 员工名
     * @return 接受结果
     */
    public boolean acceptResit(String courseID, String staffNumber);
}
