package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;


import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Student extends Person{

    private Double mark;

    public Student(String name,String surname, Integer yearOfbirthday,String addr, Organization org, Double mark,String nameStaff) {
        super("Student",name,surname,yearOfbirthday,addr,org,nameStaff);
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

    @Override
    public String toString() {
        try {
            return "Student{" +
                    "mark=" + getMark() +
                    ", name='" + getName() + '\'' +
                    ", surname='" + getSurname() + '\'' +
                    ", year of birthday=" + getYearOfBirthday() +
                    '}';
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int compareTo(Person p2) {
        Student p22 = (Student)p2;
        return (int)(this.getMark()- p22.getMark());
    }
}
