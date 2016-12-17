package manager.impl;

import dao.impl.CourseDaoImpl;
import entity.Course;
import entity.Staff;
import entity.Teacher;
import manager.ICourseManager;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 14/12/2016.
 */
public class CourseManagerImpl implements ICourseManager {
    CourseDaoImpl courseDao = new CourseDaoImpl();
    @Override
    public boolean addCourse(String ID, String name, int classHour, String teacherNumber) {
        Course course = new Course();
        course.setId(ID);
        course.setName(name);
        course.setClassHour(classHour);
        course.setTeacherNumber(teacherNumber);
        return courseDao.addCourse(course);
    }

    @Override
    public boolean addCourses(Teacher teacher, String filename) {
        //todo 可以过测试，但是在UI界面中无法操作，路径有问题，打成jar估计可以解决
        List<Course> courses = new ArrayList<>();
        InputStream inp = null;
        try {
            inp = new FileInputStream(filename);
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Course course = new Course();
                String teacherNumber = row.getCell(1).getStringCellValue();
                if (teacher.getNumber().equals(teacherNumber)){
                    course.setId(row.getCell(0).getStringCellValue());
                    course.setTeacherNumber(teacherNumber);
                    course.setName(row.getCell(2).getStringCellValue());
                    course.setClassHour((int) row.getCell(3).getNumericCellValue());
                    courses.add(course);
                }else{
                    //todo:提示没有权限
                    System.out.println("don't have authority to add this course");
                }
            }
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return courseDao.addCourses(courses);
    }

    @Override
    public boolean uploadGrade(String ID, String staffNumber, String grade) {
        return courseDao.addCourseGrade(ID, staffNumber, grade);
    }

    @Override
    public boolean updateGrade(String teachernumber, String ID, String staffNumber, String grade) {
        Teacher teacher = new Teacher();
        teacher.setNumber(teachernumber);
        return courseDao.updateCourseGrade(teacher,ID,staffNumber,grade);
    }

    @Override
    public List<Staff> queryResits(Teacher teacher, String courseID) {
        return courseDao.queryResits(teacher,courseID);
    }

    @Override
    public boolean acceptResit(String courseID, String staffNumber) {
        return false;
    }

    @Override
    public boolean deleteCourse(String courseID) {
        //todo: 在courseDao中没有做权限校验
        return courseDao.deleteCourse(courseID);
    }

    @Override
    public List<Course> queryCourses() {
        return courseDao.queryCourses();
    }


}
