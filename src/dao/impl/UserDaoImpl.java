package dao.impl;

import dao.IUserDao;
import entity.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public class UserDaoImpl implements IUserDao {
    private JDBCUtil util;


    public UserDaoImpl() {
        util = new JDBCUtil();
    }

    @Override
    public boolean addUser(User user) {
        Connection conn = util.getConnection();
        String sql = "INSERT INTO User(number,password,type) VALUES(?,?,?)";
        PreparedStatement pst = null;
        boolean res = false;
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,user.getNumber());
            pst.setString(2,user.getPassword());
            pst.setString(3,user.getType());
            pst.executeLargeUpdate();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(null,pst,conn);
        }
        return res;
    }

    @Override
    public boolean addUsers(List<User> users) {
        return false;
    }

    @Override
    public User queryUser(String number) {
        Connection conn = util.getConnection();
        String sql = "SELECT * FROM User where number = ?";
        PreparedStatement pst = null;
        ResultSet rs = null;
        User user = new User();
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1,number);
            rs = pst.executeQuery();
            if (rs.next()){
                user.setNumber(rs.getString("number"));
                user.setPassword(rs.getString("password"));
                user.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(rs,pst,conn);
        }
        return user;
    }

    @Override
    public boolean deleteUser(String number) {
        return false;
    }

    @Override
    public boolean updateUserInfo(String number, User user) {
        return false;
    }
}
