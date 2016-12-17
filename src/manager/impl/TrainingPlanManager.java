package manager.impl;

import entity.Director;
import entity.TrainPlan;
import manager.ITrainingPlanManager;

import java.util.List;

/**
 * Created by Jindiwei on 2016/12/17.
 */
public class TrainingPlanManager implements ITrainingPlanManager {
    @Override
    public boolean addTrainingPlan(String courseID, String departmentName, String type) {
        return false;
    }

    @Override
    public List<TrainPlan> queryTrainPlans(Director director) {
        return null;
    }
}
