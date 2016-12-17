package dao.impl;

import dao.ITrainPlanDao;
import entity.Director;
import entity.TrainPlan;

import java.util.List;

/**
 * Created by Jindiwei on 2016/12/17.
 */
public class TrainPlanDaoImpl implements ITrainPlanDao {
    @Override
    public boolean addTrainPlan(Director director, TrainPlan trainPlan) {
        return false;
    }

    @Override
    public List<TrainPlan> queryTrainPlans(Director director) {
        return null;
    }
}
