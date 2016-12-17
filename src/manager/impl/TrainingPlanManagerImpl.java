package manager.impl;

import dao.impl.TrainingPlanDaoImpl;
import entity.Director;
import entity.TrainPlan;
import manager.ITrainingPlanManager;

/**
 * Created by alex on 17/12/2016.
 */
public class TrainingPlanManagerImpl implements ITrainingPlanManager {
    TrainingPlanDaoImpl trainingPlanDao = new TrainingPlanDaoImpl();
    @Override
    public boolean addTrainingPlan(String courseID, String departmentName, String type) {
        TrainPlan trainPlan = new TrainPlan();
        trainPlan.setCourseID(courseID);
        trainPlan.setDepartmentName(departmentName);
        trainPlan.setType(type);
        return trainingPlanDao.addTrainPlan(trainPlan);
    }
}
