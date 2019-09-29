package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;


import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student extends Person implements ISerializable {

    private int mark;

    Organization organization;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Student(final String name, final String surname, final int age, final int year,
                   final int mark, final String org) {
        super(name, surname, age, year,org);
        this.mark = mark;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Student(final String name, final String surname, final int age, final int mark,
                   final String org) {
        super(name, surname, age,org);
        this.mark = mark;
    }

    @Override
    public String toString() {
        try {
            return "Student{" +
                    "mark=" + mark +
                    ", name='" + getName() + '\'' +
                    ", surname='" + getSurname() + '\'' +
                    ", age=" + getAge() +
                    '}';
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public boolean saveInfo() {
        return  true;
    }

    @Override
    public Person loadInfo() {
        return null;
    }
}
