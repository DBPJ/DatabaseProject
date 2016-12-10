package entity;

/**
 * Created by alex on 10/12/2016.
 */
public class Staff extends User {
    /**
     * Staff's gender
     */
    private Enum gender;

    /**
     * place where staff work in
     */
    private String location;

    /**
     * work age of staff
     */
    private int workAge;

    /**
     * salary
     * todo: 这个薪水是加了additionrate之后的还是之前的
     */
    private double salary;

    /**
     * addition rate
     */
    private double additionRate;

    /**
     * department the staff work for
     */
    private String departmentName;

    public Enum getGender() {
        return gender;
    }

    public void setGender(Enum gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getWorkAge() {
        return workAge;
    }

    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAdditionRate() {
        return additionRate;
    }

    public void setAdditionRate(double additionRate) {
        this.additionRate = additionRate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
