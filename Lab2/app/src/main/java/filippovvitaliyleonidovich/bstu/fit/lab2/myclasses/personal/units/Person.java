package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import java.util.List;

public abstract class Person {

    private String firstName;
    private String lastName;
    private int age;
    private List<Integer> cursId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getCursId() {
        return cursId;
    }

    public void setCursId(int cursId) { this.cursId.add(cursId); }
}
