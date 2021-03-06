package dao;

import entity.User;

import java.util.List;

/**
 * Created by alex on 10/12/2016.
 */
public interface IUserDao {
    /**
     * @param user: the only user that will be add to DB
     * @return true for add success, false for add fail
     */
    public boolean addUser(User user);

    /**
     * @param users: a batch of users that will be add to DB
     * @return true for add success, false for add fail
     */
    public boolean addUsers(List<User> users);

    /**
     * @param number: number of the target user
     * @return user with this number
     */
    public User queryUser(String number);

    /**
     * @param number: number of the target user
     * @return true for delete success, false for delete fail
     */
    public boolean deleteUser(String number);


    /**
     * @param number the number of the user
     * @param user user with newest information
     * @return true for update success, false for update fail
     */
    public boolean updateUserInfo(String number, User user);
}
