package manager.impl;

import dao.impl.CourseDaoImpl;
import dao.impl.TrainingPlanDaoImpl;
import entity.Course;
import entity.Director;
import entity.Staff;
import entity.TrainPlan;
import manager.ITrainingPlanManager;

import java.util.ArrayList;
import java.util.List;

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

    public boolean addTrainingPlan(TrainPlan trainPlan){
        return trainingPlanDao.addTrainPlan(trainPlan);
    }

    @Override
    public List<TrainPlan> queryTrainPlans(Director director) {
        return trainingPlanDao.queryTrainPlans(director);
    }

    @Override
    public List<TrainPlan> queryTrainPlans(Staff staff) {
        return trainingPlanDao.queryTrainPlans(staff);
    }

    @Override
    public boolean hasTrainingPlan(String depart) {
        return trainingPlanDao.hasTrainingPlan(depart);
    }

    @Override
    public List<TrainPlan> queryFreePlans() {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        List<Course> courses = courseDao.queryCourses();
        List<TrainPlan> trainPlans = new ArrayList<>();
        for (Course course:courses){
            TrainPlan trainPlan = new TrainPlan();
            trainPlan.setCourseID(course.getId());
            trainPlan.setCourseName(course.getName());
            trainPlan.setClassHour(course.getClassHour());
            trainPlan.setType("other");
            trainPlans.add(trainPlan);
        }
        return trainPlans;
    }

}