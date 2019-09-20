package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.educationmanager;

public class Manager {
    private String firstName;
    private String lastName;
    private String birthday;
    private String address;

    public Manager() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddrres(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return birthday;
    }

    public void setAge(String birthday) {
        this.birthday = birthday;
    }
}
