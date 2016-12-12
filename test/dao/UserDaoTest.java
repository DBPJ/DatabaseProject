package dao;

import dao.impl.UserDaoImpl;
import entity.User;
import org.junit.Test;

/**
 * Created by alex on 12/12/2016.
 */
public class UserDaoTest {
    @Test
    public void testAddUser(){
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setNumber("test123");
        user.setPassword("test456");
        user.setType("test");
        userDao.addUser(user);
    }
}
