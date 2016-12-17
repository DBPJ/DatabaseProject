package dao.impl;

import dao.ITrainPlanDao;
import entity.Director;
import entity.TrainPlan;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alex on 17/12/2016.
 */
public class TrainingPlanDaoImpl implements ITrainPlanDao {
    JDBCUtil util = new JDBCUtil();
    @Override
    public boolean addTrainPlan(TrainPlan trainPlan) {
        Connection conn  = util.getConnection();
        String sql = "insert into Training_Plan(Course_ID, Department_name,type) values(?,?,?)";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,trainPlan.getCourseID());
            pst.setString(2,trainPlan.getDepartmentName());
            pst.setString(3,trainPlan.getType());
            pst.executeUpdate();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(null,pst,conn);
        }
        return res;
    }

    @Override
    public List<TrainPlan> queryTrainPlans(Director director) {
        Connection conn = util.getConnection();
        String sql = "select * from Training_Plan";
        return null;
    }
}
