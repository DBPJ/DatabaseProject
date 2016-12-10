package manager.impl;

/**
 * Created by alex on 10/12/2016.
 */

import dao.impl.UserDaoImpl;
import entity.User;
import manager.IUserManager;

import java.util.List;

/**
 * 只有系统管理员才有权限执行这条动作
 * 增加用户登录信息到数据库
 */
public class UserManagerImpl implements IUserManager{
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean addUser(String number, String password, String type) {
        User user = new User();
        user.setNumber(number);
        user.setPassword(password);
        user.setType(type);
        return userDao.addUser(user);
    }

    @Override
    public boolean addUsers(List<User> users) {
        return userDao.addUsers(users);
    }

    @Override
    public User queryUser(String number) {
        return userDao.queryUser(number);
    }

    @Override
    public boolean deleteUser(String number) {
        return userDao.deleteUser(number);
    }

    @Override
    public boolean updateUserInfo(String number, User user) {
        return userDao.updateUserInfo(number,user);
    }
}
