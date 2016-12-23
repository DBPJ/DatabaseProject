package dao.impl;

import dao.ITrainPlanDao;
import entity.Director;
import entity.Staff;
import entity.TrainPlan;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql2 = "select number from Staff where Department_name = ?";
        String sql3 = "insert into Staff_take_Course(Course_ID,Staff_number) values(?,?)";
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,trainPlan.getCourseID());
            pst.setString(2,trainPlan.getDepartmentName());
            pst.setString(3,trainPlan.getType());
            pst.executeUpdate();
            res = true;

            //必修课，为所有本部门员工选
            if (trainPlan.getType().equals("required")){
                pst = conn.prepareStatement(sql2);
                pst.setString(1,trainPlan.getDepartmentName());
                rs = pst.executeQuery();
                while (rs.next()){
                    String number = rs.getString("number");
                    pst = conn.prepareStatement(sql3);
                    pst.setString(1,trainPlan.getCourseID());
                    pst.setString(2,number);
                    pst.executeUpdate();
                }
            }
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
        String sql = "select * from Training_Plan as T1 inner join Course as T2 where T1.Course_ID = T2.ID and Department_name = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TrainPlan> trainPlans = new ArrayList<>();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,director.getDepartmentName());
            rs = pst.executeQuery();
            while (rs.next()){
                TrainPlan trainPlan = new TrainPlan();
                trainPlan.setCourseID(rs.getString("ID"));
                trainPlan.setCourseName(rs.getString("name"));
                trainPlan.setDepartmentName(rs.getString("Department_name"));
                trainPlan.setCourseName(rs.getString("name"));
                trainPlan.setType(rs.getString("type"));
                trainPlans.add(trainPlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        //SELECT T1.Staff_number, T2.name from Staff_take_Course as T1
        // inner join Staff as T2 where T1.Course_ID = ? and T1.resit = 'need'
        return trainPlans;
    }

    @Override
    public List<TrainPlan> queryTrainPlans(Staff staff) {
        Connection conn = util.getConnection();
        String sql = "select * from Training_Plan as T1 inner join Course as T2 where T1.Course_ID = T2.ID and Department_name = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<TrainPlan> trainPlans = new ArrayList<>();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,staff.getDepartmentName());
            rs = pst.executeQuery();
            while (rs.next()){
                TrainPlan trainPlan = new TrainPlan();
                trainPlan.setCourseID(rs.getString("ID"));
                trainPlan.setDepartmentName(rs.getString("Department_name"));
                trainPlan.setCourseName(rs.getString("name"));
                trainPlan.setType(rs.getString("type"));
                trainPlans.add(trainPlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        //SELECT T1.Staff_number, T2.name from Staff_take_Course as T1
        // inner join Staff as T2 where T1.Course_ID = ? and T1.resit = 'need'
        return trainPlans;
    }

    @Override
    public boolean hasTrainingPlan(String department) {
        Connection conn = util.getConnection();
        String sql = "select Course_ID from Training_Plan where Department_name = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,department);
            rs = pst.executeQuery();
            if (rs.next()){
                res = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

}
