package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;


import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Student extends Person{

    private Double mark;

    public Student(String name, Integer age, Organization org, Double mark) {
        super(name, age, org);
        this.mark = mark;
        Log.d("Student", "Create object Student");
    }

    public Double getMark() {
        return mark;
    }
    public void setMark(Double mark) {
        this.mark = mark;
        Log.d("Student", "Set new value of mark");
    }

    public int compare(Student s1, Student s2) {
        return (int)(s1.getMark() - s2.getMark());
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
}
