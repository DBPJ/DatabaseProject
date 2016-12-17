package manager.impl;

/**
 * Created by alex on 10/12/2016.
 */

import dao.impl.DirectorDaoImpl;
import dao.impl.StaffDaoImpl;
import dao.impl.TeacherDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Director;
import entity.Staff;
import entity.Teacher;
import entity.User;
import manager.IUserManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import view.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 只有系统管理员才有权限执行这条动作
 * 增加用户登录信息到数据库
 */
public class UserManagerImpl implements IUserManager {
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
    public boolean addUsers(List<String[]> usersInfo) {
        List<User> userList = new ArrayList<>();
        for (String[] info : usersInfo) {
            if (info.length != 3) {
                return false;
            }
            User user = new User();
            user.setNumber(info[0]);
            user.setPassword(info[1]);
            user.setType(info[2]);
            userList.add(user);
        }
        return userDao.addUsers(userList);
    }

    @Override
    public boolean addUsers(String filename) {
        List<User> users = new ArrayList<>();
        InputStream inp = null;
        try {
            inp = new FileInputStream(filename);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                User user = new User();
                user.setNumber(row.getCell(0).getStringCellValue());
                user.setPassword(row.getCell(1).getStringCellValue());
                user.setType(row.getCell(2).getStringCellValue());
                users.add(user);
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return userDao.addUsers(users);
    }

    @Override
    public User queryUser(String number) {
        return userDao.queryUser(number);
    }

    @Override
    public boolean verifyUser(String number, String password) {
        User user = userDao.queryUser(number);
        if(password.equals(user.getPassword())){
            String type = user.getType();
            if(type.equals("主管")){
                DirectorDaoImpl directorDaoImpl = new DirectorDaoImpl();
                Director director = directorDaoImpl.queryDirector(number);
                if(director != null) {
                    new DirectorUI(director).setVisible(true);
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(type.equals("系统管理员")){
                new AdministratorUI().setVisible(true);
                return true;
            }
            else if(type.equals("教师")){
                TeacherDaoImpl teacherDao = new TeacherDaoImpl();
                Teacher teacher = teacherDao.queryTeacher(number);
                if(teacher != null) {
                    new TeacherUI(teacher).setVisible(true);
                    return true;
                }
                else{
                    return false;
                }
            }
            else if(type.equals("员工")){
                StaffDaoImpl staffDao = new StaffDaoImpl();
                Staff staff = staffDao.queryStaff(number);
                new StaffUI(staff).setVisible(true);
                return true;
            }
            else if(type.equals("CEO")){
                new CEOUI().setVisible(true);
                return true;

            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public boolean deleteUser(String number) {
        return userDao.deleteUser(number);
    }

    @Override
    public boolean updateUserInfo(String number, String password, String type) {
        User user  = new User();
        user.setNumber(number);
        user.setPassword(password);
        user.setType(type);
        return userDao.updateUserInfo(number, user);
    }
}
