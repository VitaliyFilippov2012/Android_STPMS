package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import java.util.List;

public class Student extends Person{
    private List<Integer> marks;
    public Student(final String firstName, final String lastName, final int age) {
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

    public List<Integer> getMarks() {
        return marks;
    }

    public void setMark(final int mark) {
        this.marks.add(mark);
    }
}
