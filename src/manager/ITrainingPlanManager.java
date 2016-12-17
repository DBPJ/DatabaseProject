package manager;

import entity.Director;
import entity.TrainPlan;
import java.util.*;
/**
 * Created by Jindiwei on 2016/12/17.
 */
public interface ITrainingPlanManager {

    public boolean addTrainingPlan(String courseID, String departmentName, String type);

    public List<TrainPlan> queryTrainPlans(Director director);
}
