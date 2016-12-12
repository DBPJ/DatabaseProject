package manager;

import entity.User;

import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface IUserManager {
    /**
     * @param number: user number.
     * @param password: user password.
     * @param type: user type
     * @return true for add success, false for add fail
     */
    public boolean addUser(String number, String password, String type);

    /**
     * @param usersInfo: a batch of users that will be add to DB
     * @return true for add success, false for add fail
     */
    public boolean addUsers(List<String[]> usersInfo);

    /**
     * 批量导入数据只支持从excel中导入
     * excel文件名第一行就需要是数字
     * 第一列问number
     * 第二列为password
     * 第三列为type
     * @param filename excel文件名
     * @return 是否导入成功
     */
    public boolean addUsers(String filename);

    /**
     * @param number: number of the target user
     * @return user with this number
     */
    public User queryUser(String number);


    /**
     * verify sign in info
     * @param number user number
     * @param password user password
     * @return whwether the user is in DB
     */
    public boolean verifyUser(String number, String password);

    /**
     * @param number: number of the target user
     * @return true for delete success, false for delete fail
     */
    public boolean deleteUser(String number);


    /**
     * @param number the number of the user
     * @param password user with newest information
     * @param type User type
     * @return true for update success, false for update fail
     */
    public boolean updateUserInfo(String number,String password, String type);
}
