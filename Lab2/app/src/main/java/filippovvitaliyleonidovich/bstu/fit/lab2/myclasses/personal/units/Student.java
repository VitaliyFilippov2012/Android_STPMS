package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;


import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;

public class Student extends Person implements ISerializable {
    private int mark;
    Organization organization;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Student( String name, String surname, int age, int year, int mark,String org) {
        super(name, surname, age, year,org);
        this.mark = mark;
    }

    public Student() {
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Student(String name, String surname, int age, int mark,String org) {
        super(name, surname, age,org);
        this.mark = mark;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mark=" + mark +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
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
