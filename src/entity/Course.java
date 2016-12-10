package entity;


import java.sql.Date;

/**
 * Created by alex on 10/12/2016.
 */
public class Course extends Entity {
    /**
     * Course ID
     */
    private String id;

    /**
     * Course name
     */
    private String name;

    /**
     * The date of uploading grade
     * todo: convert to java.sql.Date before insert it to DB
     */
    private Date gradeUploadTime;

    /**
     * Class hour
     */
    private int classHour;

    /**
     * The number of the teacher who teaches this class
     */
    private String teacherNumber;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGradeUploadTime() {
        return gradeUploadTime;
    }

    public void setGradeUploadTime(Date gradeUploadTime) {
        this.gradeUploadTime = gradeUploadTime;
    }

    public int getClassHour() {
        return classHour;
    }

    public void setClassHour(int classHour) {
        this.classHour = classHour;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
}
