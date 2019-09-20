package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

public class Listener extends Person {

    public Listener(final String firstName, final String lastName, final int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
    }
    public void joinCourse(final int cursId) {
        setCursId(cursId);
    }
    public void leftCourse(final int cursId) {
        getCursId().removeIf(id -> id.equals(cursId));
    }
}
