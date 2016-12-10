package entity;

/**
 * Created by alex on 10/12/2016.
 */
public class TrainPlan extends Entity {
    /**
     * the id of the course
     */
    private String courseID;

    /**
     * the department name
     */
    private String departmentName;

    /**
     * The type of the course for the department
     * CourseType.REQUIRED: all staff of the department have to learn the course
     * CourseType.ELECTIVE: staff of the department can choose whether to study this course
     */
    private Enum type;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Enum getType() {
        return type;
    }

    public void setType(Enum type) {
        this.type = type;
    }
}
