package manager;

import entity.Director;
import entity.TrainPlan;
import manager.impl.TrainingPlanManagerImpl;
import org.junit.Test;

/**
 * Created by alex on 17/12/2016.
 */
public class TrainingPlanManagerTest {

    @Test
    public void testAddTrainPlan(){
        TrainingPlanManagerImpl trainingPlanManager = new TrainingPlanManagerImpl();
        trainingPlanManager.addTrainingPlan("CWGL02001","财务","required");
        trainingPlanManager.addTrainingPlan("GJJJ02002","财务","required");
        trainingPlanManager.addTrainingPlan("GSLC02003","财务","elective");
    }
}
