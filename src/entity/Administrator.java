package entity;

/**
 * Created by alex on 10/12/2016.
 */
public class Administrator extends User{
    /**
     * gender of the administrator
     */
    private Enum gender;

    public Enum getGender() {
        return gender;
    }

    public void setGender(Enum gender) {
        this.gender = gender;
    }
}
