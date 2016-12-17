package manager;

import entity.Director;

/**
 * Created by alex on 17/12/2016.
 */
public interface ITrainingPlanManager {
    /**
     * @param courseID 课程号
     * @param departmentName 部门
     * @param type 对此部门而言选修还是必修
     * @return
     */
    public boolean addTrainingPlan(String courseID, String departmentName, String type);
}
