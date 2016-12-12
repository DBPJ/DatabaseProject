package manager;

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
}
