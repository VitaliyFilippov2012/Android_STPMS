package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff.Staff;

public class Manager extends Person implements IAction{

    private ArrayList<Staff> staffs;

    public Manager(String name,String surname,String addr, Integer yearOfBirthday){
        super("Manager",name,surname,addr,yearOfBirthday);
        staffs = new ArrayList<Staff>();
        Log.d("Manager", "Create object Manager");
    }

    public List<Staff> getStuffs() {
        return staffs;
    }

    @Override
    public void addStuff(String name, Integer maxStudents, Integer maxListeners,
                         String students, String listeners){

    }


    @Override
    public void addStuff(String name, Integer maxStudents, Integer maxListeners){

    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }

    // внутренний класс
    class InternalClass{

    }
}
