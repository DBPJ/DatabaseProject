package manager;

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
}
