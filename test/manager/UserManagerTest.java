package manager;

import entity.User;
import manager.impl.UserManagerImpl;
import org.junit.Test;

/**
 * Created by alex on 12/12/2016.
 */
public class UserManagerTest {
    @Test
    public void testAddUser(){
        UserManagerImpl userManager = new UserManagerImpl();
        userManager.addUser("test","abc12345","test");
    }

    @Test
    public void testAddUsers(){
        UserManagerImpl userManager = new UserManagerImpl();
        userManager.addUsers("test/data/初始用户登录信息.xls");
    }

    @Test
    public void testDeleteuser(){
        UserManagerImpl userManager = new UserManagerImpl();
        userManager.deleteUser("YY02004");
    }

    @Test
    public void testUpdateUserInfo(){
        UserManagerImpl userManager = new UserManagerImpl();
        userManager.updateUserInfo("YY02003","testpass","测试");
    }

    @Test
    public void testQueryUser(){
        UserManagerImpl userManager = new UserManagerImpl();
        User user = userManager.queryUser("YY02003");
        System.out.println(user.getPassword());
        System.out.println(user.getType());

    }
}
