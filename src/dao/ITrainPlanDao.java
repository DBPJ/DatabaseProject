package dao;

import entity.Director;
import entity.TrainPlan;

import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface ITrainPlanDao {
    /**
     * @param director director
     * @param trainPlan train plan
     * @return add result
     */
    public boolean addTrainPlan(Director director,TrainPlan trainPlan);

    /**
     * @param director director
     * @return all train plan
     */
    public List<TrainPlan> queryTrainPlans(Director director);
}
