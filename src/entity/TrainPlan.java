package entity;

/**
 * Created by alex on 10/12/2016.
 */
public class TrainPlan extends Entity {
    /**
     * the id of the course
     */
    private String courseID;

    private String courseName;

    private int classHour;

    /**
     * the department name
     */
    private String departmentName;

    /**
     * The type of the course for the department
     * CourseType.REQUIRED: all staff of the department have to learn the course
     * CourseType.ELECTIVE: staff of the department can choose whether to study this course
     */
    private String type;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }
}
