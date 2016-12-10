package entity;

/**
 * Created by alex on 10/12/2016.
 */
public class Director extends User {
    /**
     * The department where the director work for
     */
    private String departmentName;

    /**
     * The place where the director work in
     */
    private String workspace;

    /**
     * director's phone number
     */
    private int phoneNumber;

    /**
     * director's email address
     */
    private String email;

    /**
     * gender
     */
    private Enum gender;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Enum getGender() {
        return gender;
    }

    public void setGender(Enum gender) {
        this.gender = gender;
    }
}
