package entity;

/**
 * Created by alex on 10/12/2016.
 */
public class User extends Entity {
    /**
     * User number
     */
    private String number;

    /**
     * User password
     */
    private String password;

    /**
     * User type, e.g. Administrator
     */
    private String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
